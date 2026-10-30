package com.example;

// Rule: openjdk21-to-openjdk25+-00250
import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextChild;
import java.beans.beancontext.BeanContextSupport;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk21-to-openjdk25+-00250
        BeanContextSupport context = new BeanContextSupport();
        BeanContext bc = context;
        System.out.println(bc.size());
    }
}
