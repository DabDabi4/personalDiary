package com.dabdabi4.personaldiary.service;

import static com.dabdabi4.personaldiary.Application.currentUser;

import com.dabdabi4.personaldiary.Application;
import com.dabdabi4.personaldiary.entity.model.Diary;
import com.dabdabi4.personaldiary.entity.model.Note;
import com.dabdabi4.personaldiary.entity.model.Path;
import com.dabdabi4.personaldiary.entity.model.User;
import com.dabdabi4.personaldiary.view.CustomerConsoleUI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Клас, який надає сервіси для роботи із щоденниками.
 */
public class DiaryService {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Метод для створення нового щоденника користувача.
     */
    public void createDiary() {
        CustomerConsoleUI.printSystemMessage("Бажаєте створити щоденник? (так/ні): ");
        String createDiaryChoice = scanner.nextLine();

        if ("так".equalsIgnoreCase(createDiaryChoice)) {
            List<Diary> userDiaries = getAllUserDiaries(Application.currentUser.getIdUser());
            // Вивести список щоденників користувача
            System.out.println("Список ваших щоденників:");
            userDiaries.forEach(
                diary -> System.out.println(diary.getName() + ": " + diary.getDescription()));
            CustomerConsoleUI.printSystemMessage("Введіть назву щоденника: ");
            String diaryName = scanner.nextLine();

            // Перевірка, чи щоденник із введеною назвою вже існує
            if (diaryAlreadyExists(diaryName)) {
                System.out.println("Щоденник із такою назвою вже існує.");
                return;
            }

            CustomerConsoleUI.printSystemMessage("Введіть опис щоденника: ");
            String diaryDescription = scanner.nextLine();

            LocalDate createdAt = LocalDate.now();

            User currentUser = Application.currentUser; // отримайте поточного користувача
            Diary newDiary = new Diary(createdAt, diaryName, diaryDescription,
                currentUser.getIdUser());

            CustomerConsoleUI.printSystemMessage("Бажаєте зберегти щоденник? (так/ні): ");
            String saveDiaryChoice = scanner.nextLine();

            if ("так".equalsIgnoreCase(saveDiaryChoice)) {
                saveDiaryToJson(newDiary);
                System.out.println("Щоденник збережено у файлі.");
            }

            System.out.println("Створений щоденник: " + newDiary);
        } else {
            // System.out.println("До побачення!");
        }
    }

    /**
     * Перевірка, чи щоденник із введеною назвою вже існує у списку користувача.
     *
     * @param diaryName Назва щоденника.
     * @return true, якщо щоденник із такою назвою вже існує, false - в іншому випадку.
     */
    private boolean diaryAlreadyExists(String diaryName) {
        List<Diary> userDiaries = getAllUserDiaries(currentUser.getIdUser());
        return userDiaries.stream().anyMatch(diary -> diary.getName().equalsIgnoreCase(diaryName));
    }

    /**
     * Збереження щоденника у JSON-файл.
     *
     * @param updatedDiary Оновлений або новий щоденник для збереження.
     */
    public void saveDiaryToJson(Diary updatedDiary) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String filePath = Path.DIARY_JSON.getPath();
            File file = new File(filePath);

            List<Diary> existingDiaries;

            if (file.exists()) {
                existingDiaries = objectMapper.readValue(file, new TypeReference<List<Diary>>() {
                });
            } else {
                existingDiaries = new ArrayList<>();
            }

            // Оновлюємо або додаємо щоденник до списку
            boolean found = false;
            for (int i = 0; i < existingDiaries.size(); i++) {
                if (existingDiaries.get(i).getIdDiary().equals(updatedDiary.getIdDiary())) {
                    existingDiaries.set(i, updatedDiary);
                    found = true;
                    break;
                }
            }

            if (!found) {
                // Якщо щоденник не знайдено, то додаємо його до списку
                existingDiaries.add(updatedDiary);
            }

            // Збереження оновленого списку щоденників
            objectMapper.writeValue(file, existingDiaries);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Видалення щоденника та пов'язаних з ним записів.
     *
     * @param diaryToDelete Щоденник, який слід видалити.
     */
    public void deleteDiary(Diary diaryToDelete) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String filePath = Path.DIARY_JSON.getPath();
            File file = new File(filePath);

            if (file.exists()) {
                List<Diary> existingDiaries = objectMapper.readValue(file,
                    new TypeReference<List<Diary>>() {
                    });

                // Видалення щоденника
                existingDiaries.removeIf(
                    diary -> diary.getIdDiary().equals(diaryToDelete.getIdDiary()));

                // Збереження оновленого списку щоденників
                objectMapper.writeValue(file, existingDiaries);

                System.out.println("Щоденник видалено.");

                // Видалення записів, пов'язаних із видаленим щоденником та користувачем
                deleteRelatedNotes(diaryToDelete.getUserId(), diaryToDelete.getIdDiary());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Видалення записів, пов'язаних із видаленим щоденником та користувачем.
     *
     * @param userId  ID користувача.
     * @param diaryId ID щоденника.
     */
    private void deleteRelatedNotes(String userId, String diaryId) {
        NoteService noteService = new NoteService();
        List<Note> allNotes = noteService.readNotesFromFile();

        // Видалення записів, пов'язаних із видаленим щоденником та користувачем
        allNotes.removeIf(note ->
            note.getIdDiary().equals(diaryId) &&
                note.getUserId().equals(userId));

        // Збереження оновленого списку записів
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String filePath = Path.NOTE_JSON.getPath();
            File file = new File(filePath);

            objectMapper.writeValue(file, allNotes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отримання списку всіх щоденників користувача.
     *
     * @param userId ID користувача.
     * @return Список щоденників користувача.
     */
    public List<Diary> getAllUserDiaries(String userId) {
        return readDiariesFromFile(userId);
    }

    /**
     * Зчитування щоденників із JSON-файлу.
     *
     * @param userId ID користувача.
     * @return Список щоденників користувача.
     */
    public List<Diary> readDiariesFromFile(String userId) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(Path.DIARY_JSON.getPath());

        try {
            if (file.exists()) {
                List<Diary> allDiaries = objectMapper.readValue(file,
                    new TypeReference<List<Diary>>() {
                    });

                // Фільтруємо щоденники за userId
                return allDiaries.stream()
                    .filter(diary -> diary.getUserId().equals(userId))
                    .collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


}
