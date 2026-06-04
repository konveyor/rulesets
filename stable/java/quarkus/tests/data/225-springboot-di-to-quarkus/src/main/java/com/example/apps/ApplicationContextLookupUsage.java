package com.example.apps;

import org.springframework.context.ApplicationContext;

public class ApplicationContextLookupUsage {

    private final ApplicationContext applicationContext;

    public ApplicationContextLookupUsage(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public SimpleBean loadSimpleBean() {
        return applicationContext.getBean(SimpleBean.class);
    }
}
