package repository.database;
import classes.*;
import repository.ICrudRepository;
import repository.inmemory.ExhibitRepositoryMemory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class PaintingDB implements ICrudRepository<String, Painting> {

    private static PaintingDB single_instance = null;
    private final ArrayList<Painting> allPaintings = new ArrayList<>();

    public static PaintingDB getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new PaintingDB();
        }
        return single_instance;
    }

    @Override
    public void add(Painting entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Painting already exists, please try again using an new name!");
            return;
        }
        allPaintings.add(entity);
//        DB Code:
        Artist Painter = entity.getPainter();
        ArtMovement Artmovement = entity.getArtMovement();
        Block Location = entity.getLocation();
        Double Price = entity.getPrice();
        java.util.Date Creation = entity.getCreation();
        String Name = entity.getName();
        String ID = entity.getId();

        //----------
        Connection connection = null;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Painting (ID, Name, Creation, Price, Location, ArtMovement, Painter) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, ID);
            statement.setString(2, Name);
            statement.setDate(3, (java.sql.Date) Creation);
            statement.setDouble(4, Price);
            statement.setString(5, Location.getId());
            statement.setString(6, Artmovement.getId());
            statement.setString(7, Painter.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------

        System.out.println("Added Painting!");
    }

    public void addNoDB(Painting entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Painting already exists, please try again using an new name!");
            return;
        }
        allPaintings.add(entity);
        System.out.println("Added Painting!");
    }

    @Override
    public void remove(String s) {
        boolean found = false;
        Painting PaintingToDelete = null;
        for (Painting a : allPaintings) {
            if (a.getId().equals(s)) {
                found = true;
                PaintingToDelete = a;
            }
        }
        if (found) {
            allPaintings.remove(PaintingToDelete);
            //        DB Code:
            String ID = PaintingToDelete.getId();

            //----------
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement  statement = connection.prepareStatement("DELETE FROM Painting WHERE Painting.ID = ?");
                statement.setString(1, ID);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("The Painting has been removed!");
            return;
        }
        System.out.println("The Painting does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String id, Painting newEntity) {
        boolean found = false;
        for (Painting a : allPaintings) {
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
        System.out.println("The Painting you want to update does not exist!");
    }

    @Override
    public void updateName(String id, String newName) {
        boolean found = false;
        Painting PaintingToDelete = null;
        for (Painting a : allPaintings) {
            if (a.getId().equals(id)) {
                found = true;
                PaintingToDelete = a;
            }
        }
        if (found) {
            allPaintings.remove(PaintingToDelete);
            PaintingToDelete.setName(newName);
            allPaintings.add(PaintingToDelete);

            //----------
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement  statement = connection.prepareStatement("UPDATE Painting SET Name = ? WHERE Painting.ID = ?");
                statement.setString(1, newName);
                statement.setString(2, PaintingToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated name!");
            return;
        }
        System.out.println("The Painting you want to update does not exist!");
    }

    public void updatePrice(String id, Double newPrice) {
        boolean found = false;
        Painting PaintingToDelete = null;
        for (Painting a : allPaintings) {
            if (a.getId().equals(id)) {
                found = true;
                PaintingToDelete = a;
            }
        }
        if (found) {
            allPaintings.remove(PaintingToDelete);
            PaintingToDelete.setPrice(newPrice);
            allPaintings.add(PaintingToDelete);

            //----------
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement  statement = connection.prepareStatement("UPDATE Painting SET Price = ? WHERE Painting.ID = ?");
                statement.setDouble(1, newPrice);
                statement.setString(2, PaintingToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated price!");
            return;
        }
        System.out.println("The Painting you want to update does not exist!");
    }


    public void updateCreation(String id, Date newCreation) {
        boolean found = false;
        Painting PaintingToDelete = null;
        for (Painting a : allPaintings) {
            if (a.getId().equals(id)) {
                found = true;
                PaintingToDelete = a;
            }
        }
        if (found) {
            allPaintings.remove(PaintingToDelete);
            PaintingToDelete.setCreation(newCreation);
            allPaintings.add(PaintingToDelete);

            //----------
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement  statement = connection.prepareStatement("UPDATE Painting SET Creation = ? WHERE Painting.ID = ?");
                statement.setDate(1, (java.sql.Date) newCreation);
                statement.setString(2, PaintingToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated Creation!");
            return;
        }
        System.out.println("The Painting you want to update does not exist!");
    }

    public void updatePainter(String id, Artist newPainter) {
        boolean found = false;
        Painting PaintingToDelete = null;
        for (Painting a : allPaintings) {
            if (a.getId().equals(id)) {
                found = true;
                PaintingToDelete = a;
            }
        }
        if (found) {
            allPaintings.remove(PaintingToDelete);
            PaintingToDelete.setPainter(newPainter);
            allPaintings.add(PaintingToDelete);

            //----------
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement  statement = connection.prepareStatement("UPDATE Painting SET Painter = ? WHERE Painting.ID = ?");
                statement.setString(1, newPainter.getId());
                statement.setString(2, PaintingToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated Painter!");
            return;
        }
        System.out.println("The Painting you want to update does not exist!");
    }

    public void updateArtMovement(String id, ArtMovement newArtMovement) {
        boolean found = false;
        Painting PaintingToDelete = null;
        for (Painting a : allPaintings) {
            if (a.getId().equals(id)) {
                found = true;
                PaintingToDelete = a;
            }
        }
        if (found) {
            allPaintings.remove(PaintingToDelete);
            PaintingToDelete.setArtMovement(newArtMovement);
            allPaintings.add(PaintingToDelete);

            //----------
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement  statement = connection.prepareStatement("UPDATE Painting SET ArtMovement = ? WHERE Painting.ID = ?");
                statement.setString(1, newArtMovement.getId());
                statement.setString(2, PaintingToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated Art Movement!");
            return;
        }
        System.out.println("The Painting you want to update does not exist!");
    }

    public void updateLocation(String id, Block newLocation) {
        boolean found = false;
        Painting PaintingToDelete = null;
        for (Painting a : allPaintings) {
            if (a.getId().equals(id)) {
                found = true;
                PaintingToDelete = a;
            }
        }
        if (found) {
            allPaintings.remove(PaintingToDelete);
            PaintingToDelete.setLocation(newLocation);
            allPaintings.add(PaintingToDelete);

            //----------
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement  statement = connection.prepareStatement("UPDATE Painting SET Location = ? WHERE Painting.ID = ?");
                statement.setString(1, newLocation.getId());
                statement.setString(2, PaintingToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated Location!");
            return;
        }
        System.out.println("The Painting you want to update does not exist!");
    }


    @Override
    public Painting findById(String s) {
        for (Painting a : allPaintings) {
            if (s.equals(a.getId())) {
                return a;
            }
        }
        return null;
    }


    @Override
    public boolean checkIfExists(String s) {
        for (Painting a : allPaintings) {
            if (s.equals(a.getId())) {
                return true;
            }
        }
        return false;
    }

    public List<Painting> getPaintings() {
        return allPaintings;
    }

    public Painting findByName(String name) {
        for (Painting a : allPaintings) {
            if (name.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }

    public static void populate(Connection connection) throws SQLException, ClassNotFoundException {
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();

        ResultSet resultSet = statement1.executeQuery("SELECT * FROM Painting");

        while (resultSet.next()) {

            String p1 = resultSet.getString(1);
            Artist painter = ArtistDB.getInstance().findById(p1);

            String am1 = resultSet.getString(2);
            ArtMovement artMovement = ArtMovementDB.getInstance().findById(am1);

            String b1 = resultSet.getString(3);
            Block location = BlockDB.getInstance().findById(b1);

            Double price = resultSet.getDouble(4);
            Date creation = resultSet.getDate(5);
            String name = resultSet.getString(6);
            String id = resultSet.getString(7);
            //System.out.println(id + ": " + name);
            PaintingDB.getInstance().addNoDB(new Painting(id, name, creation, location, painter, artMovement, price));
        }
    }

}
