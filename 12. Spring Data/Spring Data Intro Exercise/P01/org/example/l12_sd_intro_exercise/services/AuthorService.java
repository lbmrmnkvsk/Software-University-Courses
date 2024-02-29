package org.example.l12_sd_intro_exercise.services;

import org.example.l12_sd_intro_exercise.models.Author;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author save(Author author);
    Optional<Author> findById(Integer id);

    @Transactional(readOnly = true)
    Author getRandomAuthor();
    List<Author> findAuthorsWithBooksBeforeYear(LocalDate year);
    List<Object[]> findAuthorsOrderedByBookCount();
}
