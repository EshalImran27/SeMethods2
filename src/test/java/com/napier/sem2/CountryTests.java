package com.napier.sem2;

/*import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;//to add mock objects
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.List;
import org.junit.jupiter.api.TestInstance;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class CountryTests
{
    static Country country;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeAll
    static void init()
    {
        country = new Country();
    }

    @BeforeEach
    void setUpOutput(){
        System.setOut(new PrintStream(output));
    }
    @AfterEach
    void resetOutput(){
        System.setOut(System.out);
    }

    @Test
    public void testDefaultConstructor(){
        Country country = new Country();
        assertNotNull(country, "Country constructor should not be null");
    }
    //Test for parameterised Constructor
    @Test
    public void testParameterisedConstructorWithAllParameters()
    {
        Country country = new Country("FRA", "France", "Europe", "Western Europe", "Paris", 22903129);
        assertEquals("FRA", country.code);
        assertEquals("France", country.name);
        assertEquals("Europe", country.continent);
        assertEquals("Western Europe", country.region);
        assertEquals(22903129, country.population);
    }
    //1.ALL POPULATED VALUES
    @Test
    public void testDisplayWithAllPopulatedValues(){
        Country country = new Country("FRA", "France", "Europe", "Western Europe", "Paris", 22903129);
        assertEquals("FRA", country.code);
        String result = output.toString();
        assertTrue(result.contains("France"),  ("Name cannot be empty"));
        assertTrue(result.contains("Europe"),  ("Continent cannot be empty"));
        assertTrue(result.contains("FRA"),  ("code cannot be empty"));
        assertTrue(result.contains("Western Europe"),  ("region cannot be empty"));
    }
    @Test
    public void printDisplayWithNullCode(){
        Country tempCon= new Country();
        tempCon.
        tempCon.code=null;
        tempCon.name="France";
        tempCon.continent="Europe";
        tempCon.region="Western Europe";
        tempCon.population = 20;
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("N/A"), ("Error message not found"));
    }
    //Tests for DisplayCountries Method
    //1. NULL TEST
    @Test
    public void printCountriesTestNull()
    {
        Country.displayCountries(null);
        String result = output.toString();
        assertTrue(result.contains("No countries can be displayed"), ("Error message not found"));
    }
    //2. EMPTY LIST
    @Test
    public void printCountriesTestEmpty() {
        List<Country> emptyList = new ArrayList<>();
        Country.displayCountries(emptyList);
        String result = output.toString();
        assertTrue(result.contains("No countries can be displayed"), ("Error message not found"));
    }
    //3. NULL MEMBER IN LIST
    @Test
    public void printCountriesTestNullMember()
    {
        List<Country> list = new ArrayList<>();
        list.add(null);
        Country.displayCountries(list);
        String result = output.toString();
        assertTrue(result.contains("Warning: country is null"), ("Error message not found"));
    }
    //4. NORMAL LIST
    @Test
    public void printCountriesNormalList()
    {
        List<Country> list = new ArrayList<>();
        Country tempCountry = new Country();
        tempCountry.code="FRA";
        tempCountry.name ="France";
        tempCountry.continent = "Europe";
        tempCountry.population = 20;
        tempCountry.region = "Western Europe";
        list.add(tempCountry);
        Country.displayCountries(list);
        String result = output.toString();
        assertTrue(result.contains("France"), ("Error message not found"));
    }
    //Tests for getCountriesByPopulationInWorld Method
    // 1.NULL DATABASE
    @Test
    public void printNullDatabaseConnection()
    {
        List<Country> list = Country.getCountriesByPopulationInWorld(null);
        String result = output.toString();
        assertTrue(result.contains("Database Connection is null"), ("Error message not found"));
    }
    //2.RESULTSET EMPTY


    //2.NULL CODE

    //3.NULL NAME
    @Test
    public void printDisplayWithNullName(){
        Country tempCon= new Country();
        tempCon.code="FRA";
        tempCon.name=null;
        tempCon.continent="Europe";
        tempCon.region="Western Europe";
        tempCon.population = 20;
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }
    //4.NULL CONTINENT
    @Test
    public void printDisplayWithNullContinent(){
        Country tempCon= new Country();
        tempCon.code="FRA";
        tempCon.name="France";
        tempCon.continent=null;
        tempCon.region="Western Europe";
        tempCon.population = 20;
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }
    //5. ZERO POPULATION
    @Test
    public void printDisplayWithNoPopulation(){
        Country tempCon= new Country();
        tempCon.code="FRA";
        tempCon.name="France";
        tempCon.continent="Europe";
        tempCon.region="Western Europe";
        tempCon.population = 0;
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("0"),  ("Error message not found"));
    }
}*/