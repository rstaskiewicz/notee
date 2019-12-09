CREATE TABLE IF NOT EXISTS creator_profile (
    id INTEGER PRIMARY KEY,
    creator_id VARCHAR(32) NOT NULL UNIQUE,
    username   VARCHAR(32) NOT NULL,
    avatar_url VARCHAR(1000) NOT NULL,
    creator_type VARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS notebook_view (
    id INTEGER PRIMARY KEY,
    notebook_id UUID NOT NULL UNIQUE,
    notebook_name VARCHAR(32) NOT NULL,
    owner_id VARCHAR(32) NOT NULL);

CREATE TABLE IF NOT EXISTS note_view (
    id INTEGER PRIMARY KEY,
    note_id UUID NOT NULL UNIQUE,
    note_name VARCHAR(32) NOT NULL,
    note_type VARCHAR(100) NOT NULL,
    note_content VARCHAR(10000) NOT NULL,
    notebook_id UUID NOT NULL,
    created_by VARCHAR(32) NOT NULL,
    modified_by VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL);

CREATE TABLE IF NOT EXISTS comment_view (
    id INTEGER PRIMARY KEY,
    comment_id INTEGER NOT NULL,
    comment_content VARCHAR(10000) NOT NULL,
    note_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL);

CREATE TABLE IF NOT EXISTS tag_view (
    id INTEGER PRIMARY KEY,
    tag_value VARCHAR(20) NOT NULL,
    note_id UUID NOT NULL);

CREATE TABLE IF NOT EXISTS note_activity_view (
    id INTEGER PRIMARY KEY,
    event_id UUID NOT NULL,
    activity_type VARCHAR(100) NOT NULL,
    note_id UUID NOT NULL,
    creator_id VARCHAR(32) NOT NULL,
    occurred_at TIMESTAMP NOT NULL);

CREATE TABLE IF NOT EXISTS note_version_view (
    id INTEGER PRIMARY KEY,
    note_id UUID NOT NULL,
    version_id UUID NOT NULL,
    note_content VARCHAR(10000) NOT NULL,
    version_by VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL);

CREATE SEQUENCE creator_profile_seq;
CREATE SEQUENCE notebook_view_seq;
CREATE SEQUENCE note_view_seq;
CREATE SEQUENCE comment_view_seq;
CREATE SEQUENCE tag_view_seq;
CREATE SEQUENCE note_activity_view_seq;
CREATE SEQUENCE note_version_view_seq;
