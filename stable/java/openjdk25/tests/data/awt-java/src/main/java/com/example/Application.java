package com.example;

import java.awt.Component;
// Rule: openjdk25--to-openjdk25+-00600
import java.awt.peer.ComponentPeer;
// Rule: openjdk25--to-openjdk25+-00610
import java.awt.dnd.peer.DragSourceContextPeer;

public class Application {
    public static void main(String[] args) throws Throwable {
        // Rule: openjdk25--to-openjdk25+-00620
        Component component = new java.awt.Button("test");
        component.getPeer();
    }
}
