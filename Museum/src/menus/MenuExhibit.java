package menus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

public class MenuExhibit {
    static final String currentMenuClass = "exhibit";
    private static final Scanner scanner2 = new Scanner(System.in);
    private static final Scanner scanner3 = new Scanner(System.in);
    private static final DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
    static Scanner scanner = new Scanner(System.in);

    public static void options() {
        System.out.println("Exhibit Menu");
        System.out.println("\t1. Add " + currentMenuClass);
        System.out.println("\t2. Delete " + currentMenuClass);
        System.out.println("\t3. Update " + currentMenuClass);
        System.out.println("\t4. Display information about an " + currentMenuClass);
        System.out.println("\t0. Return");

        int optionChosen = scanner.nextInt();

//        if (optionChosen == 1)
//            MenuExhibit.add();
//        if (optionChosen == 2)
//            MenuExhibit.delete();
        if (optionChosen == 3)
            MenuExhibit.optionsUpdate();
//        if (optionChosen == 4)
//            MenuExhibit.display();
    }

    public static void optionsUpdate() {
        System.out.println("\t\t1. Update " + currentMenuClass + " name");
        System.out.println("\t\t2. Update date of creation of an " + currentMenuClass);
        System.out.println("\t\t3. Update price of an " + currentMenuClass + " name");
        System.out.println("\t\t4. Add Art Movement to an " + currentMenuClass);
        System.out.println("\t\t5. Delete Art Movement from an " + currentMenuClass);
        System.out.println("\t\t4. Add Exhibit to an " + currentMenuClass);
        System.out.println("\t\t5. Delete Exhibit from an " + currentMenuClass);
        System.out.println("\t\t0. Return");

        Scanner scannerUpdate = new Scanner(System.in);
        int optionChosen = scannerUpdate.nextInt();
    }
}
