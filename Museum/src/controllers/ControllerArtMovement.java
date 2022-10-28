package controllers;

import classes.ArtMovement;
import classes.Artist;
import repository.inmemory.ArtMovementRepositoryMemory;
import repository.inmemory.ArtistRepositoryMemory;
import views.ViewArtMovement;

import java.util.ArrayList;
import java.util.Date;

public class ControllerArtMovement {

    static public void add(String name, Date startDate, Date endDate) {
        if (ArtMovementRepositoryMemory.getInstance().checkIfExistsByName(name)) {
            System.out.println("It does already exist!");
            return;
        }
        ArtMovement artMovementToAdd = new ArtMovement(name, startDate, endDate);
        ArtMovementRepositoryMemory.getInstance().add(artMovementToAdd);
    }

    static public void delete(String name) {
        ArtMovementRepositoryMemory.getInstance().remove(name);
    }

    static public void changeName(String oldName, String newName) {
        ArtMovementRepositoryMemory.getInstance().updateName(oldName, newName);
    }

    public static void updateDateCreated(String name, Date newDate) {
        ArtMovementRepositoryMemory.getInstance().updateDateStarted(name, newDate);
    }

    public static void updateDateEnded(String name, Date newDate) {
        ArtMovementRepositoryMemory.getInstance().updateDateEnded(name, newDate);
    }

    public static void deleteArtist(String nameArtMovement, String artistId) {
        if (checkIfNotExistsArtist(nameArtMovement, artistId)) {
            return;
        }
        ArtMovement wantedArtMovement = ArtMovementRepositoryMemory.getInstance().findByName(nameArtMovement);
        Artist artist = ArtistRepositoryMemory.getInstance().findById(artistId);
        wantedArtMovement.deleteArtist(artist);
        ArtMovementRepositoryMemory.getInstance().update(nameArtMovement, wantedArtMovement);
    }

    public static void addArtist(String nameArtMovement, String artistId) {
        if (checkIfNotExistsArtist(nameArtMovement, artistId)) {
            return;
        }
        ArtMovement wantedArtMovement = ArtMovementRepositoryMemory.getInstance().findByName(nameArtMovement);
        Artist artist = ArtistRepositoryMemory.getInstance().findById(artistId);
        wantedArtMovement.addArtist(artist);
        ArtMovementRepositoryMemory.getInstance().update(nameArtMovement, wantedArtMovement);
    }

    public static void display(String name) {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }
        ArtMovement wantedArtMovement = ArtMovementRepositoryMemory.getInstance().findByName(name);
        ArrayList<Artist> artists = (ArrayList<Artist>) wantedArtMovement.getArtists();
        ViewArtMovement.allInformationDisplay(wantedArtMovement, artists);
    }

    public static void displayRandomArtist(String name) {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }

        ArtMovement wantedArtMovement = ArtMovementRepositoryMemory.getInstance().findByName(name);
        ViewArtMovement.displayRandomArtist(wantedArtMovement);
    }

    public static boolean checkIfNotExistsArtist(String name, String id) {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return true;
        }
        if (!ArtistRepositoryMemory.getInstance().checkIfExists(id)) {
            System.out.println("Artist doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }
}

