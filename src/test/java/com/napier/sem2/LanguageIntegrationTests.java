// Package declaration
package com.napier.sem2;

// Import SQL, JUnit and utility libraries
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test suite for verifying Language-related database queries.
 * <p>
 * These tests connect to a real (or containerized) MySQL instance and validate
 * the correctness of query results from the {@link LanguageQueries} class.
 * </p>
 *
 * <p><b>Note:</b> These are integration tests — they require the database
 * to be running and properly seeded with expected data before execution.</p>
 */
public class LanguageIntegrationTests {
    /** The main application instance used to establish a database connection. */
    static App app;

    /** The query handler for executing capital-related SQL queries. */
    static LanguageQueries languageQueries;

    /**
     * Initializes the database connection and prepares the query handler
     * before running any tests.
     * <p>
     * This method executes once for the entire test class.
     * </p>
     */
    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 5000);
        languageQueries = new LanguageQueries(app.con);
    }

    /**
     * Cleans up resources after all integration tests have completed.
     * <p>
     * This method ensures that the database connection established by the
     * {@link App} instance is properly closed to prevent resource leaks
     * or lingering open connections after the test suite finishes.
     * </p>
     */
    @AfterAll
    static void clean(){
        if(app != null)
            app.disconnect();
    }

    /**
     * Tests retrieval the most spoken languages (descending for population).
     * Verifies the top result matches expected data.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void testGetReportLanguages() throws SQLException {
        List<Language> languages = languageQueries.getReportLanguageList();
        assertEquals("Chinese", languages.get(0).getName());
        assertEquals("19.61%", languages.get(0).getPercentage());
        assertEquals(1191843539, languages.get(0).getPopulation());
        assertEquals("Hindi", languages.get(1).getName());
        assertEquals("6.67%", languages.get(1).getPercentage());
        assertEquals(405633070, languages.get(1).getPopulation());
        assertEquals("Spanish", languages.get(2).getName());
        assertEquals("5.84%", languages.get(2).getPercentage());
        assertEquals(355029462, languages.get(2).getPopulation());
        assertEquals("English", languages.get(3).getName());
        assertEquals("5.71%", languages.get(3).getPercentage());
        assertEquals(347077867, languages.get(3).getPopulation());
        assertEquals("Arabic", languages.get(4).getName());
        assertEquals("3.85%", languages.get(4).getPercentage());
        assertEquals(233839239, languages.get(4).getPopulation());
    }
}
