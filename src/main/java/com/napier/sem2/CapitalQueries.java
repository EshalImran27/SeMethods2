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
 * The {@code CapitalQueries} class handles all SQL queries related to capital cities.
 * <p>
 * This class supports:
 * <ul>
 *     <li>Retrieving capital city data by world, continent, or region</li>
 *     <li>Generating top-N ranked lists of capitals</li>
 *     <li>Exporting capital reports to Markdown files</li>
 * </ul>
 * </p>
 */
public class CapitalQueries {
    /** Connection object used to communicate with the database. */
    private final Connection con;

    /**
     * Constructs a new {@code CapitalQueries} instance.
     *
     * @param con A valid SQL {@link Connection} object.
     */
    public CapitalQueries(Connection con) {
        this.con = con;
    }

    // ============================================================
    //                  INTERNAL UTILITY METHODS
    // ============================================================

    /**
     * Converts a {@link ResultSet} into a list of {@link City} objects.
     *
     * @param rset The SQL {@link ResultSet} containing capital city data.
     * @return A list of {@link City} objects extracted from the result set.
     * @throws SQLException If a database access error occurs.
     */
    private List<City> CapitalsFromResultSet(ResultSet rset) throws SQLException {
        List<City> listOfCapitals = new ArrayList<>();
        while(rset.next()){
            City capital = new City(
                    rset.getString("city_name"),
                    rset.getString("country_name"),
                    rset.getInt("population"));
            listOfCapitals.add(capital);
        }
        return listOfCapitals;
    }

    /**
     * Executes a SQL query for capital cities and prints the results to console.
     *
     * @param sql The SQL query string.
     * @throws SQLException If an SQL or connection error occurs.
     */
    private void SqlQuery(String sql) throws SQLException {
        List<City> listOfCapital = new ArrayList<>();
        if(con==null){
            System.out.println("Database Connection is null");

        }else {
            try{
                Statement stmt = con.createStatement();
                ResultSet rset = stmt.executeQuery(sql);
                listOfCapital=CapitalsFromResultSet(rset);
                rset.close();
                stmt.close();
            }
            catch(Exception e){
                System.out.println("SQL Exception: "+e.getMessage());
            }
            City.displayListOfCapital(listOfCapital);
        }
    }

    /**
     * Executes a SQL query and returns a list of {@link City} objects.
     *
     * @param query The SQL query string.
     * @return A list of {@link City} results.
     * @throws SQLException If a database access error occurs.
     */
    private List<City> getCapitals(String query) throws SQLException {
        List<City> capitals = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
                String name = rs.getString("city_name");
                String country = rs.getString("country_name");
                int population = rs.getInt("population");

                capitals.add(new City(name, country, population));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return capitals;
    }

    // ============================================================
    //                  CAPITAL REPORT QUERIES
    // ============================================================

    // ---------------------- Global Reports ----------------------

