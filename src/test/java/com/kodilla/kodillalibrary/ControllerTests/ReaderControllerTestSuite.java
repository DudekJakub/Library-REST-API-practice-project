package com.kodilla.kodillalibrary.ControllerTests;

import com.kodilla.kodillalibrary.controller.ReaderController;
import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.service.DbServiceReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ReaderControllerTestSuite {

    @Autowired
    ReaderController readerController;

    @Autowired
    DbServiceReader dbServiceReader;

    @AfterEach
        //UWAGA!!! USUNĄĆ PO ETAPIE WSTĘPNEGO TESTOWANIA BAZY DANYCH!!!
    void clearEntity() {
        try {
            while (dbServiceReader.getAllReaders().iterator().hasNext()) {
                Reader readerResult = dbServiceReader.getAllReaders().iterator().next();
                dbServiceReader.deleteReaderById(readerResult.getId());
            }
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testGetAllReaders() {
        //Given
            //there is already 1 reader in DB

        //When
        List<Reader> allReaders = dbServiceReader.getAllReaders();

        //Then
        Assertions.assertEquals(1, allReaders.size());
    }

    @Test
    public void testGetReader() {
        //Given
        Reader reader = new Reader("Jakub", "Dudek", LocalDate.of(2022,1,18));

        //When
        dbServiceReader.saveReader(reader);
        Long id = reader.getId();

        Optional<Reader> optionalReader = dbServiceReader.getReaderById(id);
        Reader resultReader = optionalReader.get();

        //Then
        Assertions.assertEquals(reader.getFirstname(), resultReader.getFirstname());
    }

    @Test
    public void testGetReaderByFirstnameAndLastname() {
        //Given
        Reader reader = new Reader("Jakub", "Dudek", LocalDate.of(2022,1,18));

        //When
        dbServiceReader.saveReader(reader);

        Optional<Reader> optionalReader = dbServiceReader.getReaderByFirstnameAndLastname("Jakub", "Dudek");
        Reader resultReader = optionalReader.get();

        //Then
        Assertions.assertEquals(resultReader.getFirstname(), reader.getFirstname());
        Assertions.assertEquals(resultReader.getLastname(), reader.getLastname());
    }

}
