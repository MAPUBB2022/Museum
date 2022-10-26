package classes;

import java.util.ArrayList;
import java.util.List;

public class Client implements Person {
    private static int counter = 1000;
    private final String id;
    private String name;
    private final List<Ticket> visits;
    private List<Exhibit> favorites;

    public Client(String name) {
        this.name = name;
        this.id = "C" + counter++;
        this.visits = new ArrayList<>();
        this.favorites = new ArrayList<>();
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
}
