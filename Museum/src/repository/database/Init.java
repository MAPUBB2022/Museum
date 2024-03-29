package repository.database;

import classes.*;

import java.sql.Connection;
import java.sql.SQLException;

public class Init {
    public static void getDB() throws ClassNotFoundException, SQLException {
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection connection = OurConnection.getConnection();

        ArtistDB.getInstance();
        ArtistDB.populate(connection);

        ArtMovementDB.getInstance();
        ArtMovementDB.populate(connection);

        BlockDB.getInstance();
        BlockDB.populate(connection);

        MuseumDB.getInstance();
        MuseumDB.populate(connection);

        ArtifactDB.getInstance();
        ArtifactDB.populate(connection);

        PaintingDB.getInstance();
        PaintingDB.populate(connection);

        StatueDB.getInstance();
        StatueDB.populate(connection);

        ClientDB.getInstance();
        ClientDB.populate(connection);

        TicketDB.getInstance();
        TicketDB.populate(connection);

        AdjustCounters.getInstance().adjust();

        System.out.println("Passed Fetching Data from SQL");
    }

    public static void fixConnections() throws ClassNotFoundException {
        for (Museum m : MuseumDB.getInstance().getMuseums()) {
            ClientDB.getInstance().addClientsToMuseum(m);
        }
    }
}
