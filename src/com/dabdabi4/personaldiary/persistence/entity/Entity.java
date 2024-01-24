package com.dabdabi4.personaldiary.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Entity {

    protected final UUID id;

    protected List<String> errors;

    protected boolean isValid;

    protected Entity(UUID id) {
        errors = new ArrayList<>();
        this.id = id;
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public UUID getID() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
