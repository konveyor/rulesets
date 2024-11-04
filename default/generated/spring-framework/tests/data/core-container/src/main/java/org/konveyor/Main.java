package org.konveyor;

import org.konveyor.beans.Bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;

import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.SimpleBeanInfoFactory;

public class Main {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfoFactory factory = new SimpleBeanInfoFactory();
        BeanInfo beanInfo = factory.getBeanInfo(Bean.class);
        System.out.println(beanInfo.getBeanDescriptor());
    }

}
