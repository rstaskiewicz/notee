package com.gitlab.lamapizama.notee.user.account.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountDao extends JpaRepository<UserAccountEntity, Long> {

    UserAccountEntity findByEmail(String email);
}
