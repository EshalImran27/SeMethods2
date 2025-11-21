package com.napier.sem2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryQueries {
    /** Connection object used to communicate with the database. */
    private final Connection con;
    /**
     * Constructs a new {@code CountryQueries} instance.
     *
     * @param con A valid SQL {@link Connection} object.
     */
    public CountryQueries(Connection con) {
        this.con = con;
    }
    /**
     * Converts a {@link ResultSet} into a list of {@link Country} objects.
     *
     * @param rset The SQL {@link ResultSet} containing country data.
     * @return A list of {@link Country} objects extracted from the result set.
     * @throws SQLException If a database access error occurs.
     */
    private List<Country> CountriesFromResultSet(ResultSet rset) throws SQLException {
        List<Country> countries = new ArrayList<>();
        while(rset.next()){
            Country country = new Country(
            rset.getString("code"),
            rset.getString("name"),
            rset.getString("continent"),
            rset.getString("region"),
            rset.getInt("capital"),
            rset.getInt("population"));
            countries.add(country);
        }
        return countries;
    }
    /**
     * Executes a SQL query for countries and prints the results to console.
     *
     * @param sql The SQL query string.
     * @throws SQLException If an SQL or connection error occurs.
     */
    private List<Country>  SqlQuery(String sql) throws SQLException {
        List<Country> countries = new ArrayList<>();
        if(con==null){
            System.out.println("Database Connection is null");

        }else{
            try{
                Statement stmt = con.createStatement();
                ResultSet rset = stmt.executeQuery(sql);
                countries=CountriesFromResultSet(rset);
                rset.close();
                stmt.close();
            }
            catch(Exception e){
                System.out.println("SQL Exception: "+e.getMessage());
            }
            Country.displayCountries(countries);
        }
        return countries;
    }
    /**
     * Prints all countries in the world ranked by population.
     *
     * @throws SQLException If a database access error occurs.
     */
    public List<Country>  getCountriesByPopulationInWorld() throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "ORDER BY population DESC";
        System.out.println("All Countries in the world ranked from largest population to smallest: ");
        return SqlQuery(sqlStatement);
    }
    /**
     * Prints all countries in a specific continent ranked by population.
     *
     * @throws SQLException If a database access error occurs.
     */
    public List<Country>  getCountriesByPopulationInContinent(String continent) throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "WHERE continent = '" + continent + "'" +
                " ORDER BY population DESC";
        System.out.println("All Countries in " + continent + " ranked from largest population to smallest: ");
        return SqlQuery(sqlStatement);
    }
    /**
     * Prints all countries in a specific region ranked by population.
     *
     * @throws SQLException If a database access error occurs.
     */
    public List<Country>  getCountriesByPopulationInRegion(String region) throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "WHERE region = '" + region + "'" +
                "ORDER BY population DESC";
        System.out.println("All Countries in the region: " + region+ " ranked from largest population to smallest: ");
        return SqlQuery(sqlStatement);

    }
    /**
     * Prints the top N countries in the world ranked by population.
     *
     * @param n The number of countries to display.
     * @throws SQLException If a database access error occurs.
     */
    public List<Country>  getTopCountriesInWorld(int n) throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "ORDER BY population DESC " +
                "LIMIT " + n;
        System.out.println("Top " + n + " Countries in the world ranked from largest population to smallest: ");
        return SqlQuery(sqlStatement);
    }
    /**
     * Prints the top N countries in a continent ranked by population.
     *
     * @param n The number of countries to display.
     * @throws SQLException If a database access error occurs.
     */
    public List<Country>  getTopCountriesInContinent(String continent, int n) throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "WHERE continent = '" + continent + "' " +
                "ORDER BY population DESC " +
                "LIMIT " + n;
        System.out.println("Top " + n + " Countries in the continent " + continent + " ranked from largest population to smallest: ");
        return SqlQuery(sqlStatement);
    }
    /**
     * Prints the top N countries in a region ranked by population.
     *
     * @param n The number of countries to display.
     * @throws SQLException If a database access error occurs.
     */
    public List<Country>  getTopCountriesInRegion(String region, int n) throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "WHERE region = '" + region + "' " +
                "ORDER BY population DESC " +
                "LIMIT " + n;
        System.out.println("Top " + n + " Countries in the region " + region + " ranked from largest population to smallest: ");
        return SqlQuery(sqlStatement);
    }

    // ============================================================
    //                  REPORT OUTPUT METHODS
    // ============================================================

    /**
     * Outputs a list of countries to a Markdown (.md) report file.
     *
     * @param listOfCountries A list of {@link Country} objects to include in the report.
     * @param filename The output filename (without extension).
     */
    public void outputCountryReport(List<Country> listOfCountries, String filename) {
        // Check cities is not null
        if (listOfCountries == null) {
            System.out.println("No countries to be displayed");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Code | Name | Continent | Region | Population | Capital |\r\n");
        // Loop over all listOfCities in the list
        for (Country country : listOfCountries) {
            if (country == null) continue;
            sb.append("| " + country.getCode() +" | " + country.getName() + " | " + country.getContinent() + " | "  + country.getRegion() + " | "  +
                    country.getPopulation() +" | " + country.getCapital() + " |\r\n");
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
