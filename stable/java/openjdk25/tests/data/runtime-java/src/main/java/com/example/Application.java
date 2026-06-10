package com.example;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk21-to-openjdk25+-00610
        VarHandle vh1 = MethodHandles.byteArrayViewVarHandle(int[].class, ByteOrder.BIG_ENDIAN);

        // Rule: openjdk21-to-openjdk25+-00620
        VarHandle vh2 = MethodHandles.byteBufferViewVarHandle(int[].class, ByteOrder.BIG_ENDIAN);

        System.out.println(vh1);
        System.out.println(vh2);
    }
}
