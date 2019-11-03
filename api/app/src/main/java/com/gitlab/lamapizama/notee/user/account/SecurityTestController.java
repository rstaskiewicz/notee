package com.gitlab.lamapizama.notee.user.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

    @GetMapping("/test")
    String test() {
        return "Protected resource";
    }
}
