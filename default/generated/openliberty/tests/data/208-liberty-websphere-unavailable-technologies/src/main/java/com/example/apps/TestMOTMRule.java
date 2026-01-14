package com.example.apps;

import javax.activation.DataHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class TestMOTMRule {
	public static void main(String[] args) throws IOException {
		DataHandler testHandler = new DataHandler(new URL("http://www.example.com"));
		OutputStream test = null;
		testHandler.writeTo(test);
	}
}
