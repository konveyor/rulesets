package org.konveyor;

import org.konveyor.beans.Bean;

import java.beans.IntrospectionException;
import java.beans.Introspector;

public class Main {

    public static void main(String[] args) throws IntrospectionException {
        Bean bean = new Bean();
        Introspector.getBeanInfo(bean.getClass(), Object.class);
    }

}
