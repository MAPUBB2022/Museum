package classes;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private static int counter = 1000;
    private final String id;
    private String name;
    private List<Exhibit> exhibits;
    private List<Artist> artists;
    private List<ArtMovement> movements;

    public Block(String name) {
        this.id = "B" + counter++;
        this.name = name;
        this.exhibits = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.movements = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exhibit> getExhibits() {
        return exhibits;
    }

    public void setExhibits(List<Exhibit> worksOfArt) {
        this.exhibits = worksOfArt;
    }

    public void addExhibit(Exhibit exhibitToAdd) {
        if (exhibits.contains(exhibitToAdd)) {
            System.out.println("Duplicate!");
        } else {
            exhibits.add(exhibitToAdd);
            System.out.println("Added:" + exhibitToAdd.getName());
        }
    }

    public void deleteExhibit(Exhibit exhibitToRemove) {
        if (exhibits.contains(exhibitToRemove)) {
            System.out.println("Removed:" + exhibitToRemove.getName());
            exhibits.remove(exhibitToRemove);
        } else {
            System.out.println("No such exhibit was found!");
        }
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public void addArtist(Artist artistToAdd) {
        if (artists.contains(artistToAdd)) {
            System.out.println("Duplicate!");
        } else {
            artists.add(artistToAdd);
            System.out.println("Added:" + artistToAdd.getName());
        }
    }

    public void deleteArtist(Artist artistToRemove) {
        if (artists.contains(artistToRemove)) {
            System.out.println("Removed:" + artistToRemove.getName());
            artists.remove(artistToRemove);
        } else {
            System.out.println("No such artist was found!");
        }
    }

    public List<ArtMovement> getMovements() {
        return movements;
    }

    public void setMovements(List<ArtMovement> movements) {
        this.movements = movements;
    }

    public void addMovement(ArtMovement movementToAdd) {
        if (movements.contains(movementToAdd)) {
            System.out.println("Duplicate!");
        } else {
            movements.add(movementToAdd);
            System.out.println("Added:" + movementToAdd.getName());
        }
    }

    public void deleteMovement(ArtMovement movementToRemove) {
        if (movements.contains(movementToRemove)) {
            System.out.println("Removed:" + movementToRemove.getName());
            movements.remove(movementToRemove);
        } else {
            System.out.println("No such art movement was found!");
        }
    }
}
