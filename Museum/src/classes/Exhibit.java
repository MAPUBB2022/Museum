package classes;

import java.util.Date;

public abstract class Exhibit implements Comparable<Exhibit> {
    private static int counter = 1000;
    private final String id;
    private String name;
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
        this.id = "E" + counter++;
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

    @Override
    public int compareTo(Exhibit o) {
        return getName().compareTo(o.getName());
    }
}
