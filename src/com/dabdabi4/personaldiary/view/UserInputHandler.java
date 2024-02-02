package com.dabdabi4.personaldiary.view;

import java.util.Scanner;

/**
 * Клас, який надає методи для отримання введених даних від користувача.
 */
public class UserInputHandler {

    private Scanner scanner;

    /**
     * Конструктор класу. Ініціалізує об'єкт класу Scanner для отримання введених даних.
     */
    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Метод для отримання рядка від користувача.
     *
     * @param prompt Повідомлення, яке виводиться користувачеві перед введенням даних.
     * @return Рядок, введений користувачем.
     */
    public String promptUserForString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    /**
     * Метод для отримання цілочисельного значення від користувача.
     *
     * @param prompt Повідомлення, яке виводиться користувачеві перед введенням даних.
     * @return Ціле число, введене користувачем.
     */
    public int promptUserForInteger(String prompt) {
        CustomerConsoleUI.printSystemMessage(prompt + ": ");

        while (!scanner.hasNextInt()) {
            CustomerConsoleUI.printSystemMessage("Будь ласка, введіть ціле число.");
            CustomerConsoleUI.printSystemMessage(prompt + ": ");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }


    /**
     * Метод для закриття об'єкта Scanner.
     */
    public void closeScanner() {
        scanner.close();
    }
}
