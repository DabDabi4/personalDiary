package com.dabdabi4.personaldiary.view;

import com.dabdabi4.personaldiary.Application;
import com.dabdabi4.personaldiary.entity.model.Note;
import com.dabdabi4.personaldiary.service.EditNoteService;
import com.dabdabi4.personaldiary.service.NoteService;
import java.util.List;

public class NoteMenu {


    public static void showNoteMenu() {
        NoteService noteService = new NoteService();

        while (true) {
            CustomerConsoleUI.printMenu("1) Створити запис");
            CustomerConsoleUI.printMenu("2) Редагувати запис");
            CustomerConsoleUI.printMenu("3) Переглянути всі записи");
            CustomerConsoleUI.printMenu("0) Повернутися назад");

            int noteChoice = new UserInputHandler().promptUserForInteger("Ваш вибір");

            switch (noteChoice) {
                case 1:
                    noteService.createNote();
                    break;
                case 2:
                    EditNoteService editNoteService = new EditNoteService(noteService);
                    editNoteService.editNote();
                    break;
                case 3:

                    List<Note> allNotes = noteService.getAllUserNotes(
                        Application.currentUser.getIdUser());

                    if (allNotes.isEmpty()) {
                        CustomerConsoleUI.printSystemMessage("У вас ще немає записів.");
                    } else {
                        CustomerConsoleUI.printSystemMessage("Всі ваші записи:");
                        allNotes.forEach(note -> System.out.println(
                            note.getTitle() + ": " + note.getNoteText()));
                    }
                    break;
                case 0:
                    return; // Повертаємося до основного меню
                default:
                    CustomerConsoleUI.printSystemMessage("Невірний вибір. Спробуйте ще раз.");
                    break;
            }
        }
    }
}
