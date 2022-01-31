package com.kodilla.kodillalibrary.domain;

import com.kodilla.kodillalibrary.genericEntity.GenericEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Manager.modifyFirstnameColumn",
                query = "ALTER TABLE library.managers MODIFY firstname VARCHAR(35) CHARSET utf8 NOT NULL AFTER id",
                resultClass = Manager.class
        )}
)

@Entity(name = "MANAGERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Manager {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", unique = true)
    private Long id;

    @NotNull
    @Column(name="FIRSTNAME")
    private String name;

    @NotNull
    @Column(name="LASTNAME")
    private String lastname;
}
