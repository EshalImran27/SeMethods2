/* package com.napier.sem2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopulationQueriesTests {
    private Connection MockCon;
    private Statement MockStatement;
    private ResultSet MockResultSet;
    private PopulationQueries MockpopulationQueries;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeEach
    void setUp() throws SQLException {
        MockCon = mock(Connection.class);
        MockStatement = mock(Statement.class);
        MockResultSet = mock(ResultSet.class);
        when(MockCon.createStatement()).thenReturn(MockStatement);
        when(MockStatement.executeQuery(anyString())).thenReturn(MockResultSet);
        System.setOut(new PrintStream(outContent));
        MockpopulationQueries = new PopulationQueries(MockCon);
    }

    @AfterEach
    void tearDown() throws SQLException {
        System.setOut(System.out);}

    private void setupEmptyResultSet() throws SQLException {
        when(MockResultSet.next()).thenReturn(false);
    }

    @Test
    public void testGetContinentPopulationWithNull() throws Exception {
        setupEmptyResultSet();
        MockpopulationQueries.getContinentPopulation(null);
        String result = outContent.toString();
        assertTrue(result.contains("Failed to get continent population"), "Error message not found");
    }
}
*/