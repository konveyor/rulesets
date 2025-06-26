package com.example.apps;

import javax.management.ServiceNotFoundException;
import javax.management.loading.MLet;
import javax.management.loading.MLetContent;
import javax.management.loading.MLetMBean;
import javax.management.loading.PrivateMLet;
import javax.security.auth.Subject;
import javax.swing.plaf.basic.BasicDirectoryModel;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicToolBarUI;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Set;
import javax.lang.ThreadDeath;
import javax.swing.plaf.synth.SynthLookAndFeel;

public class Deprecation extends BasicMenuItemUI {

    public static void main(String[] args) throws IOException, ParseException, PrivilegedActionException {
        MouseInputHandler mouseInputHandler = new BasicMenuItemUI.MouseInputHandler();

        BasicDirectoryModel basicDirectoryModel = new BasicDirectoryModel(null);
        basicDirectoryModel.intervalAdded(null);
        basicDirectoryModel.intervalRemoved(null);

        BasicToolBarUI basicToolBarUI = new BasicToolBarUI();

        Subject.doAs(new Subject(), new PrivilegedActionImpl());
        Subject.doAs(new Subject(), new PrivilegedExceptionAction() {
            @Override
            public Object run() throws Exception {
                return null;
            }
        });

        MLet mLet = new MLet();
        MLetContent mLetContent = new MLetContent(null, null, null, null);
        MLetMBean mLetMBean = new MLetMBean() {
            @Override
            public Set<Object> getMBeansFromURL(String url) throws ServiceNotFoundException {
                return Set.of();
            }

            @Override
            public Set<Object> getMBeansFromURL(URL url) throws ServiceNotFoundException {
                return Set.of();
            }

            @Override
            public void addURL(URL url) {

            }

            @Override
            public void addURL(String url) throws ServiceNotFoundException {

            }

            @Override
            public URL[] getURLs() {
                return new URL[0];
            }

            @Override
            public URL getResource(String name) {
                return null;
            }

            @Override
            public InputStream getResourceAsStream(String name) {
                return null;
            }

            @Override
            public Enumeration<URL> getResources(String name) throws IOException {
                return null;
            }

            @Override
            public String getLibraryDirectory() {
                return "";
            }

            @Override
            public void setLibraryDirectory(String libdir) {

            }
        };
        mLetMBean.addURL(new URL("file:///"));
        PrivateMLet privateMLet = new PrivateMLet(null, false);

        SynthLookAndFeel synthLookAndFeel = new SynthLookAndFeel();
        URL url = new URL("hey");
        synthLookAndFeel.load(url);
        
        Thread thread = new Thread();
        thread.stop();
    }
    
    public static class PrivilegedActionImpl implements PrivilegedAction<String> {
        @Override
        public String run() {
            return "";
        }
    }

}