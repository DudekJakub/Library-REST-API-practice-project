package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Borrow;
import com.kodilla.kodillalibrary.domain.BorrowDto;
import com.kodilla.kodillalibrary.exception.ObjectNotFoundException;
import com.kodilla.kodillalibrary.mapper.BorrowMapper;
import com.kodilla.kodillalibrary.service.DbServiceBorrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/borrow")
public class BorrowController {

    @Autowired
    DbServiceBorrow dbServiceBorrow;

    @Autowired
    BorrowMapper borrowMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getBorrows")
    public List<BorrowDto> getBorrows() {
        return borrowMapper.mapToBorrowDtoList(dbServiceBorrow.getAllBorrows());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBorrow")
    public BorrowDto getBorrow(@RequestParam Long borrowId) throws ObjectNotFoundException {
        return borrowMapper.mapToBorrowDto(dbServiceBorrow.getBorrowById(borrowId).orElseThrow(ObjectNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBorrowsByReaderId")
    public List<BorrowDto> getBorrowByReader(@RequestParam Long readerId) {
        return borrowMapper.mapToBorrowDtoList(dbServiceBorrow.getBorrowsForReader(readerId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createBorrow")
    public void createBorrow(@RequestBody BorrowDto borrowDto) {
        dbServiceBorrow.saveBorrow(borrowMapper.mapToBorrow(borrowDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateBorrow")
    public BorrowDto updateBorrow(@RequestBody BorrowDto borrowDto) {
        Borrow borrow = borrowMapper.mapToBorrow(borrowDto);
        Borrow savedBorrow = dbServiceBorrow.saveBorrow(borrow);

        return borrowMapper.mapToBorrowDto(savedBorrow);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "setBorrowReturnedById")
    public BorrowDto setBorrowReturnedById(@RequestParam Long borrowId, @RequestParam boolean destroyed, @RequestParam boolean lost) {
        Borrow borrow = dbServiceBorrow.getBorrowById(borrowId).get();
        borrow.setReturnDate(LocalDate.now());
        borrow.getCopy().setDestroyed(destroyed);
        borrow.getCopy().setLost(lost);

        if(lost) {
            borrow.setReturned(false);
            borrow.getCopy().setInLibrary(false);
            borrow.getCopy().setInCirculation(false);
        } else {
            borrow.setReturned(true);
            borrow.getCopy().setInLibrary(true);
            borrow.getCopy().setInCirculation(false);
        }

        Borrow savedBorrow = dbServiceBorrow.saveBorrow(borrow);
        return borrowMapper.mapToBorrowDto(savedBorrow);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBorrow")
    public void deleteBorrow(@RequestParam Long borrowId) {
        dbServiceBorrow.deleteBorrowById(borrowId);
    }
}
