package com.dabdabi4.personaldiary.view;

import com.dabdabi4.personaldiary.entity.model.User;
import java.lang.reflect.Field;

/**
 * Клас, який надає методи для виведення інформації про користувача в консоль.
 */
public class UserConsoleUI {


    /**
     * Виводить інформацію про користувача в консоль.
     *
     * @param user Об'єкт класу User, інформацію про якого треба вивести.
     * @throws IllegalAccessException Виняток, що виникає при спробі доступу до поля.
     */
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