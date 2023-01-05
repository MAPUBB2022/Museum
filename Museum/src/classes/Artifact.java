package classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("artifact")
@Table(name = "Artifact")
public class Artifact extends Exhibit {

    // type = "R"

    @Id
    @Getter
    @Column(name="Origin")
    private String origin;

    public Artifact(String name, Date creation, Block location, String origin, double price) {
        super(name, creation, location, price);
        this.origin = origin;
    }

    public Artifact() {

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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
