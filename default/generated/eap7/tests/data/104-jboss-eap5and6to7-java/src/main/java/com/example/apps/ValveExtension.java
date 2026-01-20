package com.example.apps;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import javax.servlet.ServletException;
import java.io.IOException;

public class ValveExtension extends ValveBase {

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {

    }
}
