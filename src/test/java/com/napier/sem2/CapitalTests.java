package com.napier.sem2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CapitalTests
{
    static City capital;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();


    @BeforeAll
    static void init()
    {
        capital = new City();
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
        City capital = new City();
        assertNotNull(capital, "Capital constructor should not be null");
    }
    //Test for parameterised Constructor
    @Test
    public void testParameterisedConstructorWithAllParameters()
    {
        City capital = new City("Madrid", "Spain", 2879052);
        assertEquals("Madrid", capital.getName());
        assertEquals("Spain", capital.getCountry());
        assertEquals(2879052, capital.getPopulation());
    }
    //1.ALL POPULATED VALUES
    @Test
    public void testDisplayWithAllPopulatedValues(){
        City capital = new City("Madrid", "Spain", 2879052);
        capital.displayCapital();
        String result = output.toString();
        assertTrue(result.contains("Madrid"),  ("Name cannot be empty"));
        assertTrue(result.contains("Spain"),  ("Country cannot be empty"));
    }
    @Test
    public void printDisplayWithNullName(){
        City capital= new City();
        capital.setName(null);
        capital.setCountry("Spain");
        capital.setPopulation(2879052);
        capital.displayCapital();
        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }
    //4.NULL COUNTRY
    @Test
    public void printDisplayWithNullCountry(){
        City capital= new City();
        capital.setName("Madrid");
        capital.setCountry(null);
        capital.setPopulation(22903129);
        capital.displayCapital();
        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }
    @Test
    public void printDisplayWithNullDistrict(){
        City capital= new City();
        capital.setName("Madrid");
        capital.setCountry("Spain");
        capital.setDistrict(null);
        capital.setPopulation(22903129);
        capital.displayCapital();
        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }
    //5. ZERO POPULATION
    @Test
    public void printDisplayWithNoPopulation(){
        City capital= new City();
        capital.setName("Madrid");
        capital.setCountry("Spain");
        capital.setPopulation(0);
        capital.displayCapital();
        String result = output.toString();
        assertTrue(result.contains("0"),  ("Error message not found"));
    }
    //Tests for displayListOfCapital Method
    //1. NULL TEST
    @Test
    public void testDisplayListOfCapitalWithNull()
    {
        City.displayListOfCapital(null);
        String result = output.toString();
        assertTrue(result.contains("No capitals can be displayed"), ("Error message not found"));
    }
    //2. EMPTY LIST
    @Test
    public void testDisplayListOfCapitalWithEmptyList() {
        List<City> emptyList = new ArrayList<>();
        City.displayListOfCapital(emptyList);
        String result = output.toString();
        assertTrue(result.contains("No capitals can be displayed"), ("Error message not found"));
    }
    //3. NULL MEMBER IN LIST
    @Test
    public void testDisplayListOfCapitalWithNullMember()
    {
        List<City> list = new ArrayList<>();
        list.add(null);
        City.displayListOfCapital(list);
        String result = output.toString();
        assertTrue(result.contains("Warning: capital is null"), ("Error message not found"));
    }
    //4. NORMAL LIST
    @Test
    public void testDisplayListOfCapitalWithNormalList()
    {
        List<City> list = new ArrayList<>();
        City madrid = new City("Madrid", "Spain",2879052);
        City london = new City("London", "United Kingdom", 7285000);
        list.add(madrid);
        list.add(london);
        City.displayListOfCapital(list);
        String result = output.toString();
        assertTrue(result.contains("Madrid"), ("Error message not found"));
        assertTrue(result.contains("London"), ("Error message not found"));
    }
}