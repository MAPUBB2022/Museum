package controllers;

import classes.ArtMovement;
import classes.Artifact;
import classes.Artist;
import classes.Exhibit;
import repository.database.*;
import views.ViewArtist;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerArtist {
    static public void add(String name, Date dateBorn, Date dateDied) {
        Artist artistToAdd = new Artist(name, dateBorn, dateDied);
        ArtistDB.getInstance().add(artistToAdd);
    }


    public static void delete(String id) throws ClassNotFoundException {
        ArtistDB.getInstance().remove(id);
    }

    public static void presentSelf(String id) throws ClassNotFoundException {
        if (!ArtistDB.getInstance().checkIfExists(id)) {
            System.out.println("This artist does not exist!");
            return;
        }
        Artist artistToPresent = ArtistDB.getInstance().findById(id);
        System.out.println(artistToPresent.presentSelf());
    }

    public static void isFamous(String id) throws ClassNotFoundException {
        if (!ArtistDB.getInstance().checkIfExists(id)) {
            System.out.println("This artist does not exist!");
            return;
        }
        Artist artistToPresent = ArtistDB.getInstance().findById(id);
        System.out.println(artistToPresent.isFamous());
    }

    public static void changeName(String id, String newName) throws ClassNotFoundException {
        ArtistDB.getInstance().updateName(id, newName);
    }

    public static void updateDateBorn(String id, Date newBornDate) throws ClassNotFoundException {
        ArtistDB.getInstance().updateDateBorn(id, newBornDate);
    }

    public static void updateDateDied(String id, Date newDiedDate) throws ClassNotFoundException {
        ArtistDB.getInstance().updateDateDied(id, newDiedDate);
    }

    public static void addArtMovement(String id, String idArtMovement) {
        if (checkIfNotExistsArtMovement(id, idArtMovement)) {
            return;
        }
        Artist wantedArtist = ArtistDB.getInstance().findById(id);
        ArtMovement wantedArtMovement = ArtMovementDB.getInstance().findById(idArtMovement);
        wantedArtist.addMovement(wantedArtMovement);
        ArtistDB.getInstance().update(id, wantedArtist);
    }

    public static void deleteArtMovement(String id, String idArtMovement) {
        if (checkIfNotExistsArtMovement(id, idArtMovement)) {
            return;
        }
        Artist wantedArtist = ArtistDB.getInstance().findById(id);
        ArtMovement wantedArtMovement = ArtMovementDB.getInstance().findById(idArtMovement);
        wantedArtist.deleteMovement(wantedArtMovement);
        ArtistDB.getInstance().update(id, wantedArtist);
    }

    public static void addExhibit(String id, String idExhibit) {
        if (checkIfNotExistsExhibit(id, idExhibit)) {
            return;
        }
        Exhibit checkArtifact = ExhibitDB.getInstance().findById(idExhibit);
        if (checkArtifact instanceof Artifact) {
            System.out.println("Artifacts are old! No one knows who the artist is!");
            return;
        }

        Artist wantedArtist = ArtistDB.getInstance().findById(id);
        Exhibit wantedExhibit = ExhibitDB.getInstance().findById(idExhibit);
        wantedArtist.addExhibit(wantedExhibit);
        ArtistDB.getInstance().update(id, wantedArtist);
    }

    public static void deleteExhibit(String id, String idExhibit) {
        if (checkIfNotExistsExhibit(id, idExhibit)) {
            return;
        }

        Artist wantedArtist = ArtistDB.getInstance().findById(id);
        Exhibit wantedExhibit = ExhibitDB.getInstance().findById(idExhibit);
        wantedArtist.deleteExhibit(wantedExhibit);
        ArtistDB.getInstance().update(id, wantedArtist);
    }

    private static boolean checkIfNotExistsArtMovement(String idArtist, String idArtMovement) throws ClassNotFoundException {
        if (!ArtMovementDB.getInstance().checkIfExists(idArtMovement)) {
            System.out.println("Wrong id, please try again using an existing one!");
            return true;
        }
        if (!ArtistDB.getInstance().checkIfExists(idArtist))  {
            System.out.println("Artist doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }

    private static boolean checkIfNotExistsExhibit(String idArtist, String idExhibit) throws ClassNotFoundException {
        if (!ExhibitDB.getInstance().checkIfExists(idExhibit)) {
            System.out.println("Wrong id, please try again using an existing one!");
            return true;
        }
        if (!ArtistDB.getInstance().checkIfExists(idArtist)) {
            System.out.println("Exhibit doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }

    public static void display(String id) throws ClassNotFoundException {
        Artist artist = ArtistDB.getInstance().findById(id);
        if (artist == null) {
            System.out.println("The Artist does not exist! Try again using a different ID!");
            return;
        }
        ViewArtist.display(artist);
    }

    public static List<Artist> sort() throws ClassNotFoundException {
        List<Artist> artists = ArtistDB.getInstance().getArtists();
        Collections.sort(artists);
        for (Artist a : artists) {
            ControllerArtist.display(a.getId());
        }
        return artists;
    }

    public static List<Artist> filterByExhibit(int minNumberExhibit) {
        List<Artist> filteredArtists = new java.util.ArrayList<>(Collections.emptyList());
        for (Artist a : Collections.unmodifiableList(ArtistDB.getInstance().getArtists())) {
            if (a.getListOfArt().size() > minNumberExhibit) {
                display(a.getId());
                filteredArtists.add(a);
            }
        }
        return filteredArtists;
    }

    public static List<Artist> filterByArtMovement(String artMovementName) throws ClassNotFoundException {
        ArtMovement artMovementToCheck = ArtMovementDB.getInstance().findByName(artMovementName);
        List<Artist> filteredArtists = new java.util.ArrayList<>(Collections.emptyList());
        for (Artist a : Collections.unmodifiableList(ArtistDB.getInstance().getArtists())) {
            if (a.getMovements().contains(artMovementToCheck)) {
                display(a.getId());
                filteredArtists.add(a);
            }
        }
        return filteredArtists;
    }

    public static List<Artist> filterByBorn(Date bornDate) {
        List<Artist> filteredArtists = new java.util.ArrayList<>(Collections.emptyList());
        for (Artist a : Collections.unmodifiableList(ArtistDB.getInstance().getArtists())) {
            if (a.getBirthDate().after(bornDate)) {
                display(a.getId());
                filteredArtists.add(a);
            }
        }
        return filteredArtists.stream()
                .sorted(Comparator.comparing(Artist::getBirthDate))
                .collect(Collectors.toList());
    }

    public static List<Artist> filterByDead() {
        List<Artist> filteredArtists = new java.util.ArrayList<>(Collections.emptyList());
        for (Artist a : Collections.unmodifiableList(ArtistDB.getInstance().getArtists())) {
            if (a.getDeathDate() == null) {
                display(a.getId());
                filteredArtists.add(a);
            }
        }
        return filteredArtists.stream()
                .sorted(Comparator.comparing(Artist::getDeathDate)).toList();
    }
}
