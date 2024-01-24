package com.dabdabi4.personaldiary.persistence.entity.impl;

import com.dabdabi4.personaldiary.persistence.entity.Entity;
import java.util.Objects;
import java.util.UUID;

/**
 * TODO: зробити валідацію по аналогії з User
 */
public class Tag extends Entity implements Comparable<Tag> {

    private String name;
    private String color;

    public Tag(UUID id, String name, String color) {
        super(id);
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Tag o) {
        return this.name.compareTo(o.name);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Tag{" +
            "name='" + name + '\'' +
            ", color='" + color + '\'' +
            ", id=" + id +
            '}';
    }
}
