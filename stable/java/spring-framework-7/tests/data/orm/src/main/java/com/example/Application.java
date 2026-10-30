package com.example;

// Rule: orm-import-00010
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

// Rule: orm-import-00020
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;

public class Application {
    public static void main(String[] args) {
        // Rule: orm-import-00010
        LocalSessionFactoryBean sessionFactory = null;

        // Rule: orm-import-00020
        MutablePersistenceUnitInfo persistenceUnitInfo = null;
    }
}
