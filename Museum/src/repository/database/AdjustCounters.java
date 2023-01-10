package repository.database;

import classes.*;

import java.util.List;

public class AdjustCounters {
    private static AdjustCounters single_instance = null;

    public static AdjustCounters getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new AdjustCounters();
        }
        return single_instance;
    }

    public void adjust() throws ClassNotFoundException {
        int maxExhibit = 0;
        List<Statue> statues = StatueDB.getInstance().getStatues();
        List<Painting> paintings = PaintingDB.getInstance().getPaintings();
        List<Artifact> artifacts = ArtifactDB.getInstance().getArtifacts();
        for (Statue object : statues) {
            String ID = object.getId().substring(1);
            int intID = Integer.parseInt(ID);
            if (maxExhibit < intID) {
                maxExhibit = intID;
            }
        }
        for (Painting object : paintings) {
            String ID = object.getId().substring(1);
            int intID = Integer.parseInt(ID);
            if (maxExhibit < intID) {
                maxExhibit = intID;
            }
        }
        for (Artifact object : artifacts) {
            String ID = object.getId().substring(1);
            int intID = Integer.parseInt(ID);
            if (maxExhibit < intID) {
                maxExhibit = intID;
            }
        }
        Exhibit.changeCounter(maxExhibit + 1);

        List<Ticket> tickets = TicketDB.getInstance().getTickets();
        int maxTicket = 0;
        for (Ticket object : tickets) {
            String ID = object.getId().substring(1);
            int intID = Integer.parseInt(ID);
            if (maxTicket < intID) {
                maxTicket = intID;
            }
        }
        Ticket.changeCounter(maxTicket + 1);

        List<Client> clients = ClientDB.getInstance().getClients();
        int maxClients = 0;
        for (Client object : clients) {
            String ID = object.getId().substring(1);
            int intID = Integer.parseInt(ID);
            if (maxClients < intID) {
                maxClients = intID;
            }
        }
        Client.changeCounter(maxClients + 1);

        List<Block> blocks = BlockDB.getInstance().getBlocks();
        int maxBlocks = 0;
        for (Block object : blocks) {
            String ID = object.getId().substring(1);
            int intID = Integer.parseInt(ID);
            if (maxBlocks < intID) {
                maxBlocks = intID;
            }
        }
        Block.changeCounter(maxBlocks + 1);

        List<ArtMovement> artMovements = ArtMovementDB.getInstance().getArtMovements();
        int maxArtMovement = 0;
        for (ArtMovement object : artMovements) {
            String ID = object.getId().substring(1);
            int intID = Integer.parseInt(ID);
            if (maxArtMovement < intID) {
                maxArtMovement = intID;
            }
        }
        ArtMovement.changeCounter(maxArtMovement + 1);

        List<Artist> artists = ArtistDB.getInstance().getArtists();
        int maxArtist = 0;
        for (Artist object : artists) {
            String ID = object.getId().substring(1);
            int intID = Integer.parseInt(ID);
            if (maxArtist < intID) {
                maxArtist = intID;
            }
        }
        Artist.changeCounter(maxArtist + 1);
    }
}
