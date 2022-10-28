package views;

import classes.ArtMovement;
import classes.Artist;
import classes.Exhibit;

import java.util.ArrayList;

public class ViewArtist {
    public static void display(Artist artist) {
        System.out.println("Artist " + artist.getName());
        System.out.println("Date born:" + artist.getBirthDate());
        if (artist.getDeathDate() == null) {
            System.out.println("Artist did not die!");
        } else {
            System.out.println("Date died:" + artist.getDeathDate() + "\n");
        }

        ArrayList<ArtMovement> artMovements = (ArrayList<ArtMovement>) artist.getMovements();
        System.out.println("Art Movements:");
        for (ArtMovement am : artMovements) {
            System.out.println(am.getName() + " with ID " + am.getId());
        }

        ArrayList<Exhibit> exhibits = (ArrayList<Exhibit>) artist.getListOfArt();
        System.out.println("Blocks:");
        for (Exhibit e : exhibits) {
            System.out.println(e.getName() + " with ID " + e.getId());
        }
    }
}
