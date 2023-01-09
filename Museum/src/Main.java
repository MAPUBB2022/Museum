import repository.database.Init;
import views.menus.Menu;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Init.getDB();
            Init.fixConnections();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        Menu myMenu = Menu.getInstance();
        myMenu.showMenuOptions();
        System.out.println("Exiting Program...");
    }
}
