package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private String author;
    private LocalDate yearOfPublication;
}
