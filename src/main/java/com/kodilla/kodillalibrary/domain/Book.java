package com.kodilla.kodillalibrary.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Book.retrieveRequestedTitle",
                    query = "FROM BOOKS WHERE title = :TITLE"),

        @NamedQuery(name = "Book.retrieveRequestedAuthor",
                    query = "FROM BOOKS WHERE author = :AUTHOR")

})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="BOOKS")
public class Book {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", unique = true)
    private Long id;

    @NotNull
    @Column(name="TITLE")
    private String title;

    @NotNull
    @Column(name="AUTHOR")
    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Column(name="PUBLIC_DATA")
    private LocalDate yearOfPublication;

    public Book(String title, String author, LocalDate yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && author.equals(book.author) && yearOfPublication.equals(book.yearOfPublication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, yearOfPublication);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }
}
