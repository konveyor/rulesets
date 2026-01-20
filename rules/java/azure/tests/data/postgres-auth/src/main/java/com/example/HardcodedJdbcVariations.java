package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HardcodedJdbcVariations {

    public void connectWithUser() {
        try {
            // This should match the hardcoded JDBC pattern
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb?user=admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connectWithPassword() {
        try {
            // This should also match the hardcoded JDBC pattern
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb?password=secret");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connectWithBoth() {
        try {
            // This should also match the hardcoded JDBC pattern
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb?user=admin&password=secret&ssl=true");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
