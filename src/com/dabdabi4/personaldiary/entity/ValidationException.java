package com.dabdabi4.personaldiary.entity;


/**
 * Клас {@code ValidationException} є виключенням, що використовується для викидання помилок
 * валідації. Віднаслідований від класу {@code Exception}.
 */
public class ValidationException extends Exception {

    /**
     * Об'єкт типу {@code ValidationError}, що містить інформацію про вид помилки валідації.
     */
    private final ValidationError validationError;

    /**
     * Конструктор для створення нового об'єкта {@code ValidationException} з вказаною помилкою
     * валідації.
     *
     * @param validationError Об'єкт типу {@code ValidationError} з інформацією про вид помилки
     *                        валідації.
     */
    public ValidationException(ValidationError validationError) {
        this.validationError = validationError;
    }

    /**
     * Повертає об'єкт типу {@code ValidationError}, що містить інформацію про вид помилки
     * валідації.
     *
     * @return Об'єкт типу {@code ValidationError}.
     */
    public ValidationError getValidationError() {
        return validationError;
    }

    /**
     * Повертає текстовий опис помилки валідації.
     *
     * @return Текстовий опис помилки валідації.
     */
    public String getErrorMessage() {
        return validationError.getMessage();
    }
}
