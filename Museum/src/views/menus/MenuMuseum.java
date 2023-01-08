package views.menus;

import controllers.ControllerMuseum;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuMuseum {
    static final String currentMenuClass = "museum";
    static final Scanner scanner = new Scanner(System.in);
    static final Scanner scanner2 = new Scanner(System.in);
    static final Scanner scanner3 = new Scanner(System.in);


    public static void options() throws ClassNotFoundException, SQLException {
        System.out.println("Museum Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Update " + currentMenuClass);
        System.out.println("\t4. Display all exhibits of " + currentMenuClass);
        System.out.println("\t5. Display total visits of " + currentMenuClass);
        System.out.println("\t6. Display information about a " + currentMenuClass);
        System.out.println("\t7. Sort and Filter by ID " + currentMenuClass);
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
        if (optionChosen == 7) {
            MenuMuseum.sortAndFilter();
        }
    }

    private static void optionsUpdate() throws ClassNotFoundException {
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

    private static void sortAndFilter() throws ClassNotFoundException, SQLException {
        System.out.println("\t\t1. Sort by ID");
        System.out.println("\t\t2. Filter by minimal number of exhibits");
        System.out.println("\t\t3. Filter by minimal number of clients");
        System.out.println("\t\t4. Filter by minimal number of visits");
        System.out.println("\t\t0. Return");

        Scanner scannerUpdate = new Scanner(System.in);
        int optionChosen = scannerUpdate.nextInt();

        if (optionChosen == 1) {
            MenuMuseum.sort();
        }
        if (optionChosen == 2) {
            MenuMuseum.filterByExhibits();
        }
        if (optionChosen == 3) {
            MenuMuseum.filterByClients();
        }
        if (optionChosen == 4) {
            MenuMuseum.filterByVisits();
        }
    }

    private static void filterByClients() throws ClassNotFoundException {
        System.out.println("Input the minimal number of clients");
        int minNumberClients = scanner2.nextInt();
        ControllerMuseum.filterByClients(minNumberClients);
    }

    private static void filterByVisits() throws ClassNotFoundException, SQLException {
        System.out.println("Input the minimal number of visits");
        int minNumberVisits = scanner2.nextInt();
        ControllerMuseum.filterByVisits(minNumberVisits);
    }

    private static void filterByExhibits() throws ClassNotFoundException {
        System.out.println("Input the minimal number of clients");
        int minNumberExhibit = scanner2.nextInt();
        ControllerMuseum.filterByExhibit(minNumberExhibit);
    }

    private static void sort() throws ClassNotFoundException {
        System.out.println("Sort by ID:");
        ControllerMuseum.sort();
    }

    private static void add() throws ClassNotFoundException {
        System.out.println("\t\tMuseum Name: ");
        String name = scanner2.nextLine();
        ControllerMuseum.add(name);
    }

    private static void delete() throws ClassNotFoundException {
        System.out.println("\t\tMuseum Name: ");
        String name = scanner2.nextLine();
        ControllerMuseum.delete(name);
    }

    private static void updateName() throws ClassNotFoundException {
        System.out.println("\t\t\tMuseum Name you want to change: ");
        String previousName = scanner2.nextLine();
        System.out.println("\t\t\tNew Name you want to change to: ");
        String newName = scanner3.nextLine();
        ControllerMuseum.changeName(previousName, newName);
    }

    private static void addBlock() throws ClassNotFoundException {
        System.out.println("\t\t\tEnter ID of block you want to add: ");
        String blockID = scanner2.nextLine();
        System.out.println("\t\t\tEnter name of museum: ");
        String museumName = scanner3.nextLine();
        ControllerMuseum.addBlock(museumName, blockID);
    }

    private static void deleteBlock() throws ClassNotFoundException {
        System.out.println("\t\t\tEnter ID of block you want to remove: ");
        String blockID = scanner2.nextLine();
        System.out.println("\t\t\tEnter name of museum: ");
        String museumName = scanner3.nextLine();
        ControllerMuseum.deleteBlock(museumName, blockID);
    }

    private static void addClient() throws ClassNotFoundException {
        System.out.println("\t\t\tEnter ID of client you want to add: ");
        String clientId = scanner2.nextLine();
        System.out.println("\t\t\tEnter name of museum: ");
        String museumName = scanner3.nextLine();
        ControllerMuseum.addClient(museumName, clientId);
    }

    private static void deleteClient() throws ClassNotFoundException {
        System.out.println("\t\t\tEnter ID of client you want to remove: ");
        String clientId = scanner2.nextLine();
        System.out.println("\t\t\tEnter name of museum: ");
        String museumName = scanner3.nextLine();
        ControllerMuseum.deleteClient(museumName, clientId);
    }

    private static void displayAllExhibits() throws ClassNotFoundException {
        System.out.println("\t\tEnter name of museum: ");
        String name = scanner2.nextLine();
        ControllerMuseum.displayAllExhibits(name);
    }

    private static void displayTotalVisits() throws SQLException, ClassNotFoundException {
        System.out.println("\t\tEnter name of museum: ");
        String name = scanner2.nextLine();
        ControllerMuseum.displayTotalVisits(name);
    }

    private static void display() throws ClassNotFoundException {
        System.out.println("\t\tEnter name of museum: ");
        String name = scanner2.nextLine();
        ControllerMuseum.display(name);
    }
}
