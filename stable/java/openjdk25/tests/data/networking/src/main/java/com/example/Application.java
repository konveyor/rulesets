package com.example;

import java.net.Socket;

public class Application {
    public static void main(String[] args) throws Exception {
        // Rule: openjdk21-to-openjdk25+-00380
        Socket socket = new Socket();
        socket.close();
    }
}
