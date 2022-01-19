package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReaderDto {

    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfAccountCreation;
}
