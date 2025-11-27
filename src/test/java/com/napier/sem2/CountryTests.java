package com.napier.sem2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.*;
import static org.junit.jupiter.api.Assertions.*;


public class CountryTests
{
    static Country country;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private Handler testHandler;
    private Logger countryLogger;

    @BeforeAll
    static void init()
    {
        country = new Country();
    }

    @BeforeEach
    void setUpOutput(){
        countryLogger = Logger.getLogger(Country.class.getName());
        countryLogger.setUseParentHandlers(false); // Remove default handlers

        testHandler = new StreamHandler(new PrintStream(output), new SimpleFormatter()) {
            @Override
            public synchronized void publish(LogRecord record) {
                super.publish(record);
                flush(); // Ensure the message is written to the stream
            }
        };
        testHandler.setLevel(Level.ALL);
        countryLogger.addHandler(testHandler);
        countryLogger.setLevel(Level.ALL);


    }
    @AfterEach
    void resetOutput(){
        if (testHandler != null) {
            countryLogger.removeHandler(testHandler);
            testHandler.close();
        }
        // Restore default logging behavior
        countryLogger.setUseParentHandlers(true);
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
        Country country = new Country("FRA", "France", "Europe", "Western Europe", 1234 , 22903129);
        boolean allAssertionsPass = "FRA".equals(country.getCode()) &&
                "France".equals(country.getName()) &&
                "Europe".equals(country.getContinent()) &&
                "Western Europe".equals(country.getRegion()) &&
                1234 == country.getCapital() &&
                22903129 == country.getPopulation();

        assertTrue(allAssertionsPass, "All constructor parameters should be set correctly");
    }
    //1.ALL POPULATED VALUES
    @Test
    public void testDisplayWithAllPopulatedValues(){
        Country country = new Country("FRA", "France", "Europe", "Western Europe", 1233, 22903129);
        country.display();
        String result = output.toString();
        assertTrue(result.contains("France")&& result.contains ("Europe") && result.contains ("FRA") && result.contains ("Western Europe") , "Country must be populated correctly");
    }
    @Test
    public void printDisplayWithNullCode(){
        Country tempCon= new Country();
        tempCon.setCode(null);
        tempCon.setName("France");
        tempCon.setContinent("Europe");
        tempCon.setRegion("Western Europe");
        tempCon.setPopulation(22903129);
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("N/A"), ("Error message not found"));
    }
    @Test
    public void printDisplayWithNullName(){
        Country tempCon= new Country();
        tempCon.setCode("FRA");
        tempCon.setName(null);
        tempCon.setContinent("Europe");
        tempCon.setRegion("Western Europe");
        tempCon.setPopulation(22903129);
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }
    //4.NULL CONTINENT
    @Test
    public void printDisplayWithNullContinent(){
        Country tempCon= new Country();
        tempCon.setCode("FRA");
        tempCon.setName("France");
        tempCon.setContinent(null);
        tempCon.setRegion("Western Europe");
        tempCon.setPopulation(22903129);
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }
    //5. ZERO POPULATION
    @Test
    public void printDisplayWithNoPopulation(){
        Country tempCon= new Country();
        tempCon.setCode("FRA");
        tempCon.setName("France");
        tempCon.setContinent("Europe");
        tempCon.setRegion("Western Europe");
        tempCon.setPopulation(0);
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("0"),  ("Error message not found"));
    }
    //Tests for DisplayCountries Method
    //1. NULL TEST
    @Test
    public void testDisplayCountriesWithNull()
    {
        Country.displayCountries(null);
        String result = output.toString();
        assertTrue(result.contains("No countries can be displayed"), ("Error message not found"));
    }
    //2. EMPTY LIST
    @Test
    public void testDisplayCountriesWithEmptyList() {
        List<Country> emptyList = new ArrayList<>();
        Country.displayCountries(emptyList);
        String result = output.toString();
        assertTrue(result.contains("No countries can be displayed"), ("Error message not found"));
    }
    //3. NULL MEMBER IN LIST
    @Test
    public void testDisplayCountriesWithNullMember()
    {
        List<Country> list = new ArrayList<>();
        list.add(null);
        Country.displayCountries(list);
        String result = output.toString();
        assertTrue(result.contains("Warning: country is null"), ("Error message not found"));
    }
    //4. NORMAL LIST
    @Test
    public void testDisplayCountriesWithNormalList()
    {
        List<Country> list = new ArrayList<>();
        Country france = new Country("FRA", "France", "Europe", "Western Europe",2203, 22903129);
        Country germany = new Country("GER", "Germany", "Europe", "Western Europe",2203, 22903129);
        list.add(france);
        list.add(germany);
        Country.displayCountries(list);
        String result = output.toString();
        assertTrue(result.contains("France") && result.contains("Germany"),
                "Both country names should be found in output");

    }
}