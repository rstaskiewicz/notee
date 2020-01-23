package com.gitlab.lamapizama.notee.note.note.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FancyNoteContent {
    Instant time;
    List<NoteBlock> blocks;
}
