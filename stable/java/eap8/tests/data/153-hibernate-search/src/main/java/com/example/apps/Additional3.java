package com.example.apps;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;
import org.hibernate.search.FullTextQuery;

import java.io.Reader;
import java.util.Map;

public class Additional3 extends TokenizerFactory {
    protected Additional3(Map<String, String> args) {
        super(args);
    }

    @Override
    public Tokenizer create(AttributeFactory attributeFactory) {
        return null;
    }

    public static void main(String[] args) {
        FullTextQuery fullTextQuery = null;
    }

    public Reader create(Reader reader) {
        return null;
    }
}
