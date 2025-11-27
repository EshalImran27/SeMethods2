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
        City city = cities.get(0);

        boolean allCorrect = "Mumbai (Bombay)".equals(city.getName()) &&
                "India".equals(city.getCountry()) &&
                "Maharashtra".equals(city.getDistrict()) &&
                10500000 == city.getPopulation();

        assertTrue(allCorrect, "First city should match expected values");
    }
    /**
     * Tests retrieval of cities by continent ("Asia") and validates the data for the 3rd record.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportCityContinentList() throws SQLException {
        List<City> cities = cityQueries.getReportCityContinentList("Asia");
        City city = cities.get(2);

        boolean allCorrect = "Shanghai".equals(city.getName()) &&
                "China".equals(city.getCountry()) &&
                "Shanghai".equals(city.getDistrict()) &&
                9696300 == city.getPopulation();

        assertTrue(allCorrect, "Third city in Asia should match expected values");
    }

    /**
     * Tests retrieval of cities by region ("Caribbean") and validates the top-ranked city.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportCityRegion() throws SQLException {
        List<City> cities = cityQueries.getReportCityRegionList("Caribbean");
        City city = cities.get(0);

        boolean allCorrect = "La Habana".equals(city.getName()) &&
                "Cuba".equals(city.getCountry()) &&
                "La Habana".equals(city.getDistrict()) &&
                2256000 == city.getPopulation();

        assertTrue(allCorrect, "First city in Caribbean should match expected values");
    }

    /**
     * Tests retrieval of cities by country ("Caribbean") and validates the top-ranked city.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportCityCountry() throws SQLException {
        List<City> cities = cityQueries.getReportCityCountryList("Spain");
        City city = cities.get(1);

        boolean allCorrect = "Barcelona".equals(city.getName()) &&
                "Spain".equals(city.getCountry()) &&
                "Katalonia".equals(city.getDistrict()) &&
                1503451 == city.getPopulation();

        assertTrue(allCorrect, "Second city in Spain should match expected values");
    }

    /**
     * Tests retrieval of cities by district ("Caribbean") and validates the top-ranked city.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportCityDistrict() throws SQLException {
        List<City> cities = cityQueries.getReportCityDistrictList("Madrid");
        City city = cities.get(0);

        boolean allCorrect = "Madrid".equals(city.getName()) &&
                "Spain".equals(city.getCountry()) &&
                "Madrid".equals(city.getDistrict()) &&
                2879052 == city.getPopulation();

        assertTrue(allCorrect, "First city in Madrid district should match expected values");
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
        City city = cities.get(4);

        boolean allCorrect = "Jakarta".equals(city.getName()) &&
                "Indonesia".equals(city.getCountry()) &&
                "Jakarta Raya".equals(city.getDistrict()) &&
                9604900 == city.getPopulation();

        assertTrue(allCorrect, "Fifth city in global top 5 should match expected values");
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
        City city = cities.get(4);

        boolean allCorrect = "Karachi".equals(city.getName()) &&
                "Pakistan".equals(city.getCountry()) &&
                "Sindh".equals(city.getDistrict()) &&
                9269265 == city.getPopulation();

        assertTrue(allCorrect, "Fifth city in Asia top 5 should match expected values");
    }

    /**
     * Tests retrieval of the top 5 cities in a specific region ("Caribbean").
     * Validates the 5th record in the result list.
     *
     * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCityRegion() throws SQLException {
        List<City> cities = cityQueries.getReportTopCityRegionList("Caribbean", 5);
        City city = cities.get(4);

        // Combine all assertions into one
        assertTrue("Santiago de Cuba".equals(city.getName()) &&
                        "Cuba".equals(city.getCountry()) &&
                        "Santiago de Cuba".equals(city.getDistrict()) &&
                        433180 == city.getPopulation(),
                "City at index 4 should have correct attributes");
    }

    /**
            * Tests retrieval of the top 5 cities in a specific country ("Spain").
            * Validates the 5th record in the result list.
            *
            * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCityCountry() throws SQLException {
        List<City> cities = cityQueries.getReportTopCityCountryList("Spain", 5);
        City city = cities.get(4);

        // Combine all assertions into one
        assertTrue("Zaragoza".equals(city.getName()) &&
                        "Spain".equals(city.getCountry()) &&
                        "Aragonia".equals(city.getDistrict()) &&
                        603367 == city.getPopulation(),
                "City at index 4 should have correct attributes");
    }
    /**
            * Tests retrieval of the top 5 cities in a specific district ("Madrid").
            * Validates the 5th record in the result list.
            *
            * @throws SQLException if a database access error occurs.
     */
    @Test
    void getReportTopCityDistrict() throws SQLException {
        List<City> cities = cityQueries.getReportTopCityDistrictList("Madrid", 5);
        City city = cities.get(3);

        // Combine all assertions into one
        assertTrue("Fuenlabrada".equals(city.getName()) &&
                        "Spain".equals(city.getCountry()) &&
                        "Madrid".equals(city.getDistrict()) &&
                        171173 == city.getPopulation(),
                "City at index 3 should have correct attributes");
    }
}
