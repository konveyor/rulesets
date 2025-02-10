package org.konveyor.controller.MockTestRule;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/setSession")
    public String setSession(HttpSession session) {
        session.setAttribute("username", "JohnDoe");
        return "Session attribute 'username' set to 'JohnDoe'";
    }

    @GetMapping("/getSession")
    public String getSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "No session attribute found for 'username'";
        }
        return "Session attribute 'username' is: " + username;
    }
}