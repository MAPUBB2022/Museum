package menus;

import controllers.ControllerBlock;

import java.util.Scanner;

public class MenuBlock {
    static final String currentMenuClass = "block";
    private static final Scanner scanner2 = new Scanner(System.in);
    private static final Scanner scanner3 = new Scanner(System.in);
    static Scanner scanner = new Scanner(System.in);

    public static void options() {
        System.out.println("Block Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Change name of  " + currentMenuClass);
        System.out.println("\t4. Add new exhibit in " + currentMenuClass);
        System.out.println("\t5. Remove an exhibit from " + currentMenuClass);
        System.out.println("\t6. Display " + currentMenuClass);
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

    }

    private static void add() {
        System.out.println("\t\tWhat is the name of the block?");
        String blockName = scanner2.next();
        ControllerBlock.add(blockName);
    }

    private static void delete() {
        System.out.println("\t\tWhat is the id of the block?");
        String blockId = scanner2.next();
        ControllerBlock.delete(blockId);
    }

    private static void update() {
        System.out.println("\t\tWhat is the id of the block?");
        String blockId = scanner2.next();
        System.out.println("\t\tWhat is the new name of the block?");
        String blockName = scanner3.next();
        ControllerBlock.update(blockId, blockName);
    }

    private static void addEx() {
        System.out.println("\t\tWhat is the id of the block?");
        String blockId = scanner2.next();
        System.out.println("\t\tWhat is the id of the new exhibit?");
        String exId = scanner3.next();
        ControllerBlock.addEx(blockId, exId);
    }

    private static void remEx() {
        System.out.println("\t\tWhat is the id of the block?");
        String blockId = scanner2.next();
        System.out.println("\t\tWhat is the id of the old exhibit?");
        String exId = scanner3.next();
        ControllerBlock.remEx(blockId, exId);
    }

    private static void display() {
        System.out.println("\t\tWhat is the id of the block?");
        String blockId = scanner2.next();
        ControllerBlock.display(blockId);
    }


}
