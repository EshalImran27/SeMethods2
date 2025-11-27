package com.napier.sem2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * handle all population-related database queries for the world population information
 * this class provides methods to retrieve total population data at various geographic levels
 * including world, continent, region, country, district, and city.
 */
public class PopulationQueries {
    App  app;
    /** connecting object used to communicate with the database. */
    private Connection con;
    /** construct a new {@code PopulationQueries} instance
    * @param con A valid SQL {@link Connection} object.*/
    public PopulationQueries(Connection con) {
        this.con = con;
    }

    /**
     * retrieves the total population of the world by summing all country populations
     * this method executes a SQL query that aggregates population data from all countries
     * in the database to calculate the global population total.
     * SQL Query: SELECT SUM(Population) AS TotalPopulation FROM country
     * @return long - The total world population, or 0 if the query fails
     */


    public String getWorldPopulation()throws SQLException {
        try {
            //create an  SQL statement
            Statement stmt = con.createStatement();
            //define SQL query to sum all country population
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country";
            //execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            //check if result exists and extract the total population
            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                //Display the results in a formatted manner
                System.out.println("\n=== WORLD POPULATION ===");
                System.out.println("Total Population: " + String.format("%,d", totalPop));
                return totalPop + "";
            }
            return "0";
        } catch (Exception e) {
            //handle any exception that occur during every execution
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return "0";
        }
    }
    /**
     * Retrieves the total population of a specific continent by summing populations
     * of all countries within that continent.
     *
     * SQL Query: SELECT SUM(Population) AS TotalPopulation FROM country WHERE continent = ?
     *
     * @param continent - The name of the continent (e.g., "Asia", "Europe", "Africa")
     * @return long - The total population of the specified continent, or 0 if the query fails
     * @throws Exception If a database access error occurs
     */
    public String getContinentPopulation(String continent){
        try {
            //create an SQL statement
            Statement stmt = con.createStatement();
            //define SQL query with continent filter
            String strSelect = "SELECT SUM(Population) AS ContinentPopulation FROM country WHERE Continent = '" + continent + "' ";
            //execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            //check if result exists and extract the continent population
            if (rset.next()) {
                long totalPop = rset.getLong("ContinentPopulation");
            //display the results in a formatted manner
                System.out.println("\n=== CONTINENT POPULATION ===");
                System.out.println("Continent Population of " + continent + ": " + String.format("%,d", totalPop));
                return totalPop + "";
            }
            return "0";
        } catch (Exception e) {
            //handle any exceptions that occur during query execution
            System.out.println(e.getMessage());
            System.out.println("Failed to get continent population");
            return "0";
        }
    }
    /**
     * Retrieves the total population of a specific region by summing populations
     * of all countries within that region.
     *
     * SQL Query: SELECT SUM(Population) AS TotalPopulation FROM country WHERE region = ?
     *
     * @param region - The name of the region (e.g., "Caribbean", "Southern Europe", "Eastern Africa")
     * @return long - The total population of the specified region, or 0 if the query fails
     */
    public long getRegionPopulation(String region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Define SQL query with region filter
            String strSelect = "SELECT SUM(Population) AS RegionPopulation FROM country WHERE Region = '" + region + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the region population
            if (rset.next()) {
                long totalPop = rset.getLong("RegionPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== REGION POPULATION ===");
                System.out.println("Region Population of " + region + ": " + String.format("%,d", totalPop));
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            // Handle any exceptions that occur during query execution

            System.out.println(e.getMessage());
            System.out.println("Failed to get region population");
            return 0;
        }
    }
    /**
     * Retrieves the total population of a specific country.
     *
     * SQL Query: SELECT Population AS TotalPopulation FROM country WHERE name = ?
     *
     * @param country - The name of the country (e.g., "China", "United States", "India")
     * @return long - The total population of the specified country, or 0 if the query fails
     */
    public String getCountryPopulation(String country) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Define SQL query with country name filter
            String strSelect = "SELECT SUM(Population) AS CountryPopulation FROM country WHERE Name = '" + country + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the country population

            if (rset.next()) {
                long totalPop = rset.getLong("CountryPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== COUNTRY POPULATION ===");
                System.out.println("Country Population of " + country +": " + String.format("%,d", totalPop));
                return totalPop + "";
            }
            return "0";
        } catch (Exception e) {
            // Handle any exceptions that occur during query execution
            System.out.println(e.getMessage());
            System.out.println("Failed to get country population");
            return "0";
        }
    }
    /**
            * Retrieves the total population of a specific district by summing populations
     * of all cities within that district.
            *
            * SQL Query: SELECT SUM(Population) AS TotalPopulation FROM city WHERE district = ?
            *
            * @param district - The name of the district (e.g., "California", "Ontario", "Queensland")
     * @return long - The total population of the specified district, or 0 if the query fails
     */
    public String getDistrictPopulation(String district) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Define SQL query with district filter - queries city table, not country
            String strSelect = "SELECT SUM(Population) AS DistrictPopulation FROM city WHERE District = '" + district + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the district population
            if (rset.next()) {
                long totalPop = rset.getLong("DistrictPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== DISTRICT POPULATION ===");
                System.out.println("District Population of " + district + ": " + String.format("%,d", totalPop));
                return totalPop + "";
            }
            return "0";
        } catch (Exception e) {
            // Handle any exceptions that occur during query execution
            System.out.println(e.getMessage());
            System.out.println("Failed to get district population");
            return "0";
        }
    }
    /**
     * Retrieves the population of a specific city.
     *
     * SQL Query: SELECT Population AS TotalPopulation FROM city WHERE name = ?
     *
     * @param city - The name of the city (e.g., "Mumbai", "Tokyo", "New York")
     * @return long - The population of the specified city, or 0 if the query fails
     */
    public String getCityPopulation(String city) {
        try {
            // Create an SQL statement

            Statement stmt = con.createStatement();
            // Define SQL query with city name filter - queries city table
            String strSelect = "SELECT SUM(Population) AS CityPopulation FROM city WHERE Name = '" + city + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the city population
            if (rset.next()) {
                long totalPop = rset.getLong("CityPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== CITY POPULATION ===");
                System.out.println("City Population of " + city + ": " + String.format("%,d", totalPop));
                return totalPop + "";
            }
            return "0";
        } catch (Exception e) {
            // Handle any exceptions that occur during query execution

            System.out.println(e.getMessage());
            System.out.println("Failed to get city population");
            return "0";
        }
    }
}
