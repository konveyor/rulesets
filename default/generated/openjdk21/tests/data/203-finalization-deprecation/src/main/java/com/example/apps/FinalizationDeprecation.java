package com.example.apps;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;

public class FinalizationDeprecation extends Object {
    
    public static void main(String[] args) throws Throwable {
        FinalizationDeprecation o1 = new FinalizationDeprecation();
        o1.finalize();

        Runtime runtime = Runtime.getRuntime();
        runtime.runFinalization();
        
        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}