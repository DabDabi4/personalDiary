package com.dabdabi4.personaldiary.view;

import com.dabdabi4.personaldiary.Application;
import com.dabdabi4.personaldiary.service.AuthorizationService;
import com.dabdabi4.personaldiary.service.RegistrationService;
import com.dabdabi4.personaldiary.service.UserService;

/**
 * Клас, який представляє головне меню програми "Особистий щоденник".
 */
public class Menu {

    public Menu() {

    }


    /**
     * Виводить головне меню та надає опції для реєстрації, авторизації, роботи з щоденниками,
     * записами та перегляду даних.
     *
     * @throws IllegalAccessException Виняток, який може виникнути при невірному доступі.
     */
    public static void show() throws IllegalAccessException {

        CustomerConsoleUI.printTitle("ОСОБИСТИЙ ЩОДЕННИК");

        while (true) {
            String userRole = Application.currentUser.getRole();

            if ("".equals(userRole)) {
                CustomerConsoleUI.printMenu("1) Реєстрація");
                CustomerConsoleUI.printMenu("2) Авторизація");
            } else {
                CustomerConsoleUI.printMenu("1) Щоденник");
                CustomerConsoleUI.printMenu("2) Запис");
                CustomerConsoleUI.printMenu("3) Переглянути дані");

                if ("Адмін".equals(userRole)) {
                    CustomerConsoleUI.printMenu(
                        "4) Видалити користувача");
                    // Замініть "..." на конкретний пункт меню для лаборанта
                }
            }

            CustomerConsoleUI.printMenu("0) для виходу");
            int choice = new UserInputHandler().promptUserForInteger(
                "Ваш вибір"); // вибір користувача

            switch (choice) {
                case 1:
                    if ("".equals(userRole)) {
                        // авторизація
                        RegistrationService.registration();
                    } else {
                        DiaryMenu.showDiaryMenu();
                        // show();
                    }

                    break;
                case 2:
                    if ("".equals(userRole)) {
                        // реєстрація
                        AuthorizationService.authorization();
                    } else {
                        NoteMenu.showNoteMenu();
                    }
                    break;
                case 3:
                    UserConsoleUI.displayUserInfo(Application.currentUser);
                    break;
                case 4:
                    UserInputHandler userInputHandler = new UserInputHandler();
                    UserService userService = new UserService();
                    userService.printAllUsers();
                    String emailToDelete = userInputHandler.promptUserForString(
                        "Введіть емейл користувача для видалення:");
                    userService.deleteUserByEmail(emailToDelete);
                    break;
                case 0:
                    CustomerConsoleUI.printTitle("Дякую, що скористалися нашою програмою.");
                    System.exit(0);
                    break;
                default:
                    CustomerConsoleUI.printSystemMessage("Невірний вибір. Спробуйте ще раз.");
                    break;
            }

            if (choice == 0) {
                break;
            }
        }
    }
}



