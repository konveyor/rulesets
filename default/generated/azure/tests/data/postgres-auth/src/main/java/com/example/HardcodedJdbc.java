
package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HardcodedJdbc {
    public void connect() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test?user=test&password=test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
