package com.gitlab.lamapizama.notee.user.profile;

import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountRegistered;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static com.gitlab.lamapizama.notee.user.account.CreatorType.Regular;

@Component
@RequiredArgsConstructor
class UserAccountReadModel {

    private final JdbcTemplate userProfiles;

    @EventListener
    void handle(UserAccountRegistered event) {
        userProfiles.update("INSERT INTO creator_profile" +
                " (id, creator_id, username, avatar_url, creator_type)" +
                " VALUES (nextVal('creator_profile_seq'), ?, ?, ?, ?)",
                event.getUserEmail(),
                event.getUsername(),
                event.getAvatarUrl(),
                Regular.toString());
    }
}
