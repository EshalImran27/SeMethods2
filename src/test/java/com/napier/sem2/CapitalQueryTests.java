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
        when(MockResultSet.getString("continent"))
                .thenReturn("Europe")
                .thenReturn("Europe");
        when(MockResultSet.getString("region"))
                .thenReturn("Southern Europe")
                .thenReturn("British Islands");
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

    //getReportCapitalContinent
    @Test
    public void testGetReportCapitalContinent() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCapitalContinent("Europa");
        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
        assertTrue(result.contains("London"), "Error message not found");
    }
    @Test
    public void testGetReportCapitalContinentWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCapitalContinent("Europe");
        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    //getReportCapitalRegion
    @Test
    public void testGetReportCapitalRegion() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCapitalRegion("Southern Europe");
        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
    }
    @Test
    public void testGetReportCapitalRegionWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCapitalRegion("Southern Europe");
        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    //getReportTopCapitalGlobal
    @Test
    public void testGetReportTopCapitalGlobalWithValidNumber() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCapitalGlobal(5);
        String result = outContent.toString();
        assertTrue(result.contains("Top 5 Capitals in the world ranked from largest population to smallest: "), "Error message not found");
    }
    @Test
    public void testGetReportTopCapitalGlobalWithZero() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCapitalGlobal(0);
        String result = outContent.toString();
        assertTrue(result.contains("No capitals can be displayed"), "Error message not found");
    }
    @Test
    public void testGetReportTopCapitalGlobalWithNegativeNumber() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCapitalGlobal(-1);
        String result = outContent.toString();
        assertTrue(result.contains("No capitals can be displayed"), "Error message not found");
    }
    @Test
    public void testGetReportTopCapitalGlobalWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportTopCapitalGlobal(5);
        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    //getReportTopCapitalContinent
    @Test
    public void testGetReportTopCapitalContinentWithValidParameters() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCapitalContinent("Europe", 5);
        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
        assertTrue(result.contains("London"), "Error message not found");
    }
    @Test
    public void testGetReportTopCapitalContinentWithNullContinent() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCapitalContinent(null,5);
        String result = outContent.toString();
        assertTrue(result.contains("No capitals can be displayed"), "Error message not found");
    }

    //getReportTopCapitalRegion
    @Test
    public void testGetReportTopCapitalRegionWithValidParameters() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCapitalRegion("Southern Europe", 5);
        String result = outContent.toString();
        assertTrue(result.contains("Top 5 Capitals in the region Southern Europe ranked from largest population to smallest: "), "Error message not found");
    }
    @Test
    public void testGetReportTopCapitalRegionWithNullRegion() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCapitalRegion(null,5);
        String result = outContent.toString();
        assertTrue(result.contains("No capitals can be displayed"), "Error message not found");
    }
}
