package com.napier.sem2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PopulationQueries {
    App  app;
    public PopulationQueries(App app) {
        this.app = app;
    }
  private Connection con;
    public PopulationQueries(Connection con) {
        this.con = con;
        }

    public long getWorldPopulation() {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country";
            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                System.out.println("\n=== WORLD POPULATION ===");
                System.out.println("Total Population: " + String.format("%,d", totalPop));
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return 0;
        }
    }
    public long getContinentPopulation(String continent) throws Exception{
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country  "+"WHERE continent = '" + continent + "' ";
            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                System.out.println("\n=== CONTINENT POPULATION ===");
                System.out.println("Total Population: " + String.format("%,d", totalPop) + "Continent: " + continent);
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get continent population");
            return 0;
        }
    }
    public long getRegionPopulation(String region) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country "+"WHERE region = '" + region + "' ";
            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                System.out.println("\n=== WORLD POPULATION ===");
                System.out.println("Total Population: " + String.format("%,d", totalPop));
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return 0;
        }
    }
    public long getCountryPopulation(String country) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country "+"WHERE region = '" + country + "' ";
            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                System.out.println("\n=== WORLD POPULATION ===");
                System.out.println("Total Population: " + String.format("%,d", totalPop));
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return 0;
        }
    }
    public long getDistrictPopulation(String district) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country "+"WHERE region = '" + district + "' ";
            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                System.out.println("\n=== WORLD POPULATION ===");
                System.out.println("Total Population: " + String.format("%,d", totalPop));
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return 0;
        }
    }
    public long getCityPopulation(String city) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT SUM(Population) AS TotalPopulation FROM country "+"WHERE region = '" + city + "' ";
            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                long totalPop = rset.getLong("TotalPopulation");
                System.out.println("\n=== WORLD POPULATION ===");
                System.out.println("Total Population: " + String.format("%,d", totalPop));
                return totalPop;
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return 0;
        }
    }
/*public class CountryQueries {


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

}
