package com.kodilla.kodillalibrary.ControllerTests;

import com.kodilla.kodillalibrary.controller.BookController;
import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.exception.ObjectNotFoundException;
import com.kodilla.kodillalibrary.mapper.BookMapper;
import com.kodilla.kodillalibrary.repository.BookRepository;
import com.kodilla.kodillalibrary.service.DbServiceBook;
import com.kodilla.kodillalibrary.service.DbServiceCopy;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
public class BookControllerTestSuite {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    DbServiceBook dbServiceBook;

//    @AfterEach //UWAGA!!! USUNĄĆ PO ETAPIE WSTĘPNEGO TESTOWANIA BAZY DANYCH!!!
//    void clearEntity() {
//        try {
//            while (dbServiceBook.getAllBooks().iterator().hasNext()) {
//                Book bookResult = dbServiceBook.getAllBooks().iterator().next();
//                dbServiceBook.deleteBook(bookResult.getId());
//            }
//        } catch (Exception e) {
//            //do nothing
//        }
//    }

    @Test
    public void testFindAllBooks() {
        //Given
        Book book = new Book("Test", "FindAllBooks", LocalDate.of(1994,6,17));

        //When
        dbServiceBook.saveBook(book);

        List<Book> resultList = bookRepository.findAll();

        //Then
        Assertions.assertEquals(1, resultList.size());

        //Clean Up
        //@AfterEach
    }

    @Test
    public void testGetBookByTitle() {
        //Given
        Book book = new Book("The Shining", "Stephen King", LocalDate.of(1960,5,10));

        //When
        dbServiceBook.saveBook(book);

        Optional<Book> optionalBook = dbServiceBook.getBookByTitle("The Shining");
        Book resultBook = optionalBook.get();

        //Then
        System.out.println(               "TUTAJ PATRZ !!!");
        System.out.println(book + " ! VS ! " + resultBook);

        Assertions.assertEquals(book.getTitle(), resultBook.getTitle());
        Assertions.assertEquals(book.getAuthor(), resultBook.getAuthor());
        Assertions.assertEquals(book.getYearOfPublication(), resultBook.getYearOfPublication());

        //Clean Up
        //@AfterEach
    }

    @Test
    public void testGetBooksByAuthor() {
        //Given
        String author = "Stephen King";

        //When
        List<Book> authorBooksList = dbServiceBook.getBooksByAuthor(author);

        //Then
        Assertions.assertEquals(2, authorBooksList.size());

        //CleanUp
        //@AfterEach
    }
}
