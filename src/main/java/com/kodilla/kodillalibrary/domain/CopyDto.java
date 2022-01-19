package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CopyDto {

    private Long id;
    private Book book;
    private boolean inCirculation;
    private boolean destroyed;
    private boolean lost;
    private boolean inLibrary;
}
