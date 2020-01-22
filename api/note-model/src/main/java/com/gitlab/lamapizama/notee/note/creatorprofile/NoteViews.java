package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.NoteId;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface NoteViews {

    Option<NoteView> findBy(NoteId noteId);

    List<CommentView> findCommentsFor(NoteId noteId);

    List<TagView> findTagsFor(NoteId noteId);

    List<ActivityView> findActivitiesFor(NoteId noteId);

    List<NoteVersionView> findVersionsFor(NoteId noteId);

    List<NoteDashboardView> findAllNotesFor(CreatorId creatorId);

    List<NoteDashboardView> findLastNotesFor(CreatorId creatorId);
}
