package org.example.l12_sd_intro_exercise.services;

import org.example.l12_sd_intro_exercise.models.Book;
import org.example.l12_sd_intro_exercise.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public Book save(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> findAllBooksAfterYear(LocalDate year) {
        return this.bookRepository.findAllBooksAfterYear(year);
    }

    @Override
    public List<Book> findAllBooksByAuthorOrdered(String firstName, String lastName) {
        return this.bookRepository.findAllBooksByAuthorOrdered(firstName, lastName);
    }
}
