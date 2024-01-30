package com.dabdabi4.personaldiary.view;

import com.dabdabi4.personaldiary.Application;
import com.dabdabi4.personaldiary.entity.model.Diary;
import com.dabdabi4.personaldiary.entity.model.Note;
import com.dabdabi4.personaldiary.service.DiaryService;
import com.dabdabi4.personaldiary.service.EditDiaryService;
import com.dabdabi4.personaldiary.service.NoteService;
import java.util.List;

public class DiaryMenu {

    public static void showDiaryMenu() {
        while (true) {
            CustomerConsoleUI.printMenu("1) Створити щоденник");
            CustomerConsoleUI.printMenu("2) Редагувати щоденник");
            CustomerConsoleUI.printMenu("3) Переглянути записи щоденника");
            CustomerConsoleUI.printMenu("0) Повернутися назад");

            int diaryChoice = new UserInputHandler().promptUserForInteger("Ваш вибір");
            DiaryService diaryService = new DiaryService();
            NoteService noteService = new NoteService();
            switch (diaryChoice) {
                case 1:
                    diaryService.createDiary();
                    break;
                case 2:
                    EditDiaryService editDiaryService = new EditDiaryService(diaryService);
                    // Виклик методу для редагування чи видалення щоденника
                    editDiaryService.editOrDeleteDiary();
                    break;
                case 3:

                    List<Diary> userDiaries = diaryService.getAllUserDiaries(
                        Application.currentUser.getIdUser());

                    if (userDiaries.isEmpty()) {
                        CustomerConsoleUI.printSystemMessage(
                            "У вас немає щоденників. Спочатку створіть щоденник.");
                    } else {
                        CustomerConsoleUI.printSystemMessage("Виберіть щоденник:");

                        // Вивести список щоденників користувача
                        for (int i = 0; i < userDiaries.size(); i++) {
                            System.out.println((i + 1) + ") " + userDiaries.get(i).getName());
                        }

                        int selectedDiaryIndex = new UserInputHandler().promptUserForInteger(
                            "Виберіть номер щоденника: ") - 1;

                        if (selectedDiaryIndex >= 0 && selectedDiaryIndex < userDiaries.size()) {
                            Diary selectedDiary = userDiaries.get(selectedDiaryIndex);
                            List<Note> allNotes = noteService.getAllNotesForDiary(
                                selectedDiary.getIdDiary());

                            if (allNotes.isEmpty()) {
                                CustomerConsoleUI.printSystemMessage(
                                    "У вас ще немає записів для цього щоденника.");
                            } else {
                                CustomerConsoleUI.printSystemMessage(
                                    "Всі записи для щоденника " + selectedDiary.getName() + ":");
                                allNotes.forEach(note -> System.out.println(
                                    note.getTitle() + ": " + note.getNoteText()));
                            }
                        } else {
                            CustomerConsoleUI.printSystemMessage("Невірний номер щоденника.");
                        }
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
