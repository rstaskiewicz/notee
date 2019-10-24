package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.VerificationTokenAssigned;
import com.gitlab.lamapizama.notee.user.verification.CreateVerificationToken;
import com.gitlab.lamapizama.notee.user.verification.TokenExpiration;
import com.gitlab.lamapizama.notee.user.verification.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class VerificationEventListener {

    private final CreateVerificationToken saveVerificationToken;
    private final UserAccountMailSender mailSender;

    @Transactional
    @EventListener
    void handle(VerificationTokenAssigned event) {
        VerificationToken verificationToken = new VerificationToken(
                event.getToken(), TokenExpiration.forTokenCreatedOn(event.getTimestamp()));
        saveVerificationToken.save(verificationToken);
        mailSender.sendVerificationTokenEmail(event.getUserEmail(), event.getContextPath(), verificationToken);
    }
}