    /**
     * Prints all capitals in the world ranked by population.
     *
     * @throws SQLException If a database access error occurs.
     */
    public void getReportCapitalGlobal() throws SQLException {
        String sqlStatement =
                "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                        "FROM city " +
                        "JOIN country ON city.ID = country.Capital " +
                        "ORDER BY city.Population DESC ";
        System.out.println("All capitals in the world ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves all capitals in the world ranked by population.
     *
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportCapitalGlobalList() throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC ";
        return getCapitals(query);
    }

    // ---------------------- Continent Reports ----------------------

    /**
     * Prints all capitals in a given continent ranked by population.
     *
     * @param continent The name of the continent.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportCapitalContinent(String continent) throws SQLException {
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Continent = '" + continent + "' "
                + "ORDER BY city.Population DESC ";
        System.out.println("All Capitals in " + continent + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves all capitals in a given continent ranked by population.
     *
     * @param continent The name of the continent.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportCapitalContinentList(String continent) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Continent = '" + continent + "' "
                + "ORDER BY city.Population DESC ";
        return getCapitals(query);
    }

    // ---------------------- Region Reports ----------------------

    /**
     * Prints all capitals in a given region ranked by population.
     *
     * @param region The name of the region.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportCapitalRegion(String region) throws SQLException {
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Region = '" + region + "' "
                + "ORDER BY city.Population DESC ";
        System.out.println("All Capitals in the region: " + region + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves all capitals in a given region ranked by population.
     *
     * @param region The name of the region.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportCapitalRegionList(String region) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Region = '" + region + "' "
                + "ORDER BY city.Population DESC ";
        return getCapitals(query);
    }

    // ---------------------- Top Global ----------------------

    /**
     * Prints the top N capitals in the world ranked by population.
     *
     * @param n The number of capitals to display.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportTopCapitalGlobal(int n) throws SQLException {
        if (n <= 0) {
            System.out.println("No capitals can be displayed");
            return;
        }
        String sqlStatement =  "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "ORDER BY city.Population DESC "
                + "LIMIT " + n + " ";
        System.out.println("Top " + n + " Capitals in the world ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves the top N capitals in the world ranked by population.
     *
     * @param n Number of capitals to retrieve.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportTopCapitalGlobalList(int n) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "ORDER BY city.Population DESC "
                + "LIMIT " + n + " ";
        return getCapitals(query);
    }

    // ---------------------- Top by Continent ----------------------

    /**
     * Prints the top N capitals in a given continent ranked by population.
     *
     * @param continent The continent name.
     * @param n         The number of capitals to display.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportTopCapitalContinent(String continent, int n) throws SQLException {
        if (n <= 0 || continent==null || continent.isEmpty()) {
            System.out.println("No capitals can be displayed");
            return;
        }
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Continent = '" + continent + "' "
                + "ORDER BY city.Population DESC "
                + "LIMIT " + n + " ";
        System.out.println("Top " + n + " Capitals in the continent " + continent + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves the top N capitals in a given continent ranked by population.
     *
     * @param continent The continent name.
     * @param n         The number of capitals to retrieve.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportTopCapitalContinentList(String continent, int n) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Continent = '" + continent + "' "
                + "ORDER BY city.Population DESC "
                + "LIMIT " + n + " ";
        return getCapitals(query);
    }

    // ---------------------- Top by Region ----------------------

    /**
     * Prints the top N capitals in a given region ranked by population.
     *
     * @param region The region name.
     * @param n      The number of capitals to display.
     * @throws SQLException If a database access error occurs.
     */
    public void getReportTopCapitalRegion(String region, int n) throws SQLException {
        if (n <= 0 || region==null) {
            System.out.println("No capitals can be displayed");
            return;
        }
        String sqlStatement = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Region = '" + region + "' "
                + "ORDER BY city.Population DESC "
                + "LIMIT " + n + " ";
        System.out.println("Top " + n + " Capitals in the region " + region + " ranked from largest population to smallest: ");
        SqlQuery(sqlStatement);
    }

    /**
     * Retrieves the top N capitals in a given region ranked by population.
     *
     * @param region The region name.
     * @param n      The number of capitals to retrieve.
     * @return List of {@link City} objects.
     * @throws SQLException If a database access error occurs.
     */
    public List<City> getReportTopCapitalRegionList(String region, int n) throws SQLException {
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population AS population " +
                "FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Region = '" + region + "' "
                + "ORDER BY city.Population DESC "
                + "LIMIT " + n + " ";
        return getCapitals(query);
    }

    // ============================================================
    //                  REPORT OUTPUT METHODS
    // ============================================================

    /**
     * Outputs a list of capital cities to a Markdown (.md) report file.
     *
     * @param capitals A list of {@link City} objects to include in the report.
     * @param filename The output filename (without extension).
     */
    public void outputCapitalReport(List<City> capitals, String filename) {
        // Check capitals is not null
        if (capitals == null) {
            System.out.println("No capitals");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name | Country | Population |\r\n");
        // Loop over all capitals in the list
        for (City capital : capitals) {
            if (capital == null) continue;
            sb.append("| " + capital.getName() + " | " + capital.getCountry() + " | "  + capital.getPopulation() + " |\r\n");
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
