package com.kodilla.kodillalibrary.ControllerTests;

import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Copy;
import com.kodilla.kodillalibrary.repository.CopyRepository;
import com.kodilla.kodillalibrary.service.DbServiceBook;
import com.kodilla.kodillalibrary.service.DbServiceCopy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class CopyControllerTestSuite {

    @Autowired
    CopyRepository copyRepository;

    @Autowired
    DbServiceCopy dbServiceCopy;

    @Autowired
    DbServiceBook dbServiceBook;

    @AfterEach
        //UWAGA!!! USUNĄĆ PO ETAPIE WSTĘPNEGO TESTOWANIA BAZY DANYCH!!!
    void clearEntity() {
        try {
            while (dbServiceCopy.getAllCopies().iterator().hasNext()) {
                Copy copyResult = dbServiceCopy.getAllCopies().iterator().next();
                dbServiceCopy.deleteCopy(copyResult.getId());
            }
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testSaveCopy() {
        //Given
        Book book = dbServiceBook.getBookByTitle("The Shining").get();
        Copy copy = new Copy();
        copy.setBook(book);
        copy.setInCirculation(false);
        copy.setDestroyed(false);
        copy.setLost(false);
        copy.setInLibrary(true);

        //When
        dbServiceCopy.saveCopy(copy);
        Long copyId = copy.getId();

        Copy resultCopy = dbServiceCopy.getCopyById(copyId).get();

        //Then
        Assertions.assertEquals(copyId, resultCopy.getId());
    }

    @Test
    public void testRetrievedCopyQuantityByTitle() {
        //Given
        Book book = dbServiceBook.getBookByTitle("The Shining").get();
        Copy copy1 = new Copy(book);
        Copy copy2 = new Copy(book);
        Copy copy3 = new Copy(book);

        //When
        dbServiceCopy.saveCopy(copy1);
        dbServiceCopy.saveCopy(copy2);
        dbServiceCopy.saveCopy(copy3);

        Long bookId = book.getId();
        Long c1Id = copy1.getId();
        Long c2Id = copy2.getId();
        Long c3Id = copy3.getId();

        int quantity = dbServiceCopy.getAllAvailableCopiesByTitle("The Shining");

        //Then
        Assertions.assertEquals(3, quantity);

        //CleanUp
        try {
            dbServiceBook.deleteBook(bookId);
            dbServiceCopy.deleteCopy(c1Id);
            dbServiceCopy.deleteCopy(c2Id);
            dbServiceCopy.deleteCopy(c3Id);
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
