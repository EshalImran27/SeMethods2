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
 * Unit test suite for the {@link LanguageQueries} class.
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
public class LanguageQueriesTests {
    /** Mocked database connection. */
    private Connection MockCon;

    /** Mocked SQL result set for simulating query results. */
    private ResultSet MockResultSet;

    /** Instance of LanguageQueries using the mocked connection. */
    private LanguageQueries MockCountryQueries;

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
        MockCountryQueries = new LanguageQueries(MockCon);
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
     * two valid language entries.
     */
    private void setUpMockResultsWithData() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getString("cl.Language"))
                .thenReturn("Spanish")
                .thenReturn("English");
        when(MockResultSet.getString("WorldPercentage"))
                .thenReturn("5.84%")
                .thenReturn("5.71%");
        when(MockResultSet.getInt("TotalSpeakers"))
                .thenReturn(355029462)
                .thenReturn(347077867);
    }

    /**
     * Configures the mock {@link ResultSet} to simulate
     * an empty query result.
     */
    private void setupEmptyResultSet() throws SQLException {
        when(MockResultSet.next()).thenReturn(false);
    }

    /**
     * Tests that a null database connection produces an appropriate console message.
     */
    @Test
    public void testNullDatabaseConnection() throws SQLException {
        LanguageQueries nullQueries = new LanguageQueries(null);
        nullQueries.getReportLanguage();

        String result = outContent.toString();
        assertTrue(result.contains("Database Connection is null"),
                "Should display null connection error");
    }

    /**
     * Tests successful retrieval and display of language data.
     */
    @Test
    public void testGetReportLanguageWithData() throws SQLException {
        setUpMockResultsWithData();
        MockCountryQueries.getReportLanguage();

        String result = outContent.toString();
        assertTrue(result.contains("Spanish"), "Error message not found");
        assertTrue(result.contains("English"), "Error message not found");
    }

    /**
     * Tests system output when no languages are found.
     */
    @Test
    public void testGetReportLanguageNoData() throws SQLException {
        setupEmptyResultSet();
        MockCountryQueries.getReportLanguage();

        String result = outContent.toString();
        assertTrue(result.contains("No languages can be displayed"), "Error message not found");
    }

    /**
     * Tests handling of SQL exceptions during global language retrieval.
     */
    @Test
    public void testGetReportLanguageWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        MockCountryQueries.getReportLanguage();

        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }
}
