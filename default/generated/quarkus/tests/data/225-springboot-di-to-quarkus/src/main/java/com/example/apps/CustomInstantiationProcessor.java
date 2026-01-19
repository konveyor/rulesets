package com.example.apps;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import java.beans.PropertyDescriptor;

public class CustomInstantiationProcessor implements InstantiationAwareBeanPostProcessor {

    public CustomInstantiationProcessor() {
        System.out.println("InstantiationAwareBeanPostProcessor In other bean Create before create");
    }

    /** Call before other bean instantiation */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass.equals(SimpleBean.class)) {
            System.out.println(beanName + " About to instantiate");
        }
        return null;
    }

    /** After the other bean instantiation is called */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean instanceof SimpleBean) {
            System.out.println(beanName + " Instantiation complete");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
