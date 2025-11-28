// Package declaration
package com.napier.sem2;

// Import SQL and utility libraries
import java.sql.*;
import java.util.List;

/**
 * The {@code App} class serves as the main entry point for the World Population Reporting application.
 * <p>
 * It manages:
 * <ul>
 *     <li>Establishing and closing a connection to a MySQL database</li>
 *     <li>Executing population and capital city queries through external classes</li>
 *     <li>Generating and outputting reports on countries and capitals</li>
 * </ul>
 * </p>
 */
public class App
{
    /** Connection object for interfacing with the MySQL database. */
    public Connection con = null;

    /**
     * Establishes a connection to the MySQL database.
     *
     * @param location The host and port of the database (e.g. "localhost:33060").
     * @param delay    Delay in milliseconds before attempting connection (to allow Docker or container startup time).
     */
    public void connect(String location, int delay){
        try{
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            // Handle case where JDBC driver is not found
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }
        // Maximum number of connection attempts
        int retries = 3;

        // Attempt to connect multiple times in case the database is not yet ready
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try{
                // Wait before attempting connection (useful for slow-starting containers)
                Thread.sleep(delay);

                // Establish connection to the 'world' database with provided credentials
                con = DriverManager.getConnection(
                        "jdbc:mysql://"+ location + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root",
                        "example"
                );

                System.out.println("Successfully connected to World database!");
                break;
            }
            catch (SQLException sql){
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sql.getMessage());
            }
            catch (InterruptedException ie){
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Closes the connection to the MySQL database, if active.
     */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Database connection closed.");
            }
            catch (Exception e){
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * The main entry point for the application.
     */
    public static void main(String[] args) throws SQLException {

        // Create application instance
        App WorldReport = new App();
        List <City> report;

        // Connect to the database
        if(args.length < 1){
            WorldReport.connect("localhost:33060", 30000);
        }else{
            WorldReport.connect(args[0], Integer.parseInt(args[1]));
        }

        System.out.println("Welcome to World Report!");

        // ===== Country Queries =====
        CountryQueries countries = new CountryQueries(WorldReport.con);
        countries.getCountriesByPopulationInWorld();
        countries.getCountriesByPopulationInContinent("Europe");
        countries.getCountriesByPopulationInRegion("Caribbean");
        countries.getTopCountriesInWorld(10);
        countries.getTopCountriesInContinent("Africa",7);
        countries.getTopCountriesInRegion("North America",6);

        // ===== Capital Queries =====
        CapitalQueries queryCapital = new CapitalQueries(WorldReport.con);

        queryCapital.getReportCapitalGlobal();
        queryCapital.getReportCapitalContinent("Asia");
        queryCapital.getReportCapitalRegion("Caribbean");
        queryCapital.getReportTopCapitalGlobal(5);
        queryCapital.getReportTopCapitalContinent("Asia",5);
        queryCapital.getReportTopCapitalRegion("Caribbean",5);

        // ===== Capital Report Output =====
        report = queryCapital.getReportCapitalGlobalList();
        queryCapital.outputCapitalReport(report, "Capital Global Report");

        report = queryCapital.getReportCapitalContinentList("Asia");
        queryCapital.outputCapitalReport(report, "Capital Continent Report");

        report = queryCapital.getReportCapitalRegionList("Caribbean");
        queryCapital.outputCapitalReport(report, "Capital Region Report");

        report = queryCapital.getReportTopCapitalGlobalList(5);
        queryCapital.outputCapitalReport(report, "Capital Top Report");

        report = queryCapital.getReportTopCapitalContinentList("Asia", 5);
        queryCapital.outputCapitalReport(report, "Capital Top Continent Report");

        report = queryCapital.getReportTopCapitalRegionList("Caribbean", 5);
        queryCapital.outputCapitalReport(report, "Capital Top Region Report");

        // ===== City Queries =====
        CityQueries queryCity = new CityQueries(WorldReport.con);

        queryCity.getReportCityGlobal();
        queryCity.getReportCityContinent("Asia");
        queryCity.getReportCityRegion("Caribbean");
        queryCity.getReportCityCountry("Spain");
        queryCity.getReportCityDistrict("Madrid");
        queryCity.getReportTopCityGlobal(5);
        queryCity.getReportTopCityContinent("Asia",5);
        queryCity.getReportTopCityRegion("Caribbean",5);
        queryCity.getReportTopCityCountry("Spain", 5);
        queryCity.getReportTopCityDistrict("Madrid", 5);

        // ===== City Report Output =====
        report = queryCity.getReportCityGlobalList();
        queryCity.outputCityReport(report, "City Global Report");

        report = queryCity.getReportCityContinentList("Asia");
        queryCity.outputCityReport(report, "City Continent Report");

        report = queryCity.getReportCityRegionList("Caribbean");
        queryCity.outputCityReport(report, "City Region Report");

        report = queryCity.getReportCityCountryList("Spain");
        queryCity.outputCityReport(report, "City Country Report");

        report = queryCity.getReportCityDistrictList("Madrid");
        queryCity.outputCityReport(report, "City District Report");

        report = queryCity.getReportTopCityGlobalList(5);
        queryCity.outputCityReport(report, "City Top Report");

        report = queryCity.getReportTopCityContinentList("Asia", 5);
        queryCity.outputCityReport(report, "City Top Continent Report");

        report = queryCity.getReportTopCityRegionList("Caribbean", 5);
        queryCity.outputCityReport(report, "City Top Region Report");

        report = queryCity.getReportTopCityCountryList("Spain", 5);
        queryCity.outputCityReport(report, "City Country Report");

        report = queryCity.getReportTopCityDistrictList("Madrid", 5);
        queryCity.outputCityReport(report, "City District Report");

        // ===== Population Queries =====
        System.out.println("\n========== POPULATION QUERIES ==========\n");

        PopulationQueries popQueries = new PopulationQueries(WorldReport.con);

// Basic population queries
        popQueries.getWorldPopulation();

        try {
            popQueries.getContinentPopulation("Asia");
            popQueries.getContinentPopulation("Europe");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        popQueries.getRegionPopulation("Caribbean");
        popQueries.getRegionPopulation("Western Europe");

// Capital city population queries
        System.out.println("\n========== CAPITAL CITY POPULATION QUERIES ==========\n");
        popQueries.getAllCapitalsInWorld();
        popQueries.getAllCapitalsInContinent("Europe");
        popQueries.getAllCapitalsInRegion("Caribbean");
        popQueries.getTopCapitalsInWorld(10);
        popQueries.getTopCapitalsInContinent("Asia", 5);
        popQueries.getTopCapitalsInRegion("Western Europe", 3);

// Population distribution queries
        System.out.println("\n========== POPULATION DISTRIBUTION QUERIES ==========\n");
        popQueries.getPopulationDistributionByContinent();
        popQueries.getPopulationDistributionByRegion();
        popQueries.getPopulationDistributionByCountry();

        System.out.println("\n========== ALL QUERIES COMPLETED ==========\n");
// Disconnect from database
        WorldReport.disconnect();}
}

