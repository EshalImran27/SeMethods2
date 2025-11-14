// Package declaration
package com.napier.sem2;

// Import SQL, JUnit and utility libraries
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the {@link City} class.
 * <p>
 * This class verifies that all functionalities of the City class
 * behave as expected, including:
 * <ul>
 *     <li>Constructors (default and parameterized)</li>
 *     <li>Display methods for individual and list outputs</li>
 *     <li>Handling of null and edge-case values</li>
 * </ul>
 * Tests are executed using JUnit 5 and rely on captured console output
 * for validation of display methods.
 * </p>
 */
public class CityTests {
    /** Shared City instance used across tests */
    static City city;

    /** Output stream used to capture console output for validation */
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Initializes a base {@link City} object before all tests run.
     */
    @BeforeAll
    static void init()
    {
        city = new City();
    }

    /**
     * Redirects standard output to {@code output} before each test.
     * Allows validation of printed console messages.
     */
    @BeforeEach
    void setUpOutput(){
        System.setOut(new PrintStream(output));
    }

    /**
     * Restores standard output to the system console after each test.
     * Prevents side effects across tests.
     */
    @AfterEach
    void resetOutput(){
        System.setOut(System.out);
    }

    // ---------- Constructor Tests ----------

    /**
     * Verifies that the default constructor creates a non-null City object.
     */
    @Test
    public void testDefaultConstructor(){
        City city = new City();
        assertNotNull(city, "City constructor should not be null");
    }

    /**
     * Verifies that the parameterized constructor assigns all values correctly.
     */
    @Test
    public void testParameterisedConstructorWithAllParameters()
    {
        City city = new City("Madrid", "Spain", "Madrid", 2879052);
        assertEquals("Madrid", city.getName());
        assertEquals("Spain", city.getCountry());
        assertEquals("Madrid", city.getDistrict());
        assertEquals(2879052, city.getPopulation());
    }

    // ---------- Display Method Tests ----------

    /**
     * Tests {@link City#displayCity()} with all populated values.
     * Ensures that valid name and country values are printed.
     */
    @Test
    public void testDisplayWithAllPopulatedValues(){
        City city = new City("Madrid", "Spain",  "Madrid", 2879052);
        city.displayCity();

        String result = output.toString();
        assertTrue(result.contains("Madrid"),  ("Name cannot be empty"));
        assertTrue(result.contains("Spain"),  ("Country cannot be empty"));
        assertTrue(result.contains("Madrid"),  ("District cannot be empty"));
    }

    /**
     * Tests {@link City#displayCity()} when the city name is null.
     * Ensures that "N/A" is displayed in place of a null value.
     */
    @Test
    public void printDisplayWithNullName(){
        City city= new City();
        city.setName(null);
        city.setCountry("Spain");
        city.setDistrict("Madrid");
        city.setPopulation(2879052);
        city.displayCity();

        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }

    /**
     * Tests {@link City#displayCity()} when the country is null.
     * Ensures that "N/A" is displayed in place of a null value.
     */
    @Test
    public void printDisplayWithNullCountry(){
        City city= new City();
        city.setName("Madrid");
        city.setCountry(null);
        city.setDistrict("Madrid");
        city.setPopulation(22903129);
        city.displayCity();

        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }

    /**
     * Tests {@link City#displayCity()} when the country is null.
     * Ensures that "N/A" is displayed in place of a null value.
     */
    @Test
    public void printDisplayWithNullDistrict(){
        City city= new City();
        city.setName("Madrid");
        city.setCountry("Spain");
        city.setDistrict(null);
        city.setPopulation(22903129);
        city.displayCity();

        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }

    /**
     * Tests {@link City#displayCity()} when the population is zero.
     * Ensures that population zero is still displayed correctly.
     */
    @Test
    public void printDisplayWithNoPopulation(){
        City city= new City();
        city.setName("Madrid");
        city.setCountry("Spain");
        city.setDistrict("Madrid");
        city.setPopulation(0);
        city.displayCity();

        String result = output.toString();
        assertTrue(result.contains("0"),  ("Error message not found"));
    }

    // ---------- Display Method Tests ----------

    /**
     * Tests {@link City#displayListOfCity(List)} when the list is null.
     * Ensures that an appropriate message is printed.
     */
    @Test
    public void testDisplayListOfCityWithNull()
    {
        City.displayListOfCity(null);

        String result = output.toString();
        assertTrue(result.contains("No cities can be displayed"), ("Error message not found"));
    }

    /**
     * Tests {@link City#displayListOfCity(List)} when the list is empty.
     * Ensures that a "No cities" message is printed.
     */
    @Test
    public void testDisplayListOfCityWithEmptyList() {
        List<City> emptyList = new ArrayList<>();
        City.displayListOfCity(emptyList);

        String result = output.toString();
        assertTrue(result.contains("No cities can be displayed"), ("Error message not found"));
    }

    /**
     * Tests {@link City#displayListOfCity(List)} when one member of the list is null.
     * Ensures that a warning message is printed for the null entry.
     */
    @Test
    public void testDisplayListOfCityWithNullMember()
    {
        List<City> list = new ArrayList<>();
        list.add(null);
        City.displayListOfCity(list);

        String result = output.toString();
        assertTrue(result.contains("Warning: city is null"), ("Error message not found"));
    }

    /**
     * Tests {@link City#displayListOfCity(List)} with a normal list containing valid city data.
     * Ensures that all valid cities are displayed correctly.
     */
    @Test
    public void testDisplayListOfCityWithNormalList()
    {
        List<City> list = new ArrayList<>();
        City madrid = new City("Madrid", "Spain", "Madrid", 2879052);
        City london = new City("London", "United Kingdom", "England", 7285000);
        list.add(madrid);
        list.add(london);
        City.displayListOfCity(list);

        String result = output.toString();
        assertTrue(result.contains("Madrid"), ("Error message not found"));
        assertTrue(result.contains("London"), ("Error message not found"));
    }
}
