package com.example;

import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import javax.sql.DataSource;

public class Application {
    public static void main(String[] args) {
    }

    // Rule: jdbc-type-00010
    abstract static class MyDriver implements Driver {
    }

    // Rule: jdbc-type-00020
    abstract static class MyDataSource implements DataSource {
    }

    // Rule: jdbc-type-00030
    abstract static class MyResultSet implements ResultSet {
    }
}
