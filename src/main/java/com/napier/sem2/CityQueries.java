package com.napier.sem2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityQueries {
    /** Connection object used to communicate with the database. */
    private final Connection con;

    /**
     * Constructs a new {@code CityQueries} instance.
     *
     * @param con A valid SQL {@link Connection} object.
     */
    public CityQueries(Connection con) {
        this.con = con;
    }

    // ============================================================
    //                  INTERNAL UTILITY METHODS
    // ============================================================

    /**
     * Converts a {@link ResultSet} into a list of {@link City} objects.
     *
     * @param rset The SQL {@link ResultSet} containing city data.
     * @return A list of {@link City} objects extracted from the result set.
     * @throws SQLException If a database access error occurs.
     */
    private List<City> CitiesFromResultSet(ResultSet rset) throws SQLException {
        List<City> listOfCities = new ArrayList<>();
        while(rset.next()){
            City city = new City(
                    rset.getString("city_name"),
                    rset.getString("country_name"),
                    rset.getString("city_district"),
                    rset.getInt("population"));
            listOfCities.add(city);
        }
        return listOfCities;
    }

    /**
     * Executes a SQL query for cities and prints the results to console.
     *
     * @param sql The SQL query string.
     * @throws SQLException If an SQL or connection error occurs.
     */
    private void SqlQuery(String sql) throws SQLException {
        List<City> listOfCities = new ArrayList<>();
        if(con==null){
            System.out.println("Database Connection is null");

        }else {
            try{
                Statement stmt = con.createStatement();
                ResultSet rset = stmt.executeQuery(sql);
                listOfCities=CitiesFromResultSet(rset);
                rset.close();
                stmt.close();
            }
            catch(Exception e){
                System.out.println("SQL Exception: "+e.getMessage());
            }
            City.displayListOfCity(listOfCities);
        }
    }

    /**
     * Executes a SQL query and returns a list of {@link City} objects.
     *
     * @param query The SQL query string.
     * @return A list of {@link City} results.
     * @throws SQLException If a database access error occurs.
     */
    private List<City> getCities(String query) throws SQLException {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
                String name = rs.getString("city_name");
                String country = rs.getString("country_name");
                String district = rs.getString("city_district");
                int population = rs.getInt("population");

                cities.add(new City(name, country, district, population));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    // ============================================================
    //                  CITY REPORT QUERIES
    // ============================================================

    // ---------------------- Global Reports ----------------------

    /**
     * Prints all cities in the world ranked by population.
     *
     * @throws SQLException If a database access error occurs.
     */
    public void getReportCityGlobal() throws SQLException {
        String sqlStatement =
                "SELECT city.Name AS city_name, country.Name AS country_name, city.District as city_district, " +
                        "city.Population AS population " +
                        "FROM city " +
                        "JOIN country ON city.CountryCode = country.Code " +
                        "ORDER BY city.Population DESC ";
        System.out.println("All cities in the world ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves all cities in the world ranked by population.
     *
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportCityGlobalList() throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, city.District as city_district, " +
                "city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC ";
        return getCities(query);
    }

    // ---------------------- Continent Reports ----------------------

    /**
     * Prints all cities in a given continent ranked by population.
     *
     * @param continent The name of the continent.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportCityContinent(String continent) throws SQLException {
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC ";
        System.out.println("All Cities in " + continent + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves all cities in a given continent ranked by population.
     *
     * @param continent The name of the continent.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportCityContinentList(String continent) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC ";
        return getCities(query);
    }

    // ---------------------- Region Reports ----------------------

    /**
     * Prints all cities in a given region ranked by population.
     *
     * @param region The name of the region.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportCityRegion(String region) throws SQLException {
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC ";
        System.out.println("All cities in the region: " + region + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves all cities in a given region ranked by population.
     *
     * @param region The name of the region.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportCityRegionList(String region) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC ";
        return getCities(query);
    }

    // ---------------------- Country Reports ----------------------

    /**
     * Prints all cities in a given country ranked by population.
     *
     * @param country The name of the country.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportCityCountry(String country) throws SQLException {
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "' " +
                "ORDER BY city.Population DESC ";
        System.out.println("All cities in the country: " + country + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves all cities in a given country ranked by population.
     *
     * @param country The name of the country.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportCityCountryList(String country) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, " +
        "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "' " +
                "ORDER BY city.Population DESC ";
        return getCities(query);
    }

    // ---------------------- District Reports ----------------------

    /**
     * Prints all cities in a given district ranked by population.
     *
     * @param district The name of the district.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportCityDistrict(String district) throws SQLException {
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + district + "' " +
                "ORDER BY city.Population DESC ";
        System.out.println("All cities in the district: " + district + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves all cities in a given district ranked by population.
     *
     * @param district The name of the district.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportCityDistrictList(String district) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + district + "' " +
                "ORDER BY city.Population DESC ";
        return getCities(query);
    }

    // ---------------------- Top Global ----------------------

    /**
     * Prints the top N cities in the world ranked by population.
     *
     * @param n The number of cities to display.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportTopCityGlobal(int n) throws SQLException {
        if (n <= 0) {
            System.out.println("No cities can be displayed");
            return;
        }
        String sqlStatement =  "SELECT city.Name AS city_name, country.Name AS country_name, city.District as city_district, " +
                "city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n + " ";
        System.out.println("Top " + n + " Cities in the world ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves the top N cities in the world ranked by population.
     *
     * @param n Number of cities to retrieve.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportTopCityGlobalList(int n) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, city.District as city_district, " +
                "city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n + " ";
        return getCities(query);
    }

    // ---------------------- Top by Continent ----------------------

    /**
     * Prints the top N cities in a given continent ranked by population.
     *
     * @param continent The continent name.
     * @param n         The number of cities to display.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportTopCityContinent(String continent, int n) throws SQLException {
        if (n <= 0 || continent==null || continent.isEmpty()) {
            System.out.println("No cities can be displayed");
            return;
        }
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n + " ";
        System.out.println("Top " + n + " Cities in the continent " + continent + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves the top N cities in a given continent ranked by population.
     *
     * @param continent The continent name.
     * @param n         The number of cities to retrieve.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportTopCityContinentList(String continent, int n) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n + " ";
        return getCities(query);
    }

    // ---------------------- Top by Region ----------------------

    /**
     * Prints the top N cities in a given region ranked by population.
     *
     * @param region The region name.
     * @param n      The number of cities to display.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportTopCityRegion(String region, int n) throws SQLException {
        if (n <= 0 || region==null) {
            System.out.println("No cities can be displayed");
            return;
        }
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n + " ";
        System.out.println("Top " + n + " Cities in the region " + region + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves the top N cities in a given region ranked by population.
     *
     * @param region The region name.
     * @param n      The number of cities to retrieve.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportTopCityRegionList(String region, int n) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n + " ";
        return getCities(query);
    }

    // ---------------------- Top by Country ----------------------

    /**
     * Prints the top N cities in a given country ranked by population.
     *
     * @param country The country name.
     * @param n      The number of cities to display.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportTopCityCountry(String country, int n) throws SQLException {
        if (n <= 0 || country==null) {
            System.out.println("No cities can be displayed");
            return;
        }
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n + " ";
        System.out.println("Top " + n + " Cities in the country " + country + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves the top N cities in a given country ranked by population.
     *
     * @param country The country name.
     * @param n      The number of cities to retrieve.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportTopCityCountryList(String country, int n) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n + " ";
        return getCities(query);
    }

    // ---------------------- Top by District ----------------------

    /**
     * Prints the top N cities in a given district ranked by population.
     *
     * @param district The district name.
     * @param n      The number of cities to display.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportTopCityDistrict(String district, int n) throws SQLException {
        if (n <= 0 || district==null) {
            System.out.println("No cities can be displayed");
            return;
        }
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + district + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n + " ";
        System.out.println("Top " + n + " Cities in the district " + district + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves the top N cities in a given district ranked by population.
     *
     * @param district The district name.
     * @param n      The number of cities to retrieve.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportTopCityDistrictList(String district, int n) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, " +
                "city.District as city_district, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + district + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n + " ";
        return getCities(query);
    }

    // ============================================================
    //                  REPORT OUTPUT METHODS
    // ============================================================

    /**
     * Outputs a list of cities to a Markdown (.md) report file.
     *
     * @param listOfCities A list of {@link City} objects to include in the report.
     * @param filename The output filename (without extension).
     */
    public void outputCityReport(List<City> listOfCities, String filename) {
        // Check cities is not null
        if (listOfCities == null) {
            System.out.println("No cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name | Country | District | Population |\r\n");
        // Loop over all listOfCities in the list
        for (City city : listOfCities) {
            if (city == null) continue;
            sb.append("| " + city.getName() + " | " + city.getCountry() + " | "  + city.getDistrict() + " | "  +
                    city.getPopulation() + " |\r\n");
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
