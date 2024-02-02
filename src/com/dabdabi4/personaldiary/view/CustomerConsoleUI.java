package com.dabdabi4.personaldiary.view;

import java.util.Scanner;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * Клас, який надає методи для виведення текстової інформації в консоль з використанням кольорів та
 * форматування.
 */
public class CustomerConsoleUI {

    public CustomerConsoleUI() {

    }

    /**
     * Виводить заголовок з певним текстом у магентовому кольорі з жирним шрифтом та іконкою книги.
     *
     * @param text Текст заголовка.
     */
    public static void printTitle(String text) {
        System.out.println(Ansi.ansi()
            .fgDefault()
            .fg(Ansi.Color.MAGENTA)
            .a(Ansi.Attribute.INTENSITY_BOLD)
            .a("\uD83D\uDCD4 " + text + " \uD83D\uDCD4")
            .reset());
    }

    /**
     * Виводить поле (назву) з певним текстом у чорному кольорі.
     *
     * @param text Текст поля (назви).
     */
    public static void printFieldName(String text) {
        System.out.print(Ansi.ansi()
            .fgDefault()
            .fg(Color.BLACK)
            .a(text + ": ")
            .reset());
    }

    /**
     * Виводить текстове поле у зеленому кольорі з курсивним стилем.
     *
     * @param text Текст поля.
     */
    public static void printField(String text) {
        System.out.println(Ansi.ansi()
            .fgDefault()
            .fg(Ansi.Color.GREEN)
            .a(Ansi.Attribute.ITALIC)
            .a(text)
            .reset());
    }

    /**
     * Виводить рядок з певним символом заповнення та довжиною у вказаних кольорах.
     *
     * @param fillChar Символ заповнення.
     * @param length   Довжина рядка.
     */
    public static void printLine(Character fillChar, int length) {
        String text = String.valueOf(fillChar).repeat(length);

        System.out.println(Ansi.ansi()
            .fgDefault()
            .bg(Ansi.Color.BLACK)
            .fg(Ansi.Color.MAGENTA)
            .a(Ansi.Attribute.INTENSITY_BOLD)
            .a(text)
            .reset());
    }

    /**
     * Виводить меню у магентовому кольорі з жирним шрифтом та дефісом на початку.
     *
     * @param text Текст пункту меню.
     */
    public static void printMenu(String text) {
        System.out.println(Ansi.ansi()
            .fgDefault()
            .fg(Ansi.Color.MAGENTA)
            .a(Ansi.Attribute.INTENSITY_BOLD)
            .a("- " + text + " ")
            .reset());
    }

    /**
     * Виводить системне повідомлення червоним на чорному фоні з жирним шрифтом та дефісом на
     * початку.
     *
     * @param text Текст повідомлення.
     */
    public static void printSystemMessage(String text) {
        System.out.println(Ansi.ansi()
            .fgDefault()
            .bg(Ansi.Color.RED)
            .fg(Ansi.Color.BLACK)
            .a(Ansi.Attribute.INTENSITY_BOLD)
            .a("- " + text + " ")
            .reset());
    }

    /**
     * Запитує введення від користувача з вказаним prompt та повертає введене значення.
     *
     * @param prompt  Повідомлення-запрошення для користувача.
     * @param scanner Об'єкт Scanner для отримання введення користувача.
     * @return Введене користувачем значення.
     */
    public static String promptUserForInput(String prompt, Scanner scanner) {
        System.out.print(Ansi.ansi()
            .fgDefault()
            .bg(Ansi.Color.YELLOW)
            .fg(Ansi.Color.BLACK)
            .a(prompt + ": ")
            .reset());

        return scanner.nextLine();
    }

}
