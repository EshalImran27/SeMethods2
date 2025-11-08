package com.napier.sem2;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CountryQueryTests {
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
}
