package repository.database;
import classes.*;
import repository.ICrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
//        DB Code:
        String ID = entity.getId();
        String Name = entity.getName();
        java.util.Date BirthDate = entity.getBirthDate();
        java.util.Date DeathDate = entity.getDeathDate();
//        insert into Artist (ID, Name, BirthDate, DeathDate) values ('A1000','Leonardo Da Vinci', '1452-04-15', '1519-06-2')
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
            //        DB Code:
            String ID = artistToDelete.getId();
//          DELETE from Artist WHERE ID = '' ;
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
            //        DB Code:
            String newID = newEntity.getId();
            String newName = newEntity.getName();
            java.util.Date newBirthDate = newEntity.getBirthDate();
            java.util.Date newDeathDate = newEntity.getDeathDate();
            // UPDATE Artist SET ID = '', Name = '', BirthDate = '', DeathDate = '' WHERE ID = '';
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
            // DB Code:
            // UPDATE Artist SET Name = '' WHERE ID = '';
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

    public void updateDateBorn(String id, java.util.Date newDate) {
        for (Artist aToUpdate : allArtists) {
            if (id.equals(aToUpdate.getId())) {
                allArtists.remove(aToUpdate);
                aToUpdate.setBirthDate(newDate);
                allArtists.add(aToUpdate);
//                DB Code:
                // UPDATE Artist SET BirthDate = '' WHERE ID = '';
                System.out.println("Updated date!");
                return;
            }
        }
        System.out.println("Wrong id, please try again using an existing one!");
    }

    public void updateDateDied(String id, java.util.Date newDate) {
        for (Artist aToUpdate : allArtists) {
            if (id.equals(aToUpdate.getId())) {
                allArtists.remove(aToUpdate);
                aToUpdate.setDeathDate(newDate);
                allArtists.add(aToUpdate);
                //                DB Code:
                // UPDATE Artist SET DeathDate = '' WHERE ID = '';
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

    public List<Artist> getArtists() {
        return allArtists;
    }

    public Artist findByName(String name) {
        for (Artist a : allArtists) {
            if (name.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }

    public static void populate(Connection connection) throws SQLException, ClassNotFoundException {
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
        Statement statement4 = connection.createStatement();
        Statement statement5 = connection.createStatement();
        Statement statement6 = connection.createStatement();
        Statement statement7 = connection.createStatement();
        Statement statement8 = connection.createStatement();
        Statement statement9 = connection.createStatement();


        ResultSet resultSet = statement1.executeQuery("SELECT * FROM Artist");

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            Date d1 = resultSet.getDate(3);
            Date d2 = resultSet.getDate(4);
            System.out.println(id + ": " + name);
            ArtistDB.getInstance().addNoDB(new Artist(id,name, d1, d2));
        }


        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM ArtistMovements");
        while (resultSet2.next()) {
            String ArtistID = resultSet2.getString(1);
            String ArtMovementID = resultSet2.getString(2);
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
                }
            }
        }

        ResultSet resultSet4 = statement4.executeQuery("SELECT * FROM Painting");
        while (resultSet4.next()) {
            String PainterID = resultSet4.getString(1);
            String ArtMovementID = resultSet4.getString(2);
//            Find the Art Movement:
            ResultSet resultSetArtMovements  = statement5.executeQuery("SELECT * FROM ArtMovement");
            ArtMovement artMovementOfArtist = null;
            while (resultSetArtMovements.next()) {
                String id = resultSetArtMovements.getString(1);
                if (Objects.equals(ArtMovementID, id)) {
                    String Name = resultSetArtMovements.getString(2);
                    Date StartDate = resultSetArtMovements.getDate(3);
                    Date EndDate = resultSetArtMovements.getDate(4);
                    artMovementOfArtist = new ArtMovement(id,Name,StartDate,EndDate);
                }
            }

            String locationID = resultSet4.getString(3);
            ResultSet resultSetBlocks  = statement6.executeQuery("SELECT * FROM Blocks");
            Block blockOfArtist = null;
            //            Find the block
            while (resultSetBlocks.next()) {
                String id = resultSetBlocks.getString(3);
                if (Objects.equals(locationID, id)) {
                    String Name = resultSetBlocks.getString(1);
                    blockOfArtist = new Block(id, Name);
                }
            }
            float price = resultSet4.getFloat(4);
            Date creation = resultSet4.getDate(5);
            String name = resultSet4.getString(6);
            String PaintingID = resultSet4.getString(7);
            Painting painting = new Painting(PaintingID,name,creation,blockOfArtist,ArtistDB.getInstance().findById(PainterID),artMovementOfArtist,price);
            Artist artistToAddArtMovement = ArtistDB.getInstance().findById(PainterID);
            artistToAddArtMovement.addExhibit(painting);
        }

        ResultSet resultSet5 = statement7.executeQuery("SELECT * FROM Statue");
        while (resultSet5.next()) {
            String SculptorID = resultSet5.getString(1);
            String ArtMovementID = resultSet5.getString(2);
//            Find the Art Movement:
            ResultSet resultSetArtMovements  = statement8.executeQuery("SELECT * FROM ArtMovement");
            ArtMovement artMovementOfArtist = null;
            while (resultSetArtMovements.next()) {
                String id = resultSetArtMovements.getString(1);
                if (Objects.equals(ArtMovementID, id)) {
                    String Name = resultSetArtMovements.getString(2);
                    Date StartDate = resultSetArtMovements.getDate(3);
                    Date EndDate = resultSetArtMovements.getDate(4);
                    artMovementOfArtist = new ArtMovement(id,Name,StartDate,EndDate);
                }
            }

            String locationID = resultSet5.getString(3);
            ResultSet resultSetBlocks  = statement9.executeQuery("SELECT * FROM Blocks");
            Block blockOfArtist = null;
//            Find the block
            while (resultSetBlocks.next()) {
                String id = resultSetBlocks.getString(3);
                if (Objects.equals(locationID, id)) {
                    String Name = resultSetBlocks.getString(1);
                    blockOfArtist = new Block(id, Name);
                }
            }
            float price = resultSet5.getFloat(4);
            Date creation = resultSet5.getDate(5);
            String name = resultSet5.getString(6);
            String StatueID = resultSet5.getString(7);
            Statue statue = new Statue(StatueID,name,creation,blockOfArtist,ArtistDB.getInstance().findById(SculptorID),artMovementOfArtist,price);
            Artist artistToAddArtMovement = ArtistDB.getInstance().findById(SculptorID);
            artistToAddArtMovement.addExhibit(statue);
        }

        System.out.println("test!");
    }
}