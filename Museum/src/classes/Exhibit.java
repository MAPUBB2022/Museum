package classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "exhibit_type")
@Table(name = "Exhibit")
public abstract class Exhibit implements Comparable<Exhibit> {
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
    @Column(name="Creation")
    private Date creation;

    @ManyToOne
    @Column(name="Location")
    private Block location;

    @Getter
    @Setter
    @Column(name="Price")
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

    public Exhibit() {
        this.id = "E" + counter++;
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
