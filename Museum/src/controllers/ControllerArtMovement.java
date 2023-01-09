package controllers;
import classes.ArtMovement;
import classes.Artist;
import repository.database.*;
import views.ViewArtMovement;
import java.util.*;
import java.util.stream.Collectors;

public class ControllerArtMovement  {

    static public void add(String name, Date startDate, Date endDate) throws ClassNotFoundException {
        if (ArtMovementDB.getInstance().findByName(name) !=  null) {
            System.out.println("It does already exist!");
            return;
        }
        ArtMovement artMovementToAdd = new ArtMovement(name, startDate, endDate);
        ArtMovementDB.getInstance().add(artMovementToAdd);
    }

    static public void delete(String name) throws ClassNotFoundException {
        if(null == ArtMovementDB.getInstance().findById(name)) {
            System.out.println("The art movement does not exist!");
            return;
        }
        List<Artist> artists = ArtistDB.getInstance().getArtists();
        for(Artist a:artists) {
            a.deleteMovementNoSout(ArtMovementDB.getInstance().findById(name));
        }
        ArtMovementDB.getInstance().remove(name);
    }

    static public void changeName(String oldName, String newName) throws ClassNotFoundException {
        if(null == ArtMovementDB.getInstance().findByName(oldName)) {
            System.out.println("The art movement does not exist!");
            return;
        }
        ArtMovementDB.getInstance().updateName(ArtMovementDB.getInstance().findByName(oldName).getId(), newName);
        ArtMovement artMovementToAdd = ArtMovementDB.getInstance().findByName(newName);
        List<Artist> artists = ArtistDB.getInstance().getArtists();
        for(Artist a:artists) {
            a.updateArtMovements(oldName, artMovementToAdd);
        }
    }

    public static void updateDateCreated(String name, Date newDate) throws ClassNotFoundException {
        if(null == ArtMovementDB.getInstance().findByName(name)) {
            System.out.println("The art movement does not exist!");
            return;
        }
        ArtMovementDB.getInstance().updateDateStarted(ArtMovementDB.getInstance().findByName(name).getId(), newDate);
    }

    public static void updateDateEnded(String name, Date newDate) throws ClassNotFoundException {
        if(null == ArtMovementDB.getInstance().findByName(name)) {
            System.out.println("The art movement does not exist!");
            return;
        }
        ArtMovementDB.getInstance().updateDateEnded(ArtMovementDB.getInstance().findByName(name).getId(), newDate);
    }

    public static void deleteArtist(String nameArtMovement, String artistId) throws ClassNotFoundException {
        if(null == ArtMovementDB.getInstance().findByName(nameArtMovement)) {
            System.out.println("The art movement does not exist!");
            return;
        }
        if (checkIfNotExistsArtist(nameArtMovement, artistId)) {
            return;
        }
        ArtMovementDB.getInstance().findByName(nameArtMovement).addArtist(ArtistDB.getInstance().findById(artistId));
        ControllerArtist.deleteArtMovement(artistId,ArtMovementDB.getInstance().findByName(nameArtMovement).getId());
    }

    public static void addArtist(String nameArtMovement, String artistId) throws ClassNotFoundException {
        if(null == ArtMovementDB.getInstance().findByName(nameArtMovement)) {
            System.out.println("The art movement does not exist!");
            return;
        }
        if (checkIfNotExistsArtist(nameArtMovement, artistId)) {
            return;
        }
        ArtMovementDB.getInstance().findByName(nameArtMovement).addArtist(ArtistDB.getInstance().findById(artistId));
        ControllerArtist.addArtMovement(artistId,ArtMovementDB.getInstance().findByName(nameArtMovement).getId());
    }

    public static void display(String name) throws ClassNotFoundException {
        if (ArtMovementDB.getInstance().findByName(name) == null) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }
        ArtMovement wantedArtMovement = ArtMovementDB.getInstance().findByName(name);
        ArrayList<Artist> artists = (ArrayList<Artist>) wantedArtMovement.getArtists();
        ViewArtMovement.allInformationDisplay(wantedArtMovement, artists);
    }

    public static void displayRandomArtist(String name) throws ClassNotFoundException {
        if (ArtMovementDB.getInstance().findByName(name) == null) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }

        ArtMovement wantedArtMovement = ArtMovementDB.getInstance().findByName(name);
        ViewArtMovement.displayRandomArtist(wantedArtMovement);
    }

    public static boolean checkIfNotExistsArtist(String name, String id) throws ClassNotFoundException {
        if (ArtMovementDB.getInstance().findByName(name) == null) {
            System.out.println("Wrong name, please try again using an existing one!");
            return true;
        }
        if (!ArtistDB.getInstance().checkIfExists(id)) {
            System.out.println("Artist doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }

    public static List<ArtMovement> sort() throws ClassNotFoundException {
        List<ArtMovement> artMovements = ArtMovementDB.getInstance().getList();
        Collections.sort(artMovements);
        for (ArtMovement am: artMovements) {
            ControllerArtMovement.display(am.getName());
            System.out.println();
            System.out.println();
        }
        return artMovements;
    }

    public static List<ArtMovement> filterByDate(Date startDate) throws ClassNotFoundException {
        List<ArtMovement> filteredArtMovement = new java.util.ArrayList<>(Collections.emptyList());
        for (ArtMovement am : Collections.unmodifiableList(ArtMovementDB.getInstance().getList())) {
            if (am.getStartDate().after(startDate)) {
                display(am.getId());
                filteredArtMovement.add(am);
            }
        }
        return filteredArtMovement.stream()
                .sorted(Comparator.comparing(ArtMovement::getStartDate))
                .collect(Collectors.toList());
    }
}

