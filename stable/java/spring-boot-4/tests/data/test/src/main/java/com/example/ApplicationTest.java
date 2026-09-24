package com.example;

// Rule: test-change-00010
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;

// Rule: test-annotation-00010
import org.springframework.boot.test.context.SpringBootTest;

// Rule: test-change-00020
import org.springframework.boot.test.web.client.TestRestTemplate;

// Rule: test-annotation-00020
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;

// Rule: test-annotation-00030
import org.springframework.boot.test.mock.mockito.MockBean;

// Rule: test-annotation-00040
import org.springframework.boot.test.mock.mockito.SpyBean;

// Rule: test-annotation-00010
@SpringBootTest
// Rule: test-annotation-00020
@PropertyMapping
public class ApplicationTest {
    // Rule: test-annotation-00030
    @MockBean
    private Object mockService;

    // Rule: test-annotation-00040
    @SpyBean
    private Object spyService;

    public void testMethod() {
        // Rule: test-change-00010
        Class<?> listenerClass = MockitoTestExecutionListener.class;

        // Rule: test-change-00020
        TestRestTemplate restTemplate = new TestRestTemplate();
    }
}
