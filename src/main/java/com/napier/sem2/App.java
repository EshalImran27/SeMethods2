package com.napier.sem2;

import java.sql.*;

public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to WORLD database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected to World database!");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
                System.out.println("Database connection closed.");
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Get all countries in the world organised by largest population to smallest
     */
    public void getCountriesByPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT code, name, continent, region, population, capital " +
                            "FROM country " +
                            "ORDER BY population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Process and display results
            System.out.println("All countries in the world by population (largest to smallest):");
            System.out.println("=================================================================");
            System.out.println(String.format("%-3s %-30s %-15s %-20s %10s %s",
                    "Code", "Name", "Continent", "Region", "Population", "Capital"));
            System.out.println("-----------------------------------------------------------------");

            int count = 0;
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("code");
                country.name = rset.getString("name");
                country.continent = rset.getString("continent");
                country.region = rset.getString("region");
                country.population = rset.getInt("population");
                country.capital = rset.getString("capital");

                // Display this country
                displayCountry(country);
                count++;

                // Limit output for demonstration
                if (count >= 20) {
                    System.out.println("... and " + (getTotalCountries() - 20) + " more countries");
                    break;
                }
            }

            System.out.println("Total countries displayed: " + count);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
        }
    }

    /**
     * Display a country's information
     */
    public void displayCountry(Country country)
    {
        if (country != null)
        {
            System.out.println(
                    String.format("%-3s %-30s %-15s %-20s %,10d %s",
                            country.code,
                            country.name,
                            country.continent,
                            country.region,
                            country.population,
                            country.capital != null ? country.capital : "N/A")
            );
        }
    }

    /**
     * Helper method to get total number of countries
     */
    private int getTotalCountries()
    {
        try
        {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT COUNT(*) as total FROM country");
            if (rset.next()) {
                return rset.getInt("total");
            }
            return 0;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    /**
     * Main method - entry point for the application
     */
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get all countries by population
        a.getCountriesByPopulation();

        // Disconnect from database
        a.disconnect();
    }
}