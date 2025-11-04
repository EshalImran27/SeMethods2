//including package in the class
package com.napier.sem2;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

//adding attributes into the country class
public class Country {
    public String code; //separate code for each country
    public String name; //specific name of a country
    public String continent; //continent in which the country is present
    public String region; //region of the country
    public String capital; //capital city of the specific country
    public int population; //total population of that country

    //adding constructors for the class
    public Country() {}
    public Country(String code, String name, String continent, String region,
                    String capital, int population){
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.capital = capital;
        this.population = population;
    }
    //public method to retrieve the population of the countries in the whole world
    public static List<Country> getCountriesByPopulationInWorld(Connection connection){
        List<Country> countries = new ArrayList<>();
        //database connection check
        if (connection == null){
            System.out.println("Database Connection is null");
            return countries;
        }
        try{
            Statement stmt= connection.createStatement();
            String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                    "FROM country " +
                    "ORDER BY population DESC";
            ResultSet rset=stmt.executeQuery(sqlStatement);//execution of sql query
            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("code");
                country.name = rset.getString("name");
                country.continent = rset.getString("continent");
                country.region = rset.getString("region");
                country.population = rset.getInt("population");
                country.capital = rset.getString("capital");
                countries.add(country);
            }
            rset.close();
            stmt.close();
        } catch(SQLException e){
            System.out.println("Error getting countries by population in world" +
                    e.getMessage());
            System.out.println("failed to get country details");
        }
        return countries;

    }
    //public method to get country population in continents
    public static List<Country> getCountriesByPopulationInContinent(Connection connection, String continent){
        List<Country> countries = new ArrayList<>();
        if (connection == null){
            System.out.println("Database Connection is null");
            return countries;
        }
        try{
            String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                    "FROM country " +
                    "WHERE continent = ? " +
                    "ORDER BY population DESC";
            PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
            prepStmt.setString(1, continent);

            ResultSet rset= prepStmt.executeQuery();
            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("code");
                country.name = rset.getString("name");
                country.continent = rset.getString("continent");
                country.region = rset.getString("region");
                country.population = rset.getInt("population");
                country.capital = rset.getString("capital");

                countries.add(country);

            }
            rset.close();
        } catch(SQLException e){
            System.out.println("Error getting countries by population in continent" +
                    e.getMessage());
            System.out.println("failed to get country details");
        }
        return countries;
    }
    //public method to get the count of total countries in the world
    public static int getTotalCountries(Connection connection){
        if (connection == null){
            System.out.println("Database Connection is null");
            return 0;
        }
        try{
            Statement stmt= connection.createStatement();
            String sqlStatement = "SELECT count(*) AS TOTAL"+
                    "FROM country";
            ResultSet rset=stmt.executeQuery(sqlStatement);
            if(rset.next()){
                int total=rset.getInt("TOTAL");
                rset.close();
                stmt.close();
                return total;
            }
            return 0;
        } catch(Exception e){
            System.out.println("Error getting total countries" +
                    e.getMessage());
            return 0;
        }
    }
    //public method to display each country detail separately
    public  void display(){
        System.out.println(
                String.format("%-3s %-30s %-15s %-20s %10d %s",
                        this.code != null ? this.code : "N/A",
                        this.name != null ? this.name : "N/A",
                        this.continent != null ? this.continent : "N/A",
                        this.region != null ? this.region : "N/A",
                        this.population,
                        this.capital != null ? this.capital : "N/A"));
    }
    //public method to display the entire list of country population
    public static void displayCountries(List<Country> countries) {
        if (countries == null || countries.isEmpty()) {
            System.out.println("No countries can be displayed");
            return;
        }
        System.out.println("=================================================================");
        System.out.println(String.format("%-3s %-30s %-15s %-20s %10s %s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        System.out.println("-----------------------------------------------------------------");
        int count = 0;
        //int DisplayCount = 0;
        int total = countries.size();

        for (Country country : countries) {
            if (country == null) {
                System.out.println("Warning: country is null");
                count++;
                continue;
            }
            country.display();
            count++;
           // DisplayCount++;
            // Limit output if specified
    /*        if (limit > 0 && DisplayCount >= limit) {
                System.out.println("... and " + (total - limit) + " more countries");
                break;
            }
        }
        System.out.println("Total countries displayed: " + DisplayCount);
     */
        }
    }
}
