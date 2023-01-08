package repository.database;
import classes.*;
import classes.Ticket;
import repository.ICrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class TicketDB implements ICrudRepository<String, Ticket> {

    private static TicketDB single_instance = null;
    private final ArrayList<Ticket> allTickets = new ArrayList<>();

    public static TicketDB getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new TicketDB();
        }
        return single_instance;
    }

    @Override
    public void add(Ticket entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Ticket already exists, please try again using an new name!");
            return;
        }
        allTickets.add(entity);
//        DB Code:
        Double price = entity.getPrice();
        Client client = entity.getGuest();
        String id = entity.getId();

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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Ticket (ID, Client, Price) VALUES (?, ?, ?)");
            statement.setString(1, id);
            statement.setString(2, client.getId());
            statement.setDouble(3, price);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //----------


        System.out.println("Added Ticket!");
    }

    public void addNoDB(Ticket entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The Ticket already exists, please try again using an new name!");
            return;
        }
        allTickets.add(entity);
        System.out.println("Added Ticket!");
    }

    @Override
    public void remove(String s) {
        boolean found = false;
        Ticket TicketToDelete = null;
        for (Ticket a : allTickets) {
            if (a.getId().equals(s)) {
                found = true;
                TicketToDelete = a;
            }
        }
        if (found) {
            allTickets.remove(TicketToDelete);
            //        DB Code:
            String ID = TicketToDelete.getId();

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
                PreparedStatement  statement = connection.prepareStatement("DELETE FROM Ticket WHERE Ticket.ID = ?");
                statement.setString(1, ID);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("The Ticket has been removed!");
            return;
        }
        System.out.println("The Ticket does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String id, Ticket newEntity) {
        boolean found = false;
        for (Ticket a : allTickets) {
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
        System.out.println("The Ticket you want to update does not exist!");
    }

    @Override
    public void updateName(String id, String newName) {
    }

    public void updatePrice(String id, Double newPrice) {
        boolean found = false;
        Ticket TicketToDelete = null;
        for (Ticket a : allTickets) {
            if (a.getId().equals(id)) {
                found = true;
                TicketToDelete = a;
            }
        }
        if (found) {
            allTickets.remove(TicketToDelete);
            TicketToDelete.setPrice(newPrice);
            allTickets.add(TicketToDelete);

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
                PreparedStatement  statement = connection.prepareStatement("UPDATE Ticket SET Price = ? WHERE Ticket.ID = ?");
                statement.setDouble(1, newPrice);
                statement.setString(2, TicketToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated Price!");
            return;
        }
        System.out.println("The Ticket you want to update does not exist!");
    }

    public void updateClient(String id, Client newClient) {
        boolean found = false;
        Ticket TicketToDelete = null;
        for (Ticket a : allTickets) {
            if (a.getId().equals(id)) {
                found = true;
                TicketToDelete = a;
            }
        }
        if (found) {
            allTickets.remove(TicketToDelete);
            TicketToDelete.setGuest(newClient);
            allTickets.add(TicketToDelete);

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
                PreparedStatement  statement = connection.prepareStatement("UPDATE Ticket SET Client = ? WHERE Ticket.ID = ?");
                statement.setString(1, newClient.getId());
                statement.setString(2, TicketToDelete.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //----------

            System.out.println("Updated Guest!");
            return;
        }
        System.out.println("The Ticket you want to update does not exist!");
    }

    @Override
    public Ticket findById(String s) {
        for (Ticket a : allTickets) {
            if (s.equals(a.getId())) {
                return a;
            }
        }
        return null;
    }


    @Override
    public boolean checkIfExists(String s) {
        for (Ticket a : allTickets) {
            if (s.equals(a.getId())) {
                return true;
            }
        }
        return false;
    }

    public List<Ticket> getTickets() {
        return allTickets;
    }

    public static void populate(Connection connection) throws SQLException, ClassNotFoundException {
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();

        ResultSet resultSet = statement1.executeQuery("SELECT * FROM Ticket");

        while (resultSet.next()) {
            Double price = resultSet.getDouble(1);
            String id = resultSet.getString(2);

            String clientid = resultSet.getString(3);
            Client client = ClientDB.getInstance().findById(clientid);

            List<Block> perm = new ArrayList<>();

            ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM Permits WHERE TicketID = '" + id + "'");
            while (resultSet2.next()) {
                String blockid = resultSet2.getString(2);
                Block b = BlockDB.getInstance().findById(blockid);
                perm.add(b);
            }


            //System.out.println(id + ": " + name);
            Ticket t1 = new Ticket(id, price, client, perm);
            TicketDB.getInstance().addNoDB(t1);

            client.addVisit(t1);
        }

    }

    public int numVisits(Museum m) throws SQLException, ClassNotFoundException {

            int count = 0;
            for (Ticket ticket : this.allTickets) {
                List<Block> blocks2 = new ArrayList<>(ticket.getPermits());
                blocks2.retainAll(m.getBlocks());
                if (!blocks2.isEmpty()) {
                    count++;
                }
            }
            return count;

    }
}
