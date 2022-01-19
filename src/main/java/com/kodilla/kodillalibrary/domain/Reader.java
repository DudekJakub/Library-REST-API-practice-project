package com.kodilla.kodillalibrary.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NamedQuery(
    name = "Reader.retrieveRequestedFirstnameAndLastname",
    query = "FROM READERS WHERE firstname = :FIRSTNAME and lastname = :LASTNAME"
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name= "READERS")
public class Reader {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", unique = true)
    private Long id;

    @NotNull
    @Column(name="FIRSTNAME")
    private String firstname;

    @NotNull
    @Column(name="LASTNAME")
    private String lastname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Column(name="CREATION_DATA")
    private LocalDate dateOfAccountCreation;

    public Reader(String firstname, String lastname, LocalDate dateOfAccountCreation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfAccountCreation = dateOfAccountCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return firstname.equals(reader.firstname) && lastname.equals(reader.lastname) && dateOfAccountCreation.equals(reader.dateOfAccountCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, dateOfAccountCreation);
    }
}
