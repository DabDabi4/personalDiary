package com.dabdabi4.personaldiary.entity.model;

import com.dabdabi4.personaldiary.entity.EntityError;
import com.dabdabi4.personaldiary.entity.ValidationError;
import com.dabdabi4.personaldiary.exception.EntityArgumentException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Клас {@code User} представляє користувача системи. Кожен користувач має унікальний ідентифікатор,
 * логін, пароль, електронну пошту та роль. Реалізує основні методи для отримання та встановлення
 * властивостей користувача, а також виконує валідацію деяких полів.
 */
@JsonPropertyOrder({"idUser", "username", "password", "email", "role", "errors", "valid"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends EntityError {

    /**
     * Пароль користувача.
     */
    private String password;

    /**
     * Електронна пошта користувача.
     */
    private String email;

    /**
     * Логін користувача.
     */
    private String username;

    /**
     * Роль користувача.
     */
    private String role;

    /**
     * Унікальний ідентифікатор користувача.
     */
    private String idUser;

    /**
     * Конструктор, що створює новий об'єкт {@code User} з вказаними параметрами.
     *
     * @param password Пароль користувача.
     * @param email    Електронна пошта користувача.
     * @param username Логін користувача.
     * @param role     Роль користувача.
     */

    //private List<Diary> diaries;
    public User(@JsonProperty("password") String password,
        @JsonProperty("email") String email, @JsonProperty("username") String username,
        @JsonProperty("role") String role) {

        this.password = validatedPassword(password);
        this.email = setEmail(email);
        setUsername(username);
        this.role = role;
        this.idUser = UUID.randomUUID().toString();

    }

    /**
     * Конструктор за замовчуванням.
     */
    public User() {

    }

    /**
     * Конструктор, що приймає рядок.
     *
     * @param s Рядок.
     */
    public User(String s) {
    }

    /**
     * Повертає роль користувача.
     *
     * @return Роль користувача.
     */
    public String getRole() {
        return role;
    }

    /**
     * Встановлює роль користувача.
     *
     * @param role Нова роль користувача.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Повертає унікальний ідентифікатор користувача.
     *
     * @return Унікальний ідентифікатор користувача.
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Встановлює унікальний ідентифікатор користувача.
     *
     * @param idUser Новий унікальний ідентифікатор користувача.
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * Повертає пароль користувача.
     *
     * @return Пароль користувача.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Повертає електронну пошту користувача.
     *
     * @return Електронна пошта користувача.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Встановлює електронну пошту користувача та виконує валідацію.
     *
     * @param email Нова електронна пошта користувача.
     * @return Електронна пошта користувача.
     * @throws EntityArgumentException якщо валідація не пройшла успішно.
     */
    public String setEmail(String email) {
        final String templateName = "пошти";
        if (email.isBlank()) {
            errors.add(ValidationError.REQUIRED.getMessage().formatted(templateName));
        }
        if (email.length() < 12) {
            errors.add(ValidationError.MIN_LENGTH.getMessage().formatted(templateName, 8));
        }
        if (email.length() > 30) {
            errors.add(ValidationError.MAX_LENGTH.getMessage().formatted(templateName, 30));
        }
        if (!(email.contains("@") && email.contains("."))) {
            errors.add(ValidationError.EMAIL_CONTAINS.getMessage().formatted(templateName));
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errors.add(ValidationError.EMAIL_MATCHES.getMessage().formatted(templateName));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        return email;
    }

    /**
     * Повертає логін користувача.
     *
     * @return Логін користувача.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Встановлює логін користувача та виконує валідацію.
     *
     * @param username Новий логін користувача.
     * @throws EntityArgumentException якщо валідація не пройшла успішно.
     */
    public void setUsername(String username) {
        final String templateName = "логіну";

        if (username.isBlank()) {
            errors.add(ValidationError.REQUIRED.getMessage().formatted(templateName));
        }
        if (username.length() < 4) {
            errors.add(ValidationError.MIN_LENGTH.getMessage().formatted(templateName, 4));
        }
        if (username.length() > 24) {
            errors.add(ValidationError.MAX_LENGTH.getMessage().formatted(templateName, 24));
        }
        var pattern = Pattern.compile("^[A-Za-z0-9_.\\s]+$");
        if (!pattern.matcher(username).matches()) {
            errors.add(ValidationError.ONLY_LATIN.getMessage().formatted(templateName, 24));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.username = username;
    }

    /**
     * Виконує валідацію паролю користувача та повертає його.
     *
     * @param password Пароль користувача.
     * @return Валідований пароль користувача.
     * @throws EntityArgumentException якщо валідація не пройшла успішно.
     */
    public String validatedPassword(String password) {
        final String templateName = "паролю";

        if (password.isBlank()) {
            errors.add(ValidationError.REQUIRED.getMessage().formatted(templateName));
        }
        if (password.length() < 8) {
            errors.add(ValidationError.MIN_LENGTH.getMessage().formatted(templateName, 4));
        }
        if (password.length() > 32) {
            errors.add(ValidationError.MAX_LENGTH.getMessage().formatted(templateName, 32));
        }

        var pattern = Pattern.compile("^[A-Za-z0-9_.\\s]+$");
        if (!pattern.matcher(password).matches()) {
            errors.add(ValidationError.PASSWORD.getMessage().formatted(templateName, 24));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        return password;
    }

    /**
     * Перевизначає метод для порівняння користувачів за електронною поштою.
     *
     * @param o Об'єкт для порівняння.
     * @return {@code true}, якщо цей користувач рівний вказаному об'єкту; {@code false} в іншому
     * випадку.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    /**
     * Перевизначає метод для отримання хеш-коду користувача за електронною поштою.
     *
     * @return Хеш-код для користувача за електронною поштою.
     */

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Перевизначає метод для отримання рядкового представлення користувача.
     *
     * @return Рядкове представлення користувача.
     */
    @Override
    public String toString() {
        return "User{" +
            "password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", username='" + username + '\'' +
            ", id=" + idUser +
            '}';
    }

    /**
     * Виводить інформацію про користувача на консоль.
     */
    public void displayUserInfo() {
        System.out.println("User Information:");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
    }
}




