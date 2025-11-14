// Package declaration
package com.napier.sem2;

// Import SQL and utility libraries
import java.sql.*;
import java.util.List;

/**
 * Represents a City or Capital entity in the world database.
 * This class stores information such as name, country, district, and population.
 * It also provides utility methods for displaying city and capital data.
 */
public class City {
    // ==============================
    // Fields
    // ==============================

    /** The name of the city. */
    private String name = "";

    /** The country where the city is located. */
    private String country = "";

    /** The district or region within the country. */
    private String district = "";

    /** The population of the city. */
    private int population = 0;

    // ==============================
    // Constructors
    // ==============================

    /**
     * Default constructor.
     * Initializes a city object with default values.
     */
    public City() {}

    /**
     * Constructs a city with full attributes.
     *
     * @param name       The name of the city.
     * @param country    The country where the city is located.
     * @param district   The district or region within the country.
     * @param population The population of the city.
     */
    public City(String name, String country, String district, int population)
    {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    /**
     * Constructs a city object that represents a capital (no district information).
     *
     * @param name       The name of the capital city.
     * @param country    The country where the capital is located.
     * @param population The population of the capital city.
     */
    public City(String name, String country, int population)
    {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    // ==============================
    // Getters and Setters
    // ==============================

    /** @return The name of the city. */
    public String getName() {return name;}

    /** @param name The name of the city to set. */
    public void setName(String name) {this.name = name;}

    /** @return The country where the city is located. */
    public String getCountry() {return country;}

    /** @param country The country to set for the city. */
    public void setCountry(String country) {this.country = country;}

    /** @return The district or region of the city. */
    public String getDistrict() {return district;}

    /** @param district The district to set for the city. */
    public void setDistrict(String district) {this.district = district;}

    /** @return The population of the city. */
    public int getPopulation() {return population;}

    /** @param population The population value to set for the city. */
    public void setPopulation(int population) {this.population = population;}

    // ==============================
    // Display Methods
    // ==============================

    /**
     * Displays the details of a city in a formatted table row.
     * Handles potential null values gracefully.
     */
    public void displayCity(){
        System.out.printf(
                "%-30s %-30s %-30s %10d%n",
                this.name != null ? this.name : "N/A",
                this.country != null ? this.country : "N/A",
                this.district != null ? this.district : "N/A",
                this.population
        );
    }

    /**
     * Displays a formatted list of cities.
     * Includes column headers and error handling for null or empty lists.
     *
     * @param listOfCity The list of cities to display.
     */
    public static void displayListOfCity(List<City> listOfCity)
    {
        if (listOfCity == null || listOfCity.isEmpty()) {
            System.out.println("No cities can be displayed");
            return;
        }
        System.out.println("=================================================================");
        System.out.printf("%-30s %-30s %-30s %10s%n",
                "Name", "Country", "District", "Population");
        System.out.println("-----------------------------------------------------------------");
        for (City city : listOfCity) {
            if (city == null) {
                System.out.println("Warning: city is null");
                continue;
            }
            city.displayCity();
        }
    }

    /**
     * Displays the details of a capital city in a formatted table row.
     * Handles potential null values gracefully.
     */
    public void displayCapital(){
        System.out.printf(
                "%-30s %-35s %10d%n",
                this.name != null ? this.name : "N/A",
                this.country != null ? this.country : "N/A",
                this.population
        );
    }

    /**
     * Displays a formatted list of capital cities.
     * Includes column headers and error handling for null or empty lists.
     *
     * @param listOfCapital The list of capital cities to display.
     */
    public static void displayListOfCapital(List<City> listOfCapital)
    {
        if (listOfCapital == null || listOfCapital.isEmpty()) {
            System.out.println("No capitals can be displayed");
            return;
        }
        System.out.println("=================================================================");
        System.out.printf("%-30s %-35s %10s%n",
                "Name", "Country", "Population");
        System.out.println("-----------------------------------------------------------------");
        for (City city : listOfCapital) {
            if (city == null) {
                System.out.println("Warning: capital is null");
                continue;
            }
            city.displayCapital();
        }
    }

}
