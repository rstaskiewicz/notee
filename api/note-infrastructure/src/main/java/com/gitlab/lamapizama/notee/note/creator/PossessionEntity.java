package com.gitlab.lamapizama.notee.note.creator;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"notebookName", "creator_id"}))
@NoArgsConstructor
@EqualsAndHashCode(of = {"possessionId", "creator"})
class PossessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    UUID possessionId;

    @Column(nullable = false)
    String notebookName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    CreatorEntity creator;

    PossessionEntity(UUID possessionId, String notebookName, CreatorEntity creator) {
        this.possessionId = possessionId;
        this.notebookName = notebookName;
        this.creator = creator;
    }

    PossessionEntity(UUID possessionId, CreatorEntity creator) {
        this.possessionId = possessionId;
        this.creator = creator;
    }
}
