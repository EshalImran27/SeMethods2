//including package in the class
package com.napier.sem2;

import java.util.List;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Represents a Country entity in the world database.
 * This class stores information such as name, continent, region, and population.
 * It also provides utility methods for displaying country data.
 */
public class Country {
    private static final Logger LOGGER = Logger.getLogger(Country.class.getName());
    /** separate code for each country */
    private String code;
    /** specific name of a country */
    private String name;
    /** continent in which the country is present*/
    private String continent;
    /** region of the country */
    private String region;
    /** capital city of the specific country */
    private int capital;
    /** //total population of that country */
    private int population;

    /**
     * Default constructor.
     * Initializes a country object with default values.
     */
    public Country() {}
    /**
     * Constructs a country with full attributes.
     *
     * @param name       The name of the country.
     * @param continent    The continent where the country is located.
     * @param region   The region of the country.
     * @param population The population of the country.
     */
    public Country(String code, String name, String continent, String region,
                   int capital, int population){
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.capital = capital;
        this.population = population;
    }
    /** @return The code of the country. */
    public String getCode() {return code;}
    /** @param code The code of the country to set. */
    public void setCode(String code) {this.code = code;}
    /** @return The name of the country. */
    public String getName() {return name;}
    /** @param name The name of the country to set. */
    public void setName(String name) {this.name = name;}
    /** @return The continent of the country. */
    public String getContinent() {return continent;}
    /** @param continent the continent of the country to set. */
    public void setContinent(String continent) {this.continent = continent;}
    /** @return The region of the country. */
    public String getRegion() {return region;}
    /** @param region The region of the country to set. */
    public void setRegion(String region) {this.region = region;}
    /** @return The capital of the country. */
    public int getCapital() {return capital;}
    /** @param capital The capital of the country to set. */
    public void setCapital(int capital) {this.capital = capital;}
    /** @return The population of the country. */
    public int getPopulation() {return population;}
    /** @param population The population of the country to set. */
    public void setPopulation(int population) {this.population = population;}

    /**
     * Displays the details of a country in a formatted table row.
     * Handles potential null values gracefully.
     */
    public void display(){
        String formatted = String.format("%-3s %-40s %-15s %-30s %10d %d",
                this.code != null ? this.code : "N/A",
                this.name != null ? this.name : "N/A",
                this.continent != null ? this.continent : "N/A",
                this.region != null ? this.region : "N/A",
                this.population,
                this.capital);
        LOGGER.info(formatted);
    }
    /**
     * Displays a formatted list of countries.
     * Includes column headers and error handling for null or empty lists.
     *
     * @param countries The list of countries to display.
     */
    public static void displayCountries(List<Country> countries) {
        if (countries == null || countries.isEmpty()) {
            LOGGER.warning("No countries can be displayed");
            return;
        }
        LOGGER.info("=================================================================");
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(String.format("%-3s %-40s %-15s %-30s %10s %s",
                    "Code", "Name", "Continent", "Region", "Population", "Capital"));
        }
        LOGGER.info("-----------------------------------------------------------------");

        for (Country country : countries) {
            if (country == null) {
                LOGGER.warning("Warning: country is null");
                //count++;
                continue;
            }
            country.display();
        }
    }
}
