package com.example;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Application {
    public static void main(String[] args) throws Exception {
        // Rule: openjdk25--to-openjdk25+-01010
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("AppleScript");
    }
}
