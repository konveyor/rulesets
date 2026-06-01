package com.example;

// Rule: openjdk25--to-openjdk25+-01290
import sun.management.snmp.jvminstr.JvmMemoryMeta;

import javax.tools.ToolProvider;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00310
        ToolProvider tp = new ToolProvider();

        // Rule: openjdk25--to-openjdk25+-01290
        JvmMemoryMeta meta = null;
    }
}
