package com.dabdabi4.personaldiary.entity.model;

import java.util.Objects;


/**
 * Клас {@code Category} представляє категорію для організації елементів або записів у особистому
 * щоденнику. Кожна категорія має унікальний ідентифікатор, назву, опис та пов'язаний з нею колір.
 * Реалізує інтерфейс {@code Comparable} для сортування за назвою категорії.
 */
public class Category implements Comparable<Category> {

    /**
     * Унікальний ідентифікатор для категорії.
     */
    private long idCategory;

    /**
     * Назва категорії.
     */
    private String name;

    /**
     * Опис категорії.
     */
    private String description;

    /**
     * Колір, пов'язаний із категорією.
     */
    private String color;

    /**
     * Конструктор створює новий об'єкт {@code Category} з вказаними параметрами.
     *
     * @param idCategory  Унікальний ідентифікатор для категорії.
     * @param name        Назва категорії.
     * @param description Опис категорії.
     * @param color       Колір, пов'язаний із категорією.
     */
    public Category(long idCategory, String name, String description, String color) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.color = color;
    }

    /**
     * Повертає назву категорії.
     *
     * @return Назва категорії.
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву категорії.
     *
     * @param name Нова назва для категорії.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Повертає опис категорії.
     *
     * @return Опис категорії.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Встановлює опис категорії.
     *
     * @param description Новий опис для категорії.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Повертає колір, пов'язаний із категорією.
     *
     * @return Колір, пов'язаний із категорією.
     */
    public String getColor() {
        return color;
    }

    /**
     * Встановлює колір, пов'язаний із категорією.
     *
     * @param color Новий колір для категорії.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Повертає унікальний ідентифікатор для категорії.
     *
     * @return Унікальний ідентифікатор для категорії.
     */
    public long getIdCategory() {
        return idCategory;
    }

    /**
     * Встановлює унікальний ідентифікатор для категорії.
     *
     * @param idCategory Новий унікальний ідентифікатор для категорії.
     */
    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    /**
     * Порівнює дану категорію з іншою категорією за назвою.
     *
     * @param o Категорія для порівняння.
     * @return Від'ємне число, нуль або додатнє число, якщо ця категорія менше, рівна або більша за
     * вказану категорію відповідно.
     */
    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }

    /**
     * Повертає істину, якщо інший об'єкт рівний даній категорії.
     *
     * @param o Об'єкт для порівняння на рівність.
     * @return {@code true}, якщо ця категорія рівна вказаному об'єкту; {@code false} в іншому
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
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    /**
     * Повертає хеш-код для категорії.
     *
     * @return Хеш-код для категорії.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    /**
     * Повертає рядкове представлення категорії.
     *
     * @return Рядкове представлення категорії.
     */
    @Override
    public String toString() {
        return "Category{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", color='" + color + '\'' +
            ", id=" + idCategory +
            '}';
    }
}
