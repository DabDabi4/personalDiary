package com.dabdabi4.personaldiary.service;

import com.dabdabi4.personaldiary.Application;
import com.dabdabi4.personaldiary.entity.model.User;
import com.dabdabi4.personaldiary.view.CustomerConsoleUI;
import com.dabdabi4.personaldiary.view.UserInputHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegistrationService {

    private static User[] users2;

    public static void registration() {
        String username;
        String password;
        String email;

        do {
            try {
                if (Application.users == null) {
                    Application.users = new User[]{};
                }

                // Отримати логін та перевірити його унікальність
                username = CustomerConsoleUI.promptUserForInput("Введіть логін",
                    new java.util.Scanner(System.in));

                users2 = new ObjectMapper().readValue(
                    new File("src//com//dabdabi4//personaldiary//repository//UserData.json"),
                    User[].class);

                if (isLoginUnique(username)) {
                    // Отримати пароль
                    password = CustomerConsoleUI.promptUserForInput("Введіть пароль",
                        new java.util.Scanner(System.in));

                    // Отримати email
                    email = CustomerConsoleUI.promptUserForInput("Введіть email",
                        new java.util.Scanner(System.in));

                    // Отримати вибір ролі
                    CustomerConsoleUI.printMenu("Оберіть роль:");
                    CustomerConsoleUI.printMenu("1) Користувач");
                    CustomerConsoleUI.printMenu("2) Адмін");
                    int roleChoice = new UserInputHandler().promptUserForInteger("Ваш вибір");

                    String role;
                    switch (roleChoice) {
                        case 1:
                            role = "Користувач";
                            break;
                        case 2:
                            role = "Адмін";
                            break;
                        default:
                            role = "Користувач";
                            break;
                    }

                    // Створити нового користувача
                    User newUser = new User(password, email, username, role);
                    Application.currentUser = newUser;

                    Application.users = addNewUser(Application.users, newUser);

                    saveUsersToJson(Application.users,
                        "src//com//dabdabi4//personaldiary//repository//UserData.json");

                    CustomerConsoleUI.printSystemMessage("Реєстрація пройшла успішно.");
                    break;  // Вийти з циклу, якщо реєстрація успішна
                } else {
                    throw new IllegalArgumentException(
                        "Цей логін вже використовується. Оберіть інший.");
                }

            } catch (IllegalArgumentException e) {
                // Вивести повідомлення про помилку та продовжити цикл
                System.out.println(e.getMessage());
            } catch (Exception e) {
                // Обробити інші винятки
                e.printStackTrace();
                System.out.println("Сталася помилка. Будь ласка, спробуйте ще раз.");
            }

        } while (true);  // Цикл, доки користувач не введе правильні дані
    }

    private static User[] addNewUser(User[] users, User newUser) {
        User[] newUsers = Arrays.copyOf(users, users.length + 1);
        newUsers[users.length] = newUser;
        return newUsers;
    }

    private static boolean isLoginUnique(String login) {
        if (users2 != null) {
            for (User existingUser : users2) {
                if (existingUser.getUsername().equals(login)) {
                    return false; // Логін не є унікальним
                }
            }
        }
        return true; // Логін є унікальним
    }

    private static void saveUsersToJson(User[] users, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(filePath);
            User[] existingUsers;

            if (file.exists()) {
                existingUsers = objectMapper.readValue(file, User[].class);
            } else {
                existingUsers = new User[]{};
            }

            List<User> userList = new ArrayList<>(Arrays.asList(existingUsers));
            userList.addAll(Arrays.asList(users));

            objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(file, userList.toArray(new User[0]));

            Application.users = userList.toArray(new User[0]);

        } catch (IOException e) {
            e.printStackTrace();
            CustomerConsoleUI.printSystemMessage("Сталася помилка під час роботи з файлом.");
        }
    }
}
