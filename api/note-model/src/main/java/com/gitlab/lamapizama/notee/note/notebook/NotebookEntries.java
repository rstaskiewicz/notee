package com.gitlab.lamapizama.notee.note.notebook;

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
}
