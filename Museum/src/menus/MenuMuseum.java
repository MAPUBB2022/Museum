package menus;

import controllers.ControllerMuseum;

import java.util.Scanner;

public class MenuMuseum {
    static final String currentMenuClass = "museum";
    static final Scanner scanner = new Scanner(System.in);
    static final Scanner scanner2 = new Scanner(System.in);
    static final Scanner scanner3 = new Scanner(System.in);


    public static void options() {
        System.out.println("Museum Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Update " + currentMenuClass);
        System.out.println("\t4. Display all exhibits of " + currentMenuClass);
        System.out.println("\t5. Display total visits of " + currentMenuClass);
        System.out.println("\t6. Display information about a " + currentMenuClass);
        System.out.println("\t0. Return");

        int optionChosen = scanner.nextInt();

        if (optionChosen == 1)
            MenuMuseum.add();
        if (optionChosen == 2)
            MenuMuseum.delete();
        if (optionChosen == 3)
            MenuMuseum.optionsUpdate();
        if (optionChosen == 4)
            MenuMuseum.displayAllExhibits();
        if (optionChosen == 5)
            MenuMuseum.displayTotalVisits();
        if (optionChosen == 6)
            MenuMuseum.display();
    }

    private static void optionsUpdate() {
        System.out.println("\t\t1. Update " + currentMenuClass + " name");
        System.out.println("\t\t2. Add Block to a " + currentMenuClass);
        System.out.println("\t\t3. Remove Block from a " + currentMenuClass);
        System.out.println("\t\t4. Add Client to a " + currentMenuClass);
        System.out.println("\t\t5. Remove Client from a " + currentMenuClass);
        System.out.println("\t\t0. Return");

        Scanner scannerUpdate = new Scanner(System.in);
        int optionChosen = scannerUpdate.nextInt();

        if (optionChosen == 1) {
            MenuMuseum.updateName();
        }
        if (optionChosen == 2) {
            MenuMuseum.addBlock();
        }
        if (optionChosen == 3) {
            MenuMuseum.deleteBlock();
        }
        if (optionChosen == 4) {
            MenuMuseum.addClient();
        }
        if (optionChosen == 5) {
            MenuMuseum.deleteClient();
        }
    }

    private static void add() {
        System.out.println("\t\tMuseum Name: ");
        String name = scanner2.nextLine();
        ControllerMuseum.add(name);
    }

    private static void delete() {
        System.out.println("\t\tMuseum Name: ");
        String name = scanner2.nextLine();
        ControllerMuseum.delete(name);
    }

    private static void updateName() {
        System.out.println("\t\t\tMuseum Name you want to change: ");
        String previousName = scanner2.nextLine();
        System.out.println("\t\t\tNew Name you want to change to: ");
        String newName = scanner3.nextLine();
        ControllerMuseum.changeName(previousName, newName);
    }

    private static void addBlock() {
        System.out.println("\t\t\tEnter ID of block you want to add: ");
        String blockID = scanner2.nextLine();
        System.out.println("\t\t\tEnter name of museum: ");
        String museumName = scanner3.nextLine();
        ControllerMuseum.addBlock(museumName, blockID);
    }

    private static void deleteBlock() {
        System.out.println("\t\t\tEnter ID of block you want to remove: ");
        String blockID = scanner2.nextLine();
        System.out.println("\t\t\tEnter name of museum: ");
        String museumName = scanner3.nextLine();
        ControllerMuseum.deleteBlock(museumName, blockID);
    }

    private static void addClient() {
        System.out.println("\t\t\tEnter ID of client you want to add: ");
        String clientId = scanner2.nextLine();
        System.out.println("\t\t\tEnter name of museum: ");
        String museumName = scanner3.nextLine();
        ControllerMuseum.addClient(museumName, clientId);
    }

    private static void deleteClient() {
        System.out.println("\t\t\tEnter ID of client you want to remove: ");
        String clientId = scanner2.nextLine();
        System.out.println("\t\t\tEnter name of museum: ");
        String museumName = scanner3.nextLine();
        ControllerMuseum.deleteClient(museumName, clientId);
    }

    private static void displayAllExhibits() {
        System.out.println("\t\tEnter name of museum: ");
        String name = scanner2.nextLine();
        ControllerMuseum.displayAllExhibits(name);
    }

    private static void displayTotalVisits() {
        System.out.println("\t\tEnter name of museum: ");
        String name = scanner2.nextLine();
        ControllerMuseum.displayTotalVisits(name);
    }

    private static void display() {
        System.out.println("\t\tEnter name of museum: ");
        String name = scanner2.nextLine();
        ControllerMuseum.display(name);
    }
}
