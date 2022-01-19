package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Copy;
import com.kodilla.kodillalibrary.domain.CopyDto;
import com.kodilla.kodillalibrary.exception.ObjectNotFoundException;
import com.kodilla.kodillalibrary.mapper.CopyMapper;
import com.kodilla.kodillalibrary.service.DbServiceBook;
import com.kodilla.kodillalibrary.service.DbServiceCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NamedQuery;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/copy")
public class CopyController {

    @Autowired
    DbServiceCopy dbServiceCopy;

    @Autowired
    CopyMapper copyMapper;

    @Autowired
    DbServiceBook dbServiceBook;

    @RequestMapping(method = RequestMethod.GET, value = "getCopies")
    public List<CopyDto> getAllCopies() {
        return copyMapper.mapToCopyDtoList(dbServiceCopy.getAllCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopy")
    public CopyDto getCopy(@RequestParam Long copyId) throws ObjectNotFoundException {
        return copyMapper.mapToCopyDto(dbServiceCopy.getCopyById(copyId).orElseThrow(ObjectNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopyQuantity")
    public int getCopyQuantity(@RequestParam String title) {
        return dbServiceCopy.getAllAvailableCopiesByTitle(title);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCopy")
    public void createCopy(@RequestParam String bookTitle) {
        CopyDto copyDto = new CopyDto();
        copyDto.setBook(dbServiceBook.getBookByTitle(bookTitle).get());
        copyDto.setInCirculation(false);
        copyDto.setDestroyed(false);
        copyDto.setLost(false);
        copyDto.setInLibrary(true);

        Copy copy = copyMapper.mapToCopy(copyDto);
        dbServiceCopy.saveCopy(copy);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateCopy")
    public CopyDto updateCopy(@RequestBody CopyDto copyDto) {
        Copy copy = copyMapper.mapToCopy(copyDto);
        Copy savedCopy = dbServiceCopy.saveCopy(copy);
        return copyMapper.mapToCopyDto(savedCopy);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCopy")
    public void deleteCopy(@RequestParam Long copyId) {
        dbServiceCopy.deleteCopy(copyId);
    }
}
