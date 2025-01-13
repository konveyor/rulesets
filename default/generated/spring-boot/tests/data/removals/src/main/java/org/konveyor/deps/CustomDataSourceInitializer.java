package org.konveyor.deps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.AbstractDataSourceInitializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceInitializationMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class CustomDataSourceInitializer extends AbstractDataSourceInitializer {

    private ResourceLoader resourceLoader;

    @Autowired
    public CustomDataSourceInitializer(DataSource dataSource, ResourceLoader resourceLoader) {
        super(dataSource, resourceLoader);
        this.resourceLoader = resourceLoader;
    }

    @Override
    protected void customize(ResourceDatabasePopulator populator) {
        populator.addScript(resourceLoader.getResource("schema.sql"));
    }

    @Override
    protected DataSourceInitializationMode getMode() {
        return null;
    }

    @Override
    protected String getSchemaLocation() {
        return null;
    }

    @Override
    protected String getDatabaseName() {
        // Return the database name for which this initializer applies
        return "H2"; // Adjust based on your database type
    }
}
