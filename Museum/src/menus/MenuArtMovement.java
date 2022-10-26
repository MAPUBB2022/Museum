package menus;
import classes.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuArtMovement {
    static final String currentMenuClass = "art movement";
    static Scanner scanner = new Scanner(System.in);
    private static MenuArtMovement single_instance = null;

    public static MenuArtMovement getInstance()
    {
        if (single_instance == null)
            single_instance = new MenuArtMovement();
        return single_instance;
    }

    public static void options() {

    }
}
