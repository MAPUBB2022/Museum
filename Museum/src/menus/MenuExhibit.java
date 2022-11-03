package menus;

import controllers.ControllerExhibit;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MenuExhibit {
    static final String currentMenuClass = "exhibit";
    static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scanner2 = new Scanner(System.in);
    private static final Scanner scanner3 = new Scanner(System.in);
    private static final DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);

    public static void options() {
        System.out.println("Exhibit Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Update " + currentMenuClass);
        System.out.println("\t4. Display information about an " + currentMenuClass);
        System.out.println("\t0. Return");

        int optionChosen = scanner.nextInt();

        if (optionChosen == 1)
            MenuExhibit.add();
        if (optionChosen == 2)
            MenuExhibit.delete();
        if (optionChosen == 3)
            MenuExhibit.optionsUpdate();
        if (optionChosen == 4)
            MenuExhibit.display();
    }

    public static void optionsUpdate() {
        System.out.println("\t\t1. Update " + currentMenuClass + " name");
        System.out.println("\t\t2. Update date of creation of an " + currentMenuClass);
        System.out.println("\t\t3. Update price of an " + currentMenuClass);
        System.out.println("\t\t0. Return");

        Scanner scannerUpdate = new Scanner(System.in);
        int optionChosen = scannerUpdate.nextInt();
        if (optionChosen == 1)
            MenuExhibit.updateName();
        if (optionChosen == 2)
            MenuExhibit.updateDateOfCreation();
        if (optionChosen == 3)
            MenuExhibit.updatePrice();
    }

    private static void updateName() {
        System.out.println("\t\tEnter ID of Exhibit:");
        String idOfExhibit = scanner2.nextLine();
        System.out.println("\t\tEnter new name of Exhibit:");
        String newName = scanner3.nextLine();
        ControllerExhibit.updateName(idOfExhibit, newName);
    }

    private static void updateDateOfCreation() {
        System.out.println("\t\tEnter ID of Exhibit:");
        String idOfExhibit = scanner2.nextLine();
        System.out.println("\t\tEnter the date of creation you want to update to:");
        String newCreationDateString = scanner3.nextLine();
        Date newCreationDate;
        try {
            newCreationDate = format.parse(newCreationDateString);
        } catch (ParseException e) {
            System.out.println("\t\tDate format input is wrong:");
            updateDateOfCreation();
            return;
        }
        ControllerExhibit.updateDate(idOfExhibit, newCreationDate);
    }

    private static void updatePrice() {
        System.out.println("\t\tEnter ID of Exhibit:");
        String idOfExhibit = scanner2.nextLine();
        System.out.println("\t\tEnter new price of Exhibit:");
        double newPrice = scanner3.nextDouble();
        ControllerExhibit.updatePrice(idOfExhibit, newPrice);
    }


    private static void add() {
        System.out.println("\t\tChoose what type of exhibit you want to add:");
        System.out.println("\t\t1. Artifact");
        System.out.println("\t\t2. Painting");
        System.out.println("\t\t3. Statue");
        System.out.println("\t\t0. Return");
        int optionAdd = scanner2.nextInt();

        if (optionAdd == 1) {
            System.out.println("\t\t\tEnter Name:");
            String name = scanner3.nextLine();
            System.out.println("\t\t\tEnter the date of creation:");
            String CreationDateString = scanner3.nextLine();
            Date creationDate;
            try {
                creationDate = format.parse(CreationDateString);
            } catch (ParseException e) {
                System.out.println("\t\t\tDate format input is wrong:");
                add();
                return;
            }
            System.out.println("\t\t\tEnter Block ID where you can find it:");
            String blockId = scanner3.nextLine();
            System.out.println("\t\t\tEnter the origin place location");
            String location = scanner3.nextLine();
            System.out.println("\t\t\tEnter value of exhibit:");
            double price = scanner3.nextDouble();
            ControllerExhibit.addArtifact(name, creationDate, blockId, location, price);
            return;
        }

        if (optionAdd == 2) {
            System.out.println("\t\t\tEnter Name:");
            String name = scanner3.nextLine();
            System.out.println("\t\t\tEnter the date of creation:");
            String CreationDateString = scanner3.nextLine();
            Date creationDate;
            try {
                creationDate = format.parse(CreationDateString);
            } catch (ParseException e) {
                System.out.println("\t\t\tDate format input is wrong:");
                add();
                return;
            }
            System.out.println("\t\t\tEnter Block ID where you can find it:");
            String blockId = scanner3.nextLine();
            System.out.println("\t\t\tEnter Painter(Artist) ID:");
            String artistId = scanner3.nextLine();
            System.out.println("\t\t\tEnter Art Movement ID:");
            String artMovement = scanner3.nextLine();
            System.out.println("\t\t\tEnter value of exhibit:");
            double price = scanner3.nextDouble();
            ControllerExhibit.addPainting(name, creationDate, blockId, artistId, artMovement, price);
        }

        if (optionAdd == 3) {
            System.out.println("\t\t\tEnter Name:");
            String name = scanner3.nextLine();
            System.out.println("\t\t\tEnter the date of creation:");
            String CreationDateString = scanner3.nextLine();
            Date creationDate;
            try {
                creationDate = format.parse(CreationDateString);
            } catch (ParseException e) {
                System.out.println("\t\t\tDate format input is wrong:");
                add();
                return;
            }
            System.out.println("\t\t\tEnter Block ID where you can find it:");
            String blockId = scanner3.nextLine();
            System.out.println("\t\t\tEnter Creator(Artist) ID:");
            String artistId = scanner3.nextLine();
            System.out.println("\t\t\tEnter Art Movement ID:");
            String artMovement = scanner3.nextLine();
            System.out.println("\t\t\tEnter value of exhibit:");
            double price = scanner3.nextDouble();
            ControllerExhibit.addStatue(name, creationDate, blockId, artistId, artMovement, price);
        }
    }


    private static void delete() {
        System.out.println("\t\tEnter ID of Exhibit:");
        String idOfExhibit = scanner2.nextLine();
        ControllerExhibit.delete(idOfExhibit);
    }


    private static void display() {
        System.out.println("\t\tEnter ID of Exhibit:");
        String idOfExhibit = scanner2.nextLine();
        ControllerExhibit.display(idOfExhibit);
    }

}
