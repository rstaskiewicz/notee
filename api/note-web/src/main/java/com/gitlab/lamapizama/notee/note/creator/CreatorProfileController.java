package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookName;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/profiles/{creatorId}")
@RequiredArgsConstructor
public class CreatorProfileController {

    private final CreatingNotebook creatingNotebook;
    private final DeletingNotebook deletingNotebook;

    @PostMapping("/notebooks")
    void createNotebook(@PathVariable String creatorId, @RequestBody @Valid CreateNotebookRequest request) {
        creatingNotebook.create(new CreateNotebook(
                new CreatorId(creatorId),
                new NotebookName(request.getName())));
    }

    @DeleteMapping("/notebooks/{notebookId}")
    void deleteNotebook(@PathVariable String creatorId, @PathVariable UUID notebookId) {
        deletingNotebook.delete(new DeleteNotebook(
                new CreatorId(creatorId),
                new NotebookId(notebookId)));
    }
}
