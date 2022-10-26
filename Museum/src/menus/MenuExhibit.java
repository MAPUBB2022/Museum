package menus;

import classes.Client;
import classes.Exhibit;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuExhibit {
    static final String currentMenuClass = "exhibit";
    static Scanner scanner = new Scanner(System.in);
    private static MenuExhibit single_instance = null;

    public static MenuExhibit getInstance()
    {
        if (single_instance == null)
            single_instance = new MenuExhibit();
        return single_instance;
    }

    public static void options() {

    }
}
