package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BorrowDto {

    private Long id;
    private Copy copy;
    private Reader reader;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;
}
