package com.example.apps;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.annotation.ManagedAttribute;

@ManagedResource(objectName="bean:name=jmxTestBean", description="Test Bean")
public class SpringbootAnnotationConfiguration {

    private String item;

    @ManagedAttribute(description="Item", currencyTimeLimit=15)
    public int getItem() {
        return 3;
    }

    public void setItem(int item) {

    }

}
