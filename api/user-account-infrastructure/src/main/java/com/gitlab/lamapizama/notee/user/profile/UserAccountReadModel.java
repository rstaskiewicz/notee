package com.gitlab.lamapizama.notee.user.profile;

import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.InvitationAccepted;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.InvitationReceived;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountRegistered;
import com.gitlab.lamapizama.notee.user.account.UserEmail;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static io.vavr.collection.List.ofAll;
import static io.vavr.control.Option.none;
import static io.vavr.control.Option.of;

@Component
@RequiredArgsConstructor
class UserAccountReadModel implements UserAccountViews {

    private final JdbcTemplate views;

    @EventListener
    void handle(UserAccountRegistered event) {
        views.update("INSERT INTO user_profile_view" +
                " (id, user_email, full_name, avatar_url)" +
                " VALUES (nextVal('user_profile_view_seq'), ?, ?, ?)",
                event.getUserEmail(),
                event.getUsername(),
                event.getAvatarUrl());
    }

    @Override
    public List<UserProfileView> findUserAccounts() {
        return ofAll(views.query("SELECT * FROM user_profile_view", new BeanPropertyRowMapper<>(UserProfileView.class)));
    }

    @Override
    public Option<UserProfileView> findUserAccount(@NonNull UserEmail userEmail) {
        return Try.ofSupplier(() -> of(views.queryForObject("SELECT * FROM user_profile_view WHERE user_email = ?",
                new BeanPropertyRowMapper<>(UserProfileView.class),
                userEmail.getEmail())))
                .getOrElse(none());
    }

    @Override
    public List<InvitationView> findInvitationsFor(UserEmail userEmail, boolean onlyNew) {
        String query = "SELECT * FROM friend_invitation_view WHERE user_account_id = ?";
        if (onlyNew) {
            query += " AND visited = false";
        }
        return ofAll(views.query(query,
                new BeanPropertyRowMapper<>(InvitationView.class),
                userEmail.getEmail()));
    }

    @Override
    public List<UserProfileView> findFriendsFor(@NonNull UserEmail userEmail) {
        return ofAll(views.query("SELECT * FROM user_profile_view" +
                " JOIN friends_view ON friends_view.user_email = user_profile_view.user_email" +
                " WHERE friends_view.friend_email = ?",
                new BeanPropertyRowMapper<>(UserProfileView.class),
                userEmail.getEmail()));
    }

    @Override
    public void markInvitationsAsVisitedFor(@NonNull UserEmail userEmail) {
        views.update("UPDATE friend_invitation_view" +
                        " SET visited = true" +
                        " WHERE user_account_id = ?", userEmail.getEmail());
    }

    @EventListener
    void handle(InvitationReceived event) {
        views.update("INSERT INTO friend_invitation_view" +
                " (id, inviting_email, user_account_id, visited)" +
                " VALUES (nextval('friend_invitation_view_seq'), ?, ?, false)",
                event.getInvitingEmail(),
                event.getUserEmail());
    }

    @EventListener
    void handle(InvitationAccepted event) {
        views.update("DELETE FROM friend_invitation_view" +
                " WHERE inviting_email = ? and user_account_id = ?",
                event.getUserEmail(),
                event.getFriendEmail());
        views.update("INSERT INTO friends_view" +
                " (id, user_email, friend_email)" +
                " VALUES (nextval('friends_view_seq'), ?, ?)",
                event.getUserEmail(),
                event.getFriendEmail());
        views.update("INSERT INTO friends_view" +
                        " (id, user_email, friend_email)" +
                        " VALUES (nextval('friends_view_seq'), ?, ?)",
                event.getFriendEmail(),
                event.getUserEmail());
    }
}
