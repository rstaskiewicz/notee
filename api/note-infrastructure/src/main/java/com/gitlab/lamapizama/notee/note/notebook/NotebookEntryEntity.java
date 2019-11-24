package com.gitlab.lamapizama.notee.note.notebook;

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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"entryName", "notebook_id"}))
@NoArgsConstructor
@EqualsAndHashCode(of = {"entryId", "notebook"})
class NotebookEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    UUID entryId;

    @Column(nullable = false)
    String entryName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "notebook_id", nullable = false)
    NotebookEntity notebook;

    NotebookEntryEntity(UUID entryId, String entryName, NotebookEntity notebook) {
        this.entryId = entryId;
        this.entryName = entryName;
        this.notebook = notebook;
    }

    NotebookEntryEntity(UUID entryId, NotebookEntity notebook) {
        this.entryId = entryId;
        this.notebook = notebook;
    }
}
