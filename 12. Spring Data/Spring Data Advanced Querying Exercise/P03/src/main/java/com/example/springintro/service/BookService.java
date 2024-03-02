package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);
    List<Book> findByAgeRestriction(String ageRestriction);
    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);
    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal low, BigDecimal high);
    List<Book> findByReleaseDateYearNot(int year);
    List<Book> findByReleaseDateBefore(String dateString);
    List<Book> findByTitleContainingIgnoreCase(String searchString);
    List<Book> findByAuthorLastNameStartingWithIgnoreCase(String prefix);
    int countBooksWithTitleLongerThan(int length);
    List<Object[]> findByTitle(String title);
    int updateBookCopies(String dateString, int copies);
    int deleteBooksWithCopiesLessThan(int copies);
}
