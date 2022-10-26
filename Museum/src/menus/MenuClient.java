package menus;

import classes.Client;

import java.util.Scanner;

public class MenuClient {
    static final String currentMenuClass = "student";
    Scanner scanner = new Scanner(System.in);

    public static void options() {
        System.out.println("1. Add " + currentMenuClass);
        System.out.println("2. Delete " + currentMenuClass);
        System.out.println("3. Update " + currentMenuClass);
        System.out.println("5. Display " + currentMenuClass);

//        int optionChosen = scanner.nextInt();
//
//        if (optionChosen == 1)
//            return MenuClient.add();
//        if (optionChosen == 2)
//            return MenuClient.delete();
//        if(optionChosen == 3)
//            MenuClient.update();
//        if (optionChosen == 4)
//            MenuClient.display();

    }

    Client add() {
        System.out.println("What is the name of the client?");
        String clientName = scanner.next();
        return new Client(clientName);
    }

}
