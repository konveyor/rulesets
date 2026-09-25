package com.example;

// Rule: test-import-00010
import org.springframework.test.context.junit4.SpringRunner;

// Rule: test-import-00020
import org.springframework.mock.web.MockHttpServletRequest;

// Rule: test-import-00030
import org.springframework.mock.web.MockHttpServletResponse;

// Rule: test-change-00010
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import org.junit.runner.RunWith;
import org.junit.Test;

@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Test
    public void testMockServletRequest() throws Exception {
        // Rule: test-import-00020
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Rule: test-import-00030
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Rule: test-change-00010 - method reference
        CustomListener listener = new CustomListener();
        TestContext context = null; // mock
        listener.prepareTestInstance(context);
    }

    // Rule: test-change-00010
    static class CustomListener implements TestExecutionListener {
        @Override
        public void prepareTestInstance(TestContext testContext) throws Exception {
            // Custom test execution listener
        }
    }
}
