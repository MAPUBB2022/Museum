import repository.database.ArtistDB;
import repository.database.Init;
import repository.inmemory.ClientRepositoryMemory;
import repository.inmemory.ExhibitRepositoryMemory;
import repository.inmemory.MuseumRepositoryMemory;
import repository.inmemory.TicketRepositoryMemory;
import views.menus.Menu;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Init.getDB();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Starting to populate with some default value:
//        MuseumRepositoryMemory.getInstance();
//        ClientRepositoryMemory.getInstance();
//        ExhibitRepositoryMemory.getInstance();
//        TicketRepositoryMemory.getInstance();
//
//        Menu myMenu = Menu.getInstance();
//         myMenu.showMenuOptions();
//        System.out.println("Exiting Program...");
    }
}
