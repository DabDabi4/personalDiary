package com.dabdabi4.personaldiary;

import static java.lang.System.out;

import com.dabdabi4.personaldiary.persistence.entity.impl.User;
import com.dabdabi4.personaldiary.util.LocalDateSerializer;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        List<User> users = generateUsers(10);

        // Виведемо створених користувачів
        for (User user : users) {
            out.println(user);
        }

        writeUsersToJsonFile(users, "users.json");
    }

    public static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            UUID userId = UUID.randomUUID();
            String password = faker.internet().password();
            String email = faker.internet().emailAddress();
            String username = faker.name().username();

            User user = new User(userId, password, email, username);
            users.add(user);
        }

        return users;
    }

  
    public static void writeUsersToJsonFile(List<User> users, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Створюємо Gson з красивим виведенням
            Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .setPrettyPrinting().create();

            // Перетворюємо колекцію користувачів в JSON та записуємо у файл
            gson.toJson(users, writer);

            System.out.println("Колекцію користувачів збережено в файл " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
