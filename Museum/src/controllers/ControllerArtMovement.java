package controllers;

import classes.ArtMovement;
import classes.Artist;
import repository.Repository;
import views.ViewArtMovement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerArtMovement {
    static public boolean existsInArtMovement(String name) {
        ArrayList<ArtMovement> artMovements = Repository.getInstance().getArtMovements();
        for (ArtMovement am : artMovements) {
            if (name.equals(am.getName())) {
                return true;
            }
        }
        return false;
    }

    static public void add(String name, Date startDate, Date endDate) {
        ArrayList<ArtMovement> artMovements = Repository.getInstance().getArtMovements();
        if (existsInArtMovement(name)) {
            System.out.println("The art movement already exists, please try again using an new name!");
            return;
        }
        ArtMovement am = new ArtMovement(name, startDate, endDate);
        artMovements.add(am);
        System.out.println("Added Art Movement!");
        Repository.getInstance().setArtMovements(artMovements);
    }

    static public void delete(String name) {
        ArrayList<ArtMovement> artMovements = Repository.getInstance().getArtMovements();
        for (ArtMovement am : artMovements) {
            if (name.equals(am.getName())) {
                artMovements.remove(am);
                Repository.getInstance().setArtMovements(artMovements);
                System.out.println("Deleted art movement!");
                return;
            }
        }
        System.out.println("The art movement does not exist, please try again using an existing one!");
    }

    static public void updateName(String oldName, String newName) {
        ArrayList<ArtMovement> artMovements = Repository.getInstance().getArtMovements();

        if (existsInArtMovement(newName))
            System.out.println("The new name does actually exist!");

        for (ArtMovement am : artMovements) {
            if (oldName.equals(am.getName())) {
                am.setName(newName);
                Repository.getInstance().setArtMovements(artMovements);
                System.out.println("Changed art movement name!");
                return;
            }
        }
        System.out.println("Wrong name, please try again using an existing one!");
    }

    public static void updateDateCreated(String name, Date newDate) {
        ArrayList<ArtMovement> artMovements = Repository.getInstance().getArtMovements();
        for (ArtMovement am : artMovements) {
            if (name.equals(am.getName())) {
                am.setStartDate(newDate);
                Repository.getInstance().setArtMovements(artMovements);
                System.out.println("Changed art movement starting date!");
                return;
            }
        }
        System.out.println("Wrong name, please try again using an existing one!");
    }

    public static void updateDateEnded(String name, Date newDate) {
        ArrayList<ArtMovement> artMovements = Repository.getInstance().getArtMovements();
        for (ArtMovement am : artMovements) {
            if (name.equals(am.getName())) {
                am.setEndDate(newDate);
                Repository.getInstance().setArtMovements(artMovements);
                System.out.println("Changed art movement ending date!");
                return;
            }
        }
        System.out.println("Wrong name, please try again using an existing one!");
    }

    public static void deleteArtist(String nameArtMovement, String artistId) {
        if(checkIfNotExistsArtist(nameArtMovement, artistId)) {
            return;

        }

    }

    public static void addArtist(String nameArtMovement, String artistId) {
        if(checkIfNotExistsArtist(nameArtMovement, artistId)) {
            return;
        }

        ArrayList<ArtMovement> artMovements = Repository.getInstance().getArtMovements();
        ArtMovement wantedArtMovement = new ArtMovement("Non-Existent", null, null);

        for (ArtMovement am1 : artMovements) {
            if (am1.getName().equals(nameArtMovement)) {
                wantedArtMovement = am1;
                break;
            }
        }

        List<Artist> artistsOfArtMovement = wantedArtMovement.getArtists();

        for (Artist a : artistsOfArtMovement) {
            if (a.getId().equals(artistId)) {
                System.out.println("Artist in art movement!");
                return;
            }
        }

        Artist artist = new Artist("Non-Existent", null, null);
        for (Artist a1 : Repository.getInstance().getArtists()) {
            if (a1.getId().equals(artistId)) {
                artist = a1;
                break;
            }
        }
        for (ArtMovement a : Repository.getInstance().getArtMovements()) {
            if (a.getName().equals(nameArtMovement)) {
                artistsOfArtMovement.add(artist);
                a.setArtists(artistsOfArtMovement);
                System.out.println("Artist was added in art movement!");
                return;
            }
        }
    }

    public static void display(String name) {
        if (!existsInArtMovement(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }

        List<ArtMovement> artMovements = Repository.getInstance().getArtMovements();
        ArtMovement theArtMovement = new ArtMovement("Non-Existent", null, null);
        for (ArtMovement am : artMovements) {
            if (name.equals(am.getName())) {
                theArtMovement = am;
            }
        }

        List<Artist> artists = new ArrayList<>();
        for (ArtMovement am : Repository.getInstance().getArtMovements()) {
            if (am.getName().equals(name)) {
                artists = am.getArtists();
            }
        }

        ViewArtMovement.allInformationDisplay(theArtMovement, artists);
    }

    public static void displayRandomArtist(String name) {
        if (!existsInArtMovement(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }

        List<ArtMovement> artMovements = Repository.getInstance().getArtMovements();
        ArtMovement wantedArtMovement = new ArtMovement("Non-Existent", null, null);
        for (ArtMovement am1 : artMovements) {
            if (am1.getName().equals(name)) {
                wantedArtMovement = am1;
                break;
            }
        }
        ViewArtMovement.displayRandomArtist(wantedArtMovement);
    }

    public static boolean checkIfNotExistsArtist(String name, String id) {
        if (!existsInArtMovement(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return true;
        }
        if (!ControllerArtist.existsInArtists(id)) {
            System.out.println("Artist doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }
}
