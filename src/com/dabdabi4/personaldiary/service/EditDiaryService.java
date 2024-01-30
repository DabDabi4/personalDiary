package com.dabdabi4.personaldiary.service;


import com.dabdabi4.personaldiary.Application;
import com.dabdabi4.personaldiary.entity.model.Diary;
import com.dabdabi4.personaldiary.entity.model.User;
import com.dabdabi4.personaldiary.view.CustomerConsoleUI;
import java.util.List;
import java.util.Scanner;

public class EditDiaryService {

    private static final Scanner scanner = new Scanner(System.in);
    private final DiaryService diaryService;

    public EditDiaryService(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    public void editOrDeleteDiary() {
        User currentUser = Application.currentUser;
        List<Diary> userDiaries = diaryService.getAllUserDiaries(currentUser.getIdUser());

        // Вивести список щоденників користувача
        System.out.println("Список вашіх щоденників:");
        userDiaries.forEach(
            diary -> System.out.println(diary.getName() + ": " + diary.getDescription()));

        CustomerConsoleUI.printSystemMessage(
            "Введіть назву щоденника для редагування чи видалення: ");
        String diaryName = scanner.nextLine();

        Diary diaryToEditOrDelete = findDiaryByName(userDiaries, diaryName);

        if (diaryToEditOrDelete != null) {
            CustomerConsoleUI.printSystemMessage("Виберіть опцію:");
            System.out.println("1. Редагувати щоденник");
            System.out.println("2. Видалити щоденник");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистити буфер після nextInt()

            switch (choice) {
                case 1:
                    editDiary(diaryToEditOrDelete);
                    break;
                case 2:
                    deleteDiary(diaryToEditOrDelete);
                    break;
                default:
                    System.out.println("Невірний вибір.");
                    break;
            }
        } else {
            System.out.println("Щоденник не знайдено");
        }
    }

    private void editDiary(Diary diaryToEdit) {
        CustomerConsoleUI.printSystemMessage("Виберіть параметр для редагування:");
        System.out.println("1. Назва щоденника");
        System.out.println("2. Опис щоденника");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистити буфер після nextInt()

        switch (choice) {
            case 1:
                CustomerConsoleUI.printSystemMessage("Введіть нову назву щоденника: ");
                String newDiaryName = scanner.nextLine();
                diaryToEdit.setName(newDiaryName);
                break;
            case 2:
                CustomerConsoleUI.printSystemMessage("Введіть новий опис щоденника: ");
                String newDiaryDescription = scanner.nextLine();
                diaryToEdit.setDescription(newDiaryDescription);
                break;
            default:
                System.out.println("Невірний вибір.");
                return;
        }

        // Збереження оновленого щоденника
        diaryService.saveDiaryToJson(diaryToEdit);
        System.out.println("Щоденник оновлено.");

        System.out.println("Оновлений щоденник: " + diaryToEdit);
    }

    private void deleteDiary(Diary diaryToDelete) {
        diaryService.deleteDiary(diaryToDelete);
    }


    private Diary findDiaryByName(List<Diary> diaries, String name) {
        return diaries.stream()
            .filter(diary -> diary.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

}
