package com.example;

import java.awt.color.ICC_Profile;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.Component;
// Rule: openjdk25--to-openjdk25+-00600
import java.awt.peer.ComponentPeer;
// Rule: openjdk25--to-openjdk25+-00610
import java.awt.dnd.peer.DragSourceContextPeer;

public class Application {
    public static void main(String[] args) throws Throwable {
        // Rule: openjdk25--to-openjdk25+-00270
        ICC_Profile profile = ICC_Profile.getInstance(java.awt.color.ColorSpace.CS_sRGB);
        profile.finalize();

        // Rule: openjdk25--to-openjdk25+-00280
        ColorModel colorModel = ColorModel.getRGBdefault();
        colorModel.finalize();

        // Rule: openjdk25--to-openjdk25+-00290
        byte[] r = {0, 1};
        byte[] g = {0, 1};
        byte[] b = {0, 1};
        IndexColorModel indexColorModel = new IndexColorModel(1, 2, r, g, b);
        indexColorModel.finalize();

        // Rule: openjdk25--to-openjdk25+-00620
        Component component = new java.awt.Button("test");
        component.getPeer();
    }
}
