package repository.database;

import classes.*;
import repository.ICrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ArtistDB implements ICrudRepository<String, Artist> {
    private static ArtistDB single_instance = null;
    private final ArrayList<Artist> allArtists = new ArrayList<>();

    public static ArtistDB getInstance() throws ClassNotFoundException {
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

        //----------
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Artist (ID, Name, BirthDate, DeathDate) VALUES (?, ?, ?, ?)");
            statement.setString(1, ID);
            statement.setString(2, Name);
            Date sqlDateBirthDate = new Date(BirthDate.getTime());
            statement.setDate(3, sqlDateBirthDate);
            Date sqlDateDeathDate;
            if (DeathDate != null) {
                sqlDateDeathDate = new Date(DeathDate.getTime());
            } else {
                sqlDateDeathDate = null;
            }
            statement.setDate(4, sqlDateDeathDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------

        System.out.println("Added artist!");
    }

    public void addNoDB(Artist entity) {
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

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statementPainting = connection.prepareStatement("DELETE FROM Painting WHERE Painting.Painter = ?");
                statementPainting.setString(1, ID);
                PreparedStatement statementStatue = connection.prepareStatement("DELETE FROM Statue WHERE Statue.Sculptor = ?");
                statementStatue.setString(1, ID);
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Artist WHERE Artist.ID = ?");
                statement.setString(1, ID);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("The artist has been removed!");
            return;
        }
        System.out.println("The artist does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String id, Artist newEntity) {
        boolean found = false;
        for (Artist a : allArtists) {
            if (a.getId().equals(id)) {
                found = true;
                break;
            }
        }
        if (found) {
            //        DB Code:
            this.remove(id);
            this.add(newEntity);
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
            // DB Code:
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Updated name!");
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Artist SET Name = ? WHERE Artist.ID = ?");
                statement.setString(1, newName);
                statement.setString(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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

    public void updateDateBorn(String id, java.util.Date newDate) {
        for (Artist aToUpdate : allArtists) {
            if (id.equals(aToUpdate.getId())) {
                allArtists.remove(aToUpdate);
                aToUpdate.setBirthDate(newDate);
                allArtists.add(aToUpdate);
//                DB Code:
                Connection connection;
                try {
                    connection = OurConnection.getConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    PreparedStatement statement = connection.prepareStatement("UPDATE Artist SET BirthDate = ? WHERE Artist.ID = ?");
                    java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
                    statement.setDate(1, sqlDate);
                    statement.setString(2, id);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
                Connection connection;
                try {
                    connection = OurConnection.getConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    PreparedStatement statement = connection.prepareStatement("UPDATE Artist SET DeathDate = ? WHERE Artist.ID = ?");
                    Date sqlDateDeathDate;
                    if (newDate != null) {
                        sqlDateDeathDate = new Date(newDate.getTime());
                    } else {
                        sqlDateDeathDate = null;
                    }
                    java.sql.Date sqlDate = sqlDateDeathDate;
                    statement.setDate(1, sqlDate);
                    statement.setString(2, id);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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

    public void deleteArtMovement(String artistID, String artMovement) {
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE from ArtistMovements where ArtistMovements.ArtistID = ? and ArtistMovements.ArtMovementID = ?");
            statement.setString(1, artistID);
            statement.setString(2, artMovement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Deleted Art Movement");
    }

    public static void addArtMovement(String artistID, String artMovement) {
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ArtistMovements (ArtistID,ArtMovementID) VALUES (?, ?)");
            statement.setString(1, artistID);
            statement.setString(2, artMovement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Added Art Movement");
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
            //System.out.println(id + ": " + name);
            ArtistDB.getInstance().addNoDB(new Artist(id, name, d1, d2));
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
                    ArtMovement artMovementOfArtist = new ArtMovement(id, Name, StartDate, EndDate);
                    Artist artistToAddArtMovement = ArtistDB.getInstance().findById(ArtistID);
                    artistToAddArtMovement.addMovement(artMovementOfArtist);
                }
            }
        }
        System.out.println("test!");
    }
}