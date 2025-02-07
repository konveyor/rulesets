package org.konveyor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HelloControllerTest {

    @Autowired
    private HelloController helloController;

    @Test
    void testSayHello() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("name", "John");

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();

        try {
            var result = mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/hello")
                        .param("name", "John")
            ).andReturn();

            String responseContent = result.getResponse().getContentAsString();
            assertThat(responseContent).isEqualTo("Hello, John!");
        } catch (Exception e) {
            e.printStackTrace();
            assertThat(false).isTrue(); // Ensure failure if an exception occurs
        }
    }
}