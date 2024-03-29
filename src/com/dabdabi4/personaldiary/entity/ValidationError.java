package com.dabdabi4.personaldiary.entity;

/**
 * Перерахування {@code ValidationError} містить можливі види помилок валідації для полів сутностей.
 * Кожен елемент перерахування визначає конкретну помилку та містить відповідний текстовий опис
 * помилки.
 */
public enum ValidationError {

    /**
     * Поле є обов'язковим до заповнення.
     */
    REQUIRED("Поле %S є обов'язковим до заповнення."),

    /**
     * Поле не може бути меншим за певну кількість символів.
     */
    MIN_LENGTH("Поле %s не може бути меншим за %d символів"),

    /**
     * Поле не може бути більшим за певну кількість символів.
     */
    MAX_LENGTH("Поле %s не може бути більшим за %d символів"),

    /**
     * Поле приймає лише латинські символи та символи _.
     */
    ONLY_LATIN("Поле %s приймає лише латинські символи та символи _"),

    /**
     * Поле приймає лише латинські малі та великі літери та хоча б одну цифру.
     */
    PASSWORD(
        "Поле %s приймає лише латинські малі та великі літери, та хоча б одну цифру."),

    /**
     * Поле має містити @ та одну крапку для відповідності формату електронної пошти.
     */
    EMAIL_CONTAINS("Поле %s має містити @ та одну крапку."),

    /**
     * Поле не відповідає стандартному формату електронної пошти.
     */
    EMAIL_MATCHES("Поле %s не відповідає стандартному формату електронної пошти.");

    /**
     * Текстовий опис помилки.
     */
    private final String message;

    /**
     * Конструктор для створення нового об'єкта {@code ValidationError} з вказаним текстовим описом
     * помилки.
     *
     * @param message Текстовий опис помилки.
     */
    ValidationError(String message) {
        this.message = message;
    }

    /**
     * Повертає текстовий опис помилки.
     *
     * @return Текстовий опис помилки.
     */
    public String getMessage() {
        return message;
    }
}