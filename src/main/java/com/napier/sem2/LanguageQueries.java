// Package declaration
package com.napier.sem2;

// Import SQL and utility libraries
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code LanguageQueries} class handles all SQL queries related to language languages.
 * <p>
 * This class supports:
 * <ul>
 *     <li>Retrieving language data by world, continent, or region</li>
 *     <li>Generating top-N ranked lists of languages</li>
 *     <li>Exporting language reports to Markdown files</li>
 * </ul>
 * </p>
 */
public class LanguageQueries {
    /** Connection object used to communicate with the database. */
    private final Connection con;

    /**
     * Constructs a new {@code LanguageQueries} instance.
     *
     * @param con A valid SQL {@link Connection} object.
     */
    public LanguageQueries(Connection con) {
        this.con = con;
    }

    // ============================================================
    //                  INTERNAL UTILITY METHODS
    // ============================================================

    /**
     * Converts a {@link ResultSet} into a list of {@link Language} objects.
     *
     * @param rset The SQL {@link ResultSet} containing language data.
     * @return A list of {@link Language} objects extracted from the result set.
     * @throws SQLException If a database access error occurs.
     */
    private List<Language> LanguagesFromResultSet(ResultSet rset) throws SQLException {
        List<Language> listOfLanguages = new ArrayList<>();
        while(rset.next()){
            Language language = new Language(
                    rset.getString("cl.Language"),
                    rset.getString("WorldPercentage"),
                    rset.getInt("TotalSpeakers"));
            listOfLanguages.add(language);
        }
        return listOfLanguages;
    }

    /**
     * Executes a SQL query for language languages and prints the results to console.
     *
     * @param sql The SQL query string.
     * @throws SQLException If an SQL or connection error occurs.
     */
    private void SqlQuery(String sql) throws SQLException {
        List<Language> listOfLanguage = new ArrayList<>();
        if(con==null){
            System.out.println("Database Connection is null");

        }else {
            try{
                Statement stmt = con.createStatement();
                ResultSet rset = stmt.executeQuery(sql);
                listOfLanguage=LanguagesFromResultSet(rset);
                rset.close();
                stmt.close();
            }
            catch(Exception e){
                System.out.println("SQL Exception: "+e.getMessage());
            }
            Language.displayListOfLanguage(listOfLanguage);
        }
    }

    /**
     * Executes a SQL query and returns a list of {@link Language} objects.
     *
     * @param query The SQL query string.
     * @return A list of {@link Language} results.
     * @throws SQLException If a database access error occurs.
     */
    private List<Language> getLanguage(String query) throws SQLException {
        List<Language> languages = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
                String name = rs.getString("cl.Language");
                String percentage = rs.getString("WorldPercentage");
                int population = rs.getInt("TotalSpeakers");

                languages.add(new Language(name, percentage, population));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return languages;
    }

    // ============================================================
    //                  LANGUAGE REPORT QUERY
    // ============================================================

    /**
     * Prints languages ranked by population.
     *
     * @throws SQLException If a database access error occurs.
     */
    public void getReportLanguage() throws SQLException {
        String sqlStatement = "SELECT cl.Language,\n" +
                "ROUND(SUM(c.Population * (cl.Percentage / 100))) AS TotalSpeakers,\n" +
                "CONCAT(ROUND((SUM(c.Population * (cl.Percentage / 100)) /\n" +
                "(SELECT SUM(Population) FROM country)) * 100,2),'%') AS WorldPercentage\n" +
                "FROM countrylanguage cl\n" +
                "JOIN country c ON cl.CountryCode = c.Code\n" +
                "WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic')\n" +
                "GROUP BY cl.Language\n" +
                "ORDER BY TotalSpeakers DESC; ";
        System.out.println("Percentage languages in the world ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves most spoken languages ranked by population.
     *
     * @return List of {@link Language} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<Language> getReportLanguageList() throws SQLException {
        String query = "SELECT cl.Language,\n" +
                "ROUND(SUM(c.Population * (cl.Percentage / 100))) AS TotalSpeakers,\n" +
                "CONCAT(ROUND((SUM(c.Population * (cl.Percentage / 100)) /\n" +
                "(SELECT SUM(Population) FROM country)) * 100,2),'%') AS WorldPercentage\n" +
                "FROM countrylanguage cl\n" +
                "JOIN country c ON cl.CountryCode = c.Code\n" +
                "WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic')\n" +
                "GROUP BY cl.Language\n" +
                "ORDER BY TotalSpeakers DESC; ";
        return getLanguage(query);
    }

    // ============================================================
    //                  REPORT OUTPUT METHODS
    // ============================================================

    /**
     * Outputs a list of languages to a Markdown (.md) report file.
     *
     * @param listOfLanguages A list of {@link Language} objects to include in the report.
     * @param filename The output filename (without extension).
     */
    public void outputLanguageReport(List<Language> listOfLanguages, String filename) {
        // Check cities is not null
        if (listOfLanguages == null) {
            System.out.println("No languages");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name | Percentage | Population |\r\n");
        // Loop over all listOfCities in the list
        for (Language language : listOfLanguages) {
            if (language == null) continue;
            sb.append("| " + language.getName() + " | "+ language.getPercentage() +
                    "| " + language.getPopulation() + " |\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
