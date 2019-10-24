package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.verification.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccountMailSender {

    private final JavaMailSender mailSender;

    private static final String SIGN_UP_CONFIRM_PATH = "%s/confirm/%s?token=%s";

    public void sendVerificationTokenEmail(UserEmail userEmail, RegistrationContextPath contextPath, VerificationToken verificationToken) {
        SimpleMailMessage mailMessage = createVerificationTokenMailMessage(
                userEmail.getEmail(), contextPath.getContextPath(), verificationToken.getToken().getValue());
        mailSender.send(mailMessage);
    }

    private SimpleMailMessage createVerificationTokenMailMessage(String respondentEmail,
                                                                 String contextPath,
                                                                 String token) {
        final String subject = "Sign up confirmation";
        final String message = "Please, confirm your email";
        final String confirmationUrl = String.format(SIGN_UP_CONFIRM_PATH, contextPath, respondentEmail, token);

        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(respondentEmail);
        email.setSubject(subject);
        email.setText(message + ": " + confirmationUrl);

        return email;
    }
}
