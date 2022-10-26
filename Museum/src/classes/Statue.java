package classes;

import classes.ArtMovement;
import classes.Artist;
import classes.Exhibit;

import java.util.Date;

public class Statue extends Exhibit {

    // type = "S"
    private Artist sculptor;
    private ArtMovement artMovement;

    public Statue(String name, Date creation, Block location, Artist sculptor, ArtMovement artMove, double price) {
        super(name, creation, location, sculptor, price);
        this.sculptor = sculptor;
        this.artMovement = artMove;
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }


    public String getId() {
        return super.getId();
    }

    public Date getCreation() {
        return super.getCreation();
    }

    public void setCreation(Date creation) {
        super.setCreation(creation);
    }

    public Block getLocation() {
        return super.getLocation();
    }

    public void setLocation(Block location) {
        super.setLocation(location);
    }

    public Artist getSculptor() {
        return sculptor;
    }

    public void setSculptor(Artist sculptor) {
        this.sculptor = sculptor;
    }

    public ArtMovement getArtMovement() {
        return artMovement;
    }

    public void setArtMovement(ArtMovement artMovement) {
        this.artMovement = artMovement;
    }
}