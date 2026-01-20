package com.example.apps;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

public class Deprecation {

    public static void main(String[] args) throws ParseException, IOException {
        SynthLookAndFeel synth = new SynthLookAndFeel();
        URL url = Deprecation.class.getResource("/com/example/apps/deprecation.fxml");
        synth.load(url);
    }

}