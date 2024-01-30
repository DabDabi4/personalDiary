package com.dabdabi4.personaldiary.entity.model;

import com.dabdabi4.personaldiary.entity.Entity;
import com.dabdabi4.personaldiary.entity.ValidationError;
import com.dabdabi4.personaldiary.exception.EntityArgumentException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;


@JsonPropertyOrder({"idUser", "username", "password", "email", "role", "errors", "valid"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Entity {

    private String password;
    private String email;
    private String username;

    private String role;

    private String idUser;

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

    public User() {

    }

    public User(String s) {
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

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

    public String getUsername() {
        return username;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
            "password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", username='" + username + '\'' +
            ", id=" + idUser +
            '}';
    }

    public void displayUserInfo() {
        System.out.println("User Information:");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
    }
}




