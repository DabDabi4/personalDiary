package com.dabdabi4.personaldiary.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас {@code Entity} є базовим класом для всіх сутностей системи. Визначає спільні властивості та
 * методи для обробки помилок та валідації.
 */
public class EntityError {

    /**
     * Список помилок, пов'язаних із об'єктом сутності.
     */
    protected List<String> errors;

    /**
     * Флаг, що вказує на те, чи є об'єкт сутності валідним.
     */
    protected boolean isValid;

    /**
     * Конструктор за замовчуванням, що ініціалізує список помилок.
     */
    protected EntityError() {
        errors = new ArrayList<>();
    }

    /**
     * Перевіряє, чи є об'єкт сутності валідним.
     *
     * @return {@code true}, якщо об'єкт сутності валідний; {@code false} в іншому випадку.
     */
    public boolean isValid() {
        return errors.isEmpty();
    }

    /**
     * Повертає список помилок, пов'язаних із об'єктом сутності.
     *
     * @return Список помилок.
     */
    public List<String> getErrors() {
        return errors;
    }
}
