package com.example.apps;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.GeneratedValue;
import javax.persistence.TableGenerator;
import javax.persistence.Entity;
import javax.persistence.Id;

import static javax.persistence.GenerationType.TABLE;

public class Hibernate515301100 {
    private static SessionFactory factory;

    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    @Entity
    public class Employee {
        @TableGenerator(
                name="empGen",
                table="ID_GEN",
                pkColumnName="GEN_KEY",
                valueColumnName="GEN_VALUE",
                pkColumnValue="EMP_ID",
                allocationSize=1)
        @Id
        @GeneratedValue(strategy=TABLE, generator="empGen")
        int id;
    }
}