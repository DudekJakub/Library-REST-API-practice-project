package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.domain.ReaderDto;
import com.kodilla.kodillalibrary.exception.ObjectNotFoundException;
import com.kodilla.kodillalibrary.mapper.ReaderMapper;
import com.kodilla.kodillalibrary.service.DbServiceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/reader")
public class ReaderController {

    @Autowired
    private DbServiceReader dbServiceReader;

    @Autowired
    private ReaderMapper readerMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getReaders")
    public List<ReaderDto> getReaders() {
        return readerMapper.mapToReaderDtoList(dbServiceReader.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReader")
    public ReaderDto getReader(@RequestParam Long readerId) throws ObjectNotFoundException {
        return readerMapper.mapToReaderDto(dbServiceReader.getReaderById(readerId).orElseThrow(ObjectNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReaderByFirstnameAndLastname")
    public ReaderDto getReaderByFirstnameAndLastname(@RequestParam String firstname, @RequestParam String lastname) throws ObjectNotFoundException {
        return readerMapper.mapToReaderDto(dbServiceReader.getReaderByFirstnameAndLastname(firstname, lastname).orElseThrow(ObjectNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createReader", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        dbServiceReader.saveReader(reader);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateReader")
    public ReaderDto updateReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader savedReader = dbServiceReader.saveReader(reader);
        return readerMapper.mapToReaderDto(savedReader);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteReader")
    public void deleteReader(@RequestParam Long readerId) {
        dbServiceReader.deleteReaderById(readerId);
    }
}
