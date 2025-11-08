package com.napier.sem2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;//to add mock objects
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalQueryTests {
    private Connection MockCon;
    private Statement MockStatement;
    private ResultSet MockResultSet;
    private Query MockCountryQueries;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() throws SQLException {
        MockCon = mock(Connection.class);
        MockStatement = mock(Statement.class);
        MockResultSet = mock(ResultSet.class);
        when(MockCon.createStatement()).thenReturn(MockStatement);
        when(MockStatement.executeQuery(anyString())).thenReturn(MockResultSet);
        System.setOut(new PrintStream(outContent));
        MockCountryQueries = new Query(MockCon);
    }

    @AfterEach
    void tearDown() throws SQLException {
        System.setOut(System.out);
    }

    private void setUpMockResultsWithData() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getString("name"))
                .thenReturn("Madrid")
                .thenReturn("London");
        when(MockResultSet.getString("country"))
                .thenReturn("Spain")
                .thenReturn("United Kingdom");
        when(MockResultSet.getInt("population"))
                .thenReturn(2879052)
                .thenReturn(7285000);
    }

    private void setupEmptyResultSet() throws SQLException {
        when(MockResultSet.next()).thenReturn(false);
    }

    //getReportCapitalGlobal
    @Test
    public void testNullDatabaseConnection() throws SQLException {
        Query nullQueries = new Query(null);
        nullQueries.getReportCapitalGlobal();
        String result = outContent.toString();
        assertTrue(result.contains("Database Connection is null"),
                "Should display null connection error");
    }

    @Test
    public void testGetReportCapitalGlobalWithData() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCapitalGlobal();
        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
        assertTrue(result.contains("London"), "Error message not found");
    }
    @Test
    public void testGetReportCapitalGlobalNoData() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportCapitalGlobal();
        String result = outContent.toString();
        assertTrue(result.contains("No countries can be displayed"), "Error message not found");
    }
    @Test
    public void testGetReportCapitalGlobalWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCapitalGlobal();
        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }
}
