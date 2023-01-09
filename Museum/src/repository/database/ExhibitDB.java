package repository.database;
import classes.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExhibitDB {
    private static ExhibitDB single_instance = null;

    public static ExhibitDB getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new ExhibitDB();
        }
        return single_instance;
    }

    public Exhibit findById(String id) throws ClassNotFoundException{
        if(ArtifactDB.getInstance().findById(id) != null) {
            return ArtifactDB.getInstance().findById(id);
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            return PaintingDB.getInstance().findById(id);
        }
        if(StatueDB.getInstance().findById(id) != null) {
            return StatueDB.getInstance().findById(id);
        }
        return null;
    }

    public boolean checkIfExists(String id) throws ClassNotFoundException {
        return this.findById(id) != null;
    }

    public void remove(String id) throws ClassNotFoundException {
        if(ArtifactDB.getInstance().findById(id) != null) {
            ArtifactDB.getInstance().remove(id);
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            PaintingDB.getInstance().remove(id);
        }
        if(StatueDB.getInstance().findById(id) != null) {
            StatueDB.getInstance().remove(id);
        }
        System.out.println("The exhibit does not exist, please try again using an existing one!");
    }

    public void update(String id, Exhibit newEntity) throws ClassNotFoundException {
        if(ArtifactDB.getInstance().findById(id) != null) {
            ArtifactDB.getInstance().update(id, (Artifact) newEntity);
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            PaintingDB.getInstance().update(id, (Painting) newEntity);
        }
        if(StatueDB.getInstance().findById(id) != null) {
            StatueDB.getInstance().update(id, (Statue) newEntity);
        }
        System.out.println("The exhibit you want to update does not exist!");
    }

    public void updateName(String id, String newName) throws ClassNotFoundException {
        if(ArtifactDB.getInstance().findById(id) != null) {
            ArtifactDB.getInstance().updateName(id, newName);
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            PaintingDB.getInstance().updateName(id, newName);
        }
        if(StatueDB.getInstance().findById(id) != null) {
            StatueDB.getInstance().updateName(id, newName);
        }
        System.out.println("The exhibit you want to update does not exist!");
    }

    public void updateDateOfCreation(String id, Date newDateOfCreation) throws ClassNotFoundException {
        if(ArtifactDB.getInstance().findById(id) != null) {
            ArtifactDB.getInstance().updateCreation(id, newDateOfCreation);
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            PaintingDB.getInstance().updateCreation(id, newDateOfCreation);
        }
        if(StatueDB.getInstance().findById(id) != null) {
            StatueDB.getInstance().updateCreation(id, newDateOfCreation);
        }
        System.out.println("The exhibit you want to update does not exist!");
    }

    public void updatePrice(String id, double newPrice) throws ClassNotFoundException  {
        if(ArtifactDB.getInstance().findById(id) != null) {
            ArtifactDB.getInstance().updatePrice(id, newPrice);
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            PaintingDB.getInstance().updatePrice(id, newPrice);
        }
        if(StatueDB.getInstance().findById(id) != null) {
            StatueDB.getInstance().updatePrice(id, newPrice);
        }
        System.out.println("The exhibit you want to update does not exist!");
    }

    public List<Exhibit> getAllExhibits() throws ClassNotFoundException  {
        List<Exhibit> allExhibits = new ArrayList<>();
        List<Artifact> allArtifacts = ArtifactDB.getInstance().getArtifacts();
        List<Painting> allPaintings= PaintingDB.getInstance().getPaintings();
        List<Statue> allStatues= StatueDB.getInstance().getStatues();
        allExhibits.addAll(allArtifacts);
        allExhibits.addAll(allPaintings);
        allExhibits.addAll(allStatues);
        return allExhibits;
    }

    }
