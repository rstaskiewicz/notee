package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.creator.Creator;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import io.vavr.control.Option;

@FunctionalInterface
public interface FindNoteCreator {

    Option<Creator> by(CreatorId creatorId);
}
