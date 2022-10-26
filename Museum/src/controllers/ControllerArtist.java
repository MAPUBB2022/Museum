package controllers;

import classes.Artist;
import repository.Repository;

import java.util.ArrayList;

public class ControllerArtist {
    static public boolean existsInArtists(String id) {
        ArrayList<Artist> artists = Repository.getInstance().getArtists();
        for (Artist a : artists) {
            if (id.equals(a.getId())) {
                return true;
            }
        }
        return false;
    }
}
