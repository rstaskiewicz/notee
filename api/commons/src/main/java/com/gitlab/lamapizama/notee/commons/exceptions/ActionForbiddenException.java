package com.gitlab.lamapizama.notee.commons.exceptions;

public class ActionForbiddenException extends RuntimeException {

    public ActionForbiddenException(String message) {
        super(message);
    }
}
