package menus;
import java.util.Scanner;

public class Menu {
    private static Menu single_instance = null;

    public static Menu getInstance()
    {
        if (single_instance == null)
            single_instance = new Menu();
        return single_instance;
    }

    public static void showMenuOptions() {
        System.out.println("1.Client Menu");
        System.out.println("2.Museum Menu");
        System.out.println("3.Tickeet Menu");
        System.out.println("10. Exit");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if (option == 1) {
            MenuClient.options();
        }
    }
}
