package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

    @GetMapping("/test")
    String test() {
        return "Protected resource";
    }

    @GetMapping("/error")
    String error() {
        throw new ResourceNotFoundException("Item does not exists");
    }
}
