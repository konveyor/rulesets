package com.example.apps;

import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.BeanManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.InjectionPoint;


public class JakartaCDI implements Bean<String>{
    public static void main(String[] args) {

        Bean<String> bean = new JakartaCDI();

        if (bean.isNullable()) {
            BeanManager b = new JakartaBeanManager();
            AnnotatedType<CdiRequestScope> annotatedType = b.createAnnotatedType(CdiRequestScope.class);
            b.createInjectionTarget(annotatedType);

            b.fireEvent(new Object(), null);

            BeforeBeanDiscovery beforeBeanDiscovery = new BeforeBeanDiscoveryImpl();
            beforeBeanDiscovery.addAnnotatedType(null);
        }


    }

    @Override
    public Class<?> getBeanClass() {
        return null;
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return new HashSet<>();
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public String create(CreationalContext<String> creationalContext) {
        return "";
    }

    @Override
    public void destroy(String s, CreationalContext<String> creationalContext) {

    }

    @Override
    public Set<Type> getTypes() {
        return new HashSet<>();
    }

    @Override
    public Set<Annotation> getQualifiers() {
        return new HashSet<>();
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return new HashSet<>();
    }

    @Override
    public boolean isAlternative() {
        return false;
    }
}
