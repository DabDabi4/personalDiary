package com.dabdabi4.personaldiary.entity.model;

/**
 * Перерахування {@code Path} представляє шляхи до різних файлів у системі. В даному випадку,
 * використовується для визначення шляху до файлу із даними користувачів.
 */
public enum Path {
    /**
     * Шлях до файлу із даними користувачів у форматі JSON.
     */
    USER_JSON("src//com//dabdabi4//personaldiary//repository//UserData.json"),
    NOTE_JSON("src//com//dabdabi4//personaldiary//repository//note.json"),
    DIARY_JSON("src//com//dabdabi4//personaldiary//repository//diary.json");

    /**
     * Рядок, що представляє шлях до файлу.
     */
    private final String path;

    /**
     * Конструктор, що створює новий об'єкт {@code Path} з вказаним шляхом.
     *
     * @param path Рядок, що представляє шлях до файлу.
     */
    Path(String path) {
        this.path = path;
    }

    /**
     * Повертає шлях до файлу.
     *
     * @return Рядок, що представляє шлях до файлу.
     */
    public String getPath() {
        return path;
    }
}
