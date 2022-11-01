package menus;

import classes.Block;
import controllers.ControllerClient;
import repository.inmemory.BlockRepositoryMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuClient {
    static final String currentMenuClass = "client";
    private static final Scanner scanner2 = new Scanner(System.in);
    private static final Scanner scanner3 = new Scanner(System.in);
    static final Scanner scanner = new Scanner(System.in);

    public static void options() {
        System.out.println("Client Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Change name of  " + currentMenuClass);
        System.out.println("\t4. Add new visit of " + currentMenuClass);
        System.out.println("\t5. Add a new favorite of the " + currentMenuClass);
        System.out.println("\t6. Remove a favorite of the " + currentMenuClass);
        System.out.println("\t7. Display " + currentMenuClass);
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

    }

    private static void add() {
        System.out.println("\t\tWhat is the name of the client?");
        String clientName = scanner2.next();
        ControllerClient.add(clientName);
    }

    private static void delete() {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        ControllerClient.delete(clientId);
    }

    public static void update() {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        System.out.println("\t\tWhat is the new name of the client?");
        String clientName = scanner3.next();
        ControllerClient.update(clientId, clientName);
    }

    private static void addVisit() {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        System.out.println("How many blocks?");
        int num = Integer.parseInt(scanner3.next());
        List<Block> lb = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            Scanner scanner4 = new Scanner(System.in);
            System.out.println("\t\t\t" + i + ". Block");
            lb.add(BlockRepositoryMemory.getInstance().findById(scanner4.next()));
        }
        ControllerClient.addVisit(clientId, lb);
    }

    private static void addFav() {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        System.out.println("\t\tWhat is the id of the new favorite?");
        String exId = scanner3.next();
        ControllerClient.addFav(clientId, exId);
    }

    private static void remFav() {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        System.out.println("\t\tWhat is the id of the old favorite?");
        String exId = scanner3.next();
        ControllerClient.remFav(clientId, exId);
    }

    private static void display() {
        System.out.println("\t\tWhat is the id of the client?");
        String clientId = scanner2.next();
        ControllerClient.display(clientId);
    }

}
