package controllers;

import classes.*;
import repository.inmemory.*;
import views.ViewClient;

import java.util.Collections;
import java.util.List;

public class ControllerClient {
    public static void add(String s) {
        ClientRepositoryMemory.getInstance().add(new Client(s));
    }

    public static void delete(String s) {
        ClientRepositoryMemory.getInstance().remove(s);
    }

    public static void update(String clientId, String clientName) {
        ClientRepositoryMemory.getInstance().updateName(clientId, clientName);
    }

    public static void addVisit(String clientId, List<Block> lb) {
        Client c = ClientRepositoryMemory.getInstance().findById(clientId);
        Ticket t = new Ticket(lb, c);
        c.addVisit(t);
        TicketRepositoryMemory.getInstance().add(t);
        ClientRepositoryMemory.getInstance().update(clientId, c);
    }

    public static void addFav(String clientId, String exId) {
        Client c = ClientRepositoryMemory.getInstance().findById(clientId);
        Exhibit e = ExhibitRepositoryMemory.getInstance().findById(exId);
        c.addExhibitToFavorites(e);
        ClientRepositoryMemory.getInstance().update(clientId, c);
    }

    public static void remFav(String clientId, String exId) {
        Client c = ClientRepositoryMemory.getInstance().findById(clientId);
        Exhibit e = ExhibitRepositoryMemory.getInstance().findById(exId);
        c.deleteExhibitToFavorites(e);
        ClientRepositoryMemory.getInstance().update(clientId, c);
    }

    public static void display(String clientId) {
        Client c = ClientRepositoryMemory.getInstance().findById(clientId);
        if (c == null) {
            System.out.println("This client does not exist, try again using a different ID");
            return;
        }
        ViewClient.displayClient(c);
    }

    public static List<Client> sort() {
        List<Client> clients = ClientRepositoryMemory.getInstance().getAllClients();
        Collections.sort(clients);
        for (Client c : clients) {
            ControllerClient.display(c.getId());
        }
        return clients;
    }

    public static List<Client> filterByFavorites(String exhibitName) {
        Exhibit exhibitToCheck = ExhibitRepositoryMemory.getInstance().findById(exhibitName);
        List<Client> filteredClients = new java.util.ArrayList<>(Collections.emptyList());
        for (Client c : Collections.unmodifiableList(ClientRepositoryMemory.getInstance().getAllClients())) {
            if (c.getFavorites().contains(filteredClients)) {
                display(c.getId());
                filteredClients.add(c);
            }
        }
        return filteredClients;
    }

    public static List<Client> filterByVisits(int minNumberVisits) {
        List<Client> filteredClients = new java.util.ArrayList<>(Collections.emptyList());
        for (Client c : Collections.unmodifiableList(ClientRepositoryMemory.getInstance().getAllClients())) {
            if (c.getVisits().size() > minNumberVisits) {
                display(c.getId());
                filteredClients.add(c);
            }
        }
        return filteredClients;
    }
}
