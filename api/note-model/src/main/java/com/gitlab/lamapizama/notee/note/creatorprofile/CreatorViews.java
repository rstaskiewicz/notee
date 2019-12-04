package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface CreatorViews {

    Option<CreatorView> findBy(CreatorId creatorId);

    List<NotebookView> fetchNotebooksFor(CreatorId creatorId);
}
