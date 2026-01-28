package com.example.apps;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

public class CustomDestructionProcessor implements DestructionAwareBeanPostProcessor {

    public CustomDestructionProcessor() {
        System.out.println("DestructionAwareBeanPostProcessor In other bean Create before create");
    }

    /** The other bean is destroyed before it is destroyed. */
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (bean instanceof SimpleBean) {
            System.out.println(beanName + " About to be destroyed");
        }
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        return bean instanceof SimpleBean;
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
