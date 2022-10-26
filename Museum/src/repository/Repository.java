package repository;
import classes.*;
import java.util.ArrayList;

public class Repository {
    private static Repository single_instance = null;
    public static Repository getInstance()

    {
        if (single_instance == null)
            single_instance = new Repository();
        return single_instance;
    }

    private ArrayList<Museum> museums = new ArrayList<>();
    private ArrayList<Block> blocks = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();
    private ArrayList<Artist> artists = new ArrayList<>();
    private ArrayList<ArtMovement> artMovements = new ArrayList<>();
    private ArrayList<Exhibit> exhibits = new ArrayList<>();

    public ArrayList<Museum> getMuseums() {
        return museums;
    }

    public void setMuseums(ArrayList<Museum> museums) {
        this.museums = museums;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public ArrayList<ArtMovement> getArtMovements() {
        return artMovements;
    }

    public void setArtMovements(ArrayList<ArtMovement> artMovements) {
        this.artMovements = artMovements;
    }

    public ArrayList<Exhibit> getExhibits() {
        return exhibits;
    }

    public void setExhibits(ArrayList<Exhibit> exhibits) {
        this.exhibits = exhibits;
    }
}
