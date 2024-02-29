package org.example.l12_sd_intro_exercise.repositories;

import org.example.l12_sd_intro_exercise.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b where b.releaseDate > :year")
    List<Book> findAllBooksAfterYear(LocalDate year);

    @Query("select b from Book b where b.author.firstName = :firstName and b.author.lastName = :lastName " +
            "order by b.releaseDate desc, b.title")
    List<Book> findAllBooksByAuthorOrdered(String firstName, String lastName);
}
