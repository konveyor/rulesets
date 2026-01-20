package com.example.apps;

import javax.swing.*;
import javax.swing.plaf.basic.BasicDirectoryModel;

public class TestClass extends BasicDirectoryModel {
    /**
     * Constructs a new instance of {@code BasicDirectoryModel}.
     *
     * @param filechooser an instance of {JFileChooser}
     */
    public TestClass(JFileChooser filechooser) {
        super(filechooser);
    }
}
