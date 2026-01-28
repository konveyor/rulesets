package org.konveyor.deps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchDataSourceInitializer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.integration.IntegrationDataSourceInitializer;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name:org.h2.Driver}")
    private String driverClassName;

    @Value("${spring.datasource.initial-size:10}")
    private int initialSize;

    @Value("${spring.datasource.max-total:50}")
    private int maxTotal;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxTotal(maxTotal);
        return dataSource;
    }

    @Bean
    public BatchProperties batchProperties() {
        return new BatchProperties();
    }

    @Bean
    public IntegrationProperties integrationProperties() {
        return new IntegrationProperties();
    }

    @Bean
    @Autowired
    public BatchDataSourceInitializer batchDataSourceInitializer(DataSource dataSource, ResourceLoader resourceLoader, BatchProperties batchProperties) {
        return new BatchDataSourceInitializer(dataSource, resourceLoader, batchProperties);
    }

    @Bean
    @Autowired
    public IntegrationDataSourceInitializer integrationDataSourceInitializer(DataSource dataSource, ResourceLoader resourceLoader, IntegrationProperties integrationProperties) {
        return new IntegrationDataSourceInitializer(dataSource, resourceLoader, integrationProperties);
    }

}
