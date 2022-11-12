package repository.inmemory;

import classes.*;
import repository.ICrudRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ExhibitRepositoryMemory implements ICrudRepository<String, Exhibit> {
    private static ExhibitRepositoryMemory single_instance = null;
    private final ArrayList<Exhibit> allExhibits = new ArrayList<>();

    public static ExhibitRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new ExhibitRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    public static void populate() {
        BlockRepositoryMemory.getInstance();
        ArtistRepositoryMemory.getInstance();
        ArtMovementRepositoryMemory.getInstance();
        Block block1 = BlockRepositoryMemory.getInstance().findById("B1000");
        Artist artist1 = ArtistRepositoryMemory.getInstance().findById("A1000");
        ArtMovement artMovement = ArtMovementRepositoryMemory.getInstance().findById("V1000");
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        Date dateMonaLisa, dateCreationOfAdam, dateEquestrian, dateRodOfAscellus;
        try {
            dateMonaLisa = format.parse("24-11-1503");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            dateCreationOfAdam = format.parse("28-03-1510");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            dateEquestrian = format.parse("23-02-1510");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            dateRodOfAscellus = format.parse("12-06-12");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Painting paintingMonaLisa = new Painting("Mona Lisa", dateMonaLisa, block1, artist1, artMovement, 10);
        Painting paintingCreationOfAdam = new Painting("Creation of Adam", dateCreationOfAdam, BlockRepositoryMemory.getInstance().findById("B1000"), ArtistRepositoryMemory.getInstance().findById("A1001"), ArtMovementRepositoryMemory.getInstance().findById("V1000"), 0.5);
        Statue statueEquestrian = new Statue("Equestrian", dateEquestrian, BlockRepositoryMemory.getInstance().findById("B1001"), ArtistRepositoryMemory.getInstance().findById("A1000"), ArtMovementRepositoryMemory.getInstance().findById("V1000"), 19);
        Artifact artifactRodOfAscellus = new Artifact("Rod Of Ascellus", dateRodOfAscellus, BlockRepositoryMemory.getInstance().findById("B1001"), "Egypt", 14);
        ExhibitRepositoryMemory.getInstance().add(paintingMonaLisa);
        ExhibitRepositoryMemory.getInstance().add(paintingCreationOfAdam);
        ExhibitRepositoryMemory.getInstance().add(statueEquestrian);
        ExhibitRepositoryMemory.getInstance().add(artifactRodOfAscellus);
    }


    @Override
    public void add(Exhibit entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The exhibit already exists, please try again using an new one!");
            return;
        }
        allExhibits.add(entity);
        System.out.println("Added exhibit!");
    }

    @Override
    public void remove(String id) {
        if (!checkIfExists(id)) {
            System.out.println("The exhibit does not exist!");
            return;
        }
        boolean found = false;
        Exhibit exhibitToDelete = null;
        for (Exhibit e : allExhibits) {
            if (e.getId().equals(id)) {
                found = true;
                exhibitToDelete = e;
            }
        }
        if (found) {
            allExhibits.remove(exhibitToDelete);
            System.out.println("The exhibit has been removed!");
            return;
        }
        System.out.println("The exhibit does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String id, Exhibit newEntity) {
        boolean found = false;
        Exhibit exhibitToUpdate = null;
        for (Exhibit e : allExhibits) {
            if (e.getId().equals(id)) {
                found = true;
                exhibitToUpdate = e;
            }
        }
        if (found) {
            allExhibits.remove(exhibitToUpdate);
            allExhibits.add(exhibitToUpdate);
            return;
        }
        System.out.println("The exhibit you want to update does not exist!");
    }

    @Override
    public void updateName(String id, String newName) {
        boolean found = false;
        Exhibit exhibitToUpdate = null;
        for (Exhibit e : allExhibits) {
            if (e.getId().equals(id)) {
                found = true;
                exhibitToUpdate = e;
            }
        }
        if (found) {
            allExhibits.remove(exhibitToUpdate);
            exhibitToUpdate.setName(newName);
            allExhibits.add(exhibitToUpdate);
            System.out.println("Updated name!");
            return;
        }
        System.out.println("The exhibit you want to update does not exist!");
    }

    public void updateDateOfCreation(String id, Date newDateOfCreation) {
        boolean found = false;
        Exhibit exhibitToUpdate = null;
        for (Exhibit e : allExhibits) {
            if (e.getId().equals(id)) {
                found = true;
                exhibitToUpdate = e;
            }
        }
        if (found) {
            allExhibits.remove(exhibitToUpdate);
            exhibitToUpdate.setCreation(newDateOfCreation);
            allExhibits.add(exhibitToUpdate);
            System.out.println("Updated date created!");
            return;
        }
        System.out.println("The exhibit you want to update does not exist!");
    }

    public void updatePrice(String id, double newPrice) {
        boolean found = false;
        Exhibit exhibitToUpdate = null;
        for (Exhibit e : allExhibits) {
            if (e.getId().equals(id)) {
                found = true;
                exhibitToUpdate = e;
            }
        }
        if (found) {
            allExhibits.remove(exhibitToUpdate);
            exhibitToUpdate.setPrice(newPrice);
            allExhibits.add(exhibitToUpdate);
            System.out.println("Updated price!");
            return;
        }
        System.out.println("The exhibit you want to update does not exist!");
    }

    @Override
    public Exhibit findById(String s) {
        for (Exhibit e : allExhibits) {
            if (s.equals(e.getId())) {
                return e;
            }
        }
        return null;
    }

    @Override
    public boolean checkIfExists(String s) {
        for (Exhibit e : allExhibits) {
            if (s.equals(e.getId())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Exhibit> getAllExhibits() {
        return allExhibits;
    }
}
