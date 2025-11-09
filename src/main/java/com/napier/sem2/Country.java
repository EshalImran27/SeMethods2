//including package in the class
package com.napier.sem2;

import java.util.List;
import java.sql.*;

//adding attributes into the country class
public class Country {
    private String code; //separate code for each country
    private String name; //specific name of a country
    private String continent; //continent in which the country is present
    private String region; //region of the country
    private int capital; //capital city of the specific country
    private int population; //total population of that country

    //adding constructors for the class
    public Country() {}
    public Country(String code, String name, String continent, String region,
                   int capital, int population){
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.capital = capital;
        this.population = population;
    }
    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getContinent() {return continent;}
    public void setContinent(String continent) {this.continent = continent;}
    public String getRegion() {return region;}
    public void setRegion(String region) {this.region = region;}
    public int getCapital() {return capital;}
    public void setCapital(int capital) {this.capital = capital;}
    public int getPopulation() {return population;}
    public void setPopulation(int population) {this.population = population;}

    //public method to display each country detail separately
    public void display(){
        System.out.println(
                String.format("%-3s %-40s %-15s %-30s %10d %s",
                        this.code != null ? this.code : "N/A",
                        this.name != null ? this.name : "N/A",
                        this.continent != null ? this.continent : "N/A",
                        this.region != null ? this.region : "N/A",
                        this.population,
                        this.capital));
    }
    //public method to display the entire list of country population
    public static void displayCountries(List<Country> countries) {
        if (countries == null || countries.isEmpty()) {
            System.out.println("No countries can be displayed");
            return;
        }
        System.out.println("=================================================================");
        System.out.println(String.format("%-3s %-40s %-15s %-30s %10s %s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        System.out.println("-----------------------------------------------------------------");
        /*int count = 0;
        *int DisplayCount = 0;
        *int total = countries.size();
        */

        for (Country country : countries) {
            if (country == null) {
                System.out.println("Warning: country is null");
                //count++;
                continue;
            }
            country.display();
            //count++;
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
