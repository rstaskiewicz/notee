package com.gitlab.lamapizama.notee.note.creator;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@EqualsAndHashCode(of = {"notebookId", "creator"})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class PossessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    UUID notebookId;

    @Column(nullable = false)
    String notebookName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    CreatorEntity creator;

    PossessionEntity(UUID notebookId, String notebookName, CreatorEntity creator) {
        this.notebookId = notebookId;
        this.notebookName = notebookName;
        this.creator = creator;
    }

    PossessionEntity(UUID notebookId, CreatorEntity creator) {
        this.notebookId = notebookId;
        this.creator = creator;
    }
}
