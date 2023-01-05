package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Client")
public class Client implements Person, Comparable<Client> {

    private static int counter = 1000;

    @Id
    @Getter
    @Column(name="ID")
    private final String id;

    @OneToMany
    private List<Ticket> visits;

    @Getter
    @Setter
    @Column(name="Name")
    private String name;

    @OneToMany
    private List<Exhibit> favorites;

    public Client(String name) {
        this.name = name;
        this.id = "C" + counter++;
        this.visits = new ArrayList<>();
        this.favorites = new ArrayList<>();
    }

    public Client() {
        this.id = "C" + counter++;
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

    public void addVisit(Ticket t) {
        this.visits.add(t);
    }

    public List<Ticket> getVisits() {
        return visits;
    }

    public List<Exhibit> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Exhibit> favorites) {
        this.favorites = favorites;
    }

    public void addExhibitToFavorites(Exhibit exhibitToAdd) {
        if (favorites.contains(exhibitToAdd)) {
            System.out.println("Duplicate Favorite!");
        } else {
            favorites.add(exhibitToAdd);
            System.out.println("Added to favorites:" + exhibitToAdd.getName());
        }
    }

    public void deleteExhibitToFavorites(Exhibit exhibitToRemove) {
        if (favorites.contains(exhibitToRemove)) {
            System.out.println("Removed from favorites:" + exhibitToRemove.getName());
            favorites.remove(exhibitToRemove);
        } else {
            System.out.println("No such exhibit was found!");
        }
    }

    public String steal(Exhibit exhibitToSteal) {
        var chance = Math.random();
        if (chance < 0.03) {
            return ("Successfully stole:" + exhibitToSteal.getName());
        } else {
            return ("Busted by police!");
        }
    }

    public String presentSelf() {
        return ("Hello my name is " + this.getName() + " and I am a client!");
    }

    public String isFamous() {
        return ("Clients are not famous :(");
    }

    @Override
    public int compareTo(Client o) {
        return getName().compareTo(o.getName());
    }
}
