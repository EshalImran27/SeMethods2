package com.napier.sem2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CapitalIntegrationTests
{
    static App app;
    static CapitalQueries capitalQueries;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
        capitalQueries = new CapitalQueries(app.con);
    }

    @Test
    void testgetReportCapitalGlobalList() throws SQLException {
        List<City> capitals = capitalQueries.getReportCapitalGlobalList();
        assertEquals(capitals.get(0).getName(), "Seoul");
        assertEquals(capitals.get(0).getCountry(), "South Korea");
        assertEquals(capitals.get(0).getPopulation(), 9981619);
    }

    @Test
    void getReportCapitalContinentList() throws SQLException {
        List<City> capitals = capitalQueries.getReportCapitalContinentList("Asia");
        assertEquals(capitals.get(2).getName(), "Tokyo");
        assertEquals(capitals.get(2).getCountry(), "Japan");
        assertEquals(capitals.get(2).getPopulation(), 7980230);
    }

    @Test
    void getReportCapitalRegion() throws SQLException {
        List<City> capitals = capitalQueries.getReportCapitalRegionList("Caribbean");
        assertEquals(capitals.get(0).getName(), "La Habana");
        assertEquals(capitals.get(0).getCountry(), "Cuba");
        assertEquals(capitals.get(0).getPopulation(), 2256000);
    }

    @Test
    void getReportTopCapitalContinent() throws SQLException {
        List<City> capitals = capitalQueries.getReportTopCapitalContinentList("Asia",5);
        assertEquals(capitals.get(4).getName(), "Teheran");
        assertEquals(capitals.get(4).getCountry(), "Iran");
        assertEquals(capitals.get(4).getPopulation(), 6758845);
    }

    @Test
    void getReportTopCapitalRegion() throws SQLException {
        List<City> capitals = capitalQueries.getReportTopCapitalRegionList("Caribbean",5);
        assertEquals(capitals.get(4).getName(), "Nassau");
        assertEquals(capitals.get(4).getCountry(), "Bahamas");
        assertEquals(capitals.get(4).getPopulation(), 172000);
    }

    @Test
    void getReportTopCapitalGlobal() throws SQLException {
        List<City> capitals = capitalQueries.getReportTopCapitalGlobalList(5);
        assertEquals(capitals.get(2).getName(), "Ciudad de México");
        assertEquals(capitals.get(2).getCountry(), "Mexico");
        assertEquals(capitals.get(2).getPopulation(), 8591309);
    }
}
