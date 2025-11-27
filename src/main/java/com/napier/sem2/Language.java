package com.napier.sem2;

import java.util.List;

public class Language {
    // ==============================
    // Fields
    // ==============================

    /** The name of the language. */
    private String name = "";

    /** The percentage of the language spoken in city. */
    private String percentage = "0%";

    /** The total population of the language spoken. */
    private int population = 0;

    // ==============================
    // Constructors
    // ==============================

    /**
     * Default constructor.
     * Initializes a language object with default values.
     */
    public Language() {}

    /**
     * Constructs a language with full attributes.
     *
     * @param name       The name of the language.
     * @param percentage The population of the language.
     */
    public Language(String name,String percentage, int population)
    {
        this.name = name;
        this.percentage = percentage;
        this.population = population;
    }

    // ==============================
    // Getters and Setters
    // ==============================

    /** @return The name of the language. */
    public String getName() {return name;}

    /** @param name The name of the language to set. */
    public void setName(String name) {this.name = name;}

    /** @return The percentage where the language is located.*/
    public String getPercentage() {return percentage;}

    /** @param percentage The percentage to set for the language. */
    public void setPercentage(String percentage) {this.percentage = percentage;}

    /** @return The total population spoken language.*/
    public int getPopulation() {return population;}

    /** @param population The total population to set for the language. */
    public void setPopulation(int population) {this.population = population;}

    // ==============================
    // Display Methods
    // ==============================

    /**
     * Displays the details of a language in a formatted table row.
     * Handles potential null values gracefully.
     */
    public void displayLanguage(){
        System.out.printf(
                "%-10s %-12s %-15s%n",
                this.name != null ? this.name : "N/A",
                this.percentage,
                this.population
        );
    }

    /**
     * Displays a formatted list of languages.
     * Includes column headers and error handling for null or empty lists.
     *
     * @param listOfLanguage The list of languages to display.
     */
    public static void displayListOfLanguage(List<Language> listOfLanguage)
    {
        if (listOfLanguage == null || listOfLanguage.isEmpty()) {
            System.out.println("No languages can be displayed");
            return;
        }
        System.out.println("=================================================================");
        System.out.printf("%-10s %-12s %-15s%n",
                "Name", "Percentage", "Population");
        System.out.println("-----------------------------------------------------------------");
        for (Language language : listOfLanguage) {
            if (language == null) {
                System.out.println("Warning: language is null");
                continue;
            }
            language.displayLanguage();
        }
    }
}
