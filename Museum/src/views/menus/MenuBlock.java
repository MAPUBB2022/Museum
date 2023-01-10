package views.menus;

import controllers.ControllerBlock;

import java.util.Scanner;

public class MenuBlock {
    static final String currentMenuClass = "block";
    static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scanner2 = new Scanner(System.in);
    private static final Scanner scanner3 = new Scanner(System.in);

    public static void options() throws ClassNotFoundException {
        System.out.println("Block Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Change name of  " + currentMenuClass);
        System.out.println("\t4. Add new exhibit in " + currentMenuClass);
        System.out.println("\t5. Remove an exhibit from " + currentMenuClass);
        System.out.println("\t6. Display " + currentMenuClass);
        System.out.println("\t7. Sort by size");
        System.out.println("\t8. Filter " + currentMenuClass);
        System.out.println("\t0. Return");

        int optionChosen = scanner.nextInt();

        if (optionChosen == 1)
            MenuBlock.add();
        if (optionChosen == 2)
            MenuBlock.delete();
        if (optionChosen == 3)
            MenuBlock.update();
        if (optionChosen == 4)
            MenuBlock.addEx();
        if (optionChosen == 5)
            MenuBlock.remEx();
        if (optionChosen == 6)
            MenuBlock.display();
        if (optionChosen == 7)
            MenuBlock.sort();
        if (optionChosen == 8)
            MenuBlock.filter();

    }

    private static void sort() throws ClassNotFoundException {
        ControllerBlock.sort();
    }

    private static void filter() throws ClassNotFoundException {
        System.out.println("\t\t1. Filter " + currentMenuClass + "s by number of exhibits");
        System.out.println("\t\t2. Filter " + currentMenuClass + "s by art movements");
        System.out.println("\t\t3. Filter " + currentMenuClass + "s by artists");
        System.out.println("\t\t4. Multiple filters ");
        System.out.println("\t\t0. Return");

        Scanner scannerUpdate = new Scanner(System.in);
        int optionChosen = scannerUpdate.nextInt();

        if (optionChosen == 1) {
            MenuBlock.filterByExhibit();
        }
        if (optionChosen == 2) {
            MenuBlock.filterByArtMovement();
        }
        if (optionChosen == 3) {
            MenuBlock.filterByArtists();
        }
        if (optionChosen == 4) {
            MenuBlock.multipleFilters();
        }
    }

    private static void multipleFilters() throws ClassNotFoundException {
        System.out.println("Enter the minimal number of exhibits");
        int minNumberExhibit = scanner2.nextInt();
        System.out.println("Enter art movement name");
        String artMovementName = scanner2.next();
        System.out.println("Enter artist ID");
        String artistID = scanner2.next();
        ControllerBlock.multipleFilters(minNumberExhibit, artMovementName, artistID);
    }

    private static void filterByExhibit() throws ClassNotFoundException {
        System.out.println("Enter the minimal number of exhibits");
        int minNumberExhibit = scanner2.nextInt();
        ControllerBlock.filterByExhibit(minNumberExhibit);
    }

    private static void filterByArtMovement() throws ClassNotFoundException {
        System.out.println("Enter art movement name");
        String artMovementName = scanner2.next();
        ControllerBlock.filterByArtMovement(artMovementName);
    }

    private static void filterByArtists() throws ClassNotFoundException {
        System.out.println("Enter Artist ID");
        String artistID = scanner2.next();
        ControllerBlock.filterByArtist(artistID);
    }

    private static void add() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the name of the block?");
        String blockName = scanner2.next();
        System.out.println("\t\tWhat is the name of the museum?");
        String museumName = scanner3.next();
        ControllerBlock.add(blockName, museumName);
    }

    private static void delete() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the block?");
        String blockId = scanner2.next();
        ControllerBlock.delete(blockId);
    }

    private static void update() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the block?");
        String blockId = scanner2.next();
        System.out.println("\t\tWhat is the new name of the block?");
        String blockName = scanner3.next();
        ControllerBlock.update(blockId, blockName);
    }

    private static void addEx() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the block?");
        String blockId = scanner2.next();
        System.out.println("\t\tWhat is the id of the new exhibit?");
        String exId = scanner3.next();
        ControllerBlock.addEx(blockId, exId);
    }

    private static void remEx() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the block?");
        String blockId = scanner2.next();
        System.out.println("\t\tWhat is the id of the old exhibit?");
        String exId = scanner3.next();
        ControllerBlock.remEx(blockId, exId);
    }

    private static void display() throws ClassNotFoundException {
        System.out.println("\t\tWhat is the id of the block?");
        String blockId = scanner2.next();
        ControllerBlock.display(blockId);
    }


}
