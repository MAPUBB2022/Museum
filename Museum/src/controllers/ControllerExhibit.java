package controllers;

import classes.*;
import repository.inmemory.*;
import views.ViewExhibit;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerExhibit {

    public static void display(String idOfExhibit) {
        Exhibit exhibitToDisplay = ExhibitRepositoryMemory.getInstance().findById(idOfExhibit);
        if (exhibitToDisplay == null) {
            System.out.println("This exhibit does not exist, try again using a different ID");
            return;
        }
        ViewExhibit.displayExhibit(exhibitToDisplay);
    }

    public static void delete(String idOfExhibit) {
        ExhibitRepositoryMemory.getInstance().remove(idOfExhibit);
    }


    public static void updatePrice(String idOfExhibit, double newPrice) {
        ExhibitRepositoryMemory.getInstance().updatePrice(idOfExhibit, newPrice);
    }

    public static void updateDate(String idOfExhibit, Date newCreationDate) {
        ExhibitRepositoryMemory.getInstance().updateDateOfCreation(idOfExhibit, newCreationDate);
    }

    public static void updateName(String idOfExhibit, String newName) {
        ExhibitRepositoryMemory.getInstance().updateName(idOfExhibit, newName);
    }

    public static void addArtifact(String name, Date dateOfCreation, String blockId, String creation, double price) {
        Block block = BlockRepositoryMemory.getInstance().findById(blockId);
        if (block == null) {
            System.out.println("The block id does not exist!");
            return;
        }
        ExhibitRepositoryMemory.getInstance().add(new Artifact(name, dateOfCreation, block, creation, price));
    }

    public static void addPainting(String name, Date dateOfCreation, String blockId, String artistId, String artMovementId, double price) {
        Block block = BlockRepositoryMemory.getInstance().findById(blockId);
        Artist painter = ArtistRepositoryMemory.getInstance().findById(artistId);
        ArtMovement artMovement = ArtMovementRepositoryMemory.getInstance().findById(artMovementId);
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
        ExhibitRepositoryMemory.getInstance().add(new Painting(name, dateOfCreation, block, painter, artMovement, price));
    }

    public static void addStatue(String name, Date dateOfCreation, String blockId, String artistId, String artMovementId, double price) {
        Block block = BlockRepositoryMemory.getInstance().findById(blockId);
        Artist sculptor = ArtistRepositoryMemory.getInstance().findById(artistId);
        ArtMovement artMovement = ArtMovementRepositoryMemory.getInstance().findById(artMovementId);
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
        ExhibitRepositoryMemory.getInstance().add(new Statue(name, dateOfCreation, block, sculptor, artMovement, price));
    }

    public static List<Exhibit> sort() {
        List<Exhibit> exhibits = ExhibitRepositoryMemory.getInstance().getAllExhibits();
        Collections.sort(exhibits);
        for (Exhibit e : exhibits) {
            ControllerExhibit.display(e.getId());
        }
        return exhibits;
    }

    public static List<Exhibit> filterByPrice(int minPrice) {
        List<Exhibit> filteredExhibits = new java.util.ArrayList<>(Collections.emptyList());
        for (Exhibit e : Collections.unmodifiableList(ExhibitRepositoryMemory.getInstance().getAllExhibits())) {
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
        for (Exhibit e :Collections.unmodifiableList(ExhibitRepositoryMemory.getInstance().getAllExhibits())) {
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
