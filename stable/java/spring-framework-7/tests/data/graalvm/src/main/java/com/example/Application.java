package com.example;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;

public class Application {
    public static void main(String[] args) {
        RuntimeHints hints = new RuntimeHints();

        // Rule: graalvm-change-00010
        hints.reflection().registerType(Application.class, MemberCategory.DECLARED_FIELDS);

        // Rule: graalvm-change-00020
        hints.reflection().registerType(Application.class, MemberCategory.PUBLIC_FIELDS);

        // Rule: graalvm-change-00030
        ExecutableMode mode = ExecutableMode.INTROSPECT;
    }
}
