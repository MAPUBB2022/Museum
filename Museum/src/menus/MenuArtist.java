package menus;

import controllers.ControllerArtist;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MenuArtist {
    static final String currentMenuClass = "artist";
    private static final Scanner scanner2 = new Scanner(System.in);
    private static final Scanner scanner3 = new Scanner(System.in);
    private static final DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
    static Scanner scanner = new Scanner(System.in);

    public static void options() {
        System.out.println("Art Movement Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Update " + currentMenuClass);
        System.out.println("\t4. Display information about an " + currentMenuClass);
        System.out.println("\t5. An artist will present itself ");
        System.out.println("\t6. Find out if an artist is famous or not ");
        System.out.println("\t0. Return");

        int optionChosen = scanner.nextInt();

        if (optionChosen == 1)
            MenuArtist.add();
        if (optionChosen == 2)
            MenuArtist.delete();
        if (optionChosen == 3)
            MenuArtist.optionsUpdate();
//        if (optionChosen == 4)
//            MenuArtist.display();
//        if (optionChosen == 5)
//            MenuArtist.presentSelf();
//        if(optionChosen == 6)
//            MenuArtist.isFamous();
    }

    private static void optionsUpdate() {
        System.out.println("\t\t1. Update " + currentMenuClass + " name");
        System.out.println("\t\t2. Update date created of an " + currentMenuClass);
        System.out.println("\t\t3. Update date ended of an " + currentMenuClass + " name");
        System.out.println("\t\t4. Add Art Movement to an " + currentMenuClass);
        System.out.println("\t\t5. Delete Art Movement from an " + currentMenuClass);
        System.out.println("\t\t4. Add Exhibit to an " + currentMenuClass);
        System.out.println("\t\t5. Delete Exhibit from an " + currentMenuClass);
        System.out.println("\t\t0. Return");

        Scanner scannerUpdate = new Scanner(System.in);
        int optionChosen = scannerUpdate.nextInt();

//        if (optionChosen == 1) {
//            MenuArtist.updateName();
//        }
//        if (optionChosen == 2) {
//            MenuArtist.updateDateCreated();
//        }
//        if (optionChosen == 3) {
//            MenuArtist.updateDateEnded();
//        }
//        if (optionChosen == 4) {
//            MenuArtist.addArtMovement();
//        }
//        if (optionChosen == 5) {
//            MenuArtist.deleteArtMovement();
//        }
//        if (optionChosen == 6) {
//            MenuArtist.addExhibit();
//        }
//        if (optionChosen == 7) {
//            MenuArtist.deleteExhibit();
//        }
    }

    private static void add() {
        System.out.println("\t\tArtist name: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tEnter the date the artist was born: (dd-MM-yy) (e.g.24-10-2002)");
        String startedDateString = scanner3.nextLine();
        Date endedDate, startedDate;
        try {
            startedDate = format.parse(startedDateString);
        } catch (ParseException e) {
            System.out.println("Date format input is wrong:");
            add();
            return;
        }
        System.out.println("\t\tEnter the date the artist died: (dd-MM-yy) (e.g.24-10-2002)");
        String endedDateString = scanner3.nextLine();
        try {
            endedDate = format.parse(endedDateString);
        } catch (ParseException e) {
            System.out.println("Date format input is wrong:");
            add();
            return;
        }
        ControllerArtist.add(name, startedDate, endedDate);
    }

    private static void delete() {
        System.out.println("\t\tArtist name: ");
        String name = scanner2.nextLine();
//        ControllerArtist.delete(name);
    }

}
