package com.napier.sem2;

import java.util.List;

public class Population {
    // ==============================
    // Fields
    // ==============================

    private String name = "";
    private long totalPop = 0;
    private long cityPop = 0;
    private double cityPercentage = 0;
    private long ruralPop = 0;
    private double ruralPercentage = 0;

    // ==============================
    // Constructors
    // ==============================

    public Population() {}

    public Population(String name, long totalPop, long cityPop, double cityPercentage,  long ruralPop, double ruralPercentage)
    {
        this.name = name;
        this.totalPop = totalPop;
        this.cityPop = cityPop;
        this.cityPercentage = cityPercentage;
        this.ruralPop = ruralPop;
        this.ruralPercentage = ruralPercentage;
    }

    // ==============================
    // Getters and Setters
    // ==============================

    public String getName() {return name;}
    public void setName(String name) {}

    public long getTotalPop() {return totalPop;}
    public void setTotalPop(long totalPop) {this.totalPop = totalPop;}

    public long getCityPop() {return cityPop;}
    public void setCityPop(long cityPop) {this.cityPop = cityPop;}

    public double getCityPercentage() {return cityPercentage;}
    public void setCityPercentage(double cityPercentage) {this.cityPercentage = cityPercentage;}

    public long getRuralPop() {return ruralPop;}
    public void setRuralPop(long ruralPop) {this.ruralPop = ruralPop;}

    public double getRuralPercentage() {return ruralPercentage;}
    public void setRuralPercentage(double ruralPercentage) {this.ruralPercentage = ruralPercentage;}

    // ==============================
    // Display Methods
    // ==============================

    public void displayLanguage(){
        System.out.printf(
                "%-52s %-10d %-10d %-6f %-10d %-6f%n",
                this.name,
                this.totalPop,
                this.cityPop,
                this.cityPercentage,
                this.ruralPop,
                this.ruralPercentage
        );
    }

    public static void displayListOfPopulation(List<Population> listOfPopulation)
    {
        if (listOfPopulation == null || listOfPopulation.isEmpty()) {
            System.out.println("No population can be displayed");
            return;
        }
        System.out.println("=================================================================");
        System.out.printf("%-52s %-10d %-10d %-6f %-10d %-6f%n",
                "Name", "Total Pop", "City Pop",  "City Perc", "Rural Pop", "Rural Perc");
        System.out.println("-----------------------------------------------------------------");
        for (Population population : listOfPopulation) {
            if (population == null) {
                System.out.println("Warning: population is null");
                continue;
            }
            population.displayLanguage();
        }
    }
}
