package com.example.apps;

import java.awt.*;
import java.awt.peer.ComponentPeer;

public class ComponentGetPeer {
    
    public static void main(String[] args) {
        Font sans = new Font("Sans", 1, 1);
        java.awt.peer.FontPeer peer = sans.getPeer();
        String peerName = peer.toString();
        java.awt.Container cont = new java.awt.Container();
        Container c = new Container();
        int count = c.getComponentCount();
        if (count != 0) 
         {
            Component comp = c.getComponent(0);
             ComponentPeer peer1 = comp.getPeer();
         }
   }
}