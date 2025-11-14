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
 * Integration test suite for verifying City-related database queries.
 * <p>
 * These tests connect to a real (or containerized) MySQL instance and validate
 * the correctness of query results from the {@link CityQueries} class.
 * </p>
 *
 * <p><b>Note:</b> These are integration tests — they require the database
 * to be running and properly seeded with expected data before execution.</p>
 */
public class CityIntegrationTests {
    /** The main application instance used to establish a database connection. */
    static App app;

    /** The query handler for executing city-related SQL queries. */
    static CityQueries cityQueries;

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
        cityQueries = new CityQueries(app.con);
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
     * Tests retrieval of all cities in the world ranked by population (descending order).
     * Verifies the top result matches expected data.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void testGetReportCityGlobalList() throws SQLException {
        List<City> cities = cityQueries.getReportCityGlobalList();
        assertEquals("Mumbai (Bombay)", cities.get(0).getName());
        assertEquals("India", cities.get(0).getCountry());
        assertEquals("Maharashtra", cities.get(0).getDistrict());
        assertEquals(10500000, cities.get(0).getPopulation());
    }

    /**
     * Tests retrieval of cities by continent ("Asia") and validates the data for the 3rd record.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportCityContinentList() throws SQLException {
        List<City> cities = cityQueries.getReportCityContinentList("Asia");
        assertEquals("Shanghai", cities.get(2).getName());
        assertEquals("China", cities.get(2).getCountry());
        assertEquals("Shanghai", cities.get(2).getDistrict());
        assertEquals(9696300, cities.get(2).getPopulation());
    }

    /**
     * Tests retrieval of cities by region ("Caribbean") and validates the top-ranked city.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportCityRegion() throws SQLException {
        List<City> cities = cityQueries.getReportCityRegionList("Caribbean");
        assertEquals("La Habana", cities.get(0).getName());
        assertEquals("Cuba", cities.get(0).getCountry());
        assertEquals("La Habana", cities.get(0).getDistrict());
        assertEquals(2256000, cities.get(0).getPopulation());
    }

    /**
     * Tests retrieval of cities by country ("Caribbean") and validates the top-ranked city.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportCityCountry() throws SQLException {
        List<City> cities = cityQueries.getReportCityCountryList("Spain");
        assertEquals("Barcelona", cities.get(1).getName());
        assertEquals("Spain", cities.get(1).getCountry());
        assertEquals("Katalonia", cities.get(1).getDistrict());
        assertEquals(1503451, cities.get(1).getPopulation());
    }

    /**
     * Tests retrieval of cities by district ("Caribbean") and validates the top-ranked city.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportCityDistrict() throws SQLException {
        List<City> cities = cityQueries.getReportCityDistrictList("Madrid");
        assertEquals("Madrid", cities.get(0).getName());
        assertEquals("Spain", cities.get(0).getCountry());
        assertEquals("Madrid", cities.get(0).getDistrict());
        assertEquals(2879052, cities.get(0).getPopulation());
    }

    /**
     * Tests retrieval of the top 5 cities globally.
     * Validates the 3rd city in the ranking.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCityGlobal() throws SQLException {
        List<City> cities = cityQueries.getReportTopCityGlobalList(5);
        assertEquals("Jakarta", cities.get(4).getName());
        assertEquals("Indonesia", cities.get(4).getCountry());
        assertEquals("Jakarta Raya", cities.get(4).getDistrict());
        assertEquals(9604900, cities.get(4).getPopulation());
    }

    /**
     * Tests retrieval of the top 5 cities in a specific continent ("Asia").
     * Validates the 5th city’s information.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCityContinent() throws SQLException {
        List<City> cities = cityQueries.getReportTopCityContinentList("Asia",5);
        assertEquals("Karachi", cities.get(4).getName());
        assertEquals("Pakistan", cities.get(4).getCountry());
        assertEquals("Sindh", cities.get(4).getDistrict());
        assertEquals(9269265, cities.get(4).getPopulation());
    }

    /**
     * Tests retrieval of the top 5 cities in a specific region ("Caribbean").
     * Validates the 5th record in the result list.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCityRegion() throws SQLException {
        List<City> cities = cityQueries.getReportTopCityRegionList("Caribbean",5);
        assertEquals("Santiago de Cuba", cities.get(4).getName());
        assertEquals("Cuba", cities.get(4).getCountry());
        assertEquals("Santiago de Cuba", cities.get(4).getDistrict());
        assertEquals(433180, cities.get(4).getPopulation());
    }

    /**
            * Tests retrieval of the top 5 cities in a specific country ("Spain").
            * Validates the 5th record in the result list.
            *
            * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCityCountry() throws SQLException {
        List<City> cities = cityQueries.getReportTopCityCountryList("Spain",5);
        assertEquals("Zaragoza", cities.get(4).getName());
        assertEquals("Spain", cities.get(4).getCountry());
        assertEquals("Aragonia", cities.get(4).getDistrict());
        assertEquals(603367, cities.get(4).getPopulation());
    }

    /**
            * Tests retrieval of the top 5 cities in a specific district ("Madrid").
            * Validates the 5th record in the result list.
            *
            * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCityDistrict() throws SQLException {
        List<City> cities = cityQueries.getReportTopCityDistrictList("Madrid",5);
        assertEquals("Fuenlabrada", cities.get(3).getName());
        assertEquals("Spain", cities.get(3).getCountry());
        assertEquals("Madrid", cities.get(3).getDistrict());
        assertEquals(171173, cities.get(3).getPopulation());
    }
}
