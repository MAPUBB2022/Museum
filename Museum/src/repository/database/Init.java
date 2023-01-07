package repository.database;

import classes.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Init {
    public static void getDB() throws ClassNotFoundException, SQLException {
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection connection = OurConnection.getConnection();

        ArtistDB.getInstance().populate(connection);
        ArtMovementDB.getInstance().populate(connection);
        BlockDB.getInstance().populate(connection);
        MuseumDB.getInstance().populate(connection);
        ArtifactDB.getInstance().populate(connection);
        PaintingDB.getInstance().populate(connection);
        StatueDB.getInstance().populate(connection);
        ClientDB.getInstance().populate(connection);
        TicketDB.getInstance().populate(connection);
        AdjustCounters.getInstance().adjust();
//        ArtistDB.getInstance().add(new Artist("Nume", null, null));
//        ArtistDB.getInstance().update("A1002", new Artist("pasarica", null, null));
//        ArtistDB.getInstance().remove("A1003");
//
//        ClientDB.getInstance().add(new Client("Pasarica Roz"));
//        ClientDB.getInstance().update("C1003", new Client ("Pasarica Albastra"));
//        ClientDB.getInstance().remove("C1004");

        System.out.println("Passed Fetching Data from SQL");
    }
}
