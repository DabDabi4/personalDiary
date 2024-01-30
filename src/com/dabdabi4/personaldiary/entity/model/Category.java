package com.dabdabi4.personaldiary.entity.model;

import java.util.Objects;


public class Category implements Comparable<Category> {

    private long idCategory;
    private String name;
    private String description;
    private String color;

    public Category(long idCategory, String name, String description, String color) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
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
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Category{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", color='" + color + '\'' +
            ", id=" + idCategory +
            '}';
    }

    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }
}
