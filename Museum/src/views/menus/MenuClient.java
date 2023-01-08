package views.menus;

import classes.Block;
import controllers.ControllerBlock;
import controllers.ControllerClient;
import repository.database.BlockDB;
import repository.inmemory.BlockRepositoryMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuClient {
    static final String currentMenuClass = "client";
    static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scanner2 = new Scanner(System.in);
    private static final Scanner scanner3 = new Scanner(System.in);

    public static void options() throws ClassNotFoundException {
        System.out.println("Client Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Change name of  " + currentMenuClass);
        System.out.println("\t4. Add new visit of " + currentMenuClass);
        System.out.println("\t5. Add a new favorite of the " + currentMenuClass);
        System.out.println("\t6. Remove a favorite of the " + currentMenuClass);
        System.out.println("\t7. Display " + currentMenuClass);
        System.out.println("\t8. Sort by name");
        System.out.println("\t9. Filter " + currentMenuClass);
        System.out.println("\t0. Return");

        int optionChosen = scanner.nextInt();

        if (optionChosen == 1)
            MenuClient.add();
        if (optionChosen == 2)
            MenuClient.delete();
        if (optionChosen == 3)
            MenuClient.update();
        if (optionChosen == 4)
            MenuClient.addVisit();
        if (optionChosen == 5)
            MenuClient.addFav();
        if (optionChosen == 6)
            MenuClient.remFav();
        if (optionChosen == 7)
            MenuClient.display();
        if (optionChosen == 8)
            MenuClient.sort();
        if (optionChosen == 9)
            MenuClient.filter();

    }

    private static void sort() throws ClassNotFoundException {ControllerClient.sort();}

    private static void filter() throws ClassNotFoundException {
        System.out.println("\t\t1. Filter " + currentMenuClass + "s by favorites");
        System.out.println("\t\t2. Filter " + currentMenuClass + "s by number of visits");
        System.out.println("\t\t0. Return");

        Scanner scannerUpdate = new Scanner(System.in);
        int optionChosen = scannerUpdate.nextInt();

        if (optionChosen == 1) {
            MenuClient.filterByFavorites();
        }
        if (optionChosen == 2) {
            MenuClient.filterByVisits();
        }
    }

    private static void filterByFavorites() throws ClassNotFoundException {
        System.out.println("Enter exhibit name");
        String exhibitName = scanner2.next();
        ControllerClient.filterByFavorites(exhibitName);
    }

    private static void filterByVisits() throws ClassNotFoundException {
        System.out.println("Enter the minimal number of visits");
        int minNumberVisits = scanner2.nextInt();
        ControllerClient.filterByVisits(minNumberVisits);
    }

    private static void add() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the name of the client?");
        String clientName = scanner2.next();
        ControllerClient.add(clientName);
    }

    private static void delete() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        ControllerClient.delete(clientId);
    }

    public static void update() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        System.out.println("\t\tWhat is the new name of the client?");
        String clientName = scanner3.next();
        ControllerClient.update(clientId, clientName);
    }

    private static void addVisit() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        System.out.println("How many blocks?");
        int num = Integer.parseInt(scanner3.next());
        List<Block> lb = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            Scanner scanner4 = new Scanner(System.in);
            System.out.println("\t\t\t" + i + ". Block");
            lb.add(BlockDB.getInstance().findById(scanner4.next()));
        }
        ControllerClient.addVisit(clientId, lb);
    }

    private static void addFav() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        System.out.println("\t\tWhat is the id of the new favorite?");
        String exId = scanner3.next();
        ControllerClient.addFav(clientId, exId);
    }

    private static void remFav() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        System.out.println("\t\tWhat is the id of the old favorite?");
        String exId = scanner3.next();
        ControllerClient.remFav(clientId, exId);
    }

    private static void display() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        ControllerClient.display(clientId);
    }

}
