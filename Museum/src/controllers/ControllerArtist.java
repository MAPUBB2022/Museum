package controllers;

import classes.ArtMovement;
import classes.Artifact;
import classes.Artist;
import classes.Exhibit;
import repository.inmemory.ArtMovementRepositoryMemory;
import repository.inmemory.ArtistRepositoryMemory;
import repository.inmemory.ExhibitRepositoryMemory;
import views.ViewArtist;

import java.util.Date;

public class ControllerArtist {
    static public void add(String name, Date dateBorn, Date dateDied) {
        Artist artistToAdd = new Artist(name, dateBorn, dateDied);
        ArtistRepositoryMemory.getInstance().add(artistToAdd);
    }


    public static void delete(String id) {
        ArtistRepositoryMemory.getInstance().remove(id);
    }

    public static void presentSelf(String id) {
        if (!ArtistRepositoryMemory.getInstance().checkIfExists(id)) {
            System.out.println("This artist does not exist!");
            return;
        }
        Artist artistToPresent = ArtistRepositoryMemory.getInstance().findById(id);
        System.out.println(artistToPresent.presentSelf());
    }

    public static void isFamous(String id) {
        if (!ArtistRepositoryMemory.getInstance().checkIfExists(id)) {
            System.out.println("This artist does not exist!");
            return;
        }
        Artist artistToPresent = ArtistRepositoryMemory.getInstance().findById(id);
        System.out.println(artistToPresent.isFamous());
    }

    public static void changeName(String id, String newName) {
        ArtistRepositoryMemory.getInstance().updateName(id, newName);
    }

    public static void updateDateBorn(String id, Date newBornDate) {
        ArtistRepositoryMemory.getInstance().updateDateBorn(id, newBornDate);
    }

    public static void updateDateDied(String id, Date newDiedDate) {
        ArtistRepositoryMemory.getInstance().updateDateDied(id, newDiedDate);
    }

    public static void addArtMovement(String id, String idArtMovement) {
        if (checkIfNotExistsArtMovement(id, idArtMovement)) {
            return;
        }
        Artist wantedArtist = ArtistRepositoryMemory.getInstance().findById(id);
        ArtMovement wantedArtMovement = ArtMovementRepositoryMemory.getInstance().findById(idArtMovement);
        wantedArtist.addMovement(wantedArtMovement);
        ArtistRepositoryMemory.getInstance().update(id, wantedArtist);
    }

    public static void deleteArtMovement(String id, String idArtMovement) {
        if (checkIfNotExistsArtMovement(id, idArtMovement)) {
            return;
        }
        Artist wantedArtist = ArtistRepositoryMemory.getInstance().findById(id);
        ArtMovement wantedArtMovement = ArtMovementRepositoryMemory.getInstance().findById(idArtMovement);
        wantedArtist.deleteMovement(wantedArtMovement);
        ArtistRepositoryMemory.getInstance().update(id, wantedArtist);
    }

    public static void addExhibit(String id, String idExhibit) {
        if (checkIfNotExistsExhibit(id, idExhibit)) {
            return;
        }
        Exhibit checkArtifact = ExhibitRepositoryMemory.getInstance().findById(idExhibit);
        if (checkArtifact instanceof Artifact) {
            System.out.println("Artifacts are old! No one knows who the artist is!");
            return;
        }

        Artist wantedArtist = ArtistRepositoryMemory.getInstance().findById(id);
        Exhibit wantedExhibit = ExhibitRepositoryMemory.getInstance().findById(idExhibit);
        wantedArtist.addExhibit(wantedExhibit);
        ArtistRepositoryMemory.getInstance().update(id, wantedArtist);
    }

    public static void deleteExhibit(String id, String idExhibit) {
        if (checkIfNotExistsExhibit(id, idExhibit)) {
            return;
        }

        Artist wantedArtist = ArtistRepositoryMemory.getInstance().findById(id);
        Exhibit wantedExhibit = ExhibitRepositoryMemory.getInstance().findById(idExhibit);
        wantedArtist.deleteExhibit(wantedExhibit);
        ArtistRepositoryMemory.getInstance().update(id, wantedArtist);
    }

    private static boolean checkIfNotExistsArtMovement(String idArtist, String idArtMovement) {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExists(idArtMovement)) {
            System.out.println("Wrong id, please try again using an existing one!");
            return true;
        }
        if (!ArtistRepositoryMemory.getInstance().checkIfExists(idArtist)) {
            System.out.println("Artist doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }

    private static boolean checkIfNotExistsExhibit(String idArtist, String idExhibit) {
        if (!ExhibitRepositoryMemory.getInstance().checkIfExists(idExhibit)) {
            System.out.println("Wrong id, please try again using an existing one!");
            return true;
        }
        if (!ArtistRepositoryMemory.getInstance().checkIfExists(idArtist)) {
            System.out.println("Exhibit doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }

    public static void display(String id) {
        Artist artist = ArtistRepositoryMemory.getInstance().findById(id);
        if (artist == null) {
            System.out.println("The Artist does not exist! Try again using a different ID!");
            return;
        }
        ViewArtist.display(artist);
    }
}
