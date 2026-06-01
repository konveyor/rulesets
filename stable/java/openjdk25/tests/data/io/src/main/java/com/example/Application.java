package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Object;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.ZipFile;

public class Application {

    // Rule: openjdk25--to-openjdk25+-00390
    static class TestFis extends FileInputStream {
        TestFis() throws IOException { super("test.txt"); }
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }

    // Rule: openjdk25--to-openjdk25+-00400
    static class TestFos extends FileOutputStream {
        TestFos() throws IOException { super("test.txt"); }
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }

    // Rule: openjdk25--to-openjdk25+-00410
    static class TestDeflater extends Deflater {
        @Override
        protected void finalize() {
            super.finalize();
        }
    }

    // Rule: openjdk25--to-openjdk25+-00420
    static class TestInflater extends Inflater {
        @Override
        protected void finalize() {
            super.finalize();
        }
    }

    // Rule: openjdk25--to-openjdk25+-00430
    static class TestZipFile extends ZipFile {
        TestZipFile() throws IOException { super("test.zip"); }
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }

    public static void main(String[] args) {
    }
}
