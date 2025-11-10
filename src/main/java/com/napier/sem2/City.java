package com.napier.sem2;

import java.sql.*;
import java.util.List;

public class City {
    //Fields
    private String name = "";
    private String country = "";
    private String district = "";
    private int population = 0;

    //Constructors
    //All default values
    public City() {}
    //Assign all values
    public City(String name, String country, String district, int population)
    {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }
    //Capital Constructor
    public City(String name, String country, int population)
    {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    //Set & Get Methods
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
    public String getDistrict() {return district;}
    public void setDistrict(String district) {this.district = district;}
    public int getPopulation() {return population;}
    public void setPopulation(int population) {this.population = population;}

    //Display Method
    public void displayCapital(){
        System.out.println(
                String.format(
                        "%-30s %-35s %10d",
                        this.name != null ? this.name : "N/A",
                        this.country != null ? this.country : "N/A",
                        this.population
                )
        );
    }

    //Method for display a list of Capitals
    public static void displayListOfCapital(List<City> listOfCapital)
    {
        if (listOfCapital == null || listOfCapital.isEmpty()) {
            System.out.println("No capitals can be displayed");
            return;
        }
        System.out.println("=================================================================");
        System.out.println(String.format("%-30s %-35s %10s",
                "Name", "Country", "Population"));
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
