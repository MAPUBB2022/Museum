package repository.inmemory;

import classes.Artist;
import repository.ICrudRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ArtistRepositoryMemory implements ICrudRepository<String, Artist> {
    private static ArtistRepositoryMemory single_instance = null;
    private final ArrayList<Artist> allArtists = new ArrayList<>();

    public static ArtistRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new ArtistRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate() {
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        Date dateBornLeonardo, dateDiedLeonardo, dateBornMichelangelo, dateDiedMichelangelo;
        try {
            dateBornLeonardo = format.parse("15-04-1452");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            dateDiedLeonardo = format.parse("2-06-1519");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            dateBornMichelangelo = format.parse("06-03-1475");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            dateDiedMichelangelo = format.parse("18-02-1563");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ArtistRepositoryMemory.getInstance().add(new Artist("Leonardo Da Vinci", dateBornLeonardo, dateDiedLeonardo));
        ArtistRepositoryMemory.getInstance().add(new Artist("Michelangelo Buonarroti", dateBornMichelangelo, dateDiedMichelangelo));
    }

    @Override
    public void add(Artist entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The artist already exists, please try again using an new name!");
            return;
        }
        allArtists.add(entity);
        System.out.println("Added artist!");
    }

    @Override
    public void remove(String s) {
        boolean found = false;
        Artist artistToDelete = null;
        for (Artist a : allArtists) {
            if (a.getId().equals(s)) {
                found = true;
                artistToDelete = a;
            }
        }
        if (found) {
            allArtists.remove(artistToDelete);
            System.out.println("The artist has been removed!");
            return;
        }
        System.out.println("The artist does not exist, please try again using an existing one!");

    }

    @Override
    public void update(String id, Artist newEntity) {
        boolean found = false;
        Artist artistToDelete = null;
        for (Artist a : allArtists) {
            if (a.getId().equals(id)) {
                found = true;
                artistToDelete = a;
            }
        }
        if (found) {
            allArtists.remove(artistToDelete);
            allArtists.add(newEntity);
            return;
        }
        System.out.println("The artist you want to update does not exist!");

    }

    @Override
    public void updateName(String id, String newName) {
        boolean found = false;
        Artist artistToDelete = null;
        for (Artist a : allArtists) {
            if (a.getId().equals(id)) {
                found = true;
                artistToDelete = a;
            }
        }
        if (found) {
            allArtists.remove(artistToDelete);
            artistToDelete.setName(newName);
            allArtists.add(artistToDelete);
            System.out.println("Updated name!");
            return;
        }
        System.out.println("The artist you want to update does not exist!");

    }

    @Override
    public Artist findById(String s) {
        for (Artist a : allArtists) {
            if (s.equals(a.getId())) {
                return a;
            }
        }
        return null;
    }

    public void updateDateBorn(String id, Date newDate) {
        for (Artist aToUpdate : allArtists) {
            if (id.equals(aToUpdate.getId())) {
                allArtists.remove(aToUpdate);
                aToUpdate.setBirthDate(newDate);
                allArtists.add(aToUpdate);
                System.out.println("Updated date!");
                return;
            }
        }
        System.out.println("Wrong id, please try again using an existing one!");
    }

    public void updateDateDied(String id, Date newDate) {
        for (Artist aToUpdate : allArtists) {
            if (id.equals(aToUpdate.getId())) {
                allArtists.remove(aToUpdate);
                aToUpdate.setDeathDate(newDate);
                allArtists.add(aToUpdate);
                System.out.println("Updated date!");
                return;
            }
        }
        System.out.println("Wrong id, please try again using an existing one!");
    }

    @Override
    public boolean checkIfExists(String s) {
        for (Artist a : allArtists) {
            if (s.equals(a.getId())) {
                return true;
            }
        }
        return false;
    }
}
