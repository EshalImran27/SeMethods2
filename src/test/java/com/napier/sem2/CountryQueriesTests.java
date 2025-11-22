package com.napier.sem2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;//to add mock objects
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountryQueriesTests {
    private Connection MockCon;
    private ResultSet MockResultSet;
    private CountryQueries MockCountryQueries;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private List<Country> mockCountry;

    @BeforeEach
    void setUp() throws SQLException {
        MockCon = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        MockResultSet = mock(ResultSet.class);
        when(MockCon.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(MockResultSet);
        System.setOut(new PrintStream(outContent));
        MockCountryQueries = new CountryQueries(MockCon);
        mockCountry = new ArrayList<>();
    }

    @AfterEach
    void tearDown(){
        System.setOut(System.out);
    }

    private void setUpMockResultsWithData() throws SQLException {
        when(MockResultSet.next()).thenReturn(true,true,false);
        when(MockResultSet.getString("code"))
                .thenReturn("CHN")
                .thenReturn("IND");
        when(MockResultSet.getString("name"))
                .thenReturn("China")
                .thenReturn("India");
        when(MockResultSet.getString("continent"))
                .thenReturn("Asia")
                .thenReturn("Asia");
        when(MockResultSet.getString("region"))
                .thenReturn("Eastern Asia")
                .thenReturn("South east Asia");
        when(MockResultSet.getInt("capital"))
                .thenReturn(1891)
                .thenReturn(1109);

        when(MockResultSet.getInt("population"))
                .thenReturn(1277558000)
                .thenReturn(1013662000);
    }

    private void setupEmptyResultSet() throws SQLException {
        when(MockResultSet.next()).thenReturn(false);
    }

    @Test
    public void testNullDatabaseConnection() throws SQLException {
        CountryQueries nullQueries = new CountryQueries(null);
        nullQueries.getCountriesByPopulationInWorld();
        String result = outContent.toString();
        assertTrue(result.contains("Database Connection is null"),
                "Should display null connection error");
    }

    @Test
    public void testGetCountriesByPopulationInWorldWithData() throws SQLException {
        setUpMockResultsWithData();
        mockCountry = MockCountryQueries.getCountriesByPopulationInWorld();
        String result = outContent.toString();
        assertTrue(result.contains("China"), "Error message not found");
        assertTrue(result.contains("India"), "Error message not found");
    }
    @Test
    public void testGetCountriesByPopulationInWorldNoData() throws SQLException {
        setupEmptyResultSet();
        mockCountry =MockCountryQueries.getCountriesByPopulationInWorld();
        String result = outContent.toString();
        assertTrue(result.contains("No countries can be displayed"), "Error message not found");
    }
    @Test
    public void testGetCountriesByPopulationInWorldWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        mockCountry =MockCountryQueries.getCountriesByPopulationInWorld();
        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }
    @Test
    public void testGetCountriesByPopulationInValidContinent() throws SQLException {
        setUpMockResultsWithData();
        mockCountry =MockCountryQueries.getCountriesByPopulationInContinent("Asia");
        String result = outContent.toString();
        assertTrue(result.contains("China"), "Error message not found");
        assertTrue(result.contains("India"), "Error message not found");
    }
    @Test
    public void testGetCountriesByPopulationInContinentWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        mockCountry =MockCountryQueries.getCountriesByPopulationInContinent("Asia");
        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }
    @Test
    public void testGetCountriesByPopulationInValidRegion() throws SQLException {
        setUpMockResultsWithData();
        mockCountry =MockCountryQueries.getCountriesByPopulationInRegion("Eastern Asia");
        String result = outContent.toString();
        assertTrue(result.contains("China"), "Error message not found");
    }
    @Test
    public void testGetCountriesByPopulationInRegionWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        mockCountry =MockCountryQueries.getCountriesByPopulationInRegion("Caribbean");
        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }
    @Test
    public void getTopCountriesInWorldWithValidNumber() throws SQLException {
        setUpMockResultsWithData();
        mockCountry =MockCountryQueries.getTopCountriesInWorld(5);
        String result = outContent.toString();
        assertTrue(result.contains("Top 5 Countries in the world ranked from largest population to smallest: "), "Error message not found");
    }
    @Test
    public void getTopCountriesInWorldWithZero() throws SQLException {
        setupEmptyResultSet();
        mockCountry =MockCountryQueries.getTopCountriesInWorld(0);
        String result = outContent.toString();
        assertTrue(result.contains("No countries can be displayed"), "Error message not found");
    }
    @Test
    public void getTopCountriesInWorldWithNegativeNumber() throws SQLException {
        setupEmptyResultSet();
        mockCountry =MockCountryQueries.getTopCountriesInWorld(-1);
        String result = outContent.toString();
        assertTrue(result.contains("No countries can be displayed"), "Error message not found");
    }
    @Test
    public void getTopCountriesInWorldWithSQLException() throws SQLException {
        when(MockCon.createStatement()).thenThrow(new  SQLException());
        mockCountry =MockCountryQueries.getTopCountriesInWorld(5);
        String result = outContent.toString();
        assertTrue(result.contains("SQL Exception"), "Error message not found");
    }

    @Test
    public void getTopCountriesInContinentWithValidParameters() throws SQLException {
        setUpMockResultsWithData();
        mockCountry =MockCountryQueries.getTopCountriesInContinent("Asia", 5);
        String result = outContent.toString();
        assertTrue(result.contains("China"), "Error message not found");
        assertTrue(result.contains("India"), "Error message not found");
    }
    @Test
    public void getTopCountriesInWorldWithNullContinent() throws SQLException {
        setupEmptyResultSet();
        mockCountry =MockCountryQueries.getTopCountriesInContinent(null,5);
        String result = outContent.toString();
        assertTrue(result.contains("No countries can be displayed"), "Error message not found");
    }
    @Test
    public void getTopCountriesInRegionWithValidParameters() throws SQLException {
        setUpMockResultsWithData();
        mockCountry =MockCountryQueries.getTopCountriesInRegion("Caribbean", 5);
        String result = outContent.toString();
        assertTrue(result.contains("Top 5 Countries in the region Caribbean ranked from largest population to smallest: "), "Error message not found");
    }
    @Test
    public void getTopCountriesInRegionWithNullRegion() throws SQLException {
        setupEmptyResultSet();
        mockCountry =MockCountryQueries.getTopCountriesInRegion(null,5);
        String result = outContent.toString();
        assertTrue(result.contains("No countries can be displayed"), "Error message not found");
    }

}
    /*@Test
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
}*/
