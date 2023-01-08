package controllers;

import classes.*;
import repository.inmemory.*;
import repository.database.*;
import views.ViewClient;

import java.util.Collections;
import java.util.List;

public class ControllerClient {
    public static void add(String s) throws ClassNotFoundException {
        ClientDB.getInstance().add(new Client(s));
    }

    public static void delete(String s) throws ClassNotFoundException {
        ClientDB.getInstance().remove(s);
    }

    public static void update(String clientId, String clientName) throws ClassNotFoundException {
        ClientDB.getInstance().updateName(clientId, clientName);
    }

    public static void addVisit(String clientId, List<Block> lb) throws ClassNotFoundException {
        Client c = ClientDB.getInstance().findById(clientId);
        Ticket t = new Ticket(lb, c);
        c.addVisit(t);
        TicketDB.getInstance().add(t);
    }

    public static void addFav(String clientId, String exId) throws ClassNotFoundException {
        Client c = ClientDB.getInstance().findById(clientId);
        Exhibit e = ExhibitDB.getInstance().findById(exId);
        c.addExhibitToFavorites(e);
        ClientDB.getInstance().update(clientId, c);
    }

    public static void remFav(String clientId, String exId) throws ClassNotFoundException {
        Client c = ClientDB.getInstance().findById(clientId);
        Exhibit e = ExhibitDB.getInstance().findById(exId);
        c.deleteExhibitToFavorites(e);
        ClientDB.getInstance().update(clientId, c);
    }

    public static void display(String clientId) throws ClassNotFoundException {
        Client c = ClientDB.getInstance().findById(clientId);
        if (c == null) {
            System.out.println("This client does not exist, try again using a different ID");
            return;
        }
        ViewClient.displayClient(c);
    }

    public static List<Client> sort() throws ClassNotFoundException {
        List<Client> clients = ClientDB.getInstance().getAllClients();
        Collections.sort(clients);
        for (Client c : clients) {
            ControllerClient.display(c.getId());
        }
        return clients;
    }

    public static List<Client> filterByFavorites(String exhibitName) throws ClassNotFoundException {
        Exhibit exhibitToCheck = ExhibitDB.getInstance().findById(exhibitName);
        List<Client> filteredClients = new java.util.ArrayList<>(Collections.emptyList());
        for (Client c : Collections.unmodifiableList(ClientDB.getInstance().getAllClients())) {
            if (c.getFavorites().contains(filteredClients)) {
                display(c.getId());
                filteredClients.add(c);
            }
        }
        return filteredClients;
    }

    public static List<Client> filterByVisits(int minNumberVisits) throws ClassNotFoundException {
        List<Client> filteredClients = new java.util.ArrayList<>(Collections.emptyList());
        for (Client c : Collections.unmodifiableList(ClientDB.getInstance().getAllClients())) {
            if (c.getVisits().size() > minNumberVisits) {
                display(c.getId());
                filteredClients.add(c);
            }
        }
        return filteredClients;
    }
}
