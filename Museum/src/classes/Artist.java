package classes;

import lombok.Getter;
import lombok.Setter;

import java.util.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Artist")
public class Artist implements Person, Comparable<Artist> {
    private static int counter = 1000;

    @Id
    @Getter
    @Column(name = "ID")
    private final String id;

    @Getter
    @Setter
    @Column(name = "Name")
    private String name;

    @ManyToMany
    private List<ArtMovement> movements;

    @Getter
    @Setter
    @Column(name = "BirthDate")
    private Date birthDate;

    @Getter
    @Setter
    @Column(name = "DeathDate")
    private Date deathDate;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Exhibit> exhibits;

    public Artist(String name, Date birthDate, Date deathDate) {
        this.name = name;
        this.id = "A" + counter++;
        this.movements = new ArrayList<>();
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.exhibits = new ArrayList<>();
    }

    public Artist(String id, String name, Date birthDate, Date deathDate) {
        this.name = name;
        this.id = id;
        this.movements = new ArrayList<>();
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.exhibits = new ArrayList<>();
    }

    public Artist() {
        this.id = "A" + counter++;
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

    public void deleteMovementNoSout(ArtMovement movementToRemove) {
        boolean contains = movements.contains(movementToRemove);
        for (ArtMovement movement : this.movements) {
            System.out.println(movement.getName());
            System.out.println(movementToRemove.getName());
            System.out.println("*");
            if (Objects.equals(movement.getName(), movementToRemove.getName())) {
                contains = true;
            }
        }
        if (contains) {
            System.out.println("Removed:" + movementToRemove.getName());
            movements.remove(movementToRemove);
        }
    }

    public void deleteMovement(ArtMovement movementToRemove) {
        boolean contains = movements.contains(movementToRemove);
        for (ArtMovement movement : this.movements) {
            if (Objects.equals(movement.getName(), movementToRemove.getName()) && movement.getStartDate() == movementToRemove.getStartDate() && movementToRemove.getEndDate() == movement.getEndDate()) {
                contains = true;
                break;
            }
        }
        if (contains) {
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

    public void updateArtMovements(String artMovementToDelete, ArtMovement artMovementToAdd) {
        boolean flagFound = false;
        List<ArtMovement> oldArtMovements = this.getMovements();
        ArtMovement artMovementToDeleteFromList = null;
        for (ArtMovement am : oldArtMovements) {
            if (Objects.equals(am.getName(), artMovementToDelete)) {
                artMovementToDeleteFromList = am;
                flagFound = true;
            }
        }
        if (flagFound) {
            oldArtMovements.remove(artMovementToDeleteFromList);
            oldArtMovements.add(artMovementToAdd);
            System.out.println("Changed for artist!");
            this.setMovements(oldArtMovements);
        }
    }


    public static void changeCounter(int newCounter) {
        counter = newCounter;
    }
}
