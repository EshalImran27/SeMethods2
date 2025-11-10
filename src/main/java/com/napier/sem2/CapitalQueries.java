package com.napier.sem2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapitalQueries {
    private Connection con;
    public CapitalQueries(Connection con) {
        this.con = con;
    }
    //Capital Extractor
    private List<City> CapitalsFromResultSet(ResultSet rset) throws SQLException {
        List<City> listOfCapitals = new ArrayList<>();
        while(rset.next()){
            City capital = new City(
                    rset.getString("city_name"),
                    rset.getString("country_name"),
                    rset.getString("district_name"),
                    rset.getInt("population"));
            listOfCapitals.add(capital);
        }
        return listOfCapitals;
    }
    private void SqlQueryCapitals(String sql) throws SQLException{
        List<City> listOfCapital = new ArrayList<>();
        if(con==null){
            System.out.println("Database Connection is null");

        }
        try{
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(sql);
            listOfCapital=CapitalsFromResultSet(rset);
            rset.close();
            stmt.close();
        }
        catch(Exception e){
            System.out.println("SQL Exception: "+e.getMessage());
        }
        City.displayListOfCapital(listOfCapital);
    }
    //Capital Reports
    public void getReportCapitalGlobal() throws SQLException {
        String sqlStatement =
                "SELECT city.Name AS city_name, country.Name AS country_name,city.District AS district_name, city.Population AS population " +
                        "FROM city " +
                        "JOIN country ON city.ID = country.Capital " +
                        "ORDER BY city.Population DESC";
        System.out.println("All capitals in the world ranked from largest population to smallest: ");
        SqlQueryCapitals(sqlStatement);
    }
    public void getReportCapitalContinent(String continent) throws SQLException {
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name,city.District AS district_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Continent = '" + continent + "' "
                + "ORDER BY city.Population DESC";
        System.out.println("All Capitals in " + continent + " ranked from largest population to smallest: ");
        SqlQueryCapitals(sqlStatement);
    }
    public void getReportCapitalRegion(String region) throws SQLException {
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name,city.District AS district_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Region = '" + region + "' "
                + "ORDER BY city.Population DESC";
        System.out.println("All Capitals in the region: " + region + " ranked from largest population to smallest: ");
        SqlQueryCapitals(sqlStatement);
    }
    public void getReportTopCapitalGlobal(int n) throws SQLException {
        if (n <= 0) {
            System.out.println("No capitals can be displayed");
            return;
        }
        String sqlStatement =  "SELECT city.Name AS city_name, country.Name AS country_name,city.District AS district_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "ORDER BY city.Population DESC "
                + "LIMIT " + n;
        System.out.println("Top " + n + " Capitals in the world ranked from largest population to smallest: ");
        SqlQueryCapitals(sqlStatement);
    }
    public void getReportTopCapitalContinent(String continent, int n) throws SQLException {
        if (n <= 0 || continent==null || continent.isEmpty()) {
            System.out.println("No capitals can be displayed");
            return;
        }
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name,city.District AS district_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Continent = '" + continent + "' "
                + "ORDER BY city.Population DESC "
                + "LIMIT " + n;
        System.out.println("Top " + n + " Capitals in the continent " + continent + " ranked from largest population to smallest: ");
        SqlQueryCapitals(sqlStatement);
    }
    public void getReportTopCapitalRegion(String region, int n) throws SQLException {
        if (n <= 0 || region==null) {
            System.out.println("No capitals can be displayed");
            return;
        }
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name,city.District AS district_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Region = '" + region + "' "
                + "ORDER BY city.Population DESC "
                + "LIMIT " + n;
        System.out.println("Top " + n + " Capitals in the region " + region + " ranked from largest population to smallest: ");
        SqlQueryCapitals(sqlStatement);
    }
}
