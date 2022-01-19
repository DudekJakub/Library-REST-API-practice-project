package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DbServiceReader {

    @Autowired
    ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Optional<Reader> getReaderById(Long id) {
        return readerRepository.findById(id);
    }

    public Optional<Reader> getReaderByFirstnameAndLastname(final String firstname, final String lastname) {
        return readerRepository.retrieveRequestedFirstnameAndLastname(firstname, lastname);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public void deleteReaderById(final Long readerId) {
        readerRepository.deleteById(readerId);
    }
}
