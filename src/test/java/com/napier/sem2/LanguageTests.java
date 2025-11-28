// Package declaration
package com.napier.sem2;

// Import SQL, JUnit and utility libraries
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the {@link Language} class.
 * <p>
 * This class verifies that all functionalities of the Language class
 * behave as expected, including:
 * <ul>
 *     <li>Constructors (default and parameterized)</li>
 *     <li>Display methods for individual and list outputs</li>
 *     <li>Handling of null and edge-case values</li>
 * </ul>
 * Tests are executed using JUnit 5 and rely on captured console output
 * for validation of display methods.
 * </p>
 */
public class LanguageTests {
    /** Shared Language instance used across tests */
    static Language language;

    /** Output stream used to capture console output for validation */
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Initializes a base {@link Language} object before all tests run.
     */
    @BeforeAll
    static void init()
    {
        language = new Language();
    }

    /**
     * Redirects standard output to {@code output} before each test.
     * Allows validation of printed console messages.
     */
    @BeforeEach
    void setUpOutput(){
        System.setOut(new PrintStream(output));
    }

    /**
     * Restores standard output to the system console after each test.
     * Prevents side effects across tests.
     */
    @AfterEach
    void resetOutput(){
        System.setOut(System.out);
    }

    // ---------- Constructor Tests ----------

    /**
     * Verifies that the default constructor creates a non-null Language object.
     */
    @Test
    public void testDefaultConstructor(){
        Language language = new Language();
        assertNotNull(language, "Language constructor should not be null");
    }

    /**
     * Verifies that the parameterized constructor assigns all values correctly.
     */
    @Test
    public void testParameterisedConstructorWithAllParameters()
    {
        Language language = new Language("Spanish", "5.84%", 355029462);
        assertEquals("Spanish", language.getName());
        assertEquals("5.84%", language.getPercentage());
        assertEquals(355029462, language.getPopulation());
    }

    // ---------- Display Method Tests ----------

    /**
     * Tests {@link Language#displayLanguage()} with all populated values.
     * Ensures that valid name and percentage values are printed.
     */
    @Test
    public void testDisplayWithAllPopulatedValues(){
        Language language = new Language("Spanish", "5.84%", 355029462);
        language.displayLanguage();

        String result = output.toString();
        assertTrue(result.contains("Spanish"),  ("Name cannot be empty"));
        assertTrue(result.contains("5.84%"),  ("Percentage cannot be empty"));
    }

    /**
     * Tests {@link Language#displayLanguage()} when the language name is null.
     * Ensures that "N/A" is displayed in place of a null value.
     */
    @Test
    public void printDisplayWithNullName(){
        Language language= new Language();
        language.setName(null);
        language.setPercentage("5.84%");
        language.setPopulation(355029462);
        language.displayLanguage();

        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }

    /**
     * Tests {@link Language#displayLanguage()} when the percentage is null.
     * Ensures that "N/A" is displayed in place of a null value.
     */
    @Test
    public void printDisplayWithNullCountry(){
        Language language= new Language();
        language.setName("Spanish");
        language.setPercentage(null);
        language.setPopulation(22903129);
        language.displayLanguage();

        String result = output.toString();
        assertTrue(result.contains("N/A"),  ("Error message not found"));
    }

    /**
     * Tests {@link Language#displayLanguage()} when the population is zero.
     * Ensures that population zero is still displayed correctly.
     */
    @Test
    public void printDisplayWithNoPopulation(){
        Language language= new Language();
        language.setName("Spanish");
        language.setPercentage("5.84%");
        language.setPopulation(0);
        language.displayLanguage();

        String result = output.toString();
        assertTrue(result.contains("0"),  ("Error message not found"));
    }

    // ---------- List Method Tests ----------

    /**
     * Tests {@link Language#displayListOfLanguage(List)} when the list is null.
     * Ensures that an appropriate message is printed.
     */
    @Test
    public void testDisplayListOfLanguageWithNull()
    {
        Language.displayListOfLanguage(null);

        String result = output.toString();
        assertTrue(result.contains("No languages can be displayed"), ("Error message not found"));
    }

    /**
     * Tests {@link Language#displayListOfLanguage(List)} when the list is empty.
     * Ensures that a "No languages" message is printed.
     */
    @Test
    public void testDisplayListOfLanguageWithEmptyList() {
        List<Language> emptyList = new ArrayList<>();
        Language.displayListOfLanguage(emptyList);

        String result = output.toString();
        assertTrue(result.contains("No languages can be displayed"), ("Error message not found"));
    }

    /**
     * Tests {@link Language#displayListOfLanguage(List)} when one member of the list is null.
     * Ensures that a warning message is printed for the null entry.
     */
    @Test
    public void testDisplayListOfLanguageWithNullMember()
    {
        List<Language> list = new ArrayList<>();
        list.add(null);
        Language.displayListOfLanguage(list);

        String result = output.toString();
        assertTrue(result.contains("Warning: language is null"), ("Error message not found"));
    }

    /**
     * Tests {@link Language#displayListOfLanguage(List)} with a normal list containing valid language data.
     * Ensures that all valid languages are displayed correctly.
     */
    @Test
    public void testDisplayListOfLanguageWithNormalList()
    {
        List<Language> list = new ArrayList<>();
        Language spanish = new Language("Spanish", "5.84%",355029462);
        Language english = new Language("English", "5.71%", 347077867);
        list.add(spanish);
        list.add(english);
        Language.displayListOfLanguage(list);

        String result = output.toString();
        assertTrue(result.contains("Spanish"), ("Error message not found"));
        assertTrue(result.contains("English"), ("Error message not found"));
    }
}
