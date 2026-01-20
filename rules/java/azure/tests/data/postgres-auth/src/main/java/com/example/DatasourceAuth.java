
package com.example;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatasourceAuth {
    public DataSource createDataSource() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("test");
        dataSource.setPassword("test");
        return dataSource;
    }
}
