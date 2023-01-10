package repository.database;

import classes.*;
import classes.Client;
import repository.ICrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientDB implements ICrudRepository<String, Client> {

    private static ClientDB single_instance = null;
    private final ArrayList<Client> allClients = new ArrayList<>();

    public static ClientDB getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new ClientDB();
        }
        return single_instance;
    }

    @Override
    public void add(Client entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Client already exists, please try again using an new name!");
            return;
        }
        allClients.add(entity);
//        DB Code:
        String ID = entity.getId();
        String Name = entity.getName();

        //----------
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Client (ID, Name) VALUES (?, ?)");
            statement.setString(1, ID);
            statement.setString(2, Name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------

        System.out.println("Added Client!");
    }

    public void addNoDB(Client entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Client already exists, please try again using an new name!");
            return;
        }
        allClients.add(entity);
        System.out.println("Added Client!");
    }

    @Override
    public void remove(String s) {
        boolean found = false;
        Client ClientToDelete = null;
        for (Client a : allClients) {
            if (a.getId().equals(s)) {
                found = true;
                ClientToDelete = a;
            }
        }
        if (found) {
            //        DB Code:
            String ID = ClientToDelete.getId();

            //----------
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                for (Ticket t1 : ClientDB.getInstance().findById(s).getVisits()) {
                    PreparedStatement statementPermits = connection.prepareStatement("DELETE FROM Permits WHERE TicketID = ?");
                    statementPermits.setString(1, t1.getId());
                    statementPermits.executeUpdate();
                }

                PreparedStatement statementTickets = connection.prepareStatement("DELETE FROM Ticket WHERE Ticket.Client = ?");
                statementTickets.setString(1, s);
                statementTickets.executeUpdate();

                PreparedStatement statementFav = connection.prepareStatement("DELETE FROM ClientFavorites WHERE ClientFavorites.ClientID = ?");
                statementFav.setString(1, s);
                statementFav.executeUpdate();

                PreparedStatement statement = connection.prepareStatement("DELETE FROM Client WHERE Client.ID = ?");
                statement.setString(1, ID);
                statement.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            //----------
            allClients.remove(ClientToDelete);
            System.out.println("The Client has been removed!");
            return;
        }
        System.out.println("The Client does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String id, Client newEntity) {
        boolean found = false;
        for (Client a : allClients) {
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
        System.out.println("The Client you want to update does not exist!");
    }

    @Override
    public void updateName(String id, String newName) {
        boolean found = false;
        Client ClientToDelete = null;
        for (Client a : allClients) {
            if (a.getId().equals(id)) {
                found = true;
                ClientToDelete = a;
            }
        }
        if (found) {
            allClients.remove(ClientToDelete);
            ClientToDelete.setName(newName);
            allClients.add(ClientToDelete);
            System.out.println("Updated name!");
            // DB Code:
            Connection connection;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE Client SET Name = ? WHERE Client.ID = ?");
                statement.setString(1, String.valueOf(newName));
                statement.setString(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        System.out.println("The Client you want to update does not exist!");
    }


    @Override
    public Client findById(String s) {
        for (Client a : allClients) {
            if (s.equals(a.getId())) {
                return a;
            }
        }
        return null;
    }


    @Override
    public boolean checkIfExists(String s) {
        for (Client a : allClients) {
            if (s.equals(a.getId())) {
                return true;
            }
        }
        return false;
    }

    public List<Client> getClients() {
        return allClients;
    }

    public Client findByName(String name) {
        for (Client a : allClients) {
            if (name.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }

    public static void populate(Connection connection) throws SQLException, ClassNotFoundException {
        Statement statement1 = connection.createStatement();
        Statement statement3 = connection.createStatement();

        ResultSet resultSet = statement1.executeQuery("SELECT * FROM Client");

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            //System.out.println(id + ": " + name);
            ClientDB.getInstance().addNoDB(new Client(id, name));
        }

        ResultSet resultSetClientMuseum = statement2.executeQuery("SELECT * FROM  ClientMuseum");
        while (resultSetClientMuseum.next()) {
            String ClientID = resultSetClientMuseum.getString(1);
            String MuseumID = resultSetClientMuseum.getString(2);
            Client clientToAdd = ClientDB.getInstance().findById(ClientID);
            MuseumDB.getInstance().findById(MuseumID).addClient(clientToAdd);
        }

        ResultSet resultSetExhibit = statement3.executeQuery("SELECT * FROM  ClientFavorites");
        while (resultSetExhibit.next()) {
            String ClientID = resultSetExhibit.getString(1);
            String ExhibitID = resultSetExhibit.getString(2);
            Painting paintingToAdd = PaintingDB.getInstance().findById(ExhibitID);
            Statue statueToAdd = StatueDB.getInstance().findById(ExhibitID);
            Artifact artifactToAdd = ArtifactDB.getInstance().findById(ExhibitID);

            if (paintingToAdd != null) {
                ClientDB.getInstance().findById(ClientID).addExhibitToFavorites(paintingToAdd);
            }

            if (statueToAdd != null) {
                ClientDB.getInstance().findById(ClientID).addExhibitToFavorites(statueToAdd);
            }

            if (artifactToAdd != null) {
                ClientDB.getInstance().findById(ClientID).addExhibitToFavorites(artifactToAdd);
            }
        }
    }

    public List<Client> getAllClients() {
        return this.allClients;
    }

    public void addClientsToMuseum(Museum entity) {
        //----------
        Connection connection2;
        try {
            connection2 = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            for (Block b1 : entity.getBlocks()) {
                PreparedStatement statement = connection2.prepareStatement("SELECT T.Client FROM Permits P INNER JOIN Ticket T ON P.TicketID = T.ID WHERE BlockID = ?");
                statement.setString(1, b1.getId());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String client = resultSet.getString("Client");
                    if (!entity.getClients().contains(ClientDB.single_instance.findById(client))) {
                        entity.addClient(ClientDB.single_instance.findById(client));
                    }
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------
    }

    public void addFav(String clientId, String exId) throws ClassNotFoundException {
        if (!ClientDB.getInstance().checkIfExists(clientId)) {
            System.out.println("The Client doesn't exists, please try again using an new name!");
            return;
        }
        if (!ExhibitDB.getInstance().checkIfExists(exId)) {
            System.out.println("The Exhibit doesn't exists, please try again using an new name!");
            return;
        }

//        ClientDB.getInstance().findById(clientId).addExhibitToFavorites(ExhibitDB.getInstance().findById(exId));
        //----------
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ClientFavorites (ClientID, ExhibitID) VALUES (?, ?)");
            statement.setString(1, clientId);
            statement.setString(2, exId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------

    }

    public void remFav(String clientId, String exId) throws ClassNotFoundException {
        if (!ClientDB.getInstance().checkIfExists(clientId)) {
            System.out.println("The Client doesn't exists, please try again using an new name!");
            return;
        }
        if (!ExhibitDB.getInstance().checkIfExists(exId)) {
            System.out.println("The Exhibit doesn't exists, please try again using an new name!");
            return;
        }

//        ClientDB.getInstance().findById(clientId).addExhibitToFavorites(ExhibitDB.getInstance().findById(exId));
        //----------
        Connection connection;
        try {
            connection = OurConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ClientFavorites WHERE ClientFavorites.ClientID = ? AND ClientFavorites.ExhibitID = ?");
            statement.setString(1, clientId);
            statement.setString(2, exId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------

    }

}
