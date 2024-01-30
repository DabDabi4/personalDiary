package com.dabdabi4.personaldiary.entity.model;

import com.dabdabi4.personaldiary.entity.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"idDiary", "name", "description", "createdAt", "errors", "valid"})
public class Diary extends Entity implements Comparable<Diary> {


    private String createdAt;
    private String idDiary;
    private String name;
    private String description;

    private List<Note> notes;

    private String userId;


    public Diary(LocalDate createdAt, String name, String description, String userId) {
        this.idDiary = UUID.randomUUID().toString();
        this.createdAt = formatDate(createdAt);
        this.name = name;
        this.userId = userId;
        this.description = description;
        this.notes = new ArrayList<>(); // Додайте ініціалізацію notes
    }

    public Diary() {
        // конструктор за замовчуванням
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public String getIdDiary() {
        return idDiary;
    }

    public void setIdDiary(String idDiary) {
        this.idDiary = idDiary;
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
            ", id=" + idDiary +
            '}';
    }

    public int compareTo(Diary o) {
        return this.createdAt.compareTo(o.createdAt);
    }

}
