//include the package name
package com.napier.sem2;
//import all the sql libraries so as to read the sql data base and perform other functions
import java.sql.*;

import java.util.List;

//The class App that contains our entire population information needed by organiser
public class App
{
    //Connection to MySQL database
    private Connection con = null;
    //Connect to the MySQL database.
    public void connect(){
        try{
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            //throw an exception if driver noot found
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }
        // total 10 connection attempts are allowed
        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try{
                // Wait a bit for db to start because mysql container takes time to initialize
                Thread.sleep(30000);
                // Connect to WORLD database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected to World database!");
                break;
            }
            //if connection failed print which attempt is this
            catch (SQLException sqle){
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            //exception for interruption in the thread
            catch (InterruptedException ie){
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    //Disconnect from the MySQL database.
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                System.out.println("Database connection closed.");
            }
            //exception if there is a problem in closing the database
            catch (Exception e){
                System.out.println("Error closing connection to database");
            }
        }
    }
    //method to the function to get the population in countries in the whole world
    public void getCountriesByWorldPop(){
        List<Country> countries = Country.getCountriesByPopulationInWorld(con);
        System.out.println("All countries in the world by population (largest to smallest):");
        Country.displayCountries(countries, 20);
    }
    //method to the function to get the population in countries in a continent
    public void getCountriesByContinentPop(){
        List<Country> countries = Country.getCountriesByPopulationInContinent(con, "Europe");
        System.out.println("All countries in a continent by population (largest to smallest):");
        Country.displayCountries(countries, 20);
    }

    //Main method - entry point for the application
    public static void main(String[] args) {

        App WorldReport = new App(); // Create new Application
        WorldReport.connect();// Connect to database
        System.out.println("Welcome to World Report!");
        WorldReport.getCountriesByWorldPop();
        WorldReport.getCountriesByContinentPop();
       // a.getCountriesByPopulation();
        // Disconnect from database
        WorldReport.disconnect();
    }
}