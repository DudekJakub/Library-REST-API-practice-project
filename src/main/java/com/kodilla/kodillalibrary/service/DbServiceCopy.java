package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Copy;
import com.kodilla.kodillalibrary.exception.ObjectNotFoundException;
import com.kodilla.kodillalibrary.repository.BookRepository;
import com.kodilla.kodillalibrary.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DbServiceCopy {

    @Autowired
    CopyRepository copyRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    DbServiceBook dbServiceBook;

    public List<Copy> getAllCopies() {
        return copyRepository.findAll();
    }

    public Optional<Copy> getCopyById(final Long id) {
        return copyRepository.findById(id);
    }

    public int getAllAvailableCopiesByTitle(final String title) {
        return copyRepository.retrievedCopyQuantityByTitle(title);
    }

    public Copy saveCopy(final Copy copy) {
        Optional<Book> bookOptional = dbServiceBook.getBookByTitle(copy.getBook().getTitle());
        Book book = bookOptional.get();

        copy.setBook(book);

        return copyRepository.save(copy);
    }

    public void deleteCopy(final Long copyId) {
        copyRepository.deleteById(copyId);
    }


}
