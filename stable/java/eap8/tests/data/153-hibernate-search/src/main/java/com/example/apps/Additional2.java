package com.example.apps;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.CharFilterFactory;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.hibernate.search.FullTextQuery;

import java.io.Reader;
import java.util.Map;

public class Additional2 extends TokenFilterFactory {
    protected Additional2(Map<String, String> args) {
        super(args);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return null;
    }

    public static void main(String[] args) {
        FullTextQuery fullTextQuery = null;
    }

    public Reader create(Reader reader) {
        return null;
    }
}
