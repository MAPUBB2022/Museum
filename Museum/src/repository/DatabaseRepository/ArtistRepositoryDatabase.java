package repository.DatabaseRepository;

import classes.Artist;
import repository.ICrudRepository;
import repository.inmemory.ArtistRepositoryMemory;

import java.util.ArrayList;

public class ArtistRepositoryDatabase implements ICrudRepository<String, Artist> {
    private static ArtistRepositoryDatabase single_instance = null;
    private final ArrayList<Artist> allArtists = new ArrayList<>();

    public static ArtistRepositoryDatabase getInstance() {
        if (single_instance == null) {
            single_instance = new ArtistRepositoryDatabase();
            populate();
        }
        return single_instance;
    }

    public static void populate() {

    }

    @Override
    public void add(Artist entity) {

    }

    @Override
    public void remove(String s) {

    }

    @Override
    public void update(String s, Artist newEntity) {

    }

    @Override
    public void updateName(String s, String newId) {

    }

    @Override
    public Artist findById(String s) {
        return null;
    }

    @Override
    public boolean checkIfExists(String s) {
        return false;
    }
}
