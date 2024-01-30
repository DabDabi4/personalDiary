package com.dabdabi4.personaldiary.view;

import java.util.Scanner;

public class UserInputHandler {

    private Scanner scanner;

    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    // Метод для отримання рядка від користувача
    public String promptUserForString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    // Метод для отримання цілочисельного значення від користувача
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

    // Інші методи опитування користувача можна додати за необхідності

    public void closeScanner() {
        scanner.close();
    }
}
