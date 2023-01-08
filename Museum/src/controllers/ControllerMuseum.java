package controllers;

import classes.Block;
import classes.Client;
import classes.Exhibit;
import classes.Museum;
import lombok.SneakyThrows;
import repository.database.*;
import views.ViewMuseum;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ControllerMuseum {

    static public void add(String name) throws ClassNotFoundException  {
        Museum museumToAdd = new Museum(name);
        MuseumDB.getInstance().add(museumToAdd);
    }

    static public void delete(String name) throws ClassNotFoundException {
        MuseumDB.getInstance().remove(name);
    }

    static public void changeName(String previousName, String newName) throws ClassNotFoundException {
        MuseumDB.getInstance().updateName(previousName, newName);
    }

    static public void addBlock(String nameMuseum, String id) throws ClassNotFoundException {

        if (checkIfNotExistsBlock(nameMuseum, id)) {
            return;
        }

        Museum museumToUpdate = MuseumDB.getInstance().findById(nameMuseum);
        museumToUpdate.addBlock(BlockDB.getInstance().findById(id));
        MuseumDB.getInstance().update(nameMuseum, museumToUpdate);
    }

    static public void deleteBlock(String nameMuseum, String id) throws ClassNotFoundException {

        if (checkIfNotExistsBlock(nameMuseum, id)) {
            return;
        }

        Museum museumToUpdate = MuseumDB.getInstance().findById(nameMuseum);
        museumToUpdate.deleteBlock(BlockDB.getInstance().findById(id));
        MuseumDB.getInstance().update(nameMuseum, museumToUpdate);
    }

    static public void addClient(String nameMuseum, String id) throws ClassNotFoundException {

        if (checkIfNotExistsClient(nameMuseum, id)) {
            return;
        }

        Museum museumToUpdate = MuseumDB.getInstance().findById(nameMuseum);
        Client client = ClientDB.getInstance().findById(id);
        museumToUpdate.addClient(client);
        MuseumDB.getInstance().update(nameMuseum, museumToUpdate);
    }

    static public void deleteClient(String nameMuseum, String id) throws ClassNotFoundException {

        if (checkIfNotExistsClient(nameMuseum, id)) {
            return;
        }

        Museum museumToUpdate = MuseumDB.getInstance().findById(nameMuseum);
        museumToUpdate.deleteClient(ClientDB.getInstance().findById(id));
        MuseumDB.getInstance().update(nameMuseum, museumToUpdate);
    }

    static public void displayTotalVisits(String name) throws ClassNotFoundException, SQLException {
        if (!MuseumDB.getInstance().checkIfExists(name)) {
            System.out.println("The museum does not exist, please try again using a new name!");
            return;
        }
        Museum m2 = MuseumDB.getInstance().findById(name);

        System.out.println("The total number of visits to the museum is: " + TicketDB.getInstance().numVisits(m2));
    }

    static public int getTotalVisitsNoPrints(String name) throws ClassNotFoundException, RuntimeException, SQLException {
        if (!MuseumDB.getInstance().checkIfExists(name)) {
            return -1;
        }
        Museum m2 = MuseumDB.getInstance().findById(name);

        return TicketDB.getInstance().numVisits(m2);
    }

    public static void display(String name) throws ClassNotFoundException {
        if (!MuseumDB.getInstance().checkIfExists(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }
        Museum m2 = MuseumDB.getInstance().findById(name);
        ViewMuseum.allInformationDisplay(m2);
    }

    public static boolean checkIfNotExistsClient(String nameMuseum, String id) throws ClassNotFoundException {
        if (!MuseumDB.getInstance().checkIfExists(nameMuseum)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return true;
        }
        if (!ClientDB.getInstance().checkIfExists(id))  {
            System.out.println("Client doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }

    public static boolean checkIfNotExistsBlock(String nameMuseum, String id) throws ClassNotFoundException {
        if (!MuseumDB.getInstance().checkIfExists(nameMuseum)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return true;
        }
        if (!BlockDB.getInstance().checkIfExists(id)) {
            System.out.println("Block doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }

    public static void displayAllExhibits(String name) throws ClassNotFoundException {
        if (!MuseumDB.getInstance().checkIfExists(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }
        List<Block> blocksOfMuseum = MuseumDB.getInstance().findById(name).getBlocks();
        for (Block b : blocksOfMuseum) {
            for (Exhibit e : b.getExhibits()) {
                System.out.println("Exhibit " + e.getName() + " in block " + b.getId());
            }
        }
    }

    public static List<Museum> sort() throws ClassNotFoundException {
        List<Museum> museums = MuseumDB.getInstance().getMuseums();
        Collections.sort(museums);
        for (Museum m : museums) {
            ControllerMuseum.display(m.getName());
        }
        return museums;
    }

    public static List<Museum> filterByClients(int minNumberClients) throws ClassNotFoundException {
        List<Museum> filteredMuseums = new java.util.ArrayList<>(Collections.emptyList());
        for (Museum m : Collections.unmodifiableList(MuseumDB.getInstance().getMuseums())) {
            if (m.getClients().size() > minNumberClients) {
                display(m.getName());
                filteredMuseums.add(m);
            }
        }
        return filteredMuseums;
    }

    public static List<Museum> filterByVisits(int minNumberVisits) throws ClassNotFoundException, SQLException {
        List<Museum> filteredMuseums = new java.util.ArrayList<>(Collections.emptyList());
        for (Museum m : Collections.unmodifiableList(MuseumDB.getInstance().getMuseums())) {
            System.out.println(m.getName() + ' ' + getTotalVisitsNoPrints(m.getName()));
            if (getTotalVisitsNoPrints(m.getName()) > minNumberVisits) {
                display(m.getName());
                filteredMuseums.add(m);
            }
        }
        return filteredMuseums;
    }

    public static List<Museum> filterByExhibit(int minNumberExhibit)  throws ClassNotFoundException{
        List<Museum> filteredMuseums = new java.util.ArrayList<>(Collections.emptyList());
        for (Museum m : Collections.unmodifiableList(MuseumDB.getInstance().getMuseums())) {
            List<Block> blocksOfMuseum = MuseumDB.getInstance().findById(m.getName()).getBlocks();
            int numberExhibits = 0;
            for (Block b : blocksOfMuseum) {
                numberExhibits = numberExhibits + b.getExhibits().size();
            }
            if (numberExhibits > minNumberExhibit) {
                display(m.getName());
                filteredMuseums.add(m);
            }
        }
        return filteredMuseums;
    }
}
