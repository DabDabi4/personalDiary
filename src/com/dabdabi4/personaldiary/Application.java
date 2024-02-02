package com.dabdabi4.personaldiary;

import com.dabdabi4.personaldiary.entity.model.User;
import com.dabdabi4.personaldiary.view.Menu;

/**
 * Клас, який представляє основний клас програми для її запуску.
 */
public class Application {

    /**
     * Масив користувачів.
     */
    public static User[] users;

    /**
     * Поточний користувач.
     */
    public static User currentUser = new User("Den32131", "Denis123@gmail.com", "Denis123qwert",
        "");

    /**
     * Метод для запуску програми та відображення головного меню.
     *
     * @throws IllegalAccessException Виняток, який виникає при незаконному доступі.
     */
    public static void runner() throws IllegalAccessException {
        Menu.show();
    }

    /**
     * Головний метод, який запускає програму.
     *
     * @param args Аргументи командного рядка (не використовуються).
     * @throws IllegalAccessException Виняток, який виникає при незаконному доступі.
     */
    public static void main(String[] args) throws IllegalAccessException {
        runner();
    }
}
