package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.exception.ObjectNotFoundException;
import com.kodilla.kodillalibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DbServiceBook {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(final Long id) throws ObjectNotFoundException {
        return bookRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(final Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Optional<Book> getBookByTitle(final String title) {
        return bookRepository.retrieveRequestedTitle(title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.retrieveRequestedAuthor(author);
    }
}
