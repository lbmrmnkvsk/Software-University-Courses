package org.example.l12_sd_intro_exercise.config;

import org.example.l12_sd_intro_exercise.models.*;
import org.example.l12_sd_intro_exercise.services.AuthorService;
import org.example.l12_sd_intro_exercise.services.BookService;
import org.example.l12_sd_intro_exercise.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public DatabaseSeeder(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of("src/main/resources/files/authors.txt"))
                .forEach(row -> {
                    String[] tokens = row.split("\\s+");
                    String firstName = tokens[0];
                    String lastName = tokens[1];
                    Author author = new Author();
                    author.setFirstName(firstName);
                    author.setLastName(lastName);
                    authorService.save(author);
                });
    }

    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of("src/main/resources/files/categories.txt"))
                .forEach(row -> {
                    String name = row.trim();
                    Category category = new Category();
                    category.setName(name);
                    categoryService.save(category);
                });
    }

    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of("src/main/resources/files/books.txt"))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    Author author = authorService.getRandomAuthor();
                    EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
                    LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(data[2]);
                    BigDecimal price = new BigDecimal(data[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
                    String title = Arrays.stream(data)
                            .skip(5)
                            .collect(Collectors.joining(" "));
                    Set<Category> categories = categoryService.getRandomCategories();

                    Book book = new Book();
                    book.setAuthor(author);
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setTitle(title);
                    book.setCategories(categories);

                    bookService.save(book);
                });
    }

    @Override
    public void run(String... args) throws Exception {
        seedAuthors();
        seedCategories();
        seedBooks();
        runQueries();
    }

    private void runQueries() {
        bookService.findAllBooksAfterYear(LocalDate.of(2000, 12, 31))
                .forEach(b -> System.out.println(b.getTitle()));
        authorService.findAuthorsWithBooksBeforeYear(LocalDate.of(1990, 1, 1))
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
        authorService.findAuthorsOrderedByBookCount()
                .forEach(array -> {
                    Author author = (Author) array[0];
                    System.out.println(author.getFirstName() + " " + author.getLastName() + " - " + array[1]);
                });
        bookService.findAllBooksByAuthorOrdered("George", "Powell")
                .forEach(book -> System.out.println(book.getTitle() + " - " + book.getReleaseDate() + " - " + book.getCopies()));
    }
}
