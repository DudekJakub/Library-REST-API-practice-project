package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    Optional<Book> findById(Long id);

    @Override
    Book save(Book book);

    @Override
    void deleteById(Long bookId);

    @Query
    Optional<Book> retrieveRequestedTitle(@Param("TITLE") String title);

    @Query
    List<Book> retrieveRequestedAuthor(@Param("AUTHOR") String author);
}
