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
    public PopulationQueries(App app) {
        this.app = app;
    }
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


    public long getWorldPopulation() {
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
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            //handle any exception that occur during every execution
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return 0;
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
    public long getContinentPopulation(String continent) throws Exception{
        try {
            //create an SQL statement
            Statement stmt = con.createStatement();
            //define SQL query with continent filter
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country  "+"WHERE continent = '" + continent + "' ";
            //execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            //check if result exists and extract the continent population
            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
            //display the results in a formatted manner
                System.out.println("\n=== CONTINENT POPULATION ===");
                System.out.println("Continent: " + continent);
                System.out.println("Total Population: " + String.format("%,d", totalPop));
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            //handle any exceptions that occur during query execution
            System.out.println(e.getMessage());
            System.out.println("Failed to get continent population");
            return 0;
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
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country "+"WHERE region = '" + region + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the region population
            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== REGION POPULATION ===");
                System.out.println("Region: " + region);
                System.out.println("Total Population: " + String.format("%,d", totalPop));
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
    public long getCountryPopulation(String country) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Define SQL query with country name filter
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country "+"WHERE region = '" + country + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the country population

            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== WORLD POPULATION ===");
                System.out.println("Total Population: " + String.format("%,d", totalPop));
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            // Handle any exceptions that occur during query execution
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return 0;
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
    public long getDistrictPopulation(String district) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Define SQL query with district filter - queries city table, not country
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country "+"WHERE region = '" + district + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the district populatio
            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== WORLD POPULATION ===");
                System.out.println("Total Population: " + String.format("%,d", totalPop));
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            // Handle any exceptions that occur during query execution
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return 0;
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
    public long getCityPopulation(String city) {
        try {
            // Create an SQL statement

            Statement stmt = con.createStatement();
            // Define SQL query with city name filter - queries city table
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country "+"WHERE region = '" + city + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the city population
            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== WORLD POPULATION ===");
                System.out.println("Total Population: " + String.format("%,d", totalPop));
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            // Handle any exceptions that occur during query execution

            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return 0;
        }
    }

    /**
     * Retrieves all capital cities in the world ranked by population.
     * Joins the city and country tables where city.ID matches country.Capital.
     *
     * SQL Query:
     * SELECT city.Name, country.Name, city.Population
     * FROM city JOIN country ON city.ID = country.Capital
     * ORDER BY city.Population DESC
     */
    public void getAllCapitalsInWorld() {
        try {
            // SQL query to get all capitals globally
            String query =
                    "SELECT city.Name AS capital_name, country.Name AS country_name, city.Population AS population " +
                            "FROM city " +
                            "JOIN country ON city.ID = country.Capital " +
                            "ORDER BY city.Population DESC";

            // Execute query
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            // Display header
            System.out.println("\n=== ALL CAPITAL CITIES IN THE WORLD ===");
            System.out.println(String.format("%-30s %-30s %15s", "Capital", "Country", "Population"));
            System.out.println("------------------------------------------------------------------------");

            // Display results
            while (rset.next()) {
                System.out.println(String.format("%-30s %-30s %,15d",
                        rset.getString("capital_name"),
                        rset.getString("country_name"),
                        rset.getInt("population")));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get all capitals in the world");
        }
    }

    /**
     * Retrieves all capital cities in a specific continent ranked by population.
     *
     * SQL Query:
     * SELECT city.Name, country.Name, city.Population
     * FROM city JOIN country ON city.ID = country.Capital
     * WHERE country.Continent = ?
     * ORDER BY city.Population DESC
     *
     * @param continent - The name of the continent (e.g., "Asia", "Europe")
     */
    public void getAllCapitalsInContinent(String continent) {
        try {
            // SQL query to get all capitals in a continent
            String query =
                    "SELECT city.Name AS capital_name, country.Name AS country_name, city.Population AS population " +
                            "FROM city " +
                            "JOIN country ON city.ID = country.Capital " +
                            "WHERE country.Continent = ? " +
                            "ORDER BY city.Population DESC";

            // Use PreparedStatement to prevent SQL injection
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, continent);
            ResultSet rset = pstmt.executeQuery();

            // Display header
            System.out.println("\n=== ALL CAPITALS IN " + continent + " ===");
            System.out.println(String.format("%-30s %-30s %15s", "Capital", "Country", "Population"));
            System.out.println("------------------------------------------------------------------------");

            // Display results
            while (rset.next()) {
                System.out.println(String.format("%-30s %-30s %,15d",
                        rset.getString("capital_name"),
                        rset.getString("country_name"),
                        rset.getInt("population")));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get capitals in " + continent);
        }
    }

    /**
     * Retrieves all capital cities in a specific region ranked by population.
     *
     * SQL Query:
     * SELECT city.Name, country.Name, city.Population
     * FROM city JOIN country ON city.ID = country.Capital
     * WHERE country.Region = ?
     * ORDER BY city.Population DESC
     *
     * @param region - The name of the region (e.g., "Caribbean", "Southern Europe")
     */
    public void getAllCapitalsInRegion(String region) {
        try {
            // SQL query to get all capitals in a region
            String query =
                    "SELECT city.Name AS capital_name, country.Name AS country_name, city.Population AS population " +
                            "FROM city " +
                            "JOIN country ON city.ID = country.Capital " +
                            "WHERE country.Region = ? " +
                            "ORDER BY city.Population DESC";

            // Use PreparedStatement to prevent SQL injection
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, region);
            ResultSet rset = pstmt.executeQuery();

            // Display header
            System.out.println("\n=== ALL CAPITALS IN " + region + " ===");
            System.out.println(String.format("%-30s %-30s %15s", "Capital", "Country", "Population"));
            System.out.println("------------------------------------------------------------------------");

            // Display results
            while (rset.next()) {
                System.out.println(String.format("%-30s %-30s %,15d",
                        rset.getString("capital_name"),
                        rset.getString("country_name"),
                        rset.getInt("population")));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get capitals in " + region);
        }
    }

    /**
     * Retrieves the top N populated capital cities in the world.
     *
     * SQL Query:
     * SELECT city.Name, country.Name, city.Population
     * FROM city JOIN country ON city.ID = country.Capital
     * ORDER BY city.Population DESC LIMIT N
     *
     * @param n - The number of top capital cities to retrieve
     */
    public void getTopCapitalsInWorld(int n) {
        // Validate input parameter
        if (n <= 0) {
            System.out.println("Invalid number: N must be greater than 0");
            return;
        }

        try {
            // SQL query to get top N capitals globally
            String query =
                    "SELECT city.Name AS capital_name, country.Name AS country_name, city.Population AS population " +
                            "FROM city " +
                            "JOIN country ON city.ID = country.Capital " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT ?";

            // Use PreparedStatement to prevent SQL injection
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, n);
            ResultSet rset = pstmt.executeQuery();

            // Display header
            System.out.println("\n=== TOP " + n + " CAPITAL CITIES IN THE WORLD ===");
            System.out.println(String.format("%-30s %-30s %15s", "Capital", "Country", "Population"));
            System.out.println("------------------------------------------------------------------------");

            // Display results
            while (rset.next()) {
                System.out.println(String.format("%-30s %-30s %,15d",
                        rset.getString("capital_name"),
                        rset.getString("country_name"),
                        rset.getInt("population")));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get top " + n + " capitals in the world");
        }
    }

    /**
     * Retrieves the top N populated capital cities in a specific continent.
     *
     * SQL Query:
     * SELECT city.Name, country.Name, city.Population
     * FROM city JOIN country ON city.ID = country.Capital
     * WHERE country.Continent = ?
     * ORDER BY city.Population DESC LIMIT N
     *
     * @param continent - The name of the continent (e.g., "Asia", "Europe")
     * @param n - The number of top capital cities to retrieve
     */
    public void getTopCapitalsInContinent(String continent, int n) {
        // Validate input parameters
        if (n <= 0 || continent == null || continent.isEmpty()) {
            System.out.println("Invalid parameters");
            return;
        }

        try {
            // SQL query to get top N capitals in a continent
            String query =
                    "SELECT city.Name AS capital_name, country.Name AS country_name, city.Population AS population " +
                            "FROM city " +
                            "JOIN country ON city.ID = country.Capital " +
                            "WHERE country.Continent = ? " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT ?";

            // Use PreparedStatement to prevent SQL injection
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, continent);
            pstmt.setInt(2, n);
            ResultSet rset = pstmt.executeQuery();

            // Display header
            System.out.println("\n=== TOP " + n + " CAPITALS IN " + continent + " ===");
            System.out.println(String.format("%-30s %-30s %15s", "Capital", "Country", "Population"));
            System.out.println("------------------------------------------------------------------------");

            // Display results
            while (rset.next()) {
                System.out.println(String.format("%-30s %-30s %,15d",
                        rset.getString("capital_name"),
                        rset.getString("country_name"),
                        rset.getInt("population")));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get top " + n + " capitals in " + continent);
        }
    }

    /**
     * Retrieves the top N populated capital cities in a specific region.
     *
     * SQL Query:
     * SELECT city.Name, country.Name, city.Population
     * FROM city JOIN country ON city.ID = country.Capital
     * WHERE country.Region = ?
     * ORDER BY city.Population DESC LIMIT N
     *
     * @param region - The name of the region (e.g., "Caribbean", "Southern Europe")
     * @param n - The number of top capital cities to retrieve
     */
    public void getTopCapitalsInRegion(String region, int n) {
        // Validate input parameters
        if (n <= 0 || region == null || region.isEmpty()) {
            System.out.println("Invalid parameters");
            return;
        }

        try {
            // SQL query to get top N capitals in a region
            String query =
                    "SELECT city.Name AS capital_name, country.Name AS country_name, city.Population AS population " +
                            "FROM city " +
                            "JOIN country ON city.ID = country.Capital " +
                            "WHERE country.Region = ? " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT ?";

            // Use PreparedStatement to prevent SQL injection
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, region);
            pstmt.setInt(2, n);
            ResultSet rset = pstmt.executeQuery();

            // Display header
            System.out.println("\n=== TOP " + n + " CAPITALS IN " + region + " ===");
            System.out.println(String.format("%-30s %-30s %15s", "Capital", "Country", "Population"));
            System.out.println("------------------------------------------------------------------------");

            // Display results
            while (rset.next()) {
                System.out.println(String.format("%-30s %-30s %,15d",
                        rset.getString("capital_name"),
                        rset.getString("country_name"),
                        rset.getInt("population")));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get top " + n + " capitals in " + region);
        }
    }

// ============================================================
//         POPULATION DISTRIBUTION QUERY METHODS
// ============================================================

    /**
     * Shows population distribution (total, in cities, not in cities) for each continent.
     * Calculates what percentage of population lives in cities vs rural areas per continent.
     *
     * SQL Logic:
     * 1. Get total population per continent from country table
     * 2. Get city population per continent by summing cities grouped by country
     * 3. Calculate rural population as (total - city population)
     * 4. Calculate percentages
     */
    public void getPopulationDistributionByContinent() {
        try {
            // SQL query to calculate population distribution by continent
            // Uses LEFT JOIN to include countries even if they have no cities in the database
            String query =
                    "SELECT " +
                            "    country.Continent AS name, " +
                            "    SUM(country.Population) AS total_population, " +
                            "    COALESCE(SUM(city_pop.city_population), 0) AS city_population " +
                            "FROM country " +
                            "LEFT JOIN ( " +
                            "    SELECT CountryCode, SUM(Population) AS city_population " +
                            "    FROM city " +
                            "    GROUP BY CountryCode " +
                            ") AS city_pop ON country.Code = city_pop.CountryCode " +
                            "GROUP BY country.Continent " +
                            "ORDER BY total_population DESC";

            // Execute query
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            // Display header
            System.out.println("\n=== POPULATION DISTRIBUTION BY CONTINENT ===");
            System.out.println(String.format("%-30s %15s %15s %10s %15s %10s",
                    "Continent", "Total Pop", "City Pop", "City %", "Rural Pop", "Rural %"));
            System.out.println("--------------------------------------------------------------------------------------------");

            // Process and display each continent
            while (rset.next()) {
                String name = rset.getString("name");
                long totalPop = rset.getLong("total_population");
                long cityPop = rset.getLong("city_population");
                long ruralPop = totalPop - cityPop;

                // Calculate percentages
                double cityPercent = (totalPop > 0) ? (cityPop * 100.0 / totalPop) : 0;
                double ruralPercent = (totalPop > 0) ? (ruralPop * 100.0 / totalPop) : 0;

                // Display formatted results
                System.out.println(String.format("%-30s %,15d %,15d %9.2f%% %,15d %9.2f%%",
                        name, totalPop, cityPop, cityPercent, ruralPop, ruralPercent));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get population distribution by continent");
        }
    }

    /**
     * Shows population distribution (total, in cities, not in cities) for each region.
     */
    public void getPopulationDistributionByRegion() {
        try {
            // SQL query to calculate population distribution by region
            String query =
                    "SELECT " +
                            "    country.Region AS name, " +
                            "    SUM(country.Population) AS total_population, " +
                            "    COALESCE(SUM(city_pop.city_population), 0) AS city_population " +
                            "FROM country " +
                            "LEFT JOIN ( " +
                            "    SELECT CountryCode, SUM(Population) AS city_population " +
                            "    FROM city " +
                            "    GROUP BY CountryCode " +
                            ") AS city_pop ON country.Code = city_pop.CountryCode " +
                            "GROUP BY country.Region " +
                            "ORDER BY total_population DESC";

            // Execute query
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            // Display header
            System.out.println("\n=== POPULATION DISTRIBUTION BY REGION ===");
            System.out.println(String.format("%-30s %15s %15s %10s %15s %10s",
                    "Region", "Total Pop", "City Pop", "City %", "Rural Pop", "Rural %"));
            System.out.println("--------------------------------------------------------------------------------------------");

            // Process and display each region
            while (rset.next()) {
                String name = rset.getString("name");
                long totalPop = rset.getLong("total_population");
                long cityPop = rset.getLong("city_population");
                long ruralPop = totalPop - cityPop;

                // Calculate percentages
                double cityPercent = (totalPop > 0) ? (cityPop * 100.0 / totalPop) : 0;
                double ruralPercent = (totalPop > 0) ? (ruralPop * 100.0 / totalPop) : 0;

                // Display formatted results
                System.out.println(String.format("%-30s %,15d %,15d %9.2f%% %,15d %9.2f%%",
                        name, totalPop, cityPop, cityPercent, ruralPop, ruralPercent));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get population distribution by region");
        }
    }

    /**
     * Shows population distribution (total, in cities, not in cities) for each country.
     * Limited to top 20 countries for readability.
     */
    public void getPopulationDistributionByCountry() {
        try {
            // SQL query to calculate population distribution by country
            String query =
                    "SELECT " +
                            "    country.Name AS name, " +
                            "    country.Population AS total_population, " +
                            "    COALESCE(city_pop.city_population, 0) AS city_population " +
                            "FROM country " +
                            "LEFT JOIN ( " +
                            "    SELECT CountryCode, SUM(Population) AS city_population " +
                            "    FROM city " +
                            "    GROUP BY CountryCode " +
                            ") AS city_pop ON country.Code = city_pop.CountryCode " +
                            "ORDER BY total_population DESC " +
                            "LIMIT 20";  // Limited to top 20 for readability

            // Execute query
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            // Display header
            System.out.println("\n=== POPULATION DISTRIBUTION BY COUNTRY (Top 20) ===");
            System.out.println(String.format("%-30s %15s %15s %10s %15s %10s",
                    "Country", "Total Pop", "City Pop", "City %", "Rural Pop", "Rural %"));
            System.out.println("--------------------------------------------------------------------------------------------");

            // Process and display each country
            while (rset.next()) {
                String name = rset.getString("name");
                long totalPop = rset.getLong("total_population");
                long cityPop = rset.getLong("city_population");
                long ruralPop = totalPop - cityPop;

                // Calculate percentages
                double cityPercent = (totalPop > 0) ? (cityPop * 100.0 / totalPop) : 0;
                double ruralPercent = (totalPop > 0) ? (ruralPop * 100.0 / totalPop) : 0;

                // Display formatted results
                System.out.println(String.format("%-30s %,15d %,15d %9.2f%% %,15d %9.2f%%",
                        name, totalPop, cityPop, cityPercent, ruralPop, ruralPercent));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get population distribution by country");
        }
    }
}
