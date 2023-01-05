package repository.database;
import classes.ArtMovement;
import classes.Artist;
import repository.ICrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import static repository.inmemory.ExhibitRepositoryMemory.populate;

public class ArtistDB implements ICrudRepository<String, Artist> {
    private static ArtistDB single_instance = null;
    private final ArrayList<Artist> allArtists = new ArrayList<>();

    public static ArtistDB getInstance() throws SQLException, ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new ArtistDB();
        }
        return single_instance;
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

    public void addNoDB (Artist entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The artist already exists, please try again using an new name!");
            return;
        }
        allArtists.add(entity);
        System.out.println("Added artist!");
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
        for (Artist a : allArtists) {
            if (s.equals(a.getId())) {
                return a;
            }
        }
        return null;
    }

    @Override
    public boolean checkIfExists(String s) {
        return false;
    }

    public static void populate(Connection connection) throws SQLException, ClassNotFoundException {
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();

        ResultSet resultSet = statement1.executeQuery("SELECT * FROM Artist");

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            Date d1 = resultSet.getDate(3);
            Date d2 = resultSet.getDate(4);
            System.out.println(id + ": " + name);
            ArtistDB.getInstance().addNoDB(new Artist(id,name, d1, d2));
        }


        resultSet = statement2.executeQuery("SELECT * FROM ArtistMovements");
        while (resultSet.next()) {
            String ArtistID = resultSet.getString(1);
            String ArtMovementID = resultSet.getString(2);
            ResultSet resultSetArtMovements = statement3.executeQuery("SELECT * FROM ArtMovement");
            while (resultSetArtMovements.next()) {
                String id = resultSetArtMovements.getString(1);
                if (Objects.equals(ArtMovementID, id)) {
                    String Name = resultSetArtMovements.getString(2);
                    Date StartDate = resultSetArtMovements.getDate(3);
                    Date EndDate = resultSetArtMovements.getDate(4);
                    ArtMovement artMovementOfArtist = new ArtMovement(id,Name,StartDate,EndDate);
                    Artist artistToAddArtMovement = ArtistDB.getInstance().findById(ArtistID);
                    artistToAddArtMovement.addMovement(artMovementOfArtist);
                    System.out.println("x");
                }
            }
        }


        System.out.println("test!");
    }
}