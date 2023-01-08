package controllers;

import classes.*;
import repository.inmemory.*;
import repository.database.*;
import views.ViewExhibit;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerExhibit {

    public static void display(String idOfExhibit)  throws ClassNotFoundException {
        Exhibit exhibitToDisplay = ExhibitDB.getInstance().findById(idOfExhibit);
        if (exhibitToDisplay == null) {
            System.out.println("This exhibit does not exist, try again using a different ID");
            return;
        }
        ViewExhibit.displayExhibit(exhibitToDisplay);
    }

    public static void delete(String idOfExhibit) throws ClassNotFoundException {
        ExhibitDB.getInstance().remove(idOfExhibit);
    }


    public static void updatePrice(String idOfExhibit, double newPrice) throws ClassNotFoundException {
        ExhibitDB.getInstance().updatePrice(idOfExhibit, newPrice);
    }

    public static void updateDate(String idOfExhibit, Date newCreationDate) throws ClassNotFoundException {
        ExhibitDB.getInstance().updateDateOfCreation(idOfExhibit, newCreationDate);
    }

    public static void updateName(String idOfExhibit, String newName) throws ClassNotFoundException {
        ExhibitDB.getInstance().updateName(idOfExhibit, newName);
    }

    public static void addArtifact(String name, Date dateOfCreation, String blockId, String creation, double price) throws ClassNotFoundException {
        Block block = BlockDB.getInstance().findById(blockId);
        if (block == null) {
            System.out.println("The block id does not exist!");
            return;
        }
        ArtifactDB.getInstance().add(new Artifact(name, dateOfCreation, block, creation, price));
    }

    public static void addPainting(String name, Date dateOfCreation, String blockId, String artistId, String artMovementId, double price) throws ClassNotFoundException {
        Block block = BlockDB.getInstance().findById(blockId);
        Artist painter = ArtistDB.getInstance().findById(artistId);
        ArtMovement artMovement = ArtMovementDB.getInstance().findById(artMovementId);
        if (block == null) {
            System.out.println("The block id does not exist!");
            return;
        }
        if (painter == null) {
            System.out.println("The artist id does not exist!");
            return;
        }
        if (artMovement == null) {
            System.out.println("The art movement id does not exist!");
            return;
        }
        PaintingDB.getInstance().add(new Painting(name, dateOfCreation, block, painter, artMovement, price));
    }

    public static void addStatue(String name, Date dateOfCreation, String blockId, String artistId, String artMovementId, double price) throws ClassNotFoundException {
        Block block = BlockDB.getInstance().findById(blockId);
        Artist sculptor = ArtistDB.getInstance().findById(artistId);
        ArtMovement artMovement = ArtMovementDB.getInstance().findById(artMovementId);
        if (block == null) {
            System.out.println("The block id does not exist!");
            return;
        }
        if (sculptor == null) {
            System.out.println("The artist id does not exist!");
            return;
        }
        if (artMovement == null) {
            System.out.println("The art movement id does not exist!");
            return;
        }
        StatueDB.getInstance().add(new Statue(name, dateOfCreation, block, sculptor, artMovement, price));
    }

    public static List<Exhibit> sort() throws ClassNotFoundException {
        List<Exhibit> exhibits = ExhibitDB.getInstance().getAllExhibits();
        Collections.sort(exhibits);
        for (Exhibit e : exhibits) {
            ControllerExhibit.display(e.getId());
        }
        return exhibits;
    }

    public static List<Exhibit> filterByPrice(int minPrice) {
        List<Exhibit> filteredExhibits = new java.util.ArrayList<>(Collections.emptyList());
        for (Exhibit e : Collections.unmodifiableList(ExhibitDB.getInstance().getAllExhibits())) {
            if (e.getPrice() > minPrice) {
                display(e.getId());
                filteredExhibits.add(e);
            }
        }
        return filteredExhibits;
    }

    public static List<Exhibit> filterByAge(Date minDate)
    {
        List<Exhibit> filteredExhibits = new java.util.ArrayList<>(Collections.emptyList());
        for (Exhibit e :Collections.unmodifiableList(ExhibitDB.getInstance().getAllExhibits())) {
            if (e.getCreation().after(minDate))
            {
                display(e.getId());
                filteredExhibits.add(e);
            }
        }
        return filteredExhibits.stream()
                .sorted(Comparator.comparing(Exhibit::getCreation))
                .collect(Collectors.toList());
    }
}
