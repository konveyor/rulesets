package com.example.apps;

import org.apache.lucene.analysis.util.CharFilterFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.Search;

import java.io.Reader;
import java.util.Map;

public class Additional extends CharFilterFactory {
    protected Additional(Map<String, String> args) {
        super(args);
    }

    public static void main(String[] args) {
        FullTextQuery fullTextQuery = null;
    }

    @Override
    public Reader create(Reader reader) {
        return null;
    }
}
