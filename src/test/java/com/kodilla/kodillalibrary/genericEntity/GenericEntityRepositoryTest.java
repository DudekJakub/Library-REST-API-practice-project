package com.kodilla.kodillalibrary.genericEntity;

import com.kodilla.kodillalibrary.DBmenager.DbMenager;
import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Manager;
import com.kodilla.kodillalibrary.repository.BookRepository;
import com.kodilla.kodillalibrary.service.DbServiceManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenericEntityRepositoryTest {

    @Autowired
    GenericEntityRepository genericEntityRepository;

    @Autowired
    ManagerJpaRepository managerJpaRepository;

    @Autowired
    DbServiceManager dbServiceManager;

    @Test
    public void testGenericEntityRepository() {
        //Given
        GenericEntity genericEntity = new GenericEntity("managers");
        //When
        genericEntityRepository.save(genericEntity);
        //Then
            //here should appear new entity in database
    }

    @Test
    public void modifyFirstnameColumn() {
        //When
        dbServiceManager.modifyEntityColumn();
        //Then
            //firstname column should have not different parameters
    }

    @Test
    void modifyColumnWithSQLquery() throws SQLException {
        //Given
        DbMenager dbMenager = DbMenager.INSTANCE;
        String columnName = "firstname";
        String parameter1 = " VARCHAR(30)";

        //When
        String sqlQuery = "ALTER TABLE library.managers MODIFY " + columnName + parameter1 + " CHARSET utf8 NOT NULL AFTER id";
        Statement statement = dbMenager.getConnection().prepareStatement(sqlQuery);
        statement.execute(sqlQuery);
    }
}