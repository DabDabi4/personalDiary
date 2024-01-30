package com.dabdabi4.personaldiary.entity;

import java.util.ArrayList;
import java.util.List;


public class Entity {


    protected List<String> errors;
    protected boolean isValid;

    protected Entity() {
        errors = new ArrayList<>();
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

}
