package com.kodilla.kodillalibrary.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQueries(
        @NamedQuery(
                name = "Borrow.retrieveBorrowsForReaderId",
                query = "FROM BORROWS WHERE reader.id = :readerId ")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="BORROWS")
public class Borrow {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="ID", unique = true)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="ID_COPY")
    private Copy copy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="ID_READER")
    private Reader reader;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="BORROW_DATE")
    private LocalDate borrowDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="RETURN_DAte")
    private LocalDate returnDate;

    @Column(name="RETURNED")
    private boolean returned;
}
