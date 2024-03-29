package repository.database;

import classes.Artifact;
import classes.Block;
import repository.ICrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ArtifactDB implements ICrudRepository<String, Artifact> {

    private static ArtifactDB single_instance = null;
    private final ArrayList<Artifact> allArtifacts = new ArrayList<>();

    public static ArtifactDB getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new ArtifactDB();
        }
        return single_instance;
    }

    @Override
    public void add(Artifact entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Artifact already exists, please try again using an new name!");
            return;
        }
        allArtifacts.add(entity);
        String ID = entity.getId();
        String Name = entity.getName();
        String Origin = entity.getOrigin();
        Block Location = entity.getLocation();
        double Price = entity.getPrice();

        //----------
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Artifact (ID, Name, Creation, Price, Location, Origin) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, ID);
            statement.setString(2, Name);
            java.util.Date date = entity.getCreation();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            statement.setDate(3, sqlDate);
            statement.setDouble(4, Price);
            statement.setString(5, Location.getId());
            statement.setString(6, Origin);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------

        System.out.println("Added Artifact!");
    }

    public void addNoDB(Artifact entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Artifact already exists, please try again using an new name!");
            return;
        }
        allArtifacts.add(entity);
        System.out.println("Added Artifact!");
    }

    @Override
    public void remove(String s) {
        boolean found = false;
        Artifact ArtifactToDelete = null;
        for (Artifact a : allArtifacts) {
            if (a.getId().equals(s)) {
                found = true;
                ArtifactToDelete = a;
            }
        }
        if (found) {
            allArtifacts.remove(ArtifactToDelete);
            //        DB Code:
            String ID = ArtifactToDelete.getId();

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Artifact WHERE Artifact.ID = ?");
                statement.setString(1, ID);
                PreparedStatement statementClients = connection.prepareStatement("DELETE FROM ClientFavorites WHERE ClientFavorites.ExhibitID = ?");
                statementClients.setString(1, ID);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------


            System.out.println("The Artifact has been removed!");
            return;
        }
        System.out.println("The Artifact does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String id, Artifact newEntity) {
        boolean found = false;
        for (Artifact a : allArtifacts) {
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
        System.out.println("The Artifact you want to update does not exist!");
    }

    @Override
    public void updateName(String id, String newName) {
        boolean found = false;
        Artifact ArtifactToDelete = null;
        for (Artifact a : allArtifacts) {
            if (a.getId().equals(id)) {
                found = true;
                ArtifactToDelete = a;
            }
        }
        if (found) {
            allArtifacts.remove(ArtifactToDelete);
            ArtifactToDelete.setName(newName);
            allArtifacts.add(ArtifactToDelete);
            // DB Code:
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Updated name!");
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Artifact SET Name = ? WHERE Artifact.ID = ?");
                statement.setString(1, newName);
                statement.setString(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        System.out.println("The Artifact you want to update does not exist!");
    }

    public void updatePrice(String id, Double newPrice) {
        boolean found = false;
        Artifact ArtifactToDelete = null;
        for (Artifact a : allArtifacts) {
            if (a.getId().equals(id)) {
                found = true;
                ArtifactToDelete = a;
            }
        }
        if (found) {
            allArtifacts.remove(ArtifactToDelete);
            ArtifactToDelete.setPrice(newPrice);
            allArtifacts.add(ArtifactToDelete);
            System.out.println("Updated price!");
            // DB Code:
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Updated name!");
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Artifact SET Price = ? WHERE Artifact.ID = ?");
                statement.setDouble(1, newPrice);
                statement.setString(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        System.out.println("The Artifact you want to update does not exist!");
    }

    public void updateOrigin(String id, String newOrigin) {
        boolean found = false;
        Artifact ArtifactToDelete = null;
        for (Artifact a : allArtifacts) {
            if (a.getId().equals(id)) {
                found = true;
                ArtifactToDelete = a;
            }
        }
        if (found) {
            allArtifacts.remove(ArtifactToDelete);
            ArtifactToDelete.setOrigin(newOrigin);
            allArtifacts.add(ArtifactToDelete);
            System.out.println("Updated origin!");
            // DB Code:
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Updated name!");
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Artifact SET Origin = ? WHERE Artifact.ID = ?");
                statement.setString(1, newOrigin);
                statement.setString(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        System.out.println("The Artifact you want to update does not exist!");
    }

    public void updateCreation(String id, Date newCreation) {
        boolean found = false;
        Artifact ArtifactToDelete = null;
        for (Artifact a : allArtifacts) {
            if (a.getId().equals(id)) {
                found = true;
                ArtifactToDelete = a;
            }
        }
        if (found) {
            allArtifacts.remove(ArtifactToDelete);
            ArtifactToDelete.setCreation(newCreation);
            allArtifacts.add(ArtifactToDelete);
            System.out.println("Updated Creation!");
            // DB Code:
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Updated name!");
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Artifact SET Creation = ? WHERE Artifact.ID = ?");
                java.sql.Date sqlDate = new java.sql.Date(newCreation.getTime());
                statement.setDate(1, sqlDate);
                statement.setString(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        System.out.println("The Artifact you want to update does not exist!");
    }

    @Override
    public Artifact findById(String s) {
        for (Artifact a : allArtifacts) {
            if (s.equals(a.getId())) {
                return a;
            }
        }
        return null;
    }


    @Override
    public boolean checkIfExists(String s) {
        for (Artifact a : allArtifacts) {
            if (s.equals(a.getId())) {
                return true;
            }
        }
        return false;
    }

    public List<Artifact> getArtifacts() {
        return allArtifacts;
    }

    public Artifact findByName(String name) {
        for (Artifact a : allArtifacts) {
            if (name.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }

    public static void populate(Connection connection) throws SQLException, ClassNotFoundException {
        Statement statement1 = connection.createStatement();

        ResultSet resultSet = statement1.executeQuery("SELECT * FROM Artifact");

        while (resultSet.next()) {
            String origin = resultSet.getString(1);

            //Location
            String blockid = resultSet.getString(2);
            Block location = BlockDB.getInstance().findById(blockid);


            Double price = resultSet.getDouble(3);
            Date creation = resultSet.getDate(4);
            String name = resultSet.getString(5);
            String id = resultSet.getString(6);
            //System.out.println(id + ": " + name);
            ArtifactDB.getInstance().addNoDB(new Artifact(id, name, creation, location, origin, price));
        }
    }

}
