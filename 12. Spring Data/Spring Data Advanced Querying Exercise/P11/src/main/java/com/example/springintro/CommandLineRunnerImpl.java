package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
     //   printAllAuthorsAndNumberOfTheirBooks();
//        pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

//        bookService.findByAgeRestriction("miNor")
//                .forEach(b -> System.out.println(b.getTitle()));

//        bookService.findByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
//                .forEach(b -> System.out.println(b.getTitle()));

//        bookService.findByPriceLessThanOrPriceGreaterThan(new BigDecimal("5"), new BigDecimal("40"))
//                .forEach(b -> System.out.println(b.getTitle()));

//        bookService.findByReleaseDateYearNot(2000)
//                .forEach(b -> System.out.println(b.getTitle()));

//        bookService.findByReleaseDateBefore("12-04-1992").forEach(b -> System.out.println(b.getTitle()));

//        authorService.findByFirstNameEndingWithIgnoreCase("e")
//                .forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));

//        bookService.findByTitleContainingIgnoreCase("sK").forEach(b -> System.out.println(b.getTitle()));

//        bookService.findByAuthorLastNameStartingWithIgnoreCase("gr").forEach(b -> System.out.println(b.getTitle()));

//        System.out.println(bookService.countBooksWithTitleLongerThan(12));

//        authorService.getAllAuthorsOrderByCountOfTheirBooks().forEach(System.out::println);

//        bookService.findByTitle("Things Fall Apart").forEach(result ->
//                System.out.printf("%s %s %s %s%n",
//                        result[0], result[1], result[2], result[3]));

//        System.out.println(bookService.updateBookCopies("12 Oct 2005", 100));

//        System.out.println(bookService.deleteBooksWithCopiesLessThan(500));


    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
