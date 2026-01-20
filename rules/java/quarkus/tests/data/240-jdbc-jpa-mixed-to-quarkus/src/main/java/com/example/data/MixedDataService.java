package com.example.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Test case for jdbc-jpa-mixed-to-quarkus-00001: Mixed EntityManager and PreparedStatement
public class MixedDataService {

    @PersistenceContext
    private EntityManager entityManager;

    private Connection connection;

    public void saveThroughJPA(MyEntity entity) {
        entityManager.persist(entity);
    }

    public void queryThroughJDBC(String name) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM my_entity WHERE name = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
    }
}
