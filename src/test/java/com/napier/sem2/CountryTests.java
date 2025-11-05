package com.napier.sem2;

import org.junit.jupiter.api.Test;
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
    //Test for parameterised Constructor
    @Test
    public void parameterisedConstructorWithAllParameters()
    {
        Country country = new Country("FRA", "France", "Europe", "Western Europe", "Paris", 22903129);
        assertEquals("FRA", country.code);
        assertEquals("France", country.name);
        assertEquals("Europe", country.continent);
        assertEquals("Western Europe", country.region);
        assertEquals(22903129, country.population);
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
    @Test
    public void printEmptyResultSet() throws Exception{
       Connection mockCon = mock(Connection.class);
       Statement mockStmt = mock(Statement.class);
       ResultSet mockRs = mock(ResultSet.class);
       when(mockCon.createStatement()).thenReturn(mockStmt);
       when(mockStmt.executeQuery(anyString())).thenReturn(mockRs);
       when(mockRs.next()).thenReturn(false);
       List<Country> list = Country.getCountriesByPopulationInWorld(mockCon);
       assertTrue(list.isEmpty(), ("List should be empty"));
    }
    @Test
    public void creatingACountryCorrectlyAfterSqlQuery() throws SQLException {
        Connection mockCon = mock(Connection.class);
        Statement mockStmt = mock(Statement.class);
        ResultSet mockRs = mock(ResultSet.class);
        when(mockCon.createStatement()).thenReturn(mockStmt);
        when(mockStmt.executeQuery(anyString())).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true,false);
        when(mockRs.getString("code")).thenReturn("FRA");
        when(mockRs.getString("name")).thenReturn("France");
        when(mockRs.getString("continent")).thenReturn("Europe");
        when(mockRs.getString("region")).thenReturn("Western Europe");
        when(mockRs.getInt("population")).thenReturn(2222398);
        when(mockRs.getString("capital")).thenReturn("Paris");
        List<Country> list = Country.getCountriesByPopulationInWorld(mockCon);
        assertNotNull(list, "List should not be null");
        assertEquals(1, list.size(), "should return one country");
        Country france = list.get(0);
        assertEquals("FRA",france.code);
        assertEquals("France",france.name);
        assertEquals("Europe",france.continent);
        assertEquals("Western Europe",france.region);
        assertEquals(2222398,france.population);

    }
    //3.SQL EXCEPTION HANDLING
    @Test
    public void printSQLException() throws Exception{
        Connection mockCon = mock(Connection.class);
        when(mockCon.createStatement()).thenThrow(new SQLException("Connection failed"));
        List<Country> list = Country.getCountriesByPopulationInWorld(mockCon);
        assertTrue(list.isEmpty(), ("List should be empty"));
    }
    //Tests for getCountriesByPopulationInContinent Method
    //1. NULL DATABASE
    @Test
    public void printNullDatabaseConnectionInContinent(){
        List<Country> list = Country.getCountriesByPopulationInContinent(null,"Asia");
        String result = output.toString();
        assertTrue(result.contains("Database Connection is null"), ("Error message not found"));
    }
    //2. NULL CONTINENT PARAMETER
    @Test
    public void printNullContinent() throws SQLException {
        Connection mockCon = mock(Connection.class);
        PreparedStatement mockStmt = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);
        when(mockCon.prepareStatement(anyString())).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(false);
        List<Country> list = Country.getCountriesByPopulationInContinent(mockCon,null);
        assertTrue(list.isEmpty(), ("List should be empty"));

    }
    //result of sql query
    @Test
    public void creatingACountryCorrectlyInContinentAfterSqlQuery() throws SQLException {
        Connection mockCon = mock(Connection.class);
        PreparedStatement mockStmt = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);
        when(mockCon.prepareStatement(anyString())).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true,false);
        when(mockRs.getString("code")).thenReturn("FRA");
        when(mockRs.getString("name")).thenReturn("France");
        when(mockRs.getString("continent")).thenReturn("Europe");
        when(mockRs.getString("region")).thenReturn("Western Europe");
        when(mockRs.getInt("population")).thenReturn(2222398);
        when(mockRs.getString("capital")).thenReturn("Paris");
        List<Country> list = Country.getCountriesByPopulationInContinent(mockCon, "Europe");
        assertNotNull(list, "List should not be null");
        assertEquals(1, list.size(), "should return one country");
        Country france = list.get(0);
        assertEquals("FRA",france.code);
        assertEquals("France",france.name);
        assertEquals("Europe",france.continent);
        assertEquals("Western Europe",france.region);
        assertEquals(2222398,france.population);

        verify(mockStmt).setString(1, "Europe");

    }
    //Tests for display Method
    //1.ALL POPULATED VALUES
    @Test
    public void printAllPopulatedValues(){
        Country tempCon= new Country();
        tempCon.code="FRA";
        tempCon.name="France";
        tempCon.continent="Europe";
        tempCon.region="Western Europe";
        tempCon.population = 20;
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("France"),  ("Name cannot be empty"));
        assertTrue(result.contains("Europe"),  ("Continent cannot be empty"));
        assertTrue(result.contains("FRA"),  ("code cannot be empty"));
    }
    //2.NULL CODE
    @Test
    public void printDisplayWithNullCode(){
        Country tempCon= new Country();
        tempCon.code=null;
        tempCon.name="France";
        tempCon.continent="Europe";
        tempCon.region="Western Europe";
        tempCon.population = 20;
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("N/A"), ("Error message not found"));
    }
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
}