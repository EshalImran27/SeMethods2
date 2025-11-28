 package com.napier.sem2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test suite for the {@link PopulationQueries} class.
 * <p>
 * These tests use Mockito to simulate database behavior, ensuring that
 * the query methods respond correctly to expected, empty, and exceptional
 * result sets — without requiring a real database connection.
 * </p>
 *
 * <p><b>Test Strategy:</b></p>
 * <ul>
 *     <li>Mock {@link Connection}, {@link Statement}, {@link PreparedStatement}, and {@link ResultSet} objects.</li>
 *     <li>Capture console output via {@link ByteArrayOutputStream} for validation.</li>
 *     <li>Verify proper handling of normal data, null connections, and SQL exceptions.</li>
 * </ul>
 */
/**
 * Unit test suite for the {@link PopulationQueries} class.
 * <p>
 * These tests use Mockito to simulate database behavior, ensuring that
 * the query methods respond correctly to expected, empty, and exceptional
 * result sets — without requiring a real database connection.
 * </p>
 *
 * <p><b>Test Strategy:</b></p>
 * <ul>
 *     <li>Mock {@link Connection}, {@link Statement}, and {@link ResultSet} objects.</li>
 *     <li>Capture console output via {@link ByteArrayOutputStream} for validation.</li>
 *     <li>Verify proper handling of normal data, null connections, and SQL exceptions.</li>
 * </ul>
 */
public class PopulationQueriesTests {
    /** Mocked database connection. */
    private Connection mockCon;

    /** Mocked SQL statement. */
    private Statement mockStatement;

    /** Mocked SQL prepared statement. */
    private PreparedStatement mockPreparedStatement;

    /** Mocked SQL result set for simulating query results. */
    private ResultSet mockResultSet;

    /** Instance of PopulationQueries using the mocked connection. */
    private PopulationQueries populationQueries;

    /** Captures System.out output for verification. */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * Sets up fresh mock objects before each test and redirects
     * {@code System.out} to a local stream for output capture.
     */
    @BeforeEach
    void setUp() throws SQLException {
        mockCon = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockCon.createStatement()).thenReturn(mockStatement);
        when(mockCon.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        System.setOut(new PrintStream(outContent));
        populationQueries = new PopulationQueries(mockCon);
    }

