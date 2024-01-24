package com.dabdabi4.personaldiary.persistence.entity.impl;

import com.dabdabi4.personaldiary.persistence.entity.Entity;
import java.util.Objects;
import java.util.UUID;

public class Category extends Entity implements Comparable<Category> {

    private String name;
    private String description;
    private String color;

    public Category(UUID id, String name, String description, String color) {
        super(id);
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
            ", id=" + id +
            '}';
    }

    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }
}
