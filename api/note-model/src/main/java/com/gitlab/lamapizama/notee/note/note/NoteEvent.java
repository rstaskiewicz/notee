package com.gitlab.lamapizama.notee.note.note;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gitlab.lamapizama.notee.commons.events.DomainEvent;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

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
        @JsonSubTypes.Type(name = NoteTagged.TYPE, value = NoteTagged.class)
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

    @Value
    class NoteCreated implements NoteEvent {

        static final String TYPE = "note.created";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String noteName;
        @NonNull NoteType noteType;
        @NonNull UUID notebookId;

        static NoteCreated now(NoteId noteId, NoteName noteName, NoteType noteType, NotebookId notebookId) {
            return new NoteCreated(
                    noteId.getId(),
                    Instant.now(),
                    noteName.getName(),
                    noteType,
                    notebookId.getId());
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
        @NonNull String noteContent;

        static NoteEdited now(NoteId noteId, CreatorId editorId, NoteContent noteContent) {
            return new NoteEdited(
                    noteId.getId(),
                    Instant.now(),
                    editorId.getId(),
                    noteContent.getContent());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class NoteEditingFailed implements NoteEvent {

        static final String TYPE = "note.editing.failed";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String creatorId;

        static NoteEditingFailed now(NoteId noteId, CreatorId creatorId) {
            return new NoteEditingFailed(
                    noteId.getId(),
                    Instant.now(),
                    creatorId.getId());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class NoteCommented implements NoteEvent {

        static final String TYPE = "note.commented";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String commenterId;
        @NonNull Integer commentId;
        @NonNull String commentContent;

        static NoteCommented now(NoteId noteId, CreatorId commenterId, CommentId commentId, CommentContent commentContent) {
            return new NoteCommented(
                    noteId.getId(),
                    Instant.now(),
                    commenterId.getId(),
                    commentId.getId(),
                    commentContent.getContent());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class NoteCommentingFailed implements NoteEvent {

        static final String TYPE = "note.commenting.failed";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String commenterId;

        static NoteCommentingFailed now(NoteId noteId, CreatorId commenterId) {
            return new NoteCommentingFailed(
                    noteId.getId(),
                    Instant.now(),
                    commenterId.getId());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class NoteTagged implements NoteEvent {

        static final String TYPE = "note.tagged";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String taggerId;
        @NonNull Set<String> tags;

        static NoteTagged now(NoteId noteId, CreatorId taggerId, Tags tags) {
            return new NoteTagged(
                    noteId.getId(),
                    Instant.now(),
                    taggerId.getId(),
                    tags.getTags()
                            .map(Tag::getValue)
                            .toJavaSet());
        }

        public String getType() {
            return TYPE;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class NoteTaggingFailed implements NoteEvent {

        static final String TYPE = "note.tagging.failed";

        @NonNull UUID noteId;
        @NonNull Instant when;
        @NonNull String taggerId;

        static NoteTaggingFailed now(NoteId noteId, CreatorId taggerId) {
            return new NoteTaggingFailed(
                    noteId.getId(),
                    Instant.now(),
                    taggerId.getId());
        }

        public String getType() {
            return TYPE;
        }
    }
}
