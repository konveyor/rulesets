package com.example.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectJdbcService {

    // Test case for jdbc-jpa-mixed-to-quarkus-00002: Direct JDBC Connection
    public void queryWithConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM my_table");
    }

    // Test case for jdbc-jpa-mixed-to-quarkus-00003: Statement usage
    public void queryWithStatement(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeQuery("SELECT * FROM my_table");
    }
}
