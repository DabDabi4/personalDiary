package com.dabdabi4.personaldiary.entity.model;

import com.dabdabi4.personaldiary.entity.EntityError;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


/**
 * Клас {@code Diary} представляє щоденник для зберігання записів та організації інформації. Кожен
 * щоденник має унікальний ідентифікатор, дату створення, назву, опис та список записів. Реалізує
 * інтерфейс {@code Comparable} для сортування за датою створення.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"idDiary", "name", "description", "createdAt", "errors", "valid"})
public class Diary extends EntityError implements Comparable<Diary> {

    /**
     * Рядок, що представляє дату створення щоденника.
     */
    private String createdAt;

    /**
     * Унікальний ідентифікатор щоденника.
     */
    private String idDiary;
    /**
     * Назва щоденника.
     */
    private String name;
    /**
     * Опис щоденника.
     */
    private String description;
    /**
     * Список записів у щоденнику.
     */

    private List<Note> notes;
    /**
     * Ідентифікатор користувача, якому належить щоденник.
     */

    private String userId;

    /**
     * Конструктор, що створює новий об'єкт {@code Diary} з вказаними параметрами.
     *
     * @param createdAt   Дата створення щоденника.
     * @param name        Назва щоденника.
     * @param description Опис щоденника.
     * @param userId      Ідентифікатор користувача, якому належить щоденник.
     */


    public Diary(LocalDate createdAt, String name, String description, String userId) {
        this.idDiary = UUID.randomUUID().toString();
        this.createdAt = formatDate(createdAt);
        this.name = name;
        this.userId = userId;
        this.description = description;
        this.notes = new ArrayList<>(); // Додайте ініціалізацію notes
    }

    /**
     * Конструктор за замовчуванням.
     */
    public Diary() {
        // конструктор за замовчуванням
    }

    /**
     * Повертає ідентифікатор користувача, якому належить щоденник.
     *
     * @return Ідентифікатор користувача.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Встановлює ідентифікатор користувача, якому належить щоденник.
     *
     * @param userId Новий ідентифікатор користувача.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Повертає список записів у щоденнику.
     *
     * @return Список записів у щоденнику.
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Встановлює список записів у щоденнику.
     *
     * @param notes Новий список записів.
     */
    public void setNotes(List<Note> notes) {
        this.notes = notes;
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
     * Повертає дату створення щоденника у рядковому форматі.
     *
     * @return Рядок, що представляє дату створення щоденника.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Встановлює дату створення щоденника.
     *
     * @param createdAt Нова дата створення.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Повертає назву щоденника.
     *
     * @return Назва щоденника.
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву щоденника.
     *
     * @param name Нова назва щоденника.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Повертає опис щоденника.
     *
     * @return Опис щоденника.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Встановлює опис щоденника.
     *
     * @param description Новий опис щоденника.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Повертає унікальний ідентифікатор щоденника.
     *
     * @return Унікальний ідентифікатор щоденника.
     */
    public String getIdDiary() {
        return idDiary;
    }

    /**
     * Встановлює унікальний ідентифікатор щоденника.
     *
     * @param idDiary Новий унікальний ідентифікатор щоденника.
     */
    public void setIdDiary(String idDiary) {
        this.idDiary = idDiary;
    }

    /**
     * Порівнює данний щоденник з іншим об'єктом на рівність.
     *
     * @param o Об'єкт для порівняння на рівність.
     * @return {@code true}, якщо цей щоденник рівний вказаному об'єкту; {@code false} в іншому
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
        Diary diary = (Diary) o;
        return Objects.equals(name, diary.name);
    }


    /**
     * Повертає хеш-код для щоденника.
     *
     * @return Хеш-код для щоденника.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    /**
     * Повертає рядкове представлення щоденника.
     *
     * @return Рядкове представлення щоденника.
     */
    @Override
    public String toString() {
        return "Diary{" +
            "createdAt=" + createdAt +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", id=" + idDiary +
            '}';
    }

    /**
     * Порівнює данний щоденник з іншим щоденником за датою створення.
     *
     * @param o Щоденник для порівняння.
     * @return Від'ємне число, нуль або додатнє число, якщо цей щоденник менше, рівний або більший
     * за вказаний щоденник відповідно.
     */
    public int compareTo(Diary o) {
        return this.createdAt.compareTo(o.createdAt);
    }

}
