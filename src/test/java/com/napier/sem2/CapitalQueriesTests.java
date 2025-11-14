// Package declaration
package com.napier.sem2;

// Import SQL, JUnit and utility libraries
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test suite for the {@link CapitalQueries} class.
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
public class CapitalQueriesTests {
    /** Mocked database connection. */
    private Connection MockCon;

    /** Mocked SQL result set for simulating query results. */
    private ResultSet MockResultSet;

    /** Instance of CapitalQueries using the mocked connection. */
    private CapitalQueries MockCountryQueries;

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
        MockCountryQueries = new CapitalQueries(MockCon);
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
     * two valid capital entries.
     */
    private void setUpMockResultsWithData() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getString("city_name"))
                .thenReturn("Madrid")
                .thenReturn("London");
        when(MockResultSet.getString("country_name"))
                .thenReturn("Spain")
                .thenReturn("United Kingdom");
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

    // ---------- Global Capital Reports ----------

    /**
     * Tests that a null database connection produces an appropriate console message.
     */
    @Test
    public void testNullDatabaseConnection() throws SQLException {
        CapitalQueries nullQueries = new CapitalQueries(null);
        nullQueries.getReportCapitalGlobal();

        String result = outContent.toString();
        assertTrue(result.contains("Database Connection is null"),
                "Should display null connection error");
    }

    /**
     * Tests successful retrieval and display of global capital data.
     */
    @Test
    public void testGetReportCapitalGlobalWithData() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCapitalGlobal();

        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
        assertTrue(result.contains("London"), "Error message not found");
    }

    /**
     * Tests system output when no capitals are found.
     */
    @Test
    public void testGetReportCapitalGlobalNoData() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportCapitalGlobal();

        String result = outContent.toString();
        assertTrue(result.contains("No capitals can be displayed"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions during global capital retrieval.
     */
    @Test
    public void testGetReportCapitalGlobalWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCapitalGlobal();

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    // ---------- Continent Capital Reports ----------

    /**
     * Tests continent-specific capital retrieval with valid data.
     */
    @Test
    public void testGetReportCapitalContinent() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCapitalContinent("Europa");

        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
        assertTrue(result.contains("London"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions for continent-based queries.
     */
    @Test
    public void testGetReportCapitalContinentWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCapitalContinent("Europe");

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    // ---------- Region Capital Reports ----------

    /**
     * Tests successful retrieval of region-based capital reports.
     */
    @Test
    public void testGetReportCapitalRegion() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportCapitalRegion("Southern Europe");

        String result = outContent.toString();
        assertTrue(result.contains("Madrid"), "Error message not found");
    }

    /**
     * Tests SQL exception handling for region-based queries.
     */
    @Test
    public void testGetReportCapitalRegionWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportCapitalRegion("Southern Europe");

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    // ---------- Top Global Capital Reports ----------

    /**
     * Tests valid retrieval of top N capitals globally.
     */
    @Test
    public void testGetReportTopCapitalGlobalWithValidNumber() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCapitalGlobal(5);

        String result = outContent.toString();
        assertTrue(result.contains(
                "Top 5 Capitals in the world ranked from largest population to smallest: "),
                "Error message not found"
        );
    }

    /**
     * Tests when zero is passed — no capitals should be displayed.
     */
    @Test
    public void testGetReportTopCapitalGlobalWithZero() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCapitalGlobal(0);

        String result = outContent.toString();
        assertTrue(result.contains("No capitals can be displayed"), "Error message not found");
    }

    /**
     * Tests when a negative number is passed — handled gracefully.
     */
    @Test
    public void testGetReportTopCapitalGlobalWithNegativeNumber() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCapitalGlobal(-1);
        String result = outContent.toString();
        assertTrue(result.contains("No capitals can be displayed"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions when fetching top global capitals.
     */
    @Test
    public void testGetReportTopCapitalGlobalWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportTopCapitalGlobal(5);

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    // ---------- Top Continent Capital Reports ----------

    /**
     * Tests valid parameters for continent-based top N capitals retrieval.
     */
    @Test
    public void testGetReportTopCapitalContinentWithValidParameters() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCapitalContinent("Europe", 5);
        String result = outContent.toString();

        assertTrue(result.contains("Madrid"), "Error message not found");
        assertTrue(result.contains("London"), "Error message not found");
    }

    /**
     * Tests null continent handling — should output a warning message.
     */
    @Test
    public void testGetReportTopCapitalContinentWithNullContinent() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCapitalContinent(null,5);

        String result = outContent.toString();
        assertTrue(result.contains("No capitals can be displayed"), "Error message not found");
    }

    // ---------- Top Region Capital Reports ----------

    /**
     * Tests valid parameters for top N capitals within a region.
     */
    @Test
    public void testGetReportTopCapitalRegionWithValidParameters() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportTopCapitalRegion("Southern Europe", 5);

        String result = outContent.toString();
        assertTrue(result.contains(
                "Top 5 Capitals in the region Southern Europe ranked from largest population to smallest: "),
                "Error message not found"
        );
    }

    /**
     * Tests null region handling — should output a warning message.
     */
    @Test
    public void testGetReportTopCapitalRegionWithNullRegion() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportTopCapitalRegion(null,5);

        String result = outContent.toString();
        assertTrue(result.contains("No capitals can be displayed"), "Error message not found");
    }
}
