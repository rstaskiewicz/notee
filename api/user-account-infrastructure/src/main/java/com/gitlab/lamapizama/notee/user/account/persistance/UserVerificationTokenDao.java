package com.gitlab.lamapizama.notee.user.account.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationTokenDao extends JpaRepository<UserVerificationTokenEntity, Long> {
}
