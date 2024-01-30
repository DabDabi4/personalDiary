package com.dabdabi4.personaldiary.service;

import com.dabdabi4.personaldiary.Application;
import com.dabdabi4.personaldiary.entity.model.User;
import com.dabdabi4.personaldiary.view.CustomerConsoleUI;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AuthorizationService {

    public static void authorization() {
        String username = CustomerConsoleUI.promptUserForInput("Введіть логін",
            new Scanner(System.in));
        String password = CustomerConsoleUI.promptUserForInput("Введіть пароль",
            new Scanner(System.in));

        System.out.println("Ви ввели логін: " + username + " і пароль: " + password);

        List<User> users = readUsersFromJson(
            "src//com//dabdabi4//personaldiary//repository//UserData.json");

        User user = findUserByUsername(users, username);

        if (user != null && user.getPassword().equals(password)) {
            Application.currentUser = user;
            CustomerConsoleUI.printSystemMessage("Авторизація пройшла успішно.");

        } else {
            CustomerConsoleUI.printSystemMessage("Помилка авторизації. Перевірте логін та пароль.");
        }
    }

    private static User findUserByUsername(List<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private static List<User> readUsersFromJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Arrays.asList(objectMapper.readValue(new File(filePath), User[].class));
        } catch (IOException e) {
            e.printStackTrace();
            // Обробка помилок при читанні з файлу
            return new ArrayList<>();
        }
    }
}
