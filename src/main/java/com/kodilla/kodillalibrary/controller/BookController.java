package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.BookDto;
import com.kodilla.kodillalibrary.exception.ObjectNotFoundException;
import com.kodilla.kodillalibrary.mapper.BookMapper;
import com.kodilla.kodillalibrary.service.DbServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController {

    @Autowired
    private DbServiceBook dbServiceBook;

    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(dbServiceBook.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public ResponseEntity<BookDto> getBook(@RequestParam Long bookId) throws ObjectNotFoundException {
            return new ResponseEntity<>(bookMapper.mapToBookDto(dbServiceBook.getBook(bookId)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBookByTitle")
    public BookDto getBookByTitle(@RequestParam String title) throws ObjectNotFoundException {
        return bookMapper.mapToBookDto(dbServiceBook.getBookByTitle(title).orElseThrow(ObjectNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBooksByAuthor")
    public List<BookDto> getBooksByAuthor(@RequestParam String author) {
        return bookMapper.mapToBookDtoList(dbServiceBook.getBooksByAuthor(author));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createBook", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        dbServiceBook.saveBook(book);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateBook")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = dbServiceBook.saveBook(book);
        return bookMapper.mapToBookDto(savedBook);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId) {
        dbServiceBook.deleteBook(bookId);
    }
}
