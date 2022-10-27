package menus;

import repository.inmemory.ExhibitRepositoryMemory;

import java.util.Scanner;

public class Menu {
    private static Menu single_instance = null;

    public static Menu getInstance() {
        if (single_instance == null)
            single_instance = new Menu();
        return single_instance;
    }

    public void showMenuOptions() {
        System.out.println("1. Museum Menu");
        System.out.println("2. Block Menu");
        System.out.println("3. Client Menu");
        System.out.println("4. Artist Menu");
        System.out.println("5. Art Movement Menu");
        System.out.println("6. Exhibit Menu");
        System.out.println("0. Exit");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if (option == 1) {
            MenuMuseum.options();
            showMenuOptions();
        }
        if (option == 2) {
            MenuBlock.options();
            showMenuOptions();
        }
        if (option == 3) {
            MenuClient.options();
            showMenuOptions();
        }
        if (option == 4) {
            MenuArtist.options();
            showMenuOptions();
        }
        if (option == 5) {
            MenuArtMovement.options();
            showMenuOptions();
        }
        if (option == 6) {
            MenuExhibit.options();
            showMenuOptions();
        }
        ExhibitRepositoryMemory.getInstance();
    }
}
