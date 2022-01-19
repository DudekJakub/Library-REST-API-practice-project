package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.Reader;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {

    @Override
    List<Reader> findAll();

    @Override
    Reader save(Reader reader);

    @Override
    Optional<Reader> findById(Long id);

    @Override
    void deleteById(Long readerId);

    @Query
    Optional<Reader> retrieveRequestedFirstnameAndLastname(@Param("FIRSTNAME") String firstname, @Param("LASTNAME") String lastname);
}
