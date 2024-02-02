package com.dabdabi4.personaldiary.entity.model;

import com.dabdabi4.personaldiary.entity.EntityError;
import java.util.Objects;

/**
 * Клас {@code Tag} представляє тег, який може бути пов'язаний із записом у щоденнику. Кожен тег має
 * унікальний ідентифікатор, назву та колір. Реалізує інтерфейс {@code Comparable} для сортування за
 * назвою тегу.
 */
public class Tag extends EntityError implements Comparable<Tag> {

    /**
     * Унікальний ідентифікатор тегу.
     */
    private long idTag;

    /**
     * Назва тегу.
     */
    private String name;

    /**
     * Колір тегу.
     */
    private String color;

    /**
     * Конструктор, що створює новий об'єкт {@code Tag} з вказаними параметрами.
     *
     * @param idTag Унікальний ідентифікатор тегу.
     * @param name  Назва тегу.
     * @param color Колір тегу.
     */
    public Tag(long idTag, String name, String color) {
        this.idTag = idTag;
        this.name = name;
        this.color = color;
    }

    /**
     * Повертає колір тегу.
     *
     * @return Колір тегу.
     */
    public String getColor() {
        return color;
    }

    /**
     * Встановлює колір тегу.
     *
     * @param color Новий колір тегу.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Повертає назву тегу.
     *
     * @return Назва тегу.
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву тегу.
     *
     * @param name Нова назва тегу.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Порівнює данний тег з іншим тегом за назвою.
     *
     * @param o Тег для порівняння.
     * @return Від'ємне число, нуль або додатнє число, якщо цей тег менший, рівний або більший за
     * вказаний тег відповідно.
     */
    @Override
    public int compareTo(Tag o) {
        return this.name.compareTo(o.name);
    }

    /**
     * Порівнює данний тег з іншим об'єктом на рівність.
     *
     * @param o Об'єкт для порівняння на рівність.
     * @return {@code true}, якщо цей тег рівний вказаному об'єкту; {@code false} в іншому випадку.
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
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    /**
     * Повертає хеш-код для тегу.
     *
     * @return Хеш-код для тегу.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    /**
     * Повертає рядкове представлення тегу.
     *
     * @return Рядкове представлення тегу.
     */
    @Override
    public String toString() {
        return "Tag{" +
            "name='" + name + '\'' +
            ", color='" + color + '\'' +
            ", id=" + idTag +
            '}';
    }
}
