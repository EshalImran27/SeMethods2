package com.napier.sem2;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        try
        {
            // Load MySQL Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        Connection con = null;
        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to MySQL database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to MySQL WORLD database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected to World database!");

                // Test the connection by querying the database
                Statement stmt = con.createStatement();
                ResultSet rset = stmt.executeQuery("SHOW TABLES");
                System.out.println("Tables in World database:");
                while (rset.next()) {
                    System.out.println(" - " + rset.getString(1));
                }

                // Wait a bit
                Thread.sleep(10000);
                // Exit for loop
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
                System.out.println("Database connection closed.");
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
}