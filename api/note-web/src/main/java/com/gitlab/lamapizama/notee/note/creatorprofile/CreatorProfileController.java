package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import com.gitlab.lamapizama.notee.note.creator.CreateNotebook;
import com.gitlab.lamapizama.notee.note.creator.CreatingNotebook;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.creator.CreatorType;
import com.gitlab.lamapizama.notee.note.creator.DeleteNotebook;
import com.gitlab.lamapizama.notee.note.creator.DeletingNotebook;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookName;
import io.vavr.control.Try;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/creators/{creatorId}")
@RequiredArgsConstructor
public class CreatorProfileController {

    private final CreatorViews creatorProfiles;
    private final CreatingNotebook creatingNotebook;
    private final DeletingNotebook deletingNotebook;

    @GetMapping
    ResponseEntity<CreatorModel> creator(@PathVariable String creatorId) {
        return creatorProfiles.findBy(new CreatorId(creatorId))
                .map(creator -> ok(new CreatorModel(creatorId, creator.creatorType)))
                .getOrElse(() -> notFound().build());
    }

    @GetMapping("/notebooks")
    ResponseEntity<CollectionModel<EntityModel<NotebookView>>> findNotebooks(@PathVariable String creatorId) {
        List<EntityModel<NotebookView>> notebooks = creatorProfiles.fetchNotebooksFor(new CreatorId(creatorId))
                .map(notebook -> notebookWithLinkToSelf(creatorId, notebook))
                .collect(toList());
        return ok(new CollectionModel<>(
                notebooks,
                linkTo(methodOn(CreatorProfileController.class).findNotebooks(creatorId))
                        .withSelfRel()));
    }

    @PostMapping("/notebooks")
    ResponseEntity<?> createNotebook(@PathVariable String creatorId, @RequestBody @Valid CreateNotebookRequest request) {
        Try<Result> result = creatingNotebook.create(new CreateNotebook(
                new CreatorId(creatorId),
                new NotebookName(request.getName())));
        return result
                .map(success -> ok().build())
                .getOrElse(status(INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/notebooks/{notebookId}")
    ResponseEntity<?> deleteNotebook(@PathVariable String creatorId, @PathVariable UUID notebookId) {
        Try<Result> result = deletingNotebook.delete(new DeleteNotebook(
                new CreatorId(creatorId),
                new NotebookId(notebookId)));
        return result
                .map(success -> noContent().build())
                .recover(r -> Match(r).of(Case($(instanceOf(ResourceNotFoundException.class)), notFound().build())))
                .getOrElse(status(INTERNAL_SERVER_ERROR).build());
    }

    private EntityModel<NotebookView> notebookWithLinkToSelf(String creatorId, NotebookView notebook) {
        return new EntityModel<>(
                notebook,
                linkTo(methodOn(NotebookController.class).notebook(notebook.notebookId))
                        .withSelfRel()
                        .andAffordance(afford(methodOn(CreatorProfileController.class)
                                .deleteNotebook(creatorId, notebook.notebookId))));
    }
}

@Value
@EqualsAndHashCode(callSuper = false)
class CreatorModel extends RepresentationModel<CreatorModel> {

    String id;
    CreatorType type;

    CreatorModel(String id, CreatorType type) {
        this.id = id;
        this.type = type;
        add(linkTo(methodOn(CreatorProfileController.class).creator(id)).withSelfRel());
        add(linkTo(methodOn(CreatorProfileController.class).findNotebooks(id)).withRel("notebooks"));
    }
}
