package com.dabdabi4.personaldiary.entity.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Клас {@code Note} представляє запис в особистому щоденнику. Кожен запис має унікальний
 * ідентифікатор, дату створення, текст, назву, ідентифікатор щоденника, ідентифікатор категорії,
 * список тегів та ідентифікатор користувача. Реалізує інтерфейс {@code Comparable} для сортування
 * за датою створення.
 */

@JsonPropertyOrder({"idNote", "idDiary", "idCategory", "tags", "title", "noteText", "createdAt",
    "errors", "valid"})
public class Note implements Comparable<Note> {

    /**
     * Рядок, що представляє дату створення запису.
     */
    private String createdAt;
    /**
     * Унікальний ідентифікатор запису.
     */
    private String idNote;
    /**
     * Текст запису.
     */
    private String noteText;

    /**
     * Назва запису.
     */
    private String title;

    /**
     * Ідентифікатор щоденника, до якого належить запис.
     */
    private String idDiary;
    /**
     * Ідентифікатор категорії, до якої належить запис.
     */
    private int idCategory;

    /**
     * Список тегів, пов'язаних із записом.
     */
    private List<Tag> tags;
    /**
     * Ідентифікатор користувача, якому належить запис.
     */
    private String userId;

    /**
     * Конструктор, що створює новий об'єкт {@code Note} з вказаними параметрами.
     *
     * @param createdAt  Дата створення запису.
     * @param noteText   Текст запису.
     * @param title      Назва запису.
     * @param idDiary    Ідентифікатор щоденника, до якого належить запис.
     * @param idCategory Ідентифікатор категорії, до якої належить запис.
     * @param tags       Список тегів, пов'язаних із записом.
     * @param userId     Ідентифікатор користувача, якому належить запис.
     */


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

    /**
     * Повертає ідентифікатор користувача, якому належить запис.
     *
     * @return Ідентифікатор користувача або пустий рядок, якщо ідентифікатор є null.
     */
    public String getUserId() {
        return userId != null ? userId : ""; // Повертаємо пустий рядок, якщо userId є null
    }

    /**
     * Приватний метод для форматування дати у вказаний рядковий формат.
     *
     * @param date Дата, яку необхідно сформатувати.
     * @return Рядок, що представляє сформатовану дату.
     */
    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    /**
     * Повертає ідентифікатор щоденника, до якого належить запис.
     *
     * @return Ідентифікатор щоденника.
     */
    public String getIdDiary() {
        return idDiary;
    }

    /**
     * Встановлює ідентифікатор щоденника, до якого належить запис.
     *
     * @param idDiary Новий ідентифікатор щоденника.
     */
    public void setIdDiary(String idDiary) {
        this.idDiary = idDiary;
    }

    /**
     * Повертає ідентифікатор категорії, до якої належить запис.
     *
     * @return Ідентифікатор категорії.
     */
    public int getIdCategory() {
        return idCategory;
    }

    /**
     * Встановлює ідентифікатор категорії, до якої належить запис.
     *
     * @param idCategory Новий ідентифікатор категорії.
     */
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    /**
     * Повертає список тегів, пов'язаних із записом.
     *
     * @return Список тегів.
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Встановлює список тегів, пов'язаних із записом.
     *
     * @param tags Новий список тегів.
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Повертає текст запису.
     *
     * @return Текст запису.
     */
    public String getNoteText() {
        return noteText;
    }

    /**
     * Встановлює текст запису.
     *
     * @param noteText Новий текст запису.
     */
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    /**
     * Повертає дату створення запису у рядковому форматі.
     *
     * @return Рядок, що представляє дату створення запису.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Повертає назву запису.
     *
     * @return Назва запису.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Встановлює назву запису.
     *
     * @param title Нова назва запису.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Повертає унікальний ідентифікатор запису.
     *
     * @return Унікальний ідентифікатор запису.
     */
    public String getIdNote() {
        return idNote;
    }

    /**
     * Встановлює унікальний ідентифікатор запису.
     *
     * @param idNote Новий унікальний ідентифікатор запису.
     */

    public void setIdNote(String idNote) {
        this.idNote = idNote;
    }

    /**
     * Порівнює данний запис з іншим об'єктом на рівність.
     *
     * @param o Об'єкт для порівняння на рівність.
     * @return {@code true}, якщо цей запис рівний вказаному об'єкту; {@code false} в іншому
     * випадку.
     */
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

    /**
     * Повертає хеш-код для запису.
     *
     * @return Хеш-код для запису.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }

    /**
     * Повертає рядкове представлення запису.
     *
     * @return Рядкове представлення запису.
     */
    @Override
    public String toString() {
        return "Note{" +
            "noteText='" + noteText + '\'' +
            ", createdAt=" + createdAt +
            ", title='" + title + '\'' +
            ", id=" + idNote +
            '}';
    }

    /**
     * Порівнює данний запис з іншим записом за датою створення.
     *
     * @param o Запис для порівняння.
     * @return Від'ємне число, нуль або додатнє число, якщо цей запис менший, рівний або більший за
     * вказаний запис відповідно.
     */
    public int compareTo(Note o) {
        return this.createdAt.compareTo(o.createdAt);
    }

}
