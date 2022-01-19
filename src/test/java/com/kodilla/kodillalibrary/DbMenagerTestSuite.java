package com.kodilla.kodillalibrary;

import com.kodilla.kodillalibrary.DBmenager.DbMenager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;


public class DbMenagerTestSuite {

    @AfterEach
    public static void testFinished() {
        System.out.println("Test finished");
    }

    @Test
    public void testConnectionToDataBase() throws SQLException {
        //Given
        //When
        DbMenager dbMenager = DbMenager.INSTANCE;
        //Then
        assertNotNull(dbMenager.getConnection());
    }
}
