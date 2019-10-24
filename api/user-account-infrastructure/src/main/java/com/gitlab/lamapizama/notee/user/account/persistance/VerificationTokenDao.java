package com.gitlab.lamapizama.notee.user.account.persistance;

import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenDao extends JpaRepository<VerificationTokenEntity, Long> {

    Option<VerificationTokenEntity> findByToken(String token);
}
