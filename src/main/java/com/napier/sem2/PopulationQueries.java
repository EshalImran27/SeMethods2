package com.napier.sem2;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    public long getContinentPopulation(String continent){
        try {
            //create an SQL statement
            Statement stmt = con.createStatement();
            //define SQL query with continent filter
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country WHERE continent = '" + continent + "' ";
            //execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            //check if result exists and extract the continent population
            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
            //display the results in a formatted manner
                System.out.println("\n=== CONTINENT POPULATION ===");
                System.out.println("Continent: " + continent);
                System.out.println("Total Population of " + continent + ": " + String.format("%,d", totalPop));
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
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country WHERE Region = '" + region + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the region population
            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== REGION POPULATION ===");
                System.out.println("Region: " + region);
                System.out.println("Total Population of "+ region + ": " + String.format("%,d", totalPop));
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
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country WHERE Name = '" + country + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the country population

            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== COUNTRY POPULATION ===");
                System.out.println("Total Population of " + country + ": " + String.format("%,d", totalPop));
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
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM city WHERE District = '" + district + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the district populatio
            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== DISTRICT POPULATION ===");
                System.out.println("Total Population of " + district + ": " + String.format("%,d", totalPop));
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
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM city WHERE Name = '" + city + "' ";
            // Execute the query and get the result set
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result exists and extract the city population
            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                // Display the results in a formatted manner
                System.out.println("\n=== CITY POPULATION ===");
                System.out.println("Total Population of " + city + ": " + String.format("%,d", totalPop));
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
    public List<Population> getPopulationDistributionByContinent() {
        List<Population> populationList = new ArrayList<>();
        try {
            // SQL query to calculate population distribution by continent
            // Uses LEFT JOIN to include countries even if they have no cities in the database
            String query =
                    "SELECT " +
                            "    country.Continent AS name, " +
                            "    SUM(country.Population) AS total_population, " +
                            "    SUM(city_pop.city_population) AS city_population " +
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
            System.out.println(String.format("%-13s %15s %15s %10s %15s %10s",
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

                populationList.add(new Population(name,totalPop,cityPop, cityPercent, ruralPop, ruralPercent));
                // Display formatted results
                System.out.println(String.format("%-13s %,15d %,15d %9.2f%% %,15d %9.2f%%",
                        name, totalPop, cityPop, cityPercent, ruralPop, ruralPercent));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get population distribution by continent");
        }
        return populationList;
    }

    /**
     * Shows population distribution (total, in cities, not in cities) for each region.
     */
    public List<Population> getPopulationDistributionByRegion() {
        List<Population> populationList = new ArrayList<>();
        try {
            // SQL query to calculate population distribution by region
            String query =
                    "SELECT " +
                            "    country.Region AS name, " +
                            "    SUM(country.Population) AS total_population, " +
                            "    SUM(city_pop.city_population) AS city_population " +
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

                populationList.add(new Population(name,totalPop,cityPop, cityPercent, ruralPop, ruralPercent));
                // Display formatted results
                System.out.println(String.format("%-30s %,15d %,15d %9.2f%% %,15d %9.2f%%",
                        name, totalPop, cityPop, cityPercent, ruralPop, ruralPercent));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get population distribution by region");
        }
        return populationList;
    }

    /**
     * Shows population distribution (total, in cities, not in cities) for each country.
     * Limited to top 20 countries for readability.
     */
    public  List<Population> getPopulationDistributionByCountry() {
        List<Population> populationList = new ArrayList<>();
        try {
            // SQL query to calculate population distribution by country
            String query =
                    "SELECT " +
                            "    country.Name AS name, " +
                            "    country.Population AS total_population, " +
                            "    city_pop.city_population AS city_population " +
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

                populationList.add(new Population(name,totalPop,cityPop, cityPercent, ruralPop, ruralPercent));
                // Display formatted results
                System.out.println(String.format("%-30s %,15d %,15d %9.2f%% %,15d %9.2f%%",
                        name, totalPop, cityPop, cityPercent, ruralPop, ruralPercent));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("Failed to get population distribution by country");
        }
        return populationList;
    }

    // ============================================================
    //                  REPORT OUTPUT METHODS
    // ============================================================

    public void outputPopulationGlobalReport(long population, String filename) {
        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("Total Population of the World: " + population + "\r\n");
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputPopulationContinentReport(long population, String continent, String filename) {
        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("Total Population of the Continent of " + continent + ": " + population + "\r\n");
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputPopulationRegionReport(long population, String region, String filename) {
        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("Total Population of the Region of " + region + ": " + population + "\r\n");
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputPopulationCountryReport(long population, String country, String filename) {
        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("Total Population of the Country of " + country + ": " + population + "\r\n");
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputPopulationDistrictReport(long population, String district, String filename) {
        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("Total Population of the District of " + district + ": " + population + "\r\n");
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputPopulationCityReport(long population, String city, String filename) {
        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("Total Population of the City of " + city + ": " + population + "\r\n");
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputPopulationDistributionReport(List<Population> populationList, String filename) {
        // Check capitals is not null
        if (populationList == null) {
            System.out.println("No population distribution found");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name | Total Pop | City Pop | City Perc | Rural Pop | Rural Perc |\n");
        // Loop over all capitals in the list
        for (Population population : populationList) {
            if (population == null) continue;
            sb.append("| " + population.getName() + " | " + population.getTotalPop() + " | " + population.getCityPop()
                    + " | " +  Math.round(population.getCityPercentage() * 100.0) / 100.0 + "% | " + population.getRuralPop() +
                    " | " + Math.round(population.getRuralPercentage() * 100.0) / 100.0 + "% |\r\n");

        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
