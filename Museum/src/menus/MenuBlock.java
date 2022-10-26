package menus;
import classes.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuBlock {
    static final String currentMenuClass = "block";
    static Scanner scanner = new Scanner(System.in);
    private static MenuBlock single_instance = null;

    public static MenuBlock getInstance()
    {
        if (single_instance == null)
            single_instance = new MenuBlock();
        return single_instance;
    }

    public static void options() {

    }
}
