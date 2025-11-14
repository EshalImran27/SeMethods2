// Package declaration
package com.napier.sem2;

// Import SQL, JUnit and utility libraries
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;

import static com.napier.sem2.CityIntegrationTets.cityQueries;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test suite for the {@link CityQueries} class.
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
public class CityQueriesTets {
    /** Mocked database connection. */
    private Connection MockCon;

    /** Mocked SQL result set for simulating query results. */
    private ResultSet MockResultSet;

    /** Instance of CityQueries using the mocked connection. */
    private CityQueries MockCountryQueries;

    /** Captures System.out output for verification. */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * Sets up fresh mock objects before each test and redirects
     * {@code System.out} to a local stream for output capture.
     */
    @BeforeEach
    void setUp() throws SQLException {
        MockCon = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        MockResultSet = mock(ResultSet.class);

        when(MockCon.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(MockResultSet);

        System.setOut(new PrintStream(outContent));
        MockCountryQueries = new CityQueries(MockCon);
    }

    /**
     * Restores the default system output after each test.
     */
    @AfterEach
    void tearDown(){
        System.setOut(System.out);
    }

    /**
     * Configures the mock {@link ResultSet} to simulate
     * two valid city entries.
     */
    private void setUpMockResultsWithData() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getString("city_name"))
                .thenReturn("Madrid")
                .thenReturn("London");
        when(MockResultSet.getString("country_name"))
                .thenReturn("Spain")
                .thenReturn("United Kingdom");
        when(MockResultSet.getString("city_district"))
                .thenReturn("Madrid")
                .thenReturn("England");
        when(MockResultSet.getInt("population"))
                .thenReturn(2879052)
                .thenReturn(7285000);
    }

    /**
     * Configures the mock {@link ResultSet} to simulate
     * an empty query result.
     */
    private void setupEmptyResultSet() throws SQLException {
        when(MockResultSet.next()).thenReturn(false);
    }

    // ---------- Global City Reports ----------

    /**
     * Tests that a null database connection produces an appropriate console message.
     */
    @Test
    public void testNullDatabaseConnection() throws SQLException {
        CityQueries nullQueries = new CityQueries(null);
        nullQueries.getReportCityGlobal();

        String result = outContent.toString();
        assertTrue(result.contains("Database Connection is null"),
                "Should display null connection error");
    }

    /**
     * Tests successful retrieval and display of global city data.
     */
    @Test
    public void testGetReportCityGlobalWithData() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCityGlobal();

        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
        assertTrue(result.contains("London"), "Error message not found");
    }

    /**
     * Tests system output when no cities are found.
     */
    @Test
    public void testGetReportCityGlobalNoData() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportCityGlobal();

        String result = outContent.toString();
        assertTrue(result.contains("No cities can be displayed"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions during global city retrieval.
     */
    @Test
    public void testGetReportCityGlobalWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCityGlobal();

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    // ---------- Continent City Reports ----------

    /**
     * Tests continent-specific city retrieval with valid data.
     */
    @Test
    public void testGetReportCityContinent() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCityContinent("Europa");

        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
        assertTrue(result.contains("London"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions for continent-based queries.
     */
    @Test
    public void testGetReportCityContinentWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCityContinent("Europe");

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    // ---------- Region City Reports ----------

    /**
     * Tests successful retrieval of region-based city reports.
     */
    @Test
    public void testGetReportCityRegion() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCityRegion("Southern Europe");

        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
    }

    /**
     * Tests SQL exception handling for region-based queries.
     */
    @Test
    public void testGetReportCityRegionWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCityRegion("Southern Europe");

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    // ---------- Country City Reports ----------

    /**
     * Tests successful retrieval of country-based city reports.
     */
    @Test
    public void testGetReportCityCountry() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCityCountry("Spain");

        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
    }

    /**
     * Tests SQL exception handling for country-based queries.
     */
    @Test
    public void testGetReportCityCountryWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCityCountry("Spain");

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    // ---------- District City Reports ----------

    /**
     * Tests successful retrieval of district-based city reports.
     */
    @Test
    public void testGetReportCityDistrict() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCityDistrict("Southern Europe");

        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
    }

    /**
     * Tests SQL exception handling for district-based queries.
     */
    @Test
    public void testGetReportCityDistrictWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCityDistrict("Madrid");

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    // ---------- Top Global City Reports ----------

    /**
     * Tests valid retrieval of top N cities globally.
     */
    @Test
    public void testGetReportTopCityGlobalWithValidNumber() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCityGlobal(5);

        String result = outContent.toString();
        assertTrue(result.contains(
                        "Top 5 Cities in the world ranked from largest population to smallest: "),
                "Error message not found"
        );
    }

    /**
     * Tests when zero is passed — no cities should be displayed.
     */
    @Test
    public void testGetReportTopCityGlobalWithZero() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCityGlobal(0);

        String result = outContent.toString();
        assertTrue(result.contains("No cities can be displayed"), "Error message not found");
    }

    /**
     * Tests when a negative number is passed — handled gracefully.
     */
    @Test
    public void testGetReportTopCityGlobalWithNegativeNumber() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCityGlobal(-1);
        String result = outContent.toString();
        assertTrue(result.contains("No cities can be displayed"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions when fetching top global cities.
     */
    @Test
    public void testGetReportTopCityGlobalWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportTopCityGlobal(5);

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    // ---------- Top Continent City Reports ----------

    /**
     * Tests valid parameters for continent-based top N cities retrieval.
     */
    @Test
    public void testGetReportTopCityContinentWithValidParameters() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCityContinent("Europe", 5);
        String result = outContent.toString();

        assertTrue(result.contains("Madrid"), "Error message not found");
        assertTrue(result.contains("London"), "Error message not found");
    }

    /**
     * Tests null continent handling — should output a warning message.
     */
    @Test
    public void testGetReportTopCityContinentWithNullContinent() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCityContinent(null,5);

        String result = outContent.toString();
        assertTrue(result.contains("No cities can be displayed"), "Error message not found");
    }

    // ---------- Top Region City Reports ----------

    /**
     * Tests valid parameters for top N cities within a region.
     */
    @Test
    public void testGetReportTopCityRegionWithValidParameters() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCityRegion("Southern Europe", 5);

        String result = outContent.toString();
        assertTrue(result.contains(
                        "Top 5 Cities in the region Southern Europe ranked from largest population to smallest: "),
                "Error message not found"
        );
    }

    /**
     * Tests null region handling — should output a warning message.
     */
    @Test
    public void testGetReportTopCityRegionWithNullRegion() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCityRegion(null,5);

        String result = outContent.toString();
        assertTrue(result.contains("No cities can be displayed"), "Error message not found");
    }

    // ---------- Top Country City Reports ----------

    /**
     * Tests valid parameters for top N cities within a country.
     */
    @Test
    public void testGetReportTopCityCountryWithValidParameters() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCityCountry("Southern Europe", 5);

        String result = outContent.toString();
        assertTrue(result.contains(
                        "Top 5 Cities in the country Southern Europe ranked from largest population to smallest: "),
                "Error message not found"
        );
    }

    /**
     * Tests null country handling — should output a warning message.
     */
    @Test
    public void testGetReportTopCityCountryWithNullCountry() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCityCountry(null,5);

        String result = outContent.toString();
        assertTrue(result.contains("No cities can be displayed"), "Error message not found");
    }

    // ---------- Top District City Reports ----------

    /**
     * Tests valid parameters for top N cities within a district.
     */
    @Test
    public void testGetReportTopCityDistrictWithValidParameters() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCityDistrict("Southern Europe", 5);

        String result = outContent.toString();
        assertTrue(result.contains(
                        "Top 5 Cities in the district Southern Europe ranked from largest population to smallest: "),
                "Error message not found"
        );
    }

    /**
     * Tests null district handling — should output a warning message.
     */
    @Test
    public void testGetReportTopCityDistrictWithNullDistrict() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCityDistrict(null,5);

        String result = outContent.toString();
        assertTrue(result.contains("No cities can be displayed"), "Error message not found");
    }
}
