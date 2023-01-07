package repository.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Init {
    public static void getDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection connection = DriverManager.getConnection("jdbc:sqlserver://ASUSTUFGAMINGF1\\SQLEXPRESS:1433;database=MuseumV2;encrypt=false", "newuser", "password");

        ArtistDB.getInstance().populate(connection);
    }
}
