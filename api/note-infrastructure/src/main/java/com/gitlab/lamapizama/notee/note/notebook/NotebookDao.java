package com.gitlab.lamapizama.notee.note.notebook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface NotebookDao extends JpaRepository<NotebookEntity, Long> {

    NotebookEntity findByNotebookId(UUID notebookId);
}
