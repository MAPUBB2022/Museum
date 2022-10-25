package classes;

import classes.Artist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.*;

public class ArtMovement {
    private static int counter = 1000;
    private final String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private List<Artist> artists;

    public ArtMovement(String name, Date startDate, Date endDate) {
        this.id = "V" + counter++;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.artists = new ArrayList<>();
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
        if(artists.contains(artistToAdd)) {
            System.out.println("Duplicate!");
        } else {
            artists.add(artistToAdd);
            System.out.println("Added:" + artistToAdd.getName());
        }
    }

    public void deleteArtist(Artist artistToRemove) {
        if(artists.contains(artistToRemove)) {
            System.out.println("Removed:" + artistToRemove.getName());
            artists.remove(artistToRemove);
        }
        else {
            System.out.println("No such artist was found!");
        }
    }

    public void displayRandomArtist() {
        if(this.artists.isEmpty())
        {
            System.out.println("No Artists in this Movement");
            return;
        }
        int max = artists.size()-1;
        int min = 0;
        int position = (int) floor(random()*(max-min+1)+min);
        System.out.println(this.artists.get(position).getName());
    }
}
