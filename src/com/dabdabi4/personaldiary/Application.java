package com.dabdabi4.personaldiary;

import com.dabdabi4.personaldiary.entity.model.User;
import com.dabdabi4.personaldiary.view.Menu;

public class Application {

    public static User[] users;

    //поточний юзер, потрібно закинути в ІнітКонфіг
    public static User currentUser = new User("Den32131", "Denis123@gmail.com", "Denis123qwert",
        "");

    public static void runner() throws IllegalAccessException {

        Menu.show();
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("Особистий щоденник");
        runner();
    }
}
