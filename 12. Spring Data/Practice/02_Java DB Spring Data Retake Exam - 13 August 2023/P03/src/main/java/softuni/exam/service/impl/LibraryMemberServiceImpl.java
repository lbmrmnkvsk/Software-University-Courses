package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import softuni.exam.models.dto.LibraryMemberImportDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {
    private final LibraryMemberRepository libraryMemberRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/library-members.json"));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(this.gson.fromJson(readLibraryMembersFileContent(), LibraryMemberImportDto[].class))
                .filter(dto -> {
                    boolean isValid = this.validationUtil.isValid(dto);
                    if (this.libraryMemberRepository.findByPhoneNumber(dto.getPhoneNumber()).isPresent()) {
                        isValid = false;
                    }

                    if (isValid) {
                        sb.append(String.format("Successfully imported library member %s - %s",
                                dto.getFirstName(), dto.getLastName()));
                    } else {
                        sb.append("Invalid library member");
                    }
                    sb.append(System.lineSeparator());

                    return isValid;
                }).map(dto -> this.modelMapper.map(dto, LibraryMember.class))
                .forEach(this.libraryMemberRepository::save);

        return sb.toString().trim();
    }
}
