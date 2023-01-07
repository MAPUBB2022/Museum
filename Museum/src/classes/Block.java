package classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import repository.database.MuseumDB;

import java.util.Date;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Block")
public class Block implements Comparable<Block>{
    private static int counter = 1000;

    @Id
    @Getter
    @Column(name="ID")
    private final String id;

    @Getter
    @Setter
    @Column(name="Name")
    private String name;

    @Getter
    @Setter
    @ManyToOne
    private Museum museum;

    @OneToMany
    private List<Exhibit> exhibits;

    @OneToMany
    private List<Artist> artists;

    @OneToMany
    private List<ArtMovement> movements;

    public Block(String name) {
        this.id = "B" + counter++;
        this.name = name;
        this.exhibits = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.movements = new ArrayList<>();
    }

    public Block(String ID, String name) {
        this.id = ID;
        this.name = name;
        this.exhibits = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.movements = new ArrayList<>();
    }

    public Block() {
        this.id = "B" + counter++;
    }

    public Block(String id, String name, Museum museum) {
        this.id = id;
        this.name = name;
        this.exhibits = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.movements = new ArrayList<>();
        this.museum = museum;
    }

    public Museum getMuseum() {
        return museum;
    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
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

    @Override
    public int compareTo(Block o) {
        return this.getExhibits().size() - o.getExhibits().size();
    }

    public static void changeCounter(int newCounter) {
        counter = newCounter;
    }
}
