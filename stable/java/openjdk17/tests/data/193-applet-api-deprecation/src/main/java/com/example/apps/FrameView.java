package com.example.apps;

import java.applet.Applet;  // x
import java.applet.AppletContext; // x
import java.applet.AppletStub; // x
import java.applet.AudioClip; // x
import java.awt.Dimension;
import javax.swing.JApplet; // x
import javax.swing.JFrame;

public class FrameView {
  JFrame frame;
  public void createFrame()
  {
    frame = new JFrame("CLOCK");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JApplet applet = new ClockComponent(); // x
    frame.getContentPane().add(applet);
    frame.pack();
    frame.setSize(new Dimension(400, 400));
    frame.setVisible(true);
  }
}