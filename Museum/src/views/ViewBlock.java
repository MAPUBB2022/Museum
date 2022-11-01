package views;

import classes.ArtMovement;
import classes.Artist;
import classes.Block;
import classes.Exhibit;

public class ViewBlock {
    public static void displayBlock(Block b) {
        System.out.println("Block " + b.getName());

        System.out.println("Exhibits: ");
        for (Exhibit e : b.getExhibits()) {
            System.out.println("\t" + e.getName());
        }

        System.out.println("Artists: ");
        for (Artist a : b.getArtists()) {
            System.out.println("\t" + a.getName());
        }

        System.out.println("Art Movements: ");
        for (ArtMovement m : b.getMovements()) {
            System.out.println("\t" + m.getName());
        }
    }
}
