package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.Borrow;
import com.kodilla.kodillalibrary.domain.Reader;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {

    @Override
    List<Borrow> findAll();

    @Override
    Borrow save(Borrow borrow);

    @Override
    Optional<Borrow> findById(Long id);

    @Override
    void deleteById(Long borrowId);

    @Override
    long count();

    @Query
    List<Borrow> retrieveBorrowsForReaderId(Long readerId);
}
