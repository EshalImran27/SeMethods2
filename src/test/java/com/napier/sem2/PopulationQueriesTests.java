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
    private Connection MockCon;

    /** Mocked SQL result set for simulating query results. */
    private ResultSet MockResultSet;

    /** Instance of PopulationQueries using the mocked connection. */
    private PopulationQueries MockCountryQueries;

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
        MockCountryQueries = new PopulationQueries(MockCon);
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
     * an empty query result.
     */
    private void setupEmptyResultSet() throws SQLException {
        when(MockResultSet.next()).thenReturn(false);
    }

    // ---------- Global Population Reports ----------

    /**
     * Tests that a null database connection produces an appropriate console message.
     */
    @Test
    public void testNullDatabaseConnection() throws SQLException {
        PopulationQueries nullQueries = new PopulationQueries(null);
        nullQueries.getWorldPopulation();

        String result = outContent.toString();
        assertTrue(result.contains("Failed to get world population"),
                "Should display null connection error");
    }

    /**
     * Tests successful retrieval and display of global population data.
     */
    @Test
    public void testGetReportPopulationGlobalWithData() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getLong("TotalPopulation"))
                .thenReturn(6078749450L);
        MockCountryQueries.getWorldPopulation();

        String result = outContent.toString();
        assertTrue(result.contains("6,078,749,450"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions during global population retrieval.
     */
    @Test
    public void testGetReportPopulationGlobalWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getWorldPopulation();

        String result = outContent.toString();
        assertTrue(result.contains("Failed to get world population"), "Error message not found");
    }

    // ---------- Continent Population Reports ----------

    /**
     * Tests continent-specific capital retrieval with valid data.
     */
    @Test
    public void testGetReportPopulationContinent() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getLong("ContinentPopulation"))
                .thenReturn(730074600L);

        MockCountryQueries.getContinentPopulation("Europa");

        String result = outContent.toString();
        System.out.println(result);
        assertTrue(result.contains("730,074,600"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions for continent-based queries.
     */
    @Test
    public void testGetReportPopulationContinentWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getContinentPopulation("Europe");

        String result = outContent.toString();
        assertTrue(result.contains("Failed to get continent population"), "Error message not found");
    }

    // ---------- Region Population Reports ----------

    /**
     * Tests region-specific capital retrieval with valid data.
     */
    @Test
    public void testGetReportPopulationRegion() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getLong("RegionPopulation"))
                .thenReturn(38140000L);

        MockCountryQueries.getRegionPopulation("Caribbean");

        String result = outContent.toString();
        System.out.println(result);
        assertTrue(result.contains("38,140,000"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions for continent-based queries.
     */
    @Test
    public void testGetReportPopulationRegionWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getRegionPopulation("Caribbean");

        String result = outContent.toString();
        assertTrue(result.contains("Failed to get region population"), "Error message not found");
    }

    // ---------- Country Population Reports ----------

    /**
     * Tests region-specific capital retrieval with valid data.
     */
    @Test
    public void testGetReportPopulationCountry() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getLong("CountryPopulation"))
                .thenReturn(39441700L);

        MockCountryQueries.getCountryPopulation("Spain");

        String result = outContent.toString();
        System.out.println(result);
        assertTrue(result.contains("39,441,700"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions for continent-based queries.
     */
    @Test
    public void testGetReportPopulationCountryWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getCountryPopulation("Spain");

        String result = outContent.toString();
        assertTrue(result.contains("Failed to get country population"), "Error message not found");
    }

    // ---------- District Population Reports ----------

    /**
     * Tests region-specific capital retrieval with valid data.
     */
    @Test
    public void testGetReportPopulationDistrict() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getLong("DistrictPopulation"))
                .thenReturn(1540107L);

        MockCountryQueries.getDistrictPopulation("Córdoba");

        String result = outContent.toString();
        System.out.println(result);
        assertTrue(result.contains("1,540,107"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions for continent-based queries.
     */
    @Test
    public void testGetReportPopulationDistrictWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getDistrictPopulation("Córdoba");

        String result = outContent.toString();
        assertTrue(result.contains("Failed to get district population"), "Error message not found");
    }

    // ---------- City Population Reports ----------

    /**
     * Tests region-specific capital retrieval with valid data.
     */
    @Test
    public void testGetReportPopulationCity() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getLong("CityPopulation"))
                .thenReturn(2879052L);

        MockCountryQueries.getCityPopulation("Madrid");

        String result = outContent.toString();
        System.out.println(result);
        assertTrue(result.contains("2,879,052"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions for continent-based queries.
     */
    @Test
    public void testGetReportPopulationCityWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getCityPopulation("Madrid");

        String result = outContent.toString();
        assertTrue(result.contains("Failed to get city population"), "Error message not found");
    }
}