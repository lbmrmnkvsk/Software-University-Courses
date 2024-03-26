package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationImportDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ConstellationServiceImpl implements ConstellationService {
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/constellations.json"));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(readConstellationsFromFile(), ConstellationImportDto[].class))
                .filter(dto -> {
                    boolean isValid = this.validationUtil.isValid(dto);
                    if (this.constellationRepository.findByName(dto.getName()).isPresent()) {
                        isValid = false;
                    }

                    if (isValid) {
                        sb.append(String.format("Successfully imported constellation %s - %s",
                                dto.getName(), dto.getDescription()));
                    } else {
                        sb.append("Invalid constellation");
                    }
                    sb.append(System.lineSeparator());

                    return isValid;
                }).map(dto -> this.modelMapper.map(dto, Constellation.class))
                .forEach(this.constellationRepository::save);

        return sb.toString().trim();
    }

    @Override
    public Optional<Constellation> findByName(String name) {
        return this.constellationRepository.findByName(name);
    }

    @Override
    public Constellation save(Constellation constellation) {
        return this.constellationRepository.save(constellation);
    }

    @Override
    public Optional<Constellation> findById(Long id) {
        return this.constellationRepository.findById(id);
    }
}
