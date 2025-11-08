package com.napier.sem2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query {
    private Connection con;
    public Query(Connection con) {
        this.con = con;
    }
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
    private void SqlQuery(String sql) throws SQLException{
        List<Country> countries = new ArrayList<>();
        if(con==null){
            System.out.println("Database Connection is null");

        }
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
    public void getCountriesByPopulationInWorld() throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "ORDER BY population DESC";
        System.out.println("All Countries in the world ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }
    public void getCountriesByPopulationInContinent(String continent) throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "WHERE continent = '" + continent + "'" +
                " ORDER BY population DESC";
        System.out.println("All Countries in " + continent + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }
    public void getCountriesByPopulationInRegion(String region) throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "WHERE region = '" + region + "'" +
                "ORDER BY population DESC";
        System.out.println("All Countries in the region: " + region+ " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);

    }
    public void getTopCountriesInWorld(int n) throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "ORDER BY population DESC " +
                "LIMIT " + n;
        System.out.println("Top " + n + " Countries in the world ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }
    public void getTopCountriesInContinent(String continent, int n) throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "WHERE continent = '" + continent + "' " +
                "ORDER BY population DESC " +
                "LIMIT " + n;
        System.out.println("Top " + n + " Countries in the continent " + continent + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }
    public void getTopCountriesInRegion(String region, int n) throws SQLException {
        String sqlStatement = "SELECT code, name, continent, region, capital, population " +
                "FROM country " +
                "WHERE region = '" + region + "' " +
                "ORDER BY population DESC " +
                "LIMIT " + n;
        System.out.println("Top " + n + " Countries in the region " + region + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }
    //Capital Queries
    /*public ArrayList<City> getReportCapitalGlobal()
    {
        ArrayList<City> report = new ArrayList<City>();
        //database connection check
        if (database == null){
            System.out.println("Database Connection is null");
            return report;
        }
        try{
            Statement stmt= database.createStatement();
            String sqlStatement = "SELECT city.Name, country.Name, city.Population FROM city "
                    + "JOIN country ON city.CountryCode = country.Code "
                    + "ORDER BY city.Population DESC";
            ResultSet rset=stmt.executeQuery(sqlStatement);//execution of sql query
            while(rset.next()){
                City aux = new City();
                aux.setName(rset.getString("city.Name"));
                aux.setCountry(rset.getString("country.Name"));
                aux.setPopulation(rset.getInt("city.Population"));
                report.add(aux);
            }
            rset.close();
            stmt.close();
        } catch(SQLException e){
            System.out.println("Error getting capitals by population in world" +
                    e.getMessage());
            System.out.println("failed to get capitals details");
        }
        return report;
    }*/
    public void getReportCapitalGlobal() throws SQLException {
        String sqlStatement =
                "SELECT city.Name, country.Name, city.Population FROM city "
                + "JOIN country ON city.CountryCode = country.Code "
                + "ORDER BY city.Population DESC";
        System.out.println("All capitals in the world ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    public ArrayList<City> reportCapitalContinent(String continent)
    {
        ArrayList<City> report = new ArrayList<City>();
        return report;
    }

    public ArrayList<City> reportCapitalRegion(String region)
    {
        ArrayList<City> report = new ArrayList<City>();
        return report;
    }
    public ArrayList<City> topReportCapitalGlobal(int top)
    {
        ArrayList<City> report = new ArrayList<City>();
        return report;
    }

    public ArrayList<City> topReportCapitalContinent(int top, String continent)
    {
        ArrayList<City> report = new ArrayList<City>();
        return report;
    }

    public ArrayList<City> topReportCapitalRegion(int top, String region)
    {
        ArrayList<City> report = new ArrayList<City>();
        return report;
    }
}
