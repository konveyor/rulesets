package com.example;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

/**
 * Test class demonstrating usage of javax.validation annotations.
 * Must be migrated to jakarta.validation for Spring 6.
 */
public class HibernateValidatorExample {

    @NotNull
    @NotBlank
    private String name;

    @Email
    private String email;

    @Size(min = 8, max = 100)
    private String password;

    public void processUser(@Valid UserDto user) {
        System.out.println("Processing user: " + user.getName());
    }

    public static class UserDto {
        @NotNull
        private String name;

        @Email
        private String email;

        public String getName() {
            return name;
        }
    }
}
