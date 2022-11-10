package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Artist implements Person, Comparable<Artist> {
    private static int counter = 1000;
    private final String id;
    private String name;
    private List<ArtMovement> movements;
    private Date birthDate;
    private Date deathDate;
    private List<Exhibit> exhibits;

    public Artist(String name, Date birthDate, Date deathDate) {
        this.name = name;
        this.id = "A" + counter++;
        this.movements = new ArrayList<>();
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.exhibits = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public List<Exhibit> getListOfArt() {
        return exhibits;
    }

    public void setListOfArt(List<Exhibit> art) {
        this.exhibits = art;
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

    public String presentSelf() {
        return ("Hello my name is " + this.getName() + " and I am a creative artist!");
    }

    public String isFamous() {
        if (this.deathDate == null)
            return ("Sorry, you are only famous after death");
        else
            return ("You are famous, " + this.getName());
    }

    @Override
    public int compareTo(Artist o) {
        if (getBirthDate() == null || o.getBirthDate() == null) {
            return 0;
        }
        return getBirthDate().compareTo(o.getBirthDate());
    }
}
