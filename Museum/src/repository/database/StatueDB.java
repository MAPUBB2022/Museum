package repository.database;

import classes.*;
import repository.ICrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StatueDB implements ICrudRepository<String, Statue> {

    private static StatueDB single_instance = null;
    private final ArrayList<Statue> allStatues = new ArrayList<>();

    public static StatueDB getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new StatueDB();
        }
        return single_instance;
    }

    @Override
    public void add(Statue entity) throws ClassNotFoundException {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Statue already exists, please try again using an new name!");
            return;
        }
        allStatues.add(entity);
        System.out.println(entity.getArtMovement().getName());
        BlockDB.getInstance().findById(entity.getLocation().getId()).addMovement(entity.getArtMovement());
        BlockDB.getInstance().findById(entity.getLocation().getId()).addArtist(entity.getSculptor());

//        DB Code:
        Artist Sculptor = entity.getSculptor();
        ArtMovement Artmovement = entity.getArtMovement();
        Block Location = entity.getLocation();
        double Price = entity.getPrice();
        String Name = entity.getName();
        String ID = entity.getId();

        //----------
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Statue (ID, Name, Creation, Price, Location, ArtMovement, Sculptor) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, ID);
            statement.setString(2, Name);
            // Error
            java.util.Date date = entity.getCreation();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            statement.setDate(3, sqlDate);

            statement.setDouble(4, Price);
            statement.setString(5, Location.getId());
            statement.setString(6, Artmovement.getId());
            statement.setString(7, Sculptor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------

        System.out.println("Added Statue!");
    }

    public void addNoDB(Statue entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Statue already exists, please try again using an new name!");
            return;
        }
        allStatues.add(entity);
        System.out.println("Added Statue!");
    }

    @Override
    public void remove(String s) throws ClassNotFoundException {
        boolean found = false;
        Statue StatueToDelete = null;
        for (Statue a : allStatues) {
            if (a.getId().equals(s)) {
                found = true;
                StatueToDelete = a;
            }
        }
        if (found) {
            allStatues.remove(StatueToDelete);
            try {
                ArtistDB.getInstance().findById(StatueToDelete.getSculptor().getId()).deleteExhibit(StatueToDelete);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            List<Client> allClients = ClientDB.getInstance().getClients();
            for (Client c : allClients) {
                c.deleteExhibitToFavorites(StatueToDelete);
            }

            //        DB Code:
            String ID = StatueToDelete.getId();

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Statue WHERE Statue.ID = ?");
                statement.setString(1, ID);
                PreparedStatement statementClients = connection.prepareStatement("DELETE FROM ClientFavorites WHERE ClientFavorites.ExhibitID = ?");
                statementClients.setString(1, ID);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("The Statue has been removed!");
            return;
        }
        System.out.println("The Statue does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String id, Statue newEntity) throws ClassNotFoundException {
        boolean found = false;
        for (Statue a : allStatues) {
            if (a.getId().equals(id)) {
                found = true;
                break;
            }
        }
        if (found) {
            this.remove(id);
            this.add(newEntity);
            return;
        }
        System.out.println("The Statue you want to update does not exist!");
    }

    @Override
    public void updateName(String id, String newName) {
        boolean found = false;
        Statue StatueToDelete = null;
        for (Statue a : allStatues) {
            if (a.getId().equals(id)) {
                found = true;
                StatueToDelete = a;
            }
        }
        if (found) {
            allStatues.remove(StatueToDelete);
            StatueToDelete.setName(newName);
            allStatues.add(StatueToDelete);

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Statue SET Name = ? WHERE Statue.ID = ?");
                statement.setString(1, newName);
                statement.setString(2, StatueToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------


            System.out.println("Updated name!");
            return;
        }
        System.out.println("The Statue you want to update does not exist!");
    }

    public void updatePrice(String id, Double newPrice) {
        boolean found = false;
        Statue StatueToDelete = null;
        for (Statue a : allStatues) {
            if (a.getId().equals(id)) {
                found = true;
                StatueToDelete = a;
            }
        }
        if (found) {
            allStatues.remove(StatueToDelete);
            StatueToDelete.setPrice(newPrice);
            allStatues.add(StatueToDelete);

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Statue SET Price = ? WHERE Statue.ID = ?");
                statement.setDouble(1, newPrice);
                statement.setString(2, StatueToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated price!");
            return;
        }
        System.out.println("The Statue you want to update does not exist!");
    }


    public void updateCreation(String id, Date newCreation) {
        boolean found = false;
        Statue StatueToDelete = null;
        for (Statue a : allStatues) {
            if (a.getId().equals(id)) {
                found = true;
                StatueToDelete = a;
            }
        }
        if (found) {
            allStatues.remove(StatueToDelete);
            StatueToDelete.setCreation(newCreation);
            allStatues.add(StatueToDelete);

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Statue SET Creation = ? WHERE Statue.ID = ?");
                java.sql.Date sqlDate = new java.sql.Date(newCreation.getTime());
                statement.setDate(1, sqlDate);
                statement.setString(2, StatueToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated Creation!");
            return;
        }
        System.out.println("The Statue you want to update does not exist!");
    }

    public void updateSculptor(String id, Artist newSculptor) {
        boolean found = false;
        Statue StatueToDelete = null;
        for (Statue a : allStatues) {
            if (a.getId().equals(id)) {
                found = true;
                StatueToDelete = a;
            }
        }
        if (found) {
            allStatues.remove(StatueToDelete);
            StatueToDelete.setSculptor(newSculptor);
            allStatues.add(StatueToDelete);

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Statue SET Sculptor = ? WHERE Statue.ID = ?");
                statement.setString(1, newSculptor.getId());
                statement.setString(2, StatueToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated Painter!");
            return;
        }
        System.out.println("The Statue you want to update does not exist!");
    }

    public void updateArtMovement(String id, ArtMovement newArtMovement) {
        boolean found = false;
        Statue StatueToDelete = null;
        for (Statue a : allStatues) {
            if (a.getId().equals(id)) {
                found = true;
                StatueToDelete = a;
            }
        }
        if (found) {
            allStatues.remove(StatueToDelete);
            StatueToDelete.setArtMovement(newArtMovement);
            allStatues.add(StatueToDelete);

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Statue SET ArtMovement = ? WHERE Statue.ID = ?");
                statement.setString(1, newArtMovement.getId());
                statement.setString(2, StatueToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated Art Movement!");
            return;
        }
        System.out.println("The Statue you want to update does not exist!");
    }

    public void updateLocation(String id, Block newLocation) {
        boolean found = false;
        Statue StatueToDelete = null;
        for (Statue a : allStatues) {
            if (a.getId().equals(id)) {
                found = true;
                StatueToDelete = a;
            }
        }
        if (found) {
            allStatues.remove(StatueToDelete);
            StatueToDelete.setLocation(newLocation);
            allStatues.add(StatueToDelete);

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Statue SET Location = ? WHERE Statue.ID = ?");
                statement.setString(1, newLocation.getId());
                statement.setString(2, StatueToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated Location!");
            return;
        }
        System.out.println("The Statue you want to update does not exist!");
    }


    @Override
    public Statue findById(String s) {
        for (Statue a : allStatues) {
            if (s.equals(a.getId())) {
                return a;
            }
        }
        return null;
    }


    @Override
    public boolean checkIfExists(String s) {
        for (Statue a : allStatues) {
            if (s.equals(a.getId())) {
                return true;
            }
        }
        return false;
    }

    public List<Statue> getStatues() {
        return allStatues;
    }

    public Statue findByName(String name) {
        for (Statue a : allStatues) {
            if (name.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }

    public static void populate(Connection connection) throws SQLException, ClassNotFoundException {
        Statement statement1 = connection.createStatement();

        ResultSet resultSet = statement1.executeQuery("SELECT * FROM Statue");

        while (resultSet.next()) {

            String s1 = resultSet.getString(1);
            Artist sculptor = ArtistDB.getInstance().findById(s1);

            String am1 = resultSet.getString(2);
            ArtMovement artMovement = ArtMovementDB.getInstance().findById(am1);

            String b1 = resultSet.getString(3);
            Block location = BlockDB.getInstance().findById(b1);

            double price = resultSet.getDouble(4);
            Date creation = resultSet.getDate(5);
            String name = resultSet.getString(6);
            String id = resultSet.getString(7);
            //System.out.println(id + ": " + name);
            StatueDB.getInstance().addNoDB(new Statue(id, name, creation, location, sculptor, artMovement, price));
        }
    }

}
