package org.example.l12_sd_intro_exercise.services;

import org.example.l12_sd_intro_exercise.models.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
    List<Book> findAllBooksAfterYear(LocalDate year);
    List<Book> findAllBooksByAuthorOrdered(String firstName, String lastName);
}
