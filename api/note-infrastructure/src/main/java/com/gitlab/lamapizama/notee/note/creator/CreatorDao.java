package com.gitlab.lamapizama.notee.note.creator;

import org.springframework.data.jpa.repository.JpaRepository;

interface CreatorDao extends JpaRepository<CreatorEntity, Long> {

    CreatorEntity findByCreatorId(String creatorId);
}
