import repository.inmemory.ClientRepositoryMemory;
import repository.inmemory.ExhibitRepositoryMemory;
import repository.inmemory.MuseumRepositoryMemory;
import repository.inmemory.TicketRepositoryMemory;
import views.menus.Menu;

public class Main {
    public static void main(String[] args) {
        // Starting to populate with some default value:
        MuseumRepositoryMemory.getInstance();
        ClientRepositoryMemory.getInstance();
        ExhibitRepositoryMemory.getInstance();
        TicketRepositoryMemory.getInstance();

        Menu myMenu = Menu.getInstance();
        myMenu.showMenuOptions();
        System.out.println("Exiting Program...");
    }
}