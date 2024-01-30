package com.dabdabi4.personaldiary.exception;

import java.util.List;

public class EntityArgumentException extends IllegalArgumentException {


    private final List<String> errors;


    public EntityArgumentException(List<String> errors) {
        this.errors = errors;
    }


    public List<String> getErrors() {
        return errors;
    }
}