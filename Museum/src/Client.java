import java.util.ArrayList;
import java.util.List;

public class Client implements  Person{
    private String name;
    private static int counter = 1000;
    private final String id;
    private List<Ticket> visits;
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
        if(favorites.contains(exhibitToAdd)) {
            System.out.println("Duplicate Favorite!");
        } else {
        favorites.add(exhibitToAdd);
        System.out.println("Added to favorites:" + exhibitToAdd.getName());}
    }

    public void deleteExhibitToFavorites(Exhibit exhibitToRemove) {
        if(favorites.contains(exhibitToRemove)) {
            System.out.println("Removed from favorites:" + exhibitToRemove.getName());
            favorites.remove(exhibitToRemove);
        }
        else {
            System.out.println("No such exhibit was found!");
        }
    }

    public void steal(Exhibit exhibitToSteal) {
        var chance = Math.random();
        if (chance < 0.03) {
            System.out.println("Successfully stole:" + exhibitToSteal.getName());
        }
        else {
            System.out.println("Busted by police!");
        }
    }

    public void presentSelf() {
        System.out.println("Hello my name is " + this.getName() + " and I am a client!");
    }

    public void isFamous() {
        System.out.println("Clients are not famous :(");
    }
}
