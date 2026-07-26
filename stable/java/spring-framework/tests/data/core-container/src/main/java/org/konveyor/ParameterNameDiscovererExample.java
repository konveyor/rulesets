package org.konveyor;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * Test class demonstrating usage of deprecated LocalVariableTableParameterNameDiscoverer.
 * Should be migrated to StandardReflectionParameterNameDiscoverer in Spring 6.
 */
public class ParameterNameDiscovererExample {

    private final ParameterNameDiscoverer discoverer;

    public ParameterNameDiscovererExample() {
        // Deprecated in Spring 6 - use StandardReflectionParameterNameDiscoverer instead
        this.discoverer = new LocalVariableTableParameterNameDiscoverer();
    }

    public String[] getParameterNames(Method method) {
        return discoverer.getParameterNames(method);
    }

    // Example method to discover parameter names from
    public void exampleMethod(String firstName, String lastName, int age) {
        // method body
    }
}
