package com.dabdabi4.personaldiary.view;

import com.dabdabi4.personaldiary.entity.model.User;
import java.lang.reflect.Field;


public class UserConsoleUI {


    // Метод для виведення інформації про користувача в консоль
    public static void displayUserInfo(User user) throws IllegalAccessException {

        for (Field field : User.class.getDeclaredFields()) {
            String fieldName = field.getName();
            field.setAccessible(true);
            Object value = field.get(user);
            CustomerConsoleUI.printFieldName(fieldName);

            if (value instanceof String) {
                CustomerConsoleUI.printField((String) value);
            } else if (value instanceof Long) {
                CustomerConsoleUI.printField(String.valueOf(value));
            } else {
                CustomerConsoleUI.printField(value.toString());
            }
        }
    }
}