package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.account.request.RegisterUserAccountRequest;
import com.gitlab.lamapizama.notee.user.verification.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountMapper mapper;
    private final RegisteringUserAccount registeringUserAccount;
    private final ConfirmingUserRegistration confirmingUserRegistration;

    @PostMapping("/register")
    void registerUserAccount(@RequestBody @Valid RegisterUserAccountRequest request) {
        registeringUserAccount.register(mapper.fromCreate(request));
    }

    @GetMapping("/confirm/{email}")
    void confirmUserRegistration(@PathVariable String email, @RequestParam String token) {
        confirmingUserRegistration.confirm(new ConfirmUserRegistrationCommand(new UserEmail(email), new Token(token)));
    }
}
