package classes;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;
import static java.lang.Math.random;

@Entity
@Table(name = "ArtMovement")
public class ArtMovement implements Comparable<ArtMovement> {
    private static int counter = 1000;

    @Id
    @Getter
    @Column(name = "ID")
    private final String id;

    @Getter
    @Setter
    @Column(name = "Name")
    private String name;

    @Getter
    @Setter
    @Column(name = "StartDate")
    private Date startDate;

    @Getter
    @Setter
    @Column(name = "EndDate")
    private Date endDate;

    @ManyToMany
    private List<Artist> artists;

    public ArtMovement(String name, Date startDate, Date endDate) {
        this.id = "V" + counter++;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.artists = new ArrayList<>();
    }

    public ArtMovement(String ID, String name, Date startDate, Date endDate) {
        this.id = ID;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.artists = new ArrayList<>();
    }

    public ArtMovement() {
        this.id = "V" + counter++;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> creators) {
        this.artists = creators;
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

    public String getRandomArtist() {
        if (this.artists.isEmpty()) {
            return "No Artists in this Movement";
        }
        int max = artists.size() - 1;
        int min = 0;
        int position = (int) floor(random() * (max - min + 1) + min);
        return this.artists.get(position).getName();
    }

    @Override
    public int compareTo(ArtMovement am) {
        if (getStartDate() == null || am.getStartDate() == null) {
            return 0;
        }
        return getStartDate().compareTo(am.getStartDate());
    }

    public static void changeCounter(int newCounter) {
        counter = newCounter;
    }
}
