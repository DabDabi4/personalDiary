package com.dabdabi4.personaldiary.persistence.entity.impl;

import com.dabdabi4.personaldiary.persistence.entity.Entity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Note extends Entity implements Comparable<Note> {

    private final LocalDateTime createdAt;
    private String noteText;
    private String title;
    private User author;
    private List<Tag> tags;

    public Note(UUID id, LocalDateTime createdAt, String noteText, String title, User author,
        List<Tag> tags) {
        super(id);
        this.createdAt = createdAt;
        this.noteText = noteText;
        this.title = title;
        this.author = author;
        this.tags = tags;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Note note = (Note) o;
        return Objects.equals(title, note.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }

    @Override
    public String toString() {
        return "Note{" +
            "noteText='" + noteText + '\'' +
            ", createdAt=" + createdAt +
            ", title='" + title + '\'' +
            ", id=" + id +
            '}';
    }

    public int compareTo(Note o) {
        return this.createdAt.compareTo(o.createdAt);
    }

}
