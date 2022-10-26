package classes;

import classes.Artist;

import java.util.Date;

public abstract class Exhibit {
    private String name;
    private static int counter = 1000;
    private final String id;
    private Date creation;
    private Block location;
    private double price;

    public Exhibit(String name, Date creation, Block location, Artist artist, double price) {
        this.name = name;
        this.id = "E" + counter++;
        this.creation = creation;
        this.location = location;
        artist.addExhibit(this);
        location.addExhibit(this);
        this.price = price;
    }

    public Exhibit(String name, Date creation, Block location, double price) {
        this.name = name;
        this.id ="E" + counter++;
        this.creation = creation;
        this.location = location;
        location.addExhibit(this);
        this.price = price;
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

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Block getLocation() {
        return location;
    }

    public void setLocation(Block location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
