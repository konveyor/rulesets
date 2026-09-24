package com.example;

// Rule: testing-annotation-00010
import org.springframework.security.test.context.support.WithMockUser;
// Rule: testing-annotation-00020
import org.springframework.security.test.context.support.WithUserDetails;

public class ApplicationTest {

    // Rule: testing-annotation-00010
    @WithMockUser
    public void testWithMockUser() {
        // Test method using @WithMockUser annotation
    }

    // Rule: testing-annotation-00020
    @WithUserDetails
    public void testWithUserDetails() {
        // Test method using @WithUserDetails annotation
    }
}
