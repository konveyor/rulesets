package com.example;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.lang.Nullable;

public class Application {
    public static void main(String[] args) {
        // Rule: actuator-annotation-00010
        MyEndpoint endpoint = new MyEndpoint();
        endpoint.read(null);
    }
}

class MyEndpoint {
    @ReadOperation
    public String read(@Nullable String param) {
        return "value";
    }
}
