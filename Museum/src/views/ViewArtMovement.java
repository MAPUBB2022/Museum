package views;

import classes.ArtMovement;
import classes.Artist;

import java.util.List;

public class ViewArtMovement {
    public static void allInformationDisplay(ArtMovement artMovement, List<Artist> artists) {
        System.out.println("Art movement " + artMovement.getName());
        System.out.println("Date started:" + artMovement.getStartDate());
        System.out.println("Date ended:" + artMovement.getEndDate() + "\n");

        System.out.println("Artists:");
        for (Artist a : artists) {
            System.out.println(a.getName() + " with ID " + a.getId());
        }
    }

    public static void displayRandomArtist(ArtMovement artMovement) {
        System.out.println("Random artist: " + artMovement.getRandomArtist());
    }
}
