package com.gitlab.lamapizama.notee.note.note;

import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static javax.persistence.FetchType.EAGER;

@Entity
@NoArgsConstructor
class NoteEventStream {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_descriptor_seq")
    @SequenceGenerator(name = "note_descriptor_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID noteId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = EAGER)
    private List<NoteEventDescriptor> events = new ArrayList<>();

    public NoteEventStream(UUID noteId) {
        this.noteId = noteId;
    }

    void addEvent(NoteEventDescriptor event) {
        this.events.add(event);
    }

    List<NoteEventDescriptor> getEvents() {
        return events.stream()
                .sorted(comparing(event -> event.occurredAt))
                .collect(toList());
    }
}
