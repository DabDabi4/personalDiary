package com.dabdabi4.personaldiary.exception;

import java.util.List;

/**
 * Клас {@code EntityArgumentException} є підкласом виключення, що використовується для викидання
 * виняткових ситуацій у випадках неправильних аргументів сутності. Віднаслідований від класу
 * {@code IllegalArgumentException}.
 */
public class EntityArgumentException extends IllegalArgumentException {

    /**
     * Список рядків, що містять інформацію про помилки в аргументах сутності.
     */
    private final List<String> errors;

    /**
     * Конструктор для створення нового об'єкта {@code EntityArgumentException} з вказаним списком
     * помилок.
     *
     * @param errors Список рядків, що містять інформацію про помилки в аргументах сутності.
     */
    public EntityArgumentException(List<String> errors) {
        this.errors = errors;
    }

    /**
     * Повертає список рядків, що містить інформацію про помилки в аргументах сутності.
     *
     * @return Список рядків із повідомленнями про помилки.
     */
    public List<String> getErrors() {
        return errors;
    }
}