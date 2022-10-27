package controllers;

import classes.Block;
import classes.Client;
import classes.Exhibit;
import classes.Museum;
import repository.inmemory.BlockRepositoryMemory;
import repository.inmemory.ClientRepositoryMemory;
import repository.inmemory.MuseumRepositoryMemory;
import views.ViewMuseum;

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
//        if (!existsInMuseums(name)) {
//            System.out.println("The museum does not exist, please try again using a new name!");
//            return;
//        }
//        Museum m2 = new Museum("Non-Existent");
//        for (Museum m1 : Repository.getInstance().getMuseums()) {
//            if (m1.getName().equals(name)) {
//                m2 = m1;
//            }
//        }
//
//        int count = 0;
//        for (Client client : Repository.getInstance().getClients()) {
//            for (Ticket tickets2 : client.getVisits()) {
//                List<Block> blocks2 = new ArrayList<>(tickets2.getPermits());
//                blocks2.retainAll(m2.getBlocks());
//                if (!blocks2.isEmpty()) {
//                    count++;
//                }
//            }
//        }
//        System.out.println("The total number of visits to the museum is: " + count);
    }

    public static void display(String name) {
        if (!MuseumRepositoryMemory.getInstance().checkIfExists(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
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
}
