package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.note.NoteId;
import com.gitlab.lamapizama.notee.note.note.NoteName;
import io.vavr.collection.Set;
import lombok.NonNull;
import lombok.Value;

@Value
class NotebookEntries {

    static int MAX_NUMBER_OF_FREE_NOTES = 5;

    @NonNull
    Set<NotebookEntry> entries;

    int count() {
        return entries.size();
    }

    boolean reachMaximumFreeEntriesAfterEntry() {
        return count() + 1 == MAX_NUMBER_OF_FREE_NOTES;
    }

    boolean withName(NoteName noteName) {
        return entries.find(entry -> entry.getName().equals(noteName)).isDefined();
    }

    boolean withId(NoteId noteId) {
        return entries.find(entry -> entry.getId().equals(noteId)).isDefined();
    }
}
