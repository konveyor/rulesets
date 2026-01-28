package com.example.apps;

import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import java.lang.annotation.Annotation;

public class BeforeBeanDiscoveryImpl implements BeforeBeanDiscovery {
    @Override
    public void addQualifier(Class<? extends Annotation> aClass) {

    }

    @Override
    public void addQualifier(AnnotatedType<? extends Annotation> annotatedType) {

    }

    @Override
    public void addScope(Class<? extends Annotation> aClass, boolean b, boolean b1) {

    }

    @Override
    public void addStereotype(Class<? extends Annotation> aClass, Annotation... annotations) {

    }

    @Override
    public void addInterceptorBinding(AnnotatedType<? extends Annotation> annotatedType) {

    }

    @Override
    public void addInterceptorBinding(Class<? extends Annotation> aClass, Annotation... annotations) {

    }

    @Override
    public void addAnnotatedType(AnnotatedType<?> annotatedType) {

    }

    @Override
    public void addAnnotatedType(AnnotatedType<?> annotatedType, String s) {

    }
}
