package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookImportDto;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.bookRepository = bookRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/books.json"));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(this.gson.fromJson(readBooksFromFile(), BookImportDto[].class)).filter(dto -> {
            boolean isValid = this.validationUtil.isValid(dto);
            if (this.bookRepository.findByTitle(dto.getTitle()).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format("Successfully imported book %s - %s",
                        dto.getAuthor(), dto.getTitle()));
            } else {
                sb.append("Invalid book");
            }
            sb.append(System.lineSeparator());

            return isValid;
        }).map(dto -> this.modelMapper.map(dto, Book.class))
                .forEach(this.bookRepository::save);

        return sb.toString().trim();
    }
}
