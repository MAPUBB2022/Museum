import java.util.Date;

public class Painting extends Exhibit{
    private Artist painter;
    private ArtMovement artMovement;

    public Painting(String name, Date creation, Block location, Artist painter, ArtMovement artMove, double price) {
        super(name, creation, location, painter, price);
        this.painter = painter;
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

    public Artist getPainter() {
        return painter;
    }

    public void setSculptor(Artist sculptor) {
        this.painter = sculptor;
    }

    public ArtMovement getArtMovement() {
        return artMovement;
    }

    public void setArtMovement(ArtMovement artMovement) {
        this.artMovement = artMovement;
    }
}
