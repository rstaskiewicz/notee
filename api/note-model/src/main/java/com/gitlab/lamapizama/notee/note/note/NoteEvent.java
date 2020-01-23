package com.gitlab.lamapizama.notee.note.note;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gitlab.lamapizama.notee.commons.events.DomainEvent;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.content.FancyNoteContent;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

import static com.gitlab.lamapizama.notee.note.note.NoteEvent.*;
import static com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteCommented;
import static com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteCreated;
import static com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteEdited;
import static com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteTagged;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = NoteCreated.TYPE, value = NoteCreated.class),
        @JsonSubTypes.Type(name = NoteEdited.TYPE, value = NoteEdited.class),
        @JsonSubTypes.Type(name = NoteCommented.TYPE, value = NoteCommented.class),
        @JsonSubTypes.Type(name = NoteTagged.TYPE, value = NoteTagged.class),
        @JsonSubTypes.Type(name = NoteRestored.TYPE, value = NoteRestored.class)
})
public interface NoteEvent extends DomainEvent<UUID> {

    default NoteId noteId() {
        return new NoteId(getNoteId());
    }

    UUID getNoteId();

    default UUID getAggregateId() {
        return getNoteId();
    }

    String getType();

    String getCreatorId();

    UUID getEventId();

    @Value
    class NoteCreated implements NoteEvent {

        static final String TYPE = "note.created";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String noteName;
        @NonNull NoteType noteType;
        @NonNull String creatorId;
        @NonNull UUID notebookId;
        @NonNull UUID eventId;

        static NoteCreated now(NoteId noteId, NoteName noteName, NoteType noteType, CreatorId creatorId, NotebookId notebookId) {
            return new NoteCreated(
                    noteId.getId(),
                    Instant.now(),
                    noteName.getName(),
                    noteType,
                    creatorId.getId(),
                    notebookId.getId(),
                    UUID.randomUUID());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Value
    class NoteEdited implements NoteEvent {

        static final String TYPE = "note.edited";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String creatorId;
        @NonNull FancyNoteContent noteContent;
        @NonNull UUID eventId;

        static NoteEdited now(NoteId noteId, CreatorId editorId, FancyNoteContent noteContent) {
            return new NoteEdited(
                    noteId.getId(),
                    Instant.now(),
                    editorId.getId(),
                    noteContent,
                    UUID.randomUUID());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Value
    class NoteEditingFailed implements NoteEvent {

        static final String TYPE = "note.editing.failed";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String creatorId;
        @NonNull UUID eventId;

        static NoteEditingFailed now(NoteId noteId, CreatorId creatorId) {
            return new NoteEditingFailed(
                    noteId.getId(),
                    Instant.now(),
                    creatorId.getId(),
                    UUID.randomUUID());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Value
    class NoteCommented implements NoteEvent {

        static final String TYPE = "note.commented";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String creatorId;
        @NonNull Integer commentId;
        @NonNull String commentContent;
        @NonNull UUID eventId;

        static NoteCommented now(NoteId noteId, CreatorId commenterId, CommentId commentId, CommentContent commentContent) {
            return new NoteCommented(
                    noteId.getId(),
                    Instant.now(),
                    commenterId.getId(),
                    commentId.getId(),
                    commentContent.getContent(),
                    UUID.randomUUID());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Value
    class NoteCommentingFailed implements NoteEvent {

        static final String TYPE = "note.commenting.failed";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String creatorId;
        @NonNull UUID eventId;

        static NoteCommentingFailed now(NoteId noteId, CreatorId commenterId) {
            return new NoteCommentingFailed(
                    noteId.getId(),
                    Instant.now(),
                    commenterId.getId(),
                    UUID.randomUUID());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Value
    class NoteTagged implements NoteEvent {

        static final String TYPE = "note.tagged";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String creatorId;
        @NonNull String tag;
        @NonNull UUID eventId;

        static NoteTagged now(NoteId noteId, CreatorId taggerId, Tag tag) {
            return new NoteTagged(
                    noteId.getId(),
                    Instant.now(),
                    taggerId.getId(),
                    tag.getValue(),
                    UUID.randomUUID());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Value
    class NoteTaggingFailed implements NoteEvent {

        static final String TYPE = "note.tagging.failed";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String creatorId;
        @NonNull UUID eventId;

        static NoteTaggingFailed now(NoteId noteId, CreatorId taggerId) {
            return new NoteTaggingFailed(
                    noteId.getId(),
                    Instant.now(),
                    taggerId.getId(),
                    UUID.randomUUID());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Value
    class NoteRestored implements NoteEvent {

        static final String TYPE = "note.reverted";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String creatorId;
        @NonNull FancyNoteContent restoredContent;
        @NonNull UUID eventId;

        static NoteRestored now(NoteId noteId, FancyNoteContent restoredContent, CreatorId restorerId) {
            return new NoteRestored(
                    noteId.getId(),
                    Instant.now(),
                    restorerId.getId(),
                    restoredContent,
                    UUID.randomUUID());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Value
    class NoteRestoringFailed implements NoteEvent {

        static final String TYPE = "note.reverting.failed";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String creatorId;
        @NonNull UUID eventId;

        static NoteRestoringFailed now(NoteId noteId, CreatorId restorerId) {
            return new NoteRestoringFailed(
                    noteId.getId(),
                    Instant.now(),
                    restorerId.getId(),
                    UUID.randomUUID());
        }

        public String getType() {
            return TYPE;
        }
    }
}
