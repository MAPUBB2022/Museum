package controllers;

import classes.*;
import repository.database.*;
import views.ViewArtist;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerArtist {
    static public void add(String name, Date dateBorn, Date dateDied) throws ClassNotFoundException {
        Artist artistToAdd = new Artist(name, dateBorn, dateDied);
        ArtistDB.getInstance().add(artistToAdd);
    }


    public static void delete(String id) throws ClassNotFoundException {
        if(!ArtistDB.getInstance().checkIfExists(id)) {
            System.out.println("Wrong id!");
            return;
        }
        List<Exhibit> exhibits = ArtistDB.getInstance().findById(id).getListOfArt();
        List<ArtMovement> artMovementsList = ArtistDB.getInstance().findById(id).getMovements();
        List<Client> clients = ClientDB.getInstance().getClients();
        for (Exhibit e:exhibits) {
            ExhibitDB.getInstance().remove(e.getId());
        }
        for (ArtMovement am:artMovementsList) {
            ArtistDB.getInstance().deleteArtMovement(id,am.getId());
        }
        List<Block> blocks = BlockDB.getInstance().getBlocks();
        for (Block b:blocks) {
            b.deleteArtist(ArtistDB.getInstance().findById(id));
        }
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

    public static void addArtMovement(String id, String idArtMovement) throws ClassNotFoundException {
        if (checkIfNotExistsArtMovement(id, idArtMovement)) {
            return;
        }
        Artist wantedArtist = ArtistDB.getInstance().findById(id);
        ArtMovement wantedArtMovement = ArtMovementDB.getInstance().findById(idArtMovement);
        wantedArtist.addMovement(wantedArtMovement);
        wantedArtMovement.addArtist(wantedArtist);
        ArtistDB.getInstance().addArtMovement(id,idArtMovement);
    }

    public static void deleteArtMovement(String id, String idArtMovement) throws ClassNotFoundException {
        if (checkIfNotExistsArtMovement(id, idArtMovement)) {
            return;
        }
        Artist wantedArtist = ArtistDB.getInstance().findById(id);
        ArtMovement wantedArtMovement = ArtMovementDB.getInstance().findById(idArtMovement);
        wantedArtist.deleteMovement(wantedArtMovement);
        wantedArtMovement.deleteArtist(wantedArtist);
        ArtistDB.getInstance().deleteArtMovement(id, idArtMovement);
    }

    public static void addExhibit(String id, String idExhibit) throws ClassNotFoundException {
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

    public static void deleteExhibit(String id, String idExhibit) throws ClassNotFoundException {
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

    public static List<Artist> filterByExhibit(int minNumberExhibit) throws ClassNotFoundException {
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
        List<Artist> filteredArtists = new java.util.ArrayList<>(Collections.emptyList());
        for (Artist a : Collections.unmodifiableList(ArtistDB.getInstance().getArtists())) {
            boolean foundArtMovement = false;
            List<ArtMovement> artistArtMovement = a.getMovements();
            for(ArtMovement am:artistArtMovement) {
                if (Objects.equals(am.getName(), artMovementName)) {
                    foundArtMovement = true;
                    break;
                }
            }
            if (foundArtMovement) {
                display(a.getId());
                filteredArtists.add(a);
            }
        }
        return filteredArtists;
    }

    public static List<Artist> filterByBorn(Date bornDate) throws ClassNotFoundException {
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

    public static List<Artist> filterByDead() throws ClassNotFoundException {
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

    public static List<Artist> multipleFilters(String artMovementName, int minNumberExhibit) throws ClassNotFoundException {
        List<Artist> filteredArtists = new java.util.ArrayList<>(Collections.emptyList());
        for (Artist a : Collections.unmodifiableList(ArtistDB.getInstance().getArtists())) {
            boolean foundArtMovement = false;
            List<ArtMovement> artistArtMovement = a.getMovements();
            for(ArtMovement am:artistArtMovement) {
                if (Objects.equals(am.getName(), artMovementName)) {
                    foundArtMovement = true;
                    break;
                }
            }
            if (a.getListOfArt().size() > minNumberExhibit && foundArtMovement) {
                display(a.getId());
                filteredArtists.add(a);
            }
        }
        return filteredArtists;
    }
}
