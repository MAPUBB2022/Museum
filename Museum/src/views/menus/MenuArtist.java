package views.menus;

import controllers.ControllerArtist;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MenuArtist {
    static final String currentMenuClass = "artist";
    static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scanner2 = new Scanner(System.in);
    private static final Scanner scanner3 = new Scanner(System.in);
    private static final DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);

    public static void options() throws ClassNotFoundException {
        System.out.println("Artist Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Update " + currentMenuClass);
        System.out.println("\t4. Display information about an " + currentMenuClass);
        System.out.println("\t5. An artist will present itself ");
        System.out.println("\t6. Find out if an artist is famous or not ");
        System.out.println("\t7. Sort by age");
        System.out.println("\t8. Filter artists");
        System.out.println("\t0. Return");

        int optionChosen = scanner.nextInt();

        if (optionChosen == 1)
            MenuArtist.add();
        if (optionChosen == 2)
            MenuArtist.delete();
        if (optionChosen == 3)
            MenuArtist.optionsUpdate();
        if (optionChosen == 4)
            MenuArtist.display();
        if (optionChosen == 5)
            MenuArtist.presentSelf();
        if (optionChosen == 6)
            MenuArtist.isFamous();
        if (optionChosen == 7)
            MenuArtist.sort();
        if (optionChosen == 8)
            MenuArtist.filter();
    }

    private static void filter() throws ClassNotFoundException {
        System.out.println("\t\t1. Filter " + currentMenuClass + "s by number of exhibits");
        System.out.println("\t\t2. Filter " + currentMenuClass + "s by art movements");
        System.out.println("\t\t3. Filter " + currentMenuClass + "s by born date");
        System.out.println("\t\t4. Filter " + currentMenuClass + "s by not dead");
        System.out.println("\t\t0. Return");

        Scanner scannerUpdate = new Scanner(System.in);
        int optionChosen = scannerUpdate.nextInt();

        if (optionChosen == 1) {
            MenuArtist.filterByExhibit();
        }
        if (optionChosen == 2) {
            MenuArtist.filterByArtMovement();
        }
        if (optionChosen == 3) {
            MenuArtist.filterByBorn();
        }
        if (optionChosen == 4) {
            MenuArtist.filterByDead();
        }
    }

    private static void filterByDead() throws ClassNotFoundException {
        ControllerArtist.filterByDead();
    }

    private static void filterByBorn() throws ClassNotFoundException {
        System.out.println("Enter born date");
        String startedDateString = scanner3.nextLine();
        Date BornDate;
        try {
            BornDate = format.parse(startedDateString);
        } catch (ParseException e) {
            System.out.println("Date format input is wrong:");
            updateDateBorn();
            return;
        }
        ControllerArtist.filterByBorn(BornDate);
    }

    private static void filterByArtMovement() throws ClassNotFoundException {
        System.out.println("Enter art movement name");
        String artMovementName = scanner2.next();
        ControllerArtist.filterByArtMovement(artMovementName);
    }

    private static void filterByExhibit() throws ClassNotFoundException {
        System.out.println("Enter the maximal number of exhibits:");
        int minNumberExhibit = scanner2.nextInt();
        ControllerArtist.filterByExhibit(minNumberExhibit);
    }

    private static void sort() throws ClassNotFoundException {
        ControllerArtist.sort();
    }

    private static void optionsUpdate() throws ClassNotFoundException {
        System.out.println("\t\t1. Update " + currentMenuClass + " name");
        System.out.println("\t\t2. Update date born of an " + currentMenuClass);
        System.out.println("\t\t3. Update date died of an " + currentMenuClass + " name");
        System.out.println("\t\t4. Add Art Movement to an " + currentMenuClass);
        System.out.println("\t\t5. Delete Art Movement from an " + currentMenuClass);
        System.out.println("\t\t6. Add Exhibit to an " + currentMenuClass);
        System.out.println("\t\t7. Delete Exhibit from an " + currentMenuClass);
        System.out.println("\t\t0. Return");

        Scanner scannerUpdate = new Scanner(System.in);
        int optionChosen = scannerUpdate.nextInt();

        if (optionChosen == 1) {
            MenuArtist.updateName();
        }
        if (optionChosen == 2) {
            MenuArtist.updateDateBorn();
        }
        if (optionChosen == 3) {
            MenuArtist.updateDateDied();
        }
        if (optionChosen == 4) {
            MenuArtist.addArtMovement();
        }
        if (optionChosen == 5) {
            MenuArtist.deleteArtMovement();
        }
        if (optionChosen == 6) {
            MenuArtist.addExhibit();
        }
        if (optionChosen == 7) {
            MenuArtist.deleteExhibit();
        }
    }

    private static void updateName() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tNew name of the artist: ");
        String newName = scanner2.nextLine();
        ControllerArtist.changeName(name, newName);
    }

    private static void updateDateBorn() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tEnter the date the artist was born you want to change to: (dd-MM-yy) (e.g.24-10-2002)");
        String startedDateString = scanner3.nextLine();
        Date newBornDate;
        try {
            newBornDate = format.parse(startedDateString);
        } catch (ParseException e) {
            System.out.println("Date format input is wrong:");
            updateDateBorn();
            return;
        }
        ControllerArtist.updateDateBorn(name, newBornDate);
    }

    private static void updateDateDied() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tEnter the date the artist has died you want to change to: (dd-MM-yy) (e.g.24-10-2002) / null -> did not die");
        String diedDateString = scanner3.nextLine();
        Date newDiedDate;
        if (diedDateString.equals("null")) {
            newDiedDate = null;
        } else {
            try {
                newDiedDate = format.parse(diedDateString);
            } catch (ParseException e) {
                System.out.println("Date format input is wrong:");
                updateDateDied();
                return;
            }
        }
        ControllerArtist.updateDateDied(name, newDiedDate);

    }

    private static void addArtMovement() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tArt movement id: ");
        String idArtMovement = scanner2.nextLine();
        ControllerArtist.addArtMovement(name, idArtMovement);
    }

    private static void deleteArtMovement() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tArt movement id: ");
        String idArtMovement = scanner2.nextLine();
        ControllerArtist.deleteArtMovement(name, idArtMovement);
    }

    private static void addExhibit() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tExhibit id: ");
        String idExhibit = scanner2.nextLine();
        ControllerArtist.addExhibit(name, idExhibit);
    }

    private static void deleteExhibit() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tExhibit id: ");
        String idExhibit = scanner2.nextLine();
        ControllerArtist.deleteExhibit(name, idExhibit);
    }


    private static void add() throws ClassNotFoundException {
        System.out.println("\t\tArtist name: ");
        String name = scanner2.nextLine();
        System.out.println("\t\tEnter the date the artist was born: (dd-MM-yy) (e.g.24-10-2002)");
        String startedDateString = scanner3.nextLine();
        Date bornDate, diedDate;
        try {
            bornDate = format.parse(startedDateString);
        } catch (ParseException e) {
            System.out.println("Date format input is wrong:");
            add();
            return;
        }
        System.out.println("\t\tEnter the date the artist died: (dd-MM-yy) (e.g.24-10-2002) / null -> did not die");
        String endedDateString = scanner3.nextLine();
        if (endedDateString.equals("null")) {
            diedDate = null;
        } else {
            try {
                diedDate = format.parse(endedDateString);
            } catch (ParseException e) {
                System.out.println("Date format input is wrong:");
                add();
                return;
            }
        }
        ControllerArtist.add(name, bornDate, diedDate);
    }

    private static void delete() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID: ");
        String name = scanner2.nextLine();
        ControllerArtist.delete(name);
    }

    private static void display() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID you want to display: ");
        String name = scanner2.nextLine();
        ControllerArtist.display(name);
    }

    private static void presentSelf() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID you want to display information about: ");
        String id = scanner2.nextLine();
        ControllerArtist.presentSelf(id);
    }

    private static void isFamous() throws ClassNotFoundException {
        System.out.println("\t\tArtist ID you want to display if is famous or not: ");
        String id = scanner2.nextLine();
        ControllerArtist.isFamous(id);
    }


}
