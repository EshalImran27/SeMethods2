// Package declaration
package com.napier.sem2;

// Import SQL, JUnit and utility libraries
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test suite for verifying Capital-related database queries.
 * <p>
 * These tests connect to a real (or containerized) MySQL instance and validate
 * the correctness of query results from the {@link CapitalQueries} class.
 * </p>
 *
 * <p><b>Note:</b> These are integration tests — they require the database
 * to be running and properly seeded with expected data before execution.</p>
 */
public class CapitalIntegrationTests
{
    /** The main application instance used to establish a database connection. */
    static App app;

    /** The query handler for executing capital-related SQL queries. */
    static CapitalQueries capitalQueries;

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
        capitalQueries = new CapitalQueries(app.con);
    }

    /**
     * Tests retrieval of all capitals in the world ranked by population (descending order).
     * Verifies the top result matches expected data.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void testGetReportCapitalGlobalList() throws SQLException {
        List<City> capitals = capitalQueries.getReportCapitalGlobalList();
        assertEquals("Seoul", capitals.get(0).getName());
        assertEquals("South Korea", capitals.get(0).getCountry());
        assertEquals(9981619, capitals.get(0).getPopulation());
    }

    /**
     * Tests retrieval of capitals by continent ("Asia") and validates the data for the 3rd record.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportCapitalContinentList() throws SQLException {
        List<City> capitals = capitalQueries.getReportCapitalContinentList("Asia");
        assertEquals("Tokyo", capitals.get(2).getName());
        assertEquals("Japan", capitals.get(2).getCountry());
        assertEquals(7980230, capitals.get(2).getPopulation());
    }

    /**
     * Tests retrieval of capitals by region ("Caribbean") and validates the top-ranked capital.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportCapitalRegion() throws SQLException {
        List<City> capitals = capitalQueries.getReportCapitalRegionList("Caribbean");
        assertEquals("La Habana", capitals.get(0).getName());
        assertEquals("Cuba", capitals.get(0).getCountry());
        assertEquals(2256000, capitals.get(0).getPopulation());
    }

    /**
     * Tests retrieval of the top 5 capitals in a specific continent ("Asia").
     * Validates the 5th capital’s information.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCapitalContinent() throws SQLException {
        List<City> capitals = capitalQueries.getReportTopCapitalContinentList("Asia",5);
        assertEquals("Teheran", capitals.get(4).getName());
        assertEquals("Iran", capitals.get(4).getCountry());
        assertEquals(6758845, capitals.get(4).getPopulation());
    }

    /**
     * Tests retrieval of the top 5 capitals in a specific region ("Caribbean").
     * Validates the 5th record in the result list.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCapitalRegion() throws SQLException {
        List<City> capitals = capitalQueries.getReportTopCapitalRegionList("Caribbean",5);
        assertEquals("Nassau", capitals.get(4).getName());
        assertEquals("Bahamas", capitals.get(4).getCountry());
        assertEquals(172000, capitals.get(4).getPopulation());
    }

    /**
     * Tests retrieval of the top 5 capitals globally.
     * Validates the 3rd capital in the ranking.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCapitalGlobal() throws SQLException {
        List<City> capitals = capitalQueries.getReportTopCapitalGlobalList(5);
        assertEquals("Ciudad de México", capitals.get(2).getName());
        assertEquals("Mexico", capitals.get(2).getCountry());
        assertEquals(8591309, capitals.get(2).getPopulation());
    }
}
