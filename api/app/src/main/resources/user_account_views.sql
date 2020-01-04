CREATE TABLE IF NOT EXISTS user_profile_view
(
    id         INTEGER PRIMARY KEY,
    user_email VARCHAR(32)   NOT NULL UNIQUE,
    full_name  VARCHAR(32)   NOT NULL,
    avatar_url VARCHAR(1000) NOT NULL
);

CREATE TABLE IF NOT EXISTS friends_view
(
    id         INTEGER PRIMARY KEY,
    user_email   VARCHAR(32) NOT NULL,
    friend_email VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS friend_invitation_view
(
    id              INTEGER PRIMARY KEY,
    inviting_email  VARCHAR(32) NOT NULL,
    user_account_id VARCHAR(32) NOT NULL,
    visited         BOOLEAN     NOT NULL
);

CREATE SEQUENCE user_profile_view_seq;
CREATE SEQUENCE friends_view_seq;
CREATE SEQUENCE friend_invitation_view_seq;
