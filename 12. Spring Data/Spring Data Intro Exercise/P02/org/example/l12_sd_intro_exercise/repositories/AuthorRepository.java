package org.example.l12_sd_intro_exercise.repositories;

import org.example.l12_sd_intro_exercise.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("SELECT a FROM Author a JOIN a.books b WHERE b.releaseDate < :year")
    List<Author> findAuthorsWithBooksBeforeYear(@Param("year") LocalDate year);

    @Query("SELECT a, COUNT(b) as bookCount FROM Author a LEFT JOIN a.books b GROUP BY a.id ORDER BY COUNT(b) DESC")
    List<Object[]> findAuthorsOrderedByBookCount();
}
