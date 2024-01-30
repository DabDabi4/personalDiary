package com.dabdabi4.personaldiary.service;

import com.dabdabi4.personaldiary.entity.model.Diary;
import com.dabdabi4.personaldiary.entity.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class UserService {

    private final String userDataFilePath = "src//com//dabdabi4//personaldiary//repository//UserData.json";
    private List<User> users;

    public UserService() {
        // При створенні екземпляра UserService завантажте користувачів з файлу
        loadUsersFromFile();
    }

    // Метод для завантаження користувачів з JSON-файлу
    private void loadUsersFromFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            users = objectMapper.readValue(new File(userDataFilePath),
                new TypeReference<List<User>>() {
                });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Помилка при завантаженні користувачів з файлу.");
        }
    }

    // Метод для збереження користувачів у JSON-файл
    private void saveUsersToFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Додайте цей рядок

            objectMapper.writeValue(new File(userDataFilePath), users);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Помилка при збереженні користувачів у файл.");
        }
    }

    // Метод для видалення користувача за емейлом
    public void printAllUsers() {
        System.out.println("Інформація про користувачів:");
        for (User user : users) {
            System.out.println(
                "Емейл: " + user.getEmail() + ", Ім'я: " + user.getUsername() + ", Роль: "
                    + user.getRole());
        }
        System.out.println();
    }

    // Метод для видалення користувача за емейлом
    public void deleteUserByEmail(String email) {
        // Вивести інформацію про всіх користувачів перед видаленням

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getEmail().equals(email)) {
                iterator.remove();
                System.out.println("Користувач з емейлом " + email + " був видалений.");

                deleteDiariesByUserId(user.getIdUser());
                saveUsersToFile(); // Оновлення файлу після видалення
                return;
            }
        }

        System.out.println("Користувача з емейлом " + email + " не знайдено.");
    }


    private void deleteDiariesByUserId(String userId) {
        DiaryService diaryService = new DiaryService();
        List<Diary> userDiaries = diaryService.getAllUserDiaries(userId);

        // Видалення щоденників користувача
        for (Diary diary : userDiaries) {
            diaryService.deleteDiary(diary);
        }
    }

}