    /**
     * Restores the default system output after each test.
     */
    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    /**
     * Configures the mock {@link ResultSet} to simulate
     * a valid population query result.
     */
    private void setUpMockResultsWithPopulation(long population) throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("TotalPopulation")).thenReturn(population);
    }

    /**
     * Configures the mock {@link ResultSet} to simulate
     * an empty query result.
     */
    private void setupEmptyResultSet() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);
    }

    // ========== BASIC POPULATION QUERIES ==========

    /**
     * Tests successful retrieval of world population.
     */
    @Test
    public void testGetWorldPopulationWithData() throws SQLException {
        setUpMockResultsWithPopulation(6078749450L);
        long result = populationQueries.getWorldPopulation();

        assertEquals(6078749450L, result, "World population should match");
        String output = outContent.toString();
        assertTrue(output.contains("WORLD POPULATION"), "Should display world population header");
        assertTrue(output.contains("6,078,749,450"), "Should display formatted population");
    }

    /**
     * Tests world population query with empty result.
     */
    @Test
    public void testGetWorldPopulationNoData() throws SQLException {
        setupEmptyResultSet();
        long result = populationQueries.getWorldPopulation();

        assertEquals(0, result, "Should return 0 for no data");
    }

    /**
     * Tests handling of SQL exceptions during world population retrieval.
     */
    @Test
    public void testGetWorldPopulationWithSQLException() throws SQLException {
        when(mockCon.createStatement()).thenThrow(new SQLException("Database error"));
        long result = populationQueries.getWorldPopulation();

        assertEquals(0, result, "Should return 0 on exception");
        String output = outContent.toString();
        assertTrue(output.contains("Failed to get world population"), "Should display error message");
    }

    /**
     * Tests successful retrieval of continent population.
     */
    @Test
    public void testGetContinentPopulationWithData() throws Exception {
        setUpMockResultsWithPopulation(3705025700L);
        long result = populationQueries.getContinentPopulation("Asia");

        assertEquals(3705025700L, result, "Asia population should match");
        String output = outContent.toString();
        assertTrue(output.contains("CONTINENT POPULATION"), "Should display continent header");
        assertTrue(output.contains("Asia"), "Should display continent name");
    }

    /**
     * Tests handling of SQL exceptions during continent population retrieval.
     */
    @Test
    public void testGetContinentPopulationWithSQLException() throws Exception {
        when(mockCon.createStatement()).thenThrow(new SQLException("Database error"));
        long result = populationQueries.getContinentPopulation("Europe");

        assertEquals(0, result, "Should return 0 on exception");
        String output = outContent.toString();
        assertTrue(output.contains("Failed to get continent population"), "Should display error message");
    }

    /**
     * Tests successful retrieval of region population.
     */
    @Test
    public void testGetRegionPopulationWithData() throws SQLException {
        setUpMockResultsWithPopulation(38140000L);
        long result = populationQueries.getRegionPopulation("Caribbean");

        assertEquals(38140000L, result, "Caribbean population should match");
        String output = outContent.toString();
        assertTrue(output.contains("REGION POPULATION"), "Should display region header");
    }

    /**
     * Tests handling of SQL exceptions during region population retrieval.
     */
    @Test
    public void testGetRegionPopulationWithSQLException() throws SQLException {
        when(mockCon.createStatement()).thenThrow(new SQLException("Database error"));
        long result = populationQueries.getRegionPopulation("Caribbean");

        assertEquals(0, result, "Should return 0 on exception");
        String output = outContent.toString();
        assertTrue(output.contains("Failed to get region population"), "Should display error message");
    }

    // ========== POPULATION DISTRIBUTION QUERIES ==========

    /**
     * Configures the mock {@link ResultSet} to simulate distribution data.
     */
    private void setUpMockDistributionResults() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("name"))
                .thenReturn("Asia")
                .thenReturn("Europe");
        when(mockResultSet.getLong("total_population"))
                .thenReturn(3705025700L)
                .thenReturn(730074600L);
        when(mockResultSet.getLong("city_population"))
                .thenReturn(1766917815L)
                .thenReturn(241942813L);
    }

    /**
     * Tests successful retrieval of population distribution by continent.
     */
    @Test
    public void testGetPopulationDistributionByContinentWithData() throws SQLException {
        setUpMockDistributionResults();
        populationQueries.getPopulationDistributionByContinent();

        String output = outContent.toString();
        assertTrue(output.contains("POPULATION DISTRIBUTION BY CONTINENT"), "Should display header");
        assertTrue(output.contains("Asia"), "Should display Asia");
        assertTrue(output.contains("Europe"), "Should display Europe");
        assertTrue(output.contains("City %"), "Should display City % column");
        assertTrue(output.contains("Rural %"), "Should display Rural % column");
    }

    /**
     * Tests SQL exception handling for distribution by continent.
     */
    @Test
    public void testGetPopulationDistributionByContinentWithSQLException() throws SQLException {
        when(mockCon.createStatement()).thenThrow(new SQLException("Database error"));
        populationQueries.getPopulationDistributionByContinent();

        String output = outContent.toString();
        assertTrue(output.contains("Failed to get population distribution by continent"),
                "Should display error");
    }

    /**
     * Tests successful retrieval of population distribution by region.
     */
    @Test
    public void testGetPopulationDistributionByRegionWithData() throws SQLException {
        setUpMockDistributionResults();
        populationQueries.getPopulationDistributionByRegion();

        String output = outContent.toString();
        assertTrue(output.contains("POPULATION DISTRIBUTION BY REGION"), "Should display header");
        assertTrue(output.contains("City %"), "Should display City % column");
    }

    /**
     * Tests SQL exception handling for distribution by region.
     */
    @Test
    public void testGetPopulationDistributionByRegionWithSQLException() throws SQLException {
        when(mockCon.createStatement()).thenThrow(new SQLException("Database error"));
        populationQueries.getPopulationDistributionByRegion();

        String output = outContent.toString();
        assertTrue(output.contains("Failed to get population distribution by region"),
                "Should display error");
    }

    /**
     * Tests successful retrieval of population distribution by country.
     */
    @Test
    public void testGetPopulationDistributionByCountryWithData() throws SQLException {
        setUpMockDistributionResults();
        populationQueries.getPopulationDistributionByCountry();

        String output = outContent.toString();
        assertTrue(output.contains("POPULATION DISTRIBUTION BY COUNTRY"), "Should display header");
        assertTrue(output.contains("Top 20"), "Should indicate top 20 limit");
    }

    /**
     * Tests SQL exception handling for distribution by country.
     */
    @Test
    public void testGetPopulationDistributionByCountryWithSQLException() throws SQLException {
        when(mockCon.createStatement()).thenThrow(new SQLException("Database error"));
        populationQueries.getPopulationDistributionByCountry();

        String output = outContent.toString();
        assertTrue(output.contains("Failed to get population distribution by country"),
                "Should display error");
    }
}