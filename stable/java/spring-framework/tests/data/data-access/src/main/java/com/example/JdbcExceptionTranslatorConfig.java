package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

import javax.sql.DataSource;

/**
 * Test class demonstrating usage of JDBC exception translators.
 * The default has changed in Spring 6 to SQLExceptionSubclassTranslator.
 */
@Configuration
public class JdbcExceptionTranslatorConfig {

    @Bean
    public SQLExceptionTranslator customTranslator(DataSource dataSource) {
        // Using explicit SQLErrorCodeSQLExceptionTranslator for legacy behavior
        return new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    @Bean
    public SQLStateSQLExceptionTranslator stateTranslator() {
        // Direct usage of SQLStateSQLExceptionTranslator
        return new SQLStateSQLExceptionTranslator();
    }
}
