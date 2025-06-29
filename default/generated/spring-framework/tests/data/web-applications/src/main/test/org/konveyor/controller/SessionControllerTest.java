package org.konveyor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
class SessionControllerTest {

    @Autowired
    private SessionController sessionController;

    @Test
    void testSessionHandling() throws Exception {
        MockHttpSession session = new MockHttpSession();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sessionController).build();

        // Set session attribute by calling /setSession
        mockMvc.perform(get("/setSession").session(session))
                .andExpect(content().string("Session attribute 'username' set to 'JohnDoe'"));

        // Verify the session attribute is accessible via /getSession
        mockMvc.perform(get("/getSession").session(session))
                .andExpect(content().string("Session attribute 'username' is: JohnDoe"));

        // Ensure the session attribute is set correctly
        String username = (String) session.getAttribute("username");
        assertThat(username).isEqualTo("JohnDoe");
    }
}