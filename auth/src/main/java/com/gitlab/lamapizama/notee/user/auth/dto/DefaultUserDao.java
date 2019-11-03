package com.gitlab.lamapizama.notee.user.auth.dto;

import com.gitlab.lamapizama.notee.user.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultUserDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String SELECT_USER_BY_USERNAME_QUERY = "SELECT email, password, confirmed"
            + " FROM user_account_entity"
            + " WHERE email = ?";

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_USER_BY_USERNAME_QUERY, new Object[] { email },
                (rs, rowNum) -> {
                    final User user = new User();
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setConfirmed(rs.getBoolean("confirmed"));

                    return user;
                }));
    }
}
