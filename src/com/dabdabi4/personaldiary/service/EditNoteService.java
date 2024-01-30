package com.dabdabi4.personaldiary.service;

import com.dabdabi4.personaldiary.Application;
import com.dabdabi4.personaldiary.entity.model.Note;
import com.dabdabi4.personaldiary.entity.model.User;
import com.dabdabi4.personaldiary.view.CustomerConsoleUI;
import java.util.List;
import java.util.Scanner;

public class EditNoteService {

    private static final Scanner scanner = new Scanner(System.in);
    private final NoteService noteService;

    public EditNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    public void editNote() {
        User currentUser = Application.currentUser;
        List<Note> userNotes = noteService.getAllUserNotes(currentUser.getIdUser());

        // Вивести список записів користувача
        System.out.println("Список ваших записів:");
        userNotes.forEach(note -> System.out.println(note.getTitle() + ": " + note.getNoteText()));

        CustomerConsoleUI.printSystemMessage("Введіть назву запису для редагування чи видалення: ");
        String noteTitle = scanner.nextLine();

        Note noteToEditOrDelete = findNoteByTitle(userNotes, noteTitle);

        if (noteToEditOrDelete != null) {
            CustomerConsoleUI.printSystemMessage("Оберіть опцію:");
            System.out.println("1. Редагувати запис");
            System.out.println("2. Видалити запис");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистити буфер після nextInt()

            switch (choice) {
                case 1:
                    System.out.println("Вибрано редагування запису");

                    CustomerConsoleUI.printSystemMessage("Виберіть параметр для редагування:");
                    System.out.println("1. Назва запису");
                    System.out.println("2. Опис запису");

                    int editChoice = scanner.nextInt();
                    scanner.nextLine(); // Очистити буфер після nextInt()

                    switch (editChoice) {
                        case 1:
                            CustomerConsoleUI.printSystemMessage("Введіть нову назву запису: ");
                            String newNoteTitle = scanner.nextLine();
                            noteToEditOrDelete.setTitle(newNoteTitle);
                            break;
                        case 2:
                            CustomerConsoleUI.printSystemMessage("Введіть новий опис запису: ");
                            String newNoteText = scanner.nextLine();
                            noteToEditOrDelete.setNoteText(newNoteText);
                            break;
                        default:
                            System.out.println("Невірний вибір");
                            return;
                    }

                    // Збереження оновленого запису
                    noteService.saveNoteToJson(noteToEditOrDelete);
                    System.out.println("Запис оновлено.");
                    System.out.println("Оновлений запис: " + noteToEditOrDelete);
                    break;

                case 2:
                    // Видалення запису
                    userNotes.remove(noteToEditOrDelete);
                    noteService.saveAllUserNotes(currentUser.getIdUser(), userNotes);
                    System.out.println("Запис видалено");
                    break;

                default:
                    System.out.println("Невірний вибір");
                    break;
            }
        } else {
            System.out.println("Запис не знайдено");
        }
    }

    private Note findNoteByTitle(List<Note> notes, String title) {
        return notes.stream()
            .filter(note -> note.getTitle().equalsIgnoreCase(title))
            .findFirst()
            .orElse(null);
    }
}
