package com.example.apps;/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


import com.example.apps.WeatherPainter;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;


/**
 * Taken from: https://docs.oracle.com/javase/tutorial/2d/basic2d/examples/WeatherWizard.java
 */
public class Java2D extends JApplet implements ChangeListener {
    
    WeatherPainter painter;
    
    public void init() {
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
    }
    
    public void start() {
        initComponents();
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Weather Wizard");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JApplet ap = new WeatherWizard();
        ap.init();
        ap.start();
        f.add("Center", ap);
        f.pack();
        f.setVisible(true);
        
    }
    
    private BufferedImage loadImage(String name) {
        String imgFileName = "images/weather-"+name+".png";
        URL url = WeatherWizard.class.getResource(imgFileName);
        BufferedImage img = null;
        try {
            img =  ImageIO.read(url);
        } catch (Exception e) {
        }
        return img;
    }
    
    public void initComponents() {
        
        
        setLayout(new BorderLayout());
        
        
        JPanel p = new JPanel();
        p.add(new JLabel("Temperature:"));
        JSlider tempSlider = new JSlider(20, 100, 65);
        tempSlider.setMinorTickSpacing(5);
        tempSlider.setMajorTickSpacing(20);
        tempSlider.setPaintTicks(true);
        tempSlider.setPaintLabels(true);
        tempSlider.addChangeListener(this);
        p.add(tempSlider);
        add("North", p);
        
        painter = new WeatherPainter();
        painter.sun = loadImage("sun");
        painter.cloud = loadImage("cloud");
        painter.rain = loadImage("rain");
        painter.snow = loadImage("snow");
        painter.setTemperature(65);
        p.add("Center", painter);
        
    }
    
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider)e.getSource();
        painter.setTemperature(slider.getValue());
    }
}
