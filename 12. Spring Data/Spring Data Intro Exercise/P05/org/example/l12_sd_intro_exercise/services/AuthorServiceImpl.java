package org.example.l12_sd_intro_exercise.services;

import org.example.l12_sd_intro_exercise.models.Author;
import org.example.l12_sd_intro_exercise.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final Random random;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.random = new Random();
    }
    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> findById(Integer id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getRandomAuthor() {
        List<Author> authors = authorRepository.findAll();
        return authors.get(random.nextInt(authors.size()));
    }

    @Override
    public List<Author> findAuthorsWithBooksBeforeYear(LocalDate year) {
        return authorRepository.findAuthorsWithBooksBeforeYear(year);
    }

    @Override
    public List<Object[]> findAuthorsOrderedByBookCount() {
        return this.authorRepository.findAuthorsOrderedByBookCount();
    }

}
