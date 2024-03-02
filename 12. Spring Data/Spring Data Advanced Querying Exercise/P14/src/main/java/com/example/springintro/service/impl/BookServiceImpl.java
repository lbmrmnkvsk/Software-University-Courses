package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

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

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAgeRestriction(String ageRestriction) {
        AgeRestriction ageRestriction1 = AgeRestriction.valueOf(ageRestriction.toUpperCase());
        return this.bookRepository.findByAgeRestriction(ageRestriction1);
    }

    @Override
    public List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies) {
        return this.bookRepository.findByEditionTypeAndCopiesLessThan(editionType, copies);
    }

    @Override
    public List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal low, BigDecimal high) {
        return this.bookRepository.findByPriceLessThanOrPriceGreaterThan(low, high);
    }

    @Override
    public List<Book> findByReleaseDateYearNot(int year) {
        return this.bookRepository.findByReleaseDateYearNot(year);
    }

    @Override
    public List<Book> findByReleaseDateBefore(String dateString) {
        int[] tokens = Arrays.stream(dateString.split("-"))
                .mapToInt(Integer::parseInt).toArray();
        LocalDate date = LocalDate.of(tokens[2], tokens[1], tokens[0]);
        return this.bookRepository.findByReleaseDateBefore(date);
    }

    @Override
    public List<Book> findByTitleContainingIgnoreCase(String searchString) {
        return this.bookRepository.findByTitleContainingIgnoreCase(searchString);
    }

    @Override
    public List<Book> findByAuthorLastNameStartingWithIgnoreCase(String prefix) {
        return this.bookRepository.findByAuthorLastNameStartingWithIgnoreCase(prefix);
    }

    @Override
    public int deleteBooksWithCopiesLessThan(int copies) {
        return this.bookRepository.deleteBooksWithCopiesLessThan(copies);
    }

    @Override
    public int updateBookCopies(String dateString, int copies) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        int books = this.bookRepository.updateBookCopies(date, copies);
        return books * copies;
    }

    @Override
    public List<Object[]> findByTitle(String title) {
        return this.bookRepository.findByTitle(title);
    }

    @Override
    public int countBooksWithTitleLongerThan(int length) {
        return this.bookRepository.countBooksWithTitleLongerThan(length);
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }

}
