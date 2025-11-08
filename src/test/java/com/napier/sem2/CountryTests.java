package com.napier.sem2;

/*import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;//to add mock objects
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.List;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class CountryTests
{
    @Mock
    private Connection MockCon;
    @Mock
    private Statement MockStat;
    @Mock
    private ResultSet MockRS;

    private CountryQueries countryQueries;
    //private final ByteArrayOutputStream output = new ByteArrayOutputStream();
  /*  @BeforeAll
    static void init(){
        Connection mockCon = mock(Connection.class);
        CountryQueries  countryQueries= new CountryQueries(mockCon);
    }
    @BeforeEach
    void setUp() throws SQLException
    {
        MockitoAnnotations.initMocks(this);
        when(MockCon.createStatement()).thenReturn(MockStat);
        when(MockCon.createStatement().executeQuery(anyString())).thenReturn(MockRS);
        countryQueries = new CountryQueries(MockCon);
    }
   void setUpOutput(){
        System.setOut(new PrintStream(output));
    }
    @AfterEach
    void tearDown() throws SQLException
    {
        countryQueries = null;
    }
  /*  void resetOutput(){
        System.setOut(System.out);
    }

    private void setupMockResultSetWithData() throws SQLException {
        // First call returns true, subsequent calls return false (simulate 3 rows)
        when(MockRS.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        // Mock data for first country (China)
        when(MockRS.getString("code"))
                .thenReturn("CHN")
                .thenReturn("IND")
                .thenReturn("USA");

        when(MockRS.getString("name"))
                .thenReturn("China")
                .thenReturn("India")
                .thenReturn("United States");

        when(MockRS.getString("continent"))
                .thenReturn("Asia")
                .thenReturn("Asia")
                .thenReturn("North America");

        when(MockRS.getString("region"))
                .thenReturn("Eastern Asia")
                .thenReturn("Southern and Central Asia")
                .thenReturn("North America");

        when(MockRS.getInt("capital"))
                .thenReturn(1891)
                .thenReturn(1109)
                .thenReturn(3813);

        when(MockRS.getInt("population"))
                .thenReturn(1277558000)
                .thenReturn(1013662000)
                .thenReturn(278357000);
    }
    private void setupMockResultSetEmpty() throws SQLException {
        when(MockRS.next()).thenReturn(false);
    }

    @Test
    public void testGetCountriesByPopulationInWorld_Success() throws SQLException {
        setupMockResultSetWithData();
        countryQueries.getCountriesByPopulationInWorld();
        // Assert
        verify(MockCon).createStatement();
        verify(MockStat).executeQuery(contains("ORDER BY population DESC").toString());
        verify(MockRS, times(4)).next(); // 3 true + 1 false
        verify(MockRS).close();
        verify(MockRS).close();
    }

    @Test
    public void testGetCountriesByPopulationInWorld_NoData() throws SQLException {
        // Arrange
        setupMockResultSetEmpty();

        // Act
        countryQueries.getCountriesByPopulationInWorld();

        // Assert
        verify(mockStatement).executeQuery(anyString());
        verify(mockResultSet).next();
    }

    @Test
    @DisplayName("Test getCountriesByPopulationInWorld - SQL Exception")
    void testGetCountriesByPopulationInWorld_SQLException() throws SQLException {
        // Arrange
        when(mockStatement.executeQuery(anyString())).thenThrow(new SQLException("Database error"));

        // Act & Assert - should not throw exception (handled internally)
        Assertions.assertDoesNotThrow(() -> {
            countryQueries.getCountriesByPopulationInWorld();
        });
    }

    // ==================== Tests for getCountriesByPopulationInContinent ====================

    @Test
    @DisplayName("Test getCountriesByPopulationInContinent - Success")
    void testGetCountriesByPopulationInContinent_Success() throws SQLException {
        // Arrange
        setupMockResultSetWithData();
        String continent = "Asia";

        // Act
        countryQueries.getCountriesByPopulationInContinent(continent);

        // Assert
        verify(mockStatement).executeQuery(contains("WHERE continent = 'Asia'"));
        verify(mockResultSet, times(4)).next();
    }

    @Test
    @DisplayName("Test getCountriesByPopulationInContinent - Europe")
    void testGetCountriesByPopulationInContinent_Europe() throws SQLException {
        // Arrange
        setupMockResultSetEmpty();

        // Act
        countryQueries.getCountriesByPopulationInContinent("Europe");

        // Assert
        verify(mockStatement).executeQuery(contains("WHERE continent = 'Europe'"));
    }

    @Test
    @DisplayName("Test getCountriesByPopulationInContinent - Null Continent")
    void testGetCountriesByPopulationInContinent_NullContinent() throws SQLException {
        // Arrange
        setupMockResultSetEmpty();

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> {
            countryQueries.getCountriesByPopulationInContinent(null);
        });
    }

    // ==================== Tests for getCountriesByPopulationInRegion ====================

    @Test
    @DisplayName("Test getCountriesByPopulationInRegion - Success")
    void testGetCountriesByPopulationInRegion_Success() throws SQLException {
        // Arrange
        setupMockResultSetWithData();
        String region = "Eastern Asia";

        // Act
        countryQueries.getCountriesByPopulationInRegion(region);

        // Assert
        verify(mockStatement).executeQuery(contains("WHERE region = 'Eastern Asia'"));
        verify(mockResultSet, times(4)).next();
    }

    @Test
    @DisplayName("Test getCountriesByPopulationInRegion - Empty Result")
    void testGetCountriesByPopulationInRegion_EmptyResult() throws SQLException {
        // Arrange
        setupMockResultSetEmpty();

        // Act
        countryQueries.getCountriesByPopulationInRegion("NonExistent Region");

        // Assert
        verify(mockResultSet).next();
    }

    // ==================== Tests for getTopCountriesInWorld ====================

    @Test
    @DisplayName("Test getTopCountriesInWorld - Top 5")
    void testGetTopCountriesInWorld_Top5() throws SQLException {
        // Arrange
        setupMockResultSetWithData();
        int n = 5;

        // Act
        countryQueries.getTopCountriesInWorld(n);

        // Assert
        verify(mockStatement).executeQuery(contains("LIMIT 5"));
        verify(mockResultSet, atLeastOnce()).next();
    }

    @Test
    @DisplayName("Test getTopCountriesInWorld - Top 1")
    void testGetTopCountriesInWorld_Top1() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("code")).thenReturn("CHN");
        when(mockResultSet.getString("name")).thenReturn("China");
        when(mockResultSet.getString("continent")).thenReturn("Asia");
        when(mockResultSet.getString("region")).thenReturn("Eastern Asia");
        when(mockResultSet.getInt("capital")).thenReturn(1891);
        when(mockResultSet.getInt("population")).thenReturn(1277558000);

        // Act
        countryQueries.getTopCountriesInWorld(1);

        // Assert
        verify(mockStatement).executeQuery(contains("LIMIT 1"));
    }

    @Test
    @DisplayName("Test getTopCountriesInWorld - Zero Limit")
    void testGetTopCountriesInWorld_ZeroLimit() throws SQLException {
        // Arrange
        setupMockResultSetEmpty();

        // Act
        countryQueries.getTopCountriesInWorld(0);

        // Assert
        verify(mockStatement).executeQuery(contains("LIMIT 0"));
    }

    // ==================== Tests for getTopCountriesInContinent ====================

    @Test
    @DisplayName("Test getTopCountriesInContinent - Top 10 in Asia")
    void testGetTopCountriesInContinent_Top10Asia() throws SQLException {
        // Arrange
        setupMockResultSetWithData();

        // Act
        countryQueries.getTopCountriesInContinent("Asia", 10);

        // Assert
        verify(mockStatement).executeQuery(
                allOf(
                        contains("WHERE continent = 'Asia'"),
                        contains("LIMIT 10")
                )
        );
    }

    @Test
    @DisplayName("Test getTopCountriesInContinent - Top 3 in Europe")
    void testGetTopCountriesInContinent_Top3Europe() throws SQLException {
        // Arrange
        setupMockResultSetEmpty();

        // Act
        countryQueries.getTopCountriesInContinent("Europe", 3);

        // Assert
        verify(mockStatement).executeQuery(
                allOf(
                        contains("WHERE continent = 'Europe'"),
                        contains("LIMIT 3")
                )
        );
    }

    // ==================== Tests for getTopCountriesInRegion ====================

    @Test
    @DisplayName("Test getTopCountriesInRegion - Top 5 in Eastern Asia")
    void testGetTopCountriesInRegion_Top5EasternAsia() throws SQLException {
        // Arrange
        setupMockResultSetWithData();

        // Act
        countryQueries.getTopCountriesInRegion("Eastern Asia", 5);

        // Assert
        verify(mockStatement).executeQuery(
                allOf(
                        contains("WHERE region = 'Eastern Asia'"),
                        contains("LIMIT 5")
                )
        );
    }

    @Test
    @DisplayName("Test getTopCountriesInRegion - Negative Limit")
    void testGetTopCountriesInRegion_NegativeLimit() throws SQLException {
        // Arrange
        setupMockResultSetEmpty();

        // Act & Assert - SQL will handle negative limit
        Assertions.assertDoesNotThrow(() -> {
            countryQueries.getTopCountriesInRegion("Western Europe", -1);
        });
    }

    // ==================== Tests for Null Connection ====================

    @Test
    @DisplayName("Test with Null Connection")
    void testNullConnection() {
        // Arrange
        CountryQueries nullConnectionQueries = new CountryQueries(null);

        // Act & Assert - should handle gracefully
        Assertions.assertDoesNotThrow(() -> {
            nullConnectionQueries.getCountriesByPopulationInWorld();
        });
    }

    // ==================== Helper method matcher ====================

    private static org.mockito.ArgumentMatcher<String> allOf(
            org.mockito.ArgumentMatcher<String>... matchers) {
        return argument -> {
            for (org.mockito.ArgumentMatcher<String> matcher : matchers) {
                if (!matcher.matches(argument)) {
                    return false;
                }
            }
            return true;
        };
    }

    private static org.mockito.ArgumentMatcher<String> contains(String substring) {
        return argument -> argument != null && argument.contains(substring);
    }
}
    //Test for parameterised Constructor
    @Test
    public void parameterisedConstructorWithAllParameters()
    {
        Country country = new Country("FRA", "France", "Europe", "Western Europe", 1234, 22903129);
        assertEquals("FRA", country.getCode());
        assertEquals("France", country.getName());
        assertEquals("Europe", country.getContinent());
        assertEquals("Western Europe", country.getRegion());
        assertEquals(22903129, country.getPopulation());
    }
    //Tests for DisplayCountries Method
    //1. NULL TEST
    @Test
    public void displayNullCountries()
    {
        Country.displayCountries(null);
        String result = output.toString();
        assertTrue(result.contains("No countries can be displayed"), ("Error message not found"));
    }
    //2. EMPTY LIST
    @Test
    public void displayCountriesEmptyList() {
        List<Country> emptyList = new ArrayList<>();
        Country.displayCountries(emptyList);
        String result = output.toString();
        assertTrue(result.contains("No countries can be displayed"), ("Error message not found"));
    }
    //3. NULL MEMBER IN LIST
    @Test
    public void displayOneNullCountry()
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
        tempCountry.setCode("FRA");
        tempCountry.setName("France");
        tempCountry.setContinent("Europe");
        tempCountry.setPopulation(20);
        tempCountry.setRegion("Western Europe");
        list.add(tempCountry);
        Country.displayCountries(list);
        String result = output.toString();
        assertTrue(result.contains("France"), ("Error message not found"));
    }
    //Tests for getCountriesByPopulationInWorld Method
    // 1.NULL DATABASE
    @Test
    public void printNullDatabaseConnection() throws SQLException {
        CountryQueries countries = new CountryQueries(null);
        countries.getCountriesByPopulationInWorld();
        String result = output.toString();
        assertTrue(result.contains("Database Connection is null"), ("Error message not found"));
    }
    @Test
    public void testGetCountriesByPopulationInWorld_Success() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        // Mock data for first country (China)
        when(mockResultSet.getString("code"))
                .thenReturn("CHN")
                .thenReturn("IND");

        when(mockResultSet.getString("name"))
                .thenReturn("China")
                .thenReturn("India");

        when(mockResultSet.getString("continent"))
                .thenReturn("Asia")
                .thenReturn("Asia");

        when(mockResultSet.getString("region"))
                .thenReturn("Eastern Asia")
                .thenReturn("Southern and Central Asia");

        when(mockResultSet.getInt("capital"))
                .thenReturn(1891)
                .thenReturn(1109);

        when(mockResultSet.getInt("population"))
                .thenReturn(1277558000)
                .thenReturn(1013662000);
    }
    //2.RESULTSET EMPTY
   // @Test
  /*  public void printEmptyResultSet() throws Exception{
       Connection mockCon = mock(Connection.class);
       Statement mockStmt = mock(Statement.class);
       ResultSet mockRs = mock(ResultSet.class);
       when(mockCon.createStatement()).thenReturn(mockStmt);
       when(mockStmt.executeQuery(anyString())).thenReturn(mockRs);
       when(mockRs.next()).thenReturn(false);
       List<CountryQueries> list = new CountryQueries(mockCon);
       List<Country> list = Country.getCountriesByPopulationInWorld(mockCon);
       assertTrue(list.isEmpty(), ("List should be empty"));
    }*/
    /*@Test
    public void creatingACountryCorrectlyAfterSqlQuery() throws SQLException {
        Connection mockCon = mock(Connection.class);
        List<CountryQueries> countries = new CountryQueries(mockCon);
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country Japan";

        List<Country> list = Country.getCountriesByPopulationInWorld(mockCon);
        assertNotNull(list, "List should not be null");
        assertEquals(1, list.size(), "should return one country");
        Country france = list.get(0);
        assertEquals("FRA",france.getCode());
        assertEquals("France",france.getName());
        assertEquals("Europe",france.getContinent());
        assertEquals("Western Europe",france.getRegion());
        assertEquals(2222398,france.getPopulation());

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
        assertEquals("FRA",france.getCode());
        assertEquals("France",france.getName());
        assertEquals("Europe",france.getContinent());
        assertEquals("Western Europe",france.getRegion());
        assertEquals(2222398,france.getPopulation());

        verify(mockStmt).setString(1, "Europe");

    }
    //Tests for display Method
    //1.ALL POPULATED VALUES
    @Test
    public void printAllPopulatedValues(){
        Country tempCon= new Country();
        tempCon.setCode("FRA");
        tempCon.setName("France");
        tempCon.setContinent("Europe");
        tempCon.setRegion("Western Europe");
        tempCon.setPopulation(20);
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
        tempCon.setCode(null);
        tempCon.setName("France");
        tempCon.setContinent("Europe");
        tempCon.setRegion("Western Europe");
        tempCon.setPopulation(20);
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("N/A"), ("Error message not found"));
    }
    //3.NULL NAME
    @Test
    public void printDisplayWithNullName(){
        Country tempCon= new Country();
        tempCon.setCode("FRA");
        tempCon.setName(null);
        tempCon.setContinent("Europe");
        tempCon.setRegion("Western Europe");
        tempCon.setPopulation(20);
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
        tempCon.setPopulation(20);
        tempCon.display();
        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }
    public void printDisplayWithNullRegion(){
        Country tempCon= new Country();
        tempCon.setCode("FRA");
        tempCon.setName("France");
        tempCon.setContinent("Europe");
        tempCon.setRegion(null);
        tempCon.setPopulation(20);
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
}*/