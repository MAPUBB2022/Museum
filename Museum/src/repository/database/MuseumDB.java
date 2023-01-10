package repository.database;

import classes.*;
import classes.Museum;
import repository.ICrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MuseumDB implements ICrudRepository<String, Museum> {

    private static MuseumDB single_instance = null;
    private final ArrayList<Museum> allMuseums = new ArrayList<>();

    public static MuseumDB getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new MuseumDB();
        }
        return single_instance;
    }

    @Override
    public void add(Museum entity) {
        if (checkIfExists(entity.getName())) {
            System.out.println("The Museum already exists, please try again using an new name!");
            return;
        }
        allMuseums.add(entity);
//        DB Code:
        String Name = entity.getName();

        //----------
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Museum (Name) VALUES (?)");
            statement.setString(1, Name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------


        System.out.println("Added Museum!");
    }

    public void addNoDB(Museum entity) {
        if (checkIfExists(entity.getName())) {
            System.out.println("The Museum already exists, please try again using an new name!");
            return;
        }
        allMuseums.add(entity);
        System.out.println("Added Museum!");
    }

    @Override
    public void remove(String s) {
        boolean found = false;
        Museum MuseumToDelete = null;
        for (Museum a : allMuseums) {
            if (a.getName().equals(s)) {
                found = true;
                MuseumToDelete = a;
            }
        }
        if (found) {
            allMuseums.remove(MuseumToDelete);
            //        DB Code:
            String ID = MuseumToDelete.getName();

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statementDeleteBlocks = connection.prepareStatement("Update Block SET Block.Museum = null WHERE Block.Museum = ?");
                statementDeleteBlocks.setString(1, ID);
                statementDeleteBlocks.executeUpdate();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Museum WHERE Museum.Name = ?");
                statement.setString(1, ID);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("The Museum has been removed!");
            return;
        }
        System.out.println("The Museum does not exist, please try again using an existing one!");
    }

    public void changeName(String previousName, String newName) {
        if (this.checkIfExists(newName)) {
            System.out.println("New name already exists!");
            return;
        }
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statementCreateAProvisoTable = connection.prepareStatement("Insert into Museum(Name) values (?)");
            statementCreateAProvisoTable.setString(1, newName);
            statementCreateAProvisoTable.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("Update Block set Block.Museum = ? where Block.Museum = ?");
            statement.setString(1, newName);
            statement.setString(2, previousName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statementDeleteTable = connection.prepareStatement("delete from Museum where Museum.Name = ?");
            statementDeleteTable.setString(1, previousName);
            statementDeleteTable.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.findById(previousName).setName(newName);
    }

    @Override
    public void update(String id, Museum newEntity) {
        System.out.println("If you get here you are in trouble!");
        boolean found = false;
        for (Museum a : allMuseums) {
            if (a.getName().equals(id)) {
                found = true;
                break;
            }
        }
        if (found) {
            this.remove(id);
            this.add(newEntity);
            return;
        }
        System.out.println("The Museum you want to update does not exist!");
    }

    @Override
    public void updateName(String id, String newName) {
        boolean found = false;
        Museum MuseumToDelete = null;
        for (Museum a : allMuseums) {
            if (a.getName().equals(newName)) {
                System.out.println("The museum name you want to change to already exists!");
                return;
            }
        }
        for (Museum a : allMuseums) {
            if (a.getName().equals(id)) {
                found = true;
                MuseumToDelete = a;
            }
        }
        if (found) {
            String oldName = MuseumToDelete.getName();
            allMuseums.remove(MuseumToDelete);
            MuseumToDelete.setName(newName);
            allMuseums.add(MuseumToDelete);
            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE Museum WHERE Museum.Name = ? INSERT INTO Museum(Name) Values (?)");
                statement.setString(1, oldName);
                statement.setString(2, newName);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated name!");
            return;
        }
        System.out.println("The Museum you want to update does not exist!");
    }

    public void deleteBlock(Museum museum, Block block) {
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("Update Block SET Block.Museum = null WHERE Block.ID = ?");
            statement.setString(1, block.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        museum.deleteBlock(block);
        block.setMuseum(null);
    }

    @Override
    public Museum findById(String s) {
        for (Museum a : allMuseums) {
            if (s.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }


    @Override
    public boolean checkIfExists(String s) {
        for (Museum a : allMuseums) {
            if (s.equals(a.getName())) {
                return true;
            }
        }
        return false;
    }

    public List<Museum> getMuseums() {
        return allMuseums;
    }

    public Museum findByName(String name) {
        for (Museum a : allMuseums) {
            if (name.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }

    public static void populate(Connection connection) throws SQLException, ClassNotFoundException {
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();

        ResultSet resultSet = statement1.executeQuery("SELECT * FROM Museum");
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            MuseumDB.getInstance().addNoDB(new Museum(name));
        }

        ResultSet resultSetBlock = statement2.executeQuery("SELECT * FROM Block");
        while (resultSetBlock.next()) {
            String blockID = resultSetBlock.getString(3);
            Block block = BlockDB.getInstance().findById(blockID);
            String MuseumName = resultSetBlock.getString(2);
            System.out.println(MuseumName);
            if (MuseumName != null) {
                MuseumDB.getInstance().findById(MuseumName).addBlock(block);
            }
        }

        ResultSet resultSet2 = statement1.executeQuery("SELECT * FROM Block");
        while (resultSet2.next()) {
//            String name = resultSet2.getString(1);
            String museumid = resultSet2.getString(2);
            String blockID = resultSet2.getString(3);
            if (museumid != null) {
                Museum museumToChange = MuseumDB.getInstance().findById(museumid);
                BlockDB.getInstance().updateMuseumNoDB(blockID, museumToChange);
            }
        }

    }

}
