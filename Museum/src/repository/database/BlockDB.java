package repository.database;
import classes.*;
import classes.Block;
import repository.ICrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class BlockDB implements ICrudRepository<String, Block> {

    private static BlockDB single_instance = null;
    private final ArrayList<Block> allBlocks = new ArrayList<>();

    public static BlockDB getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new BlockDB();
        }
        return single_instance;
    }

    @Override
    public void add(Block entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Block already exists, please try again using an new name!");
            return;
        }
        allBlocks.add(entity);
//        DB Code:
        String Name = entity.getName();
        Museum museum = entity.getMuseum();
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Block (ID, Name, Museum) VALUES (?, ?, ?)");
            statement.setString(1, ID);
            statement.setString(2, Name);
            statement.setString(3, museum.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------

        System.out.println("Added Block!");
    }

    public void addNoDB(Block entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Block already exists, please try again using an new name!");
            return;
        }
        allBlocks.add(entity);
        System.out.println("Added Block!");
    }


    @Override
    public void remove(String s) {
        boolean found = false;
        Block BlockToDelete = null;
        for (Block a : allBlocks) {
            if (a.getId().equals(s)) {
                found = true;
                BlockToDelete = a;
            }
        }
        if (found) {
            allBlocks.remove(BlockToDelete);
            //        DB Code:
            String ID = BlockToDelete.getId();

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
                PreparedStatement  statementDeleteFromArtifact = connection.prepareStatement("UPDATE Artifact set Artifact.Location = null WHERE Artifact.Location = ?");
                statementDeleteFromArtifact.setString(1, ID);
                statementDeleteFromArtifact.executeUpdate();

                PreparedStatement  statementDeleteFromPainting = connection.prepareStatement("UPDATE Painting set Painting.Location = null WHERE Painting.Location = ?");
                statementDeleteFromPainting.setString(1, ID);
                statementDeleteFromPainting.executeUpdate();

                PreparedStatement  statementDeleteFromStatue = connection.prepareStatement("UPDATE Statue set Statue.Location = null where Statue.Location = ?");
                statementDeleteFromStatue.setString(1, ID);
                statementDeleteFromStatue.executeUpdate();

                PreparedStatement  statement = connection.prepareStatement("DELETE FROM Block WHERE Block.ID = ?");
                statement.setString(1, ID);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("The Block has been removed!");
            return;
        }
        System.out.println("The Block does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String id, Block newEntity) {
        boolean found = false;
        for (Block a : allBlocks) {
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
        System.out.println("The Block you want to update does not exist!");
    }

    @Override
    public void updateName(String id, String newName) {
        boolean found = false;
        Block BlockToDelete = null;
        for (Block a : allBlocks) {
            if (a.getId().equals(id)) {
                found = true;
                BlockToDelete = a;
            }
        }
        if (found) {
            allBlocks.remove(BlockToDelete);
            BlockToDelete.setName(newName);
            allBlocks.add(BlockToDelete);
            // DB Code:
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Block  SET Name = ? WHERE Block.ID = ?");
                statement.setString(1, String.valueOf(newName));
                statement.setString(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Updated name!");
            return;
        }
        System.out.println("The Block you want to update does not exist!");
    }

    public void updateMuseum(String id, Museum newMuseum) {
        boolean found = false;
        Block BlockToDelete = null;
        for (Block a : allBlocks) {
            if (a.getId().equals(id)) {
                found = true;
                BlockToDelete = a;
            }
        }
        if (found) {
            allBlocks.remove(BlockToDelete);
            BlockToDelete.setMuseum(newMuseum);
            allBlocks.add(BlockToDelete);
            // DB Code:
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Block SET Museum = ? WHERE Block.ID = ?");
                statement.setString(1, newMuseum.getName());
                statement.setString(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Updated name!");
            return;
        }
        System.out.println("The Block you want to update does not exist!");
    }

    public void updateMuseumNoDB(String id, Museum newMuseum) {
        boolean found = false;
        Block BlockToDelete = null;
        for (Block a : allBlocks) {
            if (a.getId().equals(id)) {
                found = true;
                BlockToDelete = a;
            }
        }
        if (found) {
            allBlocks.remove(BlockToDelete);
            BlockToDelete.setMuseum(newMuseum);
            allBlocks.add(BlockToDelete);
            System.out.println("Updated Museum!");
            return;
        }
        System.out.println("The Block you want to update does not exist!");
    }

    @Override
    public Block findById(String s) {
        if (s == null) {
            return null;
        }
            for (Block a : allBlocks) {
            if (s.equals(a.getId())) {
                return a;
            }
        }
        return null;
    }


    @Override
    public boolean checkIfExists(String s) {
        for (Block a : allBlocks) {
            if (s.equals(a.getId())) {
                return true;
            }
        }
        return false;
    }

    public List<Block> getBlocks() {
        return allBlocks;
    }

    public Block findByName(String name) {
        for (Block a : allBlocks) {
            if (name.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }

    public List<Block> getAllBlocks(){return this.allBlocks;}


    public static void populate(Connection connection) throws SQLException, ClassNotFoundException {
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
        Statement statement5 = connection.createStatement();

        ResultSet resultSet = statement1.executeQuery("SELECT * FROM Block");

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            String museumid = resultSet.getString(2);
            Museum museumToAdd = new Museum(museumid);
//            Museum museum = MuseumDB.getInstance().findById(museumid);
            String id = resultSet.getString(3);
            //System.out.println(id + ": " + name);
            BlockDB.getInstance().addNoDB(new Block(id, name, museumToAdd));
        }

        ResultSet resultSet4 = statement2.executeQuery("SELECT * FROM Painting");
        while (resultSet4.next()) {
            String artistID = resultSet4.getString(1);
            String ArtMovementID = resultSet4.getString(2);
            String BlockID = resultSet4.getString(3);
            Block block = BlockDB.getInstance().findById(BlockID);
//            Add the Artist:
            Artist artist = ArtistDB.getInstance().findById(artistID);
            block.addArtist(artist);
//            Add the Art Movement:
            ArtMovement movement = ArtMovementDB.getInstance().findById(ArtMovementID);
            block.addMovement(movement);
        }

        ResultSet resultSet5 = statement3.executeQuery("SELECT * FROM Statue");
        while (resultSet5.next()) {
            String artistID = resultSet5.getString(1);
            String ArtMovementID = resultSet5.getString(2);
            String BlockID = resultSet5.getString(3);
            Block block = BlockDB.getInstance().findById(BlockID);
//            Add the Artist:
            Artist artist = ArtistDB.getInstance().findById(artistID);
            block.addArtist(artist);
//            Add the Art Movement:
            ArtMovement movement = ArtMovementDB.getInstance().findById(ArtMovementID);
            block.addMovement(movement);
        }
    }
}
