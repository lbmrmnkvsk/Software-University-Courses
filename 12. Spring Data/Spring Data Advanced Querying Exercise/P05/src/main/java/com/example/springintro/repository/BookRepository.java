package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);
    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);
    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);
    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal low, BigDecimal high);
    @Query("select b from Book b where YEAR(b.releaseDate) != :year")
    List<Book> findByReleaseDateYearNot(@Param("year") int year);
    @Query("select b from Book b where b.releaseDate < :date")
    List<Book> findByReleaseDateBefore(@Param("date") LocalDate date);
    @Query("select b from Book b where lower(b.title) like lower(concat('%', :searchString, '%') ) ")
    List<Book> findByTitleContainingIgnoreCase(@Param("searchString") String searchString);
    @Query("select b from Book b where lower(b.author.lastName) like lower(concat(:prefix, '%') ) ")
    List<Book> findByAuthorLastNameStartingWithIgnoreCase(@Param("prefix") String prefix);
    @Query("select count(b) from Book b where length(b.title) > :length")
    int countBooksWithTitleLongerThan(@Param("length") int length);
    @Query("select b.title, b.editionType, b.ageRestriction, b.price from Book b " +
            "where b.title = :title")
    List<Object[]> findByTitle(@Param("title") String title);
    @Modifying
    @Transactional
    @Query("update Book b set b.copies = b.copies + :copies where b.releaseDate > :date")
    int updateBookCopies(@Param("date") LocalDate date, @Param("copies") int copies);
    @Modifying
    @Transactional
    @Query("delete from Book b where b.copies < :copies")
    int deleteBooksWithCopiesLessThan(@Param("copies") int copies);
}
