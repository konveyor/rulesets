package org.konveyor;

import org.konveyor.beans.Bean;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.SimpleBeanInfoFactory;
import org.springframework.scheduling.annotation.Async;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.util.concurrent.RunnableFuture;

public class Main {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfoFactory factory = new SimpleBeanInfoFactory();
        BeanInfo beanInfo = factory.getBeanInfo(Bean.class);
        System.out.println(beanInfo.getBeanDescriptor());


    }

    @Async
    public RunnableFuture<String> correctAsyncMethod(String param) {
        return null;
    }

    @Async
    public void correctAsyncMethod2(String param) {
    }

}
