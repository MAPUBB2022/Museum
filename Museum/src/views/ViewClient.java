package views;

import classes.Client;
import classes.Exhibit;

public class ViewClient {
    public static void displayClient(Client c) {
        System.out.println("Client " + c.getName());
        System.out.println("Favorites: ");
        for (Exhibit e : c.getFavorites()) {
            System.out.println("\t" + e.getName());
        }
        System.out.println(c.getVisits().size() + " tickets bought");
    }
}
