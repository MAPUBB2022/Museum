package controllers;

import classes.Block;
import classes.Client;
import classes.Exhibit;
import classes.Museum;
import repository.inmemory.BlockRepositoryMemory;
import repository.inmemory.ClientRepositoryMemory;
import repository.inmemory.MuseumRepositoryMemory;
import repository.inmemory.TicketRepositoryMemory;
import views.ViewMuseum;

import java.util.Collections;
import java.util.List;

public class ControllerMuseum {

    static public void add(String name) {
        Museum museumToAdd = new Museum(name);
        MuseumRepositoryMemory.getInstance().add(museumToAdd);
    }

    static public void delete(String name) {
        MuseumRepositoryMemory.getInstance().remove(name);
    }

    static public void changeName(String previousName, String newName) {
        MuseumRepositoryMemory.getInstance().updateName(previousName, newName);
    }

    static public void addBlock(String nameMuseum, String id) {

        if (checkIfNotExistsBlock(nameMuseum, id)) {
            return;
        }

        Museum museumToUpdate = MuseumRepositoryMemory.getInstance().findById(nameMuseum);
        museumToUpdate.addBlock(BlockRepositoryMemory.getInstance().findById(id));
        MuseumRepositoryMemory.getInstance().update(nameMuseum, museumToUpdate);
    }

    static public void deleteBlock(String nameMuseum, String id) {

        if (checkIfNotExistsBlock(nameMuseum, id)) {
            return;
        }

        Museum museumToUpdate = MuseumRepositoryMemory.getInstance().findById(nameMuseum);
        museumToUpdate.deleteBlock(BlockRepositoryMemory.getInstance().findById(id));
        MuseumRepositoryMemory.getInstance().update(nameMuseum, museumToUpdate);
    }

    static public void addClient(String nameMuseum, String id) {

        if (checkIfNotExistsClient(nameMuseum, id)) {
            return;
        }

        Museum museumToUpdate = MuseumRepositoryMemory.getInstance().findById(nameMuseum);
        Client client = ClientRepositoryMemory.getInstance().findById(id);
        museumToUpdate.addClient(client);
        MuseumRepositoryMemory.getInstance().update(nameMuseum, museumToUpdate);
    }

    static public void deleteClient(String nameMuseum, String id) {

        if (checkIfNotExistsClient(nameMuseum, id)) {
            return;
        }

        Museum museumToUpdate = MuseumRepositoryMemory.getInstance().findById(nameMuseum);
        museumToUpdate.deleteClient(ClientRepositoryMemory.getInstance().findById(id));
        MuseumRepositoryMemory.getInstance().update(nameMuseum, museumToUpdate);
    }

    static public void displayTotalVisits(String name) {
        if (!MuseumRepositoryMemory.getInstance().checkIfExists(name)) {
            System.out.println("The museum does not exist, please try again using a new name!");
            return;
        }
        Museum m2 = MuseumRepositoryMemory.getInstance().findById(name);

        System.out.println("The total number of visits to the museum is: " + TicketRepositoryMemory.getInstance().numVisits(m2));
    }

    static public int getTotalVisitsNoPrints(String name) {
        if (!MuseumRepositoryMemory.getInstance().checkIfExists(name)) {
            return -1;
        }
        Museum m2 = MuseumRepositoryMemory.getInstance().findById(name);

        return TicketRepositoryMemory.getInstance().numVisits(m2);
    }

    public static void display(String name) {
        if (!MuseumRepositoryMemory.getInstance().checkIfExists(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }
        Museum m2 = MuseumRepositoryMemory.getInstance().findById(name);
        ViewMuseum.allInformationDisplay(m2);
    }

    public static boolean checkIfNotExistsClient(String nameMuseum, String id) {
        if (!MuseumRepositoryMemory.getInstance().checkIfExists(nameMuseum)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return true;
        }
        if (!ClientRepositoryMemory.getInstance().checkIfExists(id)) {
            System.out.println("Client doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }

    public static boolean checkIfNotExistsBlock(String nameMuseum, String id) {
        if (!MuseumRepositoryMemory.getInstance().checkIfExists(nameMuseum)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return true;
        }
        if (!BlockRepositoryMemory.getInstance().checkIfExists(id)) {
            System.out.println("Block doesn't exist! Please create it first!");
            return true;
        }
        return false;
    }

    public static void displayAllExhibits(String name) {
        if (!MuseumRepositoryMemory.getInstance().checkIfExists(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }
        List<Block> blocksOfMuseum = MuseumRepositoryMemory.getInstance().findById(name).getBlocks();
        for (Block b : blocksOfMuseum) {
            for (Exhibit e : b.getExhibits()) {
                System.out.println("Exhibit " + e.getName() + " in block " + b.getId());
            }
        }
    }

    public static List<Museum> sort() {
        List<Museum> museums = MuseumRepositoryMemory.getInstance().getMuseums();
        Collections.sort(museums);
        for (Museum m : museums) {
            ControllerMuseum.display(m.getName());
        }
        return museums;
    }

    public static List<Museum> filterByClients(int minNumberClients) {
        List<Museum> filteredMuseums = new java.util.ArrayList<>(Collections.emptyList());
        for (Museum m : Collections.unmodifiableList(MuseumRepositoryMemory.getInstance().getMuseums())) {
            if (m.getClients().size() > minNumberClients) {
                display(m.getName());
                filteredMuseums.add(m);
            }
        }
        return filteredMuseums;
    }

    public static List<Museum> filterByVisits(int minNumberVisits) {
        List<Museum> filteredMuseums = new java.util.ArrayList<>(Collections.emptyList());
        for (Museum m : Collections.unmodifiableList(MuseumRepositoryMemory.getInstance().getMuseums())) {
            System.out.println(m.getName() + ' ' + getTotalVisitsNoPrints(m.getName()));
            if (getTotalVisitsNoPrints(m.getName()) > minNumberVisits) {
                display(m.getName());
                filteredMuseums.add(m);
            }
        }
        return filteredMuseums;
    }

    public static List<Museum> filterByExhibit(int minNumberExhibit) {
        List<Museum> filteredMuseums = new java.util.ArrayList<>(Collections.emptyList());
        for (Museum m : Collections.unmodifiableList(MuseumRepositoryMemory.getInstance().getMuseums())) {
            List<Block> blocksOfMuseum = MuseumRepositoryMemory.getInstance().findById(m.getName()).getBlocks();
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
