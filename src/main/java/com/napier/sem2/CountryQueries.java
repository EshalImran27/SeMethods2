package com.napier.sem2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryQueries {
    private Connection con;
    public CountryQueries(Connection con) {
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






}
