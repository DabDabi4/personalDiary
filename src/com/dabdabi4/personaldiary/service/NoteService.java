package com.dabdabi4.personaldiary.service;

import static com.dabdabi4.personaldiary.Application.currentUser;

import com.dabdabi4.personaldiary.entity.model.Diary;
import com.dabdabi4.personaldiary.entity.model.Note;
import com.dabdabi4.personaldiary.entity.model.Path;
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
 * Клас, який надає сервіси для створення, збереження та роботи з записами.
 */
public class NoteService {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Метод для створення нового запису користувача.
     */
    public void createNote() {

        System.out.println("Список ваших щоденників:");
        List<Diary> userDiaries = readDiariesFromFile(currentUser.getIdUser());
        userDiaries.forEach(diary -> System.out.println(diary.getName()));

        // Потрібно взяти назву щоденника від користувача
        CustomerConsoleUI.printSystemMessage("Введіть назву щоденника для створення запису: ");
        String diaryName = scanner.nextLine();

        // Зчитуємо дані щоденника із файлу
        List<Diary> diaries = readDiariesFromFile(currentUser.getIdUser());

        // Знаходимо щоденник за введеною назвою
        Diary selectedDiary = findDiaryByName(diaries, diaryName);

        if (selectedDiary != null) {

            // Перевірка наявності запису з такою назвою
            CustomerConsoleUI.printSystemMessage("Введіть назву запису: ");
            String noteTitle = scanner.nextLine();

            if (noteAlreadyExists(selectedDiary.getIdDiary(), noteTitle)) {
                System.out.println("Запис із такою назвою вже існує.");
                return;
            }

            // Введення інформації про новий запис
            CustomerConsoleUI.printSystemMessage("Введіть опис запису: ");
            String noteText = scanner.nextLine();

            LocalDate createdAt = LocalDate.now();

            // Створення нового запису
            Note newNote = new Note(createdAt, noteText, noteTitle, selectedDiary.getIdDiary(), 0,
                null, currentUser.getIdUser());

            // Збереження нового запису
            saveNoteToJson(newNote);
            System.out.println("Запис збережено.");

            System.out.println("Створений запис: " + newNote);
        } else {
            System.out.println("Щоденник не знайдено");
        }
    }

    /**
     * Метод, який перевіряє чи існує вже запис з такою назвою в щоденнику.
     *
     * @param diaryId   Ідентифікатор щоденника.
     * @param noteTitle Назва запису.
     * @return {@code true}, якщо запис існує; {@code false} в іншому випадку.
     */
    private boolean noteAlreadyExists(String diaryId, String noteTitle) {
        List<Note> notes = getAllUserNotes(currentUser.getIdUser());

        return notes.stream()
            .anyMatch(note -> note.getIdDiary().equals(diaryId) && note.getTitle()
                .equalsIgnoreCase(noteTitle));
    }

    private List<Diary> readDiariesFromFile(String userId) {
        DiaryService diaryService = new DiaryService();
        return diaryService.readDiariesFromFile(userId);
    }

    /**
     * Метод для збереження всіх записів користувача.
     *
     * @param userId    Ідентифікатор користувача.
     * @param userNotes Список записів користувача.
     */
    public void saveAllUserNotes(String userId, List<Note> userNotes) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String filePath = Path.NOTE_JSON.getPath();
            File file = new File(filePath);

            // Зчитуємо всі записи з файлу
            List<Note> allNotes = readNotesFromFile();

            // Оновлюємо або додаємо записи користувача до списку
            allNotes.removeIf(
                note -> note.getUserId().equals(userId)); // Видаляємо старі записи користувача

            // Додаємо нові записи користувача до списку
            allNotes.addAll(userNotes);

            // Збереження оновленого списку записів
            objectMapper.writeValue(file, allNotes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для збереження запису в файл JSON.
     *
     * @param updatedNote Оновлений запис.
     */
    public void saveNoteToJson(Note updatedNote) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String filePath = Path.NOTE_JSON.getPath();
            File file = new File(filePath);

            List<Note> existingNotes;

            if (file.exists()) {
                existingNotes = objectMapper.readValue(file, new TypeReference<List<Note>>() {
                });

                // Оновлюємо або додаємо запис до списку
                boolean found = false;
                for (int i = 0; i < existingNotes.size(); i++) {
                    if (existingNotes.get(i).getIdNote().equals(updatedNote.getIdNote())) {
                        existingNotes.set(i, updatedNote);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // Якщо запис не знайдено, то додаємо його до списку
                    existingNotes.add(updatedNote);
                }
            } else {
                // Якщо файл не існує, створюємо новий список і додаємо туди запис
                existingNotes = new ArrayList<>();
                existingNotes.add(updatedNote);
            }

            // Збереження оновленого списку записів
            objectMapper.writeValue(file, existingNotes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для зчитування всіх записів з файлу JSON.
     *
     * @return Список всіх записів.
     */
    public List<Note> readNotesFromFile() {  // Додав новий метод
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(Path.NOTE_JSON.getPath());

        try {
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Note>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * Метод для отримання всіх записів користувача.
     *
     * @param userId Ідентифікатор користувача.
     * @return Список записів користувача.
     */
    public List<Note> getAllUserNotes(String userId) {
        List<Note> allNotes = readNotesFromFile();
        return allNotes.stream()
            .filter(note -> note.getUserId().equals(userId))
            .collect(Collectors.toList());
    }

    /**
     * Метод для пошуку щоденника за назвою в списку.
     *
     * @param diaries Список щоденників.
     * @param name    Назва щоденника для пошуку.
     * @return Знайдений щоденник або {@code null}, якщо не знайдено.
     */
    private Diary findDiaryByName(List<Diary> diaries, String name) {
        return diaries.stream()
            .filter(diary -> diary.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    /**
     * Метод для отримання всіх записів для певного щоденника.
     *
     * @param diaryId Ідентифікатор щоденника.
     * @return Список записів для щоденника.
     */
    public List<Note> getAllNotesForDiary(String diaryId) {
        List<Note> allNotes = readNotesFromFile();
        return allNotes.stream()
            .filter(note -> note.getIdDiary().equals(diaryId))
            .collect(Collectors.toList());
    }


}
