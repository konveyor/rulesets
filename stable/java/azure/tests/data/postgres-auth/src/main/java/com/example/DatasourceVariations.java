package com.example;

import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatasourceVariations {

    public DataSource createSimpleDataSource() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("testuser");
        dataSource.setPassword("testpass");
        return dataSource;
    }

    public DataSource createPoolingDataSource() throws SQLException {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();
        dataSource.setUser("pooluser");
        dataSource.setPassword("poolpass");
        return dataSource;
    }

    public void configureDataSourceSeparately() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        // These should be detected as separate incidents
        ds.setUser("separateuser");
        ds.setPassword("separatepass");
    }
}
