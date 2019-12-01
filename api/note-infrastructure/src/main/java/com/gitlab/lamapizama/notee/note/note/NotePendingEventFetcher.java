package com.gitlab.lamapizama.notee.note.note;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface NotePendingEventFetcher extends JpaRepository<NoteEventDescriptor, Long> {

    List<NoteEventDescriptor> findTop100ByStatusOrderByOccurredAtAsc(NoteEventDescriptor.Status status);

    default List<NoteEventDescriptor> listPending() {
        return findTop100ByStatusOrderByOccurredAtAsc(NoteEventDescriptor.Status.PENDING);
    }
}
