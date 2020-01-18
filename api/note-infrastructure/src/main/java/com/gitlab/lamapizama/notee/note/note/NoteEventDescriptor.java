package com.gitlab.lamapizama.notee.note.note;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.Instant;
import java.util.UUID;

import static com.gitlab.lamapizama.notee.note.note.NoteEventDescriptor.Status.PENDING;
import static com.gitlab.lamapizama.notee.note.note.NoteEventDescriptor.Status.SENT;
import static javax.persistence.EnumType.STRING;

@Entity
@NoArgsConstructor
class NoteEventDescriptor {

    public enum Status {
        PENDING, SENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_descriptor_seq")
    @SequenceGenerator(name = "note_descriptor_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 10000)
    String body;

    @Column(nullable = false)
    Instant occurredAt;

    @Column(nullable = false)
    String type;

    @Column(nullable = false)
    UUID noteId;

    @Enumerated(STRING)
    @Column(nullable = false)
    Status status = PENDING;

    NoteEventDescriptor(String body, Instant occurredAt, String type, UUID noteId) {
        this.body = body;
        this.occurredAt = occurredAt;
        this.type = type;
        this.noteId = noteId;
    }

    NoteEventDescriptor markAsSent() {
        this.status = SENT;
        return this;
    }
}
