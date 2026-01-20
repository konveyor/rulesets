
package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerAuth {
    public void connect() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "test", "test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
