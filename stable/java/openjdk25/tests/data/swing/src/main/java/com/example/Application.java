package com.example;

import java.io.InputStream;
import javax.swing.JSlider;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.plaf.basic.BasicSliderUI;

public class Application {
    public static void main(String[] args) throws Exception {
        // Rule: openjdk25--to-openjdk25+-00100
        SynthLookAndFeel synth = new SynthLookAndFeel();
        InputStream is = Application.class.getResourceAsStream("/synth.xml");
        synth.load(is, Application.class);

        // Rule: openjdk25--to-openjdk25+-00110
        BasicSliderUI ui = new BasicSliderUI();
    }
}
