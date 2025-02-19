package com.example.apps;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.TeeOutputStream;

import java.io.ByteArrayInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

public class CommonIO {

    public static void main(String[] args) throws IOException {
        String str = "Hello World.";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();

        FilterOutputStream teeOutputStream
            = new TeeOutputStream(outputStream1, outputStream2);
        new TeeInputStream(inputStream, teeOutputStream, true).read(new byte[str.length()]);
    }
}
