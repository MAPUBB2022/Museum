package menus;
import classes.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuArtist {
    static final String currentMenuClass = "artist";
    static Scanner scanner = new Scanner(System.in);
    private static MenuArtist single_instance = null;

    public static MenuArtist getInstance()
    {
        if (single_instance == null)
            single_instance = new MenuArtist();
        return single_instance;
    }

    public static void options() {

    }
}
