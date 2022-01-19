package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.controller.BorrowController;
import com.kodilla.kodillalibrary.domain.Borrow;
import com.kodilla.kodillalibrary.domain.Copy;
import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.exception.ObjectNotFoundException;
import com.kodilla.kodillalibrary.repository.BorrowRepository;
import com.kodilla.kodillalibrary.repository.CopyRepository;
import com.kodilla.kodillalibrary.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceBorrow {

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    DbServiceReader dbServiceReader;

    @Autowired
    CopyRepository copyRepository;

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Optional<Borrow> getBorrowById(final Long id) {
        return borrowRepository.findById(id);
    }

    public List<Borrow> getBorrowsForReader(final Long readerId) {
        return borrowRepository.retrieveBorrowsForReaderId(readerId);
    }

    public Borrow saveBorrow(final Borrow borrow) {
        Optional<Reader> readerOptional = readerRepository.findById(borrow.getReader().getId());
        Reader reader = readerOptional.get();
        borrow.setReader(reader);
        Optional<Copy> copyOptional = copyRepository.findById(borrow.getCopy().getId());
        Copy copy = copyOptional.get();
        borrow.setCopy(copy);

        if(!borrow.isReturned()) {
            copy.setInLibrary(false);
        } else {
            copy.setInCirculation(false);
            copy.setInLibrary(true);
        }

        return borrowRepository.save(borrow);
    }

    public void deleteBorrowById(final Long borrowId) {
        borrowRepository.deleteById(borrowId);
    }
}
