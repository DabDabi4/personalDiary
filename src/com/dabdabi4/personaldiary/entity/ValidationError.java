package com.dabdabi4.personaldiary.entity;

public enum ValidationError {

    REQUIRED("Поле %S є обовя'зковим до заповнення."),
    MIN_LENGTH("ПОле %s не може бути меншим за %d символів"),
    MAX_LENGTH("ПОле %s не може бути більшим за %d символів"),
    ONLY_LATIN("Поле %s лише латинські символи та символи _"),
    PASSWORD(
        "Поле %s латинські миволи, хочаб одна буква з великої, одна з малої та хочаб одна цифра."),
    EMAIL_CONTAINS("Поле %s має містити @ та одну крапку."),
    EMAIL_MATCHES("Поле %s не відповідає стандартному формату електронної пошти.");
    private final String message;

    ValidationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
