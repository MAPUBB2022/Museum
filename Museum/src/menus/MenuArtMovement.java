package menus;

import controllers.ControllerArtMovement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MenuArtMovement {
    private static final String currentMenuClass = "art movement";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scanner2 = new Scanner(System.in);
    private static final Scanner scanner3 = new Scanner(System.in);
    private static final DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);

    public static void options() {
        System.out.println("Art Movement Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Update " + currentMenuClass);
        System.out.println("\t4. Display information about an " + currentMenuClass);
        System.out.println("\t5. Display random artists from a " + currentMenuClass);
        System.out.println("\t0. Return");

        int optionChosen = scanner.nextInt();

        if (optionChosen == 1)
            MenuArtMovement.add();
        if (optionChosen == 2)
            MenuArtMovement.delete();
        if (optionChosen == 3)
            MenuArtMovement.optionsUpdate();
        if (optionChosen == 4)
            MenuArtMovement.display();
        if (optionChosen == 5)
            MenuArtMovement.displayRandomArtist();
    }

    private static void optionsUpdate() {
        System.out.println("\t\t1. Update " + currentMenuClass + " name");
        System.out.println("\t\t2. Update date created of an " + currentMenuClass);
        System.out.println("\t\t3. Update date ended of an " + currentMenuClass + " name");
        System.out.println("\t\t4. Add Artist to an " + currentMenuClass);
        System.out.println("\t\t5. Delete Artist from an " + currentMenuClass);
        System.out.println("\t\t0. Return");

        Scanner scannerUpdate = new Scanner(System.in);
        int optionChosen = scannerUpdate.nextInt();

        if (optionChosen == 1) {
            MenuArtMovement.updateName();
        }
        if (optionChosen == 2) {
            MenuArtMovement.updateDateCreated();
        }
        if (optionChosen == 3) {
            MenuArtMovement.updateDateEnded();
        }
        if (optionChosen == 4) {
            MenuArtMovement.addArtist();
        }
        if (optionChosen == 5) {
            MenuArtMovement.deleteArtist();
        }
    }

    private static void add() {
        System.out.println("\t\tArt Movement name: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tEnter the date the art movement started: (dd-MM-yy) (e.g.24-10-2002)");
        String startedDateString = scanner3.nextLine();
        Date endedDate, startedDate;
        try {
            startedDate = format.parse(startedDateString);
        } catch (ParseException e) {
            System.out.println("Date format input is wrong:");
            add();
            return;
        }
        System.out.println("\t\tEnter the date the art movement ended: (dd-MM-yy) (e.g.24-10-2002)");
        String endedDateString = scanner3.nextLine();
        try {
            endedDate = format.parse(endedDateString);
        } catch (ParseException e) {
            System.out.println("Date format input is wrong:");
            add();
            return;
        }
        ControllerArtMovement.add(name, startedDate, endedDate);
    }

    private static void delete() {
        System.out.println("\t\tArt Movement name to delete: ");
        String name = scanner2.nextLine();
        ControllerArtMovement.delete(name);
    }

    private static void updateName() {
        System.out.println("\t\tArt Movement name you want to change: ");
        String oldName = scanner2.nextLine();
        System.out.println("\t\tNew Art Movement name you want to change to: ");
        String newName = scanner2.nextLine();
        ControllerArtMovement.changeName(oldName, newName);
    }

    private static void updateDateCreated() {
        System.out.println("\t\tArt Movement name you want to change: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tThe date created you want to change to: ");
        String startedDateString = scanner3.nextLine();
        Date startedDate;
        try {
            startedDate = format.parse(startedDateString);
        } catch (ParseException e) {
            System.out.println("Date format input is wrong:");
            updateDateCreated();
            return;
        }
        ControllerArtMovement.updateDateCreated(name, startedDate);
    }

    private static void updateDateEnded() {
        System.out.println("\t\tArt Movement name you want to change: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tThe date ended you want to change to: ");
        String endedDateString = scanner3.nextLine();
        Date endDate;
        try {
            endDate = format.parse(endedDateString);
        } catch (ParseException e) {
            System.out.println("Date format input is wrong:");
            updateDateCreated();
            return;
        }
        ControllerArtMovement.updateDateEnded(name, endDate);
    }

    private static void addArtist() {
        System.out.println("\t\tArtist ID you want to add");
        String artistId = scanner3.nextLine();
        System.out.println("\t\tArt Movement name: ");
        String name = scanner2.nextLine();
        ControllerArtMovement.addArtist(name, artistId);
    }

    private static void deleteArtist() {
        System.out.println("\t\tArtist ID you want to delete");
        String artistId = scanner3.nextLine();
        System.out.println("\t\tArt Movement name: ");
        String name = scanner2.nextLine();
        ControllerArtMovement.deleteArtist(name, artistId);
    }

    private static void display() {
        System.out.println("\t\tEnter name of art movement: ");
        String name = scanner2.nextLine();
        ControllerArtMovement.display(name);
    }

    private static void displayRandomArtist() {
        System.out.println("\t\tEnter name of art movement: ");
        String name = scanner2.nextLine();
        ControllerArtMovement.displayRandomArtist(name);
    }
}
