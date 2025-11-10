package com.napier.sem2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CapitalIntegrationTests
{
    static App app;
    static CapitalQueries capitalQueries;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 5000);
        capitalQueries = new CapitalQueries(app.con);
    }

    @Test
    void testGetReportCapitalGlobalList() throws SQLException {
        List<City> capitals = capitalQueries.getReportCapitalGlobalList();
        assertEquals("Seoul", capitals.get(0).getName());
        assertEquals("South Korea", capitals.get(0).getCountry());
        assertEquals(9981619, capitals.get(0).getPopulation());
    }

    @Test
    void getReportCapitalContinentList() throws SQLException {
        List<City> capitals = capitalQueries.getReportCapitalContinentList("Asia");
        assertEquals("Tokyo", capitals.get(2).getName());
        assertEquals("Japan", capitals.get(2).getCountry());
        assertEquals(7980230, capitals.get(2).getPopulation());
    }

    @Test
    void getReportCapitalRegion() throws SQLException {
        List<City> capitals = capitalQueries.getReportCapitalRegionList("Caribbean");
        assertEquals("La Habana", capitals.get(0).getName());
        assertEquals("Cuba", capitals.get(0).getCountry());
        assertEquals(2256000, capitals.get(0).getPopulation());
    }

    @Test
    void getReportTopCapitalContinent() throws SQLException {
        List<City> capitals = capitalQueries.getReportTopCapitalContinentList("Asia",5);
        assertEquals("Teheran", capitals.get(4).getName());
        assertEquals("Iran", capitals.get(4).getCountry());
        assertEquals(6758845, capitals.get(4).getPopulation());
    }

    @Test
    void getReportTopCapitalRegion() throws SQLException {
        List<City> capitals = capitalQueries.getReportTopCapitalRegionList("Caribbean",5);
        assertEquals("Nassau", capitals.get(4).getName());
        assertEquals("Bahamas", capitals.get(4).getCountry());
        assertEquals(172000, capitals.get(4).getPopulation());
    }

    @Test
    void getReportTopCapitalGlobal() throws SQLException {
        List<City> capitals = capitalQueries.getReportTopCapitalGlobalList(5);
        assertEquals("Ciudad de México", capitals.get(2).getName());
        assertEquals("Mexico", capitals.get(2).getCountry());
        assertEquals(8591309, capitals.get(2).getPopulation());
    }
}
