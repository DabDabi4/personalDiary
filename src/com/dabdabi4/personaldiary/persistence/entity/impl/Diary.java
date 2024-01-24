package com.dabdabi4.personaldiary.persistence.entity.impl;

import com.dabdabi4.personaldiary.persistence.entity.Entity;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Diary extends Entity implements Comparable<Diary> {

    private final LocalDateTime createdAt;
    private String name;
    private String description;
    private User author;

    public Diary(UUID id, LocalDateTime createdAt, String name, String description, User author) {
        super(id);
        this.createdAt = createdAt;
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Diary diary = (Diary) o;
        return Objects.equals(name, diary.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Diary{" +
            "createdAt=" + createdAt +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", id=" + id +
            '}';
    }

    public int compareTo(Diary o) {
        return this.createdAt.compareTo(o.createdAt);
    }

}
