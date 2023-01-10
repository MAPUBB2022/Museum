package repository.database;

import classes.ArtMovement;
import classes.Artist;
import repository.ICrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ArtMovementDB implements ICrudRepository<String, ArtMovement> {

    private static ArtMovementDB single_instance = null;
    private final ArrayList<ArtMovement> allArtMovements = new ArrayList<>();

    public static ArtMovementDB getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new ArtMovementDB();
        }
        return single_instance;
    }

    @Override
    public void add(ArtMovement entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Art Movement already exists, please try again using an new name!");
            return;
        }
        allArtMovements.add(entity);
//        DB Code:
        String ID = entity.getId();
        String Name = entity.getName();
        java.util.Date StartDate = entity.getStartDate();
        java.util.Date EndDate = entity.getEndDate();


        //----------
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ArtMovement (ID, Name, StartDate, EndDate) VALUES (?, ?, ?, ?)");
            statement.setString(1, ID);
            statement.setString(2, Name);
            java.sql.Date sqlDateStart = new java.sql.Date(StartDate.getTime());
            statement.setDate(3, sqlDateStart);
            java.sql.Date sqlDateEnd = new java.sql.Date(EndDate.getTime());
            statement.setDate(4, sqlDateEnd);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------

        System.out.println("Added Art Movement!");
    }

    public void addNoDB(ArtMovement entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Art Movement already exists, please try again using an new name!");
            return;
        }
        allArtMovements.add(entity);
        System.out.println("Added Art Movement!");
    }

    @Override
    public void remove(String s) {
        boolean found = false;
        ArtMovement ArtMovementToDelete = null;
        for (ArtMovement a : allArtMovements) {
            if (a.getId().equals(s)) {
                found = true;
                ArtMovementToDelete = a;
            }
        }
        if (found) {
            allArtMovements.remove(ArtMovementToDelete);
            //        DB Code:
            String ID = ArtMovementToDelete.getId();

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM ArtMovement WHERE ArtMovement.ID = ?");
                statement.setString(1, ID);
                PreparedStatement statementRemoveArtists = connection.prepareStatement("delete FROM ArtistMovements WHERE ArtistMovements.ArtMovementID = ?");
                statementRemoveArtists.setString(1, ID);
                statementRemoveArtists.executeUpdate();
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("The Art Movement has been removed!");
            return;
        }
        System.out.println("The Art Movement does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String id, ArtMovement newEntity) {
        boolean found = false;
        for (ArtMovement a : allArtMovements) {
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
        System.out.println("The Art Movement you want to update does not exist!");
    }

    @Override
    public void updateName(String id, String newName) {
        boolean found = false;
        ArtMovement ArtMovementToDelete = null;
        for (ArtMovement a : allArtMovements) {
            if (a.getId().equals(id)) {
                found = true;
                ArtMovementToDelete = a;
            }
        }
        if (found) {
            allArtMovements.remove(ArtMovementToDelete);
            ArtMovementToDelete.setName(newName);
            allArtMovements.add(ArtMovementToDelete);
            System.out.println("Updated name!");
            // DB Code:
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE ArtMovement SET Name = ? WHERE ArtMovement.ID = ?");
                statement.setString(1, newName);
                statement.setString(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Updated name!");
            return;
        }
        System.out.println("The Art Movement you want to update does not exist!");
    }

    public void updateDateStarted(String id, java.util.Date newDate) {
        for (ArtMovement aToUpdate : allArtMovements) {
            if (id.equals(aToUpdate.getId())) {
                allArtMovements.remove(aToUpdate);
                aToUpdate.setStartDate(newDate);
                allArtMovements.add(aToUpdate);
//                DB Code:
                Connection connection;
                try {
                    connection = OurConnection.getConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    PreparedStatement statement = connection.prepareStatement("UPDATE ArtMovement SET StartDate = ? WHERE ArtMovement.ID = ?");
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

    public void updateDateEnded(String id, java.util.Date newDate) {
        for (ArtMovement aToUpdate : allArtMovements) {
            if (id.equals(aToUpdate.getId())) {
                allArtMovements.remove(aToUpdate);
                aToUpdate.setEndDate(newDate);
                allArtMovements.add(aToUpdate);
                //                DB Code:
                Connection connection;
                try {
                    connection = OurConnection.getConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    PreparedStatement statement = connection.prepareStatement("UPDATE ArtMovement SET EndDate = ? WHERE ArtMovement.ID = ?");
                    java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
                    statement.setDate(1, sqlDate);
                    statement.setString(2, id);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Successful change! Updated date!");
                return;
            }
        }
        System.out.println("Wrong id, please try again using an existing one!");
    }

    @Override
    public ArtMovement findById(String s) {
        for (ArtMovement am : allArtMovements) {
            if (s.equals(am.getId())) {
                return am;
            }
        }
        return null;
    }


    @Override
    public boolean checkIfExists(String s) {
        for (ArtMovement a : allArtMovements) {
            if (s.equals(a.getId())) {
                return true;
            }
        }
        return false;
    }

    public List<ArtMovement> getArtMovements() {
        return allArtMovements;
    }

    public ArtMovement findByName(String name) {
        for (ArtMovement a : allArtMovements) {
            if (name.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<ArtMovement> getList() {
        return allArtMovements;
    }


    public static void populate(Connection connection) throws SQLException, ClassNotFoundException {
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();

        ResultSet resultSet = statement1.executeQuery("SELECT * FROM ArtMovement");

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            Date startDate = resultSet.getDate(3);
            Date endDate = resultSet.getDate(4);
            //System.out.println(id + ": " + name);
            ArtMovementDB.getInstance().addNoDB(new ArtMovement(id, name, startDate, endDate));
        }

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM ArtistMovements");
        while (resultSet2.next()) {
            String ArtistID = resultSet2.getString(1);
            Artist artist = ArtistDB.getInstance().findById(ArtistID);
            String ArtMovementID = resultSet2.getString(2);
            ArtMovement artMovement = ArtMovementDB.getInstance().findById(ArtMovementID);
            artMovement.addArtist(artist);
        }
    }

}
