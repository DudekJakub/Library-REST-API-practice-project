package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Copy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CopyRepository extends CrudRepository<Copy, Long> {

    @Override
    List<Copy> findAll();

    @Override
    Copy save(Copy copy);

    @Override
    Optional<Copy> findById(Long id);

    @Override
    void deleteById(Long copyId);

    @Override
    long count();

    @Query
    int retrievedCopyQuantityByTitle(@Param("book") Book book);
}
