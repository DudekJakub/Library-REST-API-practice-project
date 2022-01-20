package com.kodilla.kodillalibrary.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@NamedQuery(
        name = "Copy.retrievedCopyQuantityByTitle",
        query = "SELECT COUNT(book) FROM COPIES WHERE book.title = :bookTitle"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="COPIES")
public class Copy {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ID_TITLE")
    private Book book;

    @NotNull
    @Column(name="IN_CIRCULATION")
    private boolean inCirculation;

    @NotNull
    @Column(name="DESTROYED")
    private boolean destroyed;

    @NotNull
    @Column(name="LOST")
    private boolean lost;

    @NotNull
    @Column(name="IN_LIBRARY")
    private boolean inLibrary;

    public Copy(Book book) {
        this.book = book;
        this.inCirculation = false;
        this.destroyed = false;
        this.lost = false;
        this.inLibrary = true;
    }
}
