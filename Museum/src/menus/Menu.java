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

    public void showMenuOptions() {
        System.out.println("1.Museum Menu");
        System.out.println("2.Block Menu");
        System.out.println("3.Client Menu");
        System.out.println("4.Artist Menu");
        System.out.println("5.Art Movement Menu");
        System.out.println("6.Exhibit Menu");
        System.out.println("0. Exit");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if (option == 1) {
            MenuClient.options();
        }
    }
}
