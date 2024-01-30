package com.dabdabi4.personaldiary.entity.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JsonPropertyOrder({"idNote", "idDiary", "idCategory", "tags", "title", "noteText", "createdAt",
    "errors", "valid"})
public class Note implements Comparable<Note> {

    private String createdAt;
    private String idNote;
    private String noteText;
    private String title;
    private String idDiary;
    private int idCategory;
    private List<Tag> tags;
    private String userId;


    public Note(LocalDate createdAt, String noteText, String title, String idDiary,
        int idCategory, List<Tag> tags, String userId) {
        this.idNote = UUID.randomUUID().toString();
        this.createdAt = formatDate(createdAt);
        this.noteText = noteText;
        this.title = title;
        this.idDiary = idDiary;
        this.idCategory = idCategory;
        this.tags = tags;
        this.userId = userId;
    }

    public Note() {
        // конструктор за замовчуванням
    }

    public String getUserId() {
        return userId != null ? userId : ""; // Повертаємо пустий рядок, якщо userId є null
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public String getIdDiary() {
        return idDiary;
    }

    public void setIdDiary(String idDiary) {
        this.idDiary = idDiary;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdNote() {
        return idNote;
    }

    public void setIdNote(String idNote) {
        this.idNote = idNote;
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
            ", id=" + idNote +
            '}';
    }

    public int compareTo(Note o) {
        return this.createdAt.compareTo(o.createdAt);
    }

}
