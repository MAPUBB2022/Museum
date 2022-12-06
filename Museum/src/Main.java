import repository.inmemory.ClientRepositoryMemory;
import repository.inmemory.ExhibitRepositoryMemory;
import repository.inmemory.MuseumRepositoryMemory;
import repository.inmemory.TicketRepositoryMemory;
import views.menus.Menu;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Starting to populate with some default value:
        MuseumRepositoryMemory.getInstance();
        ClientRepositoryMemory.getInstance();
        ExhibitRepositoryMemory.getInstance();
        TicketRepositoryMemory.getInstance();

        Menu myMenu = Menu.getInstance();
        // myMenu.showMenuOptions();
        System.out.println("Exiting Program...");

        try {
            Connection con =             DriverManager.getConnection("jdbc:sqlserver://" + "DESKTOP-5UV75S5\\MSSQLSERVER" + ":1433;DatabaseName=" + "Museum" + ";integratedSecurity=true;");
            // DriverManager.getConnection(("jdbc:sqlserver://" + "DESKTOP-5UV75S5\\MSSQLSERVER" + ":1433;DatabaseName=" + "Museum" + ";encrypt=false;trustServerCertificate=true"));
            // DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Museum;integratedSecurity=true;");

            Statement st = con.createStatement();
            String query = "select * from Artist";
            ResultSet rs = st.executeQuery(query);
            System.out.println(rs);
        } catch (SQLException e) {
            System.out.println("Could not connect to the SQL Server");
            throw new RuntimeException(e);
        }
    }
}
