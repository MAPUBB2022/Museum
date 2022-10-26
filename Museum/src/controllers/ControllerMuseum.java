package controllers;

import classes.*;
import controllers.*;
import repository.Repository;
import views.ViewExhibit;
import views.ViewMuseum;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControllerMuseum {

    static public boolean existsInMuseums(String name) {
        ArrayList<Museum> museums = Repository.getInstance().getMuseums();
        for (Museum m : museums) {
            if (name.equals(m.getName())) {
                return true;
            }
        }
        return false;
    }

    static public void add(String name) {
        ArrayList<Museum> museums = Repository.getInstance().getMuseums();
        if (existsInMuseums(name)) {
            System.out.println("The museum already exists, please try again using an new name!");
            return;
        }
        Museum m1 = new Museum(name);
        museums.add(m1);
        System.out.println("Added museum!");
        Repository.getInstance().setMuseums(museums);
    }

    static public void delete(String name) {
        ArrayList<Museum> museums = Repository.getInstance().getMuseums();
        for (Museum m : museums) {
            if (name.equals(m.getName())) {
                museums.remove(m);
                Repository.getInstance().setMuseums(museums);
                System.out.println("Deleted museum!");
                return;
            }
        }
        System.out.println("The museum does not exist, please try again using an existing one!");
    }

    static public void changeName(String previousName, String newName) {
        ArrayList<Museum> museums = Repository.getInstance().getMuseums();

        if (existsInMuseums(newName)) {
            System.out.println("The name you wanted to change to already exists!");
        }

        for (Museum m : museums) {
            if (previousName.equals(m.getName())) {
                m.setName(newName);
                Repository.getInstance().setMuseums(museums);
                return;
            }
        }
        System.out.println("Wrong name, please try again using an existing one!");
    }

    static public void addBlock(String nameMuseum, String id) {

        if (!existsInMuseums(nameMuseum)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }
        if (!ControllerBlock.existsInBlocks(id)) {
            System.out.println("Block doesn't exist! Please create it first!");
            return;
        }
        ArrayList<Museum> museums = Repository.getInstance().getMuseums();

        Museum wantedMuseum = new Museum("debug");

        for (Museum m1 : museums) {
            if (m1.getName().equals(nameMuseum)) {
                wantedMuseum = m1;
                break;
            }
        }

        List<Block> blocks = wantedMuseum.getBlocks();

        for (Block b1 : blocks) {
            if (b1.getId().equals(id)) {
                System.out.println("Block already in museum!");
                return;
            }
        }
        for (Block b2 : Repository.getInstance().getBlocks()) {
            if (b2.getId().equals(id)) {
                blocks.add(b2);
                wantedMuseum.setBlocks(blocks);
                Repository.getInstance().setMuseums(museums);
                System.out.println("Block was added in museum!");
                return;
            }
        }

    }

    static public void deleteBlock(String nameMuseum, String id) {

        if (!existsInMuseums(nameMuseum)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }
        if (!ControllerBlock.existsInBlocks(id)) {
            System.out.println("Block doesn't exist! Please create it first!");
            return;
        }
        ArrayList<Museum> museums = Repository.getInstance().getMuseums();

        Museum wantedMuseum = new Museum("debug");

        for (Museum m1 : museums) {
            if (m1.getName().equals(nameMuseum)) {
                wantedMuseum = m1;
                break;
            }
        }

        List<Block> blocks = wantedMuseum.getBlocks();
        boolean ok = false;
        for (Block b1 : blocks) {
            if (b1.getId().equals(id)) {
                ok = true;
            }
        }
        if(!ok)
        {
            System.out.println("Block is not in museum!");
            return;
        }
        for (Block b2 : Repository.getInstance().getBlocks()) {
            if (b2.getId().equals(id)) {
                blocks.remove(b2);
                wantedMuseum.setBlocks(blocks);
                Repository.getInstance().setMuseums(museums);
                System.out.println("Block was removed from museum!");
                return;
            }
        }
    }

    static public void addClient(String nameMuseum, String id) {
        if (!existsInMuseums(nameMuseum)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }
        if (!ControllerClient.existsInClients(id)) {
            System.out.println("Client doesn't exist! Please create it first!");
            return;
        }
        ArrayList<Museum> museums = Repository.getInstance().getMuseums();

        Museum wantedMuseum = new Museum("debug");

        for (Museum m1 : museums) {
            if (m1.getName().equals(nameMuseum)) {
                wantedMuseum = m1;
                break;
            }
        }

        List<Client> clients = wantedMuseum.getClients();

        for (Client c1 : clients) {
            if (c1.getId().equals(id)) {
                System.out.println("Client already in museum!");
                return;
            }
        }
        for (Client c2 : Repository.getInstance().getClients()) {
            if (c2.getId().equals(id)) {
                clients.add(c2);
                wantedMuseum.setClients(clients);
                Repository.getInstance().setMuseums(museums);
                System.out.println("Client was added in museum!");
                return;
            }
        }
    }

    static public void deleteClient(String nameMuseum, String id) {
        if (!existsInMuseums(nameMuseum)) {
            System.out.println("Wrong name, please try again using an existing one!");
            return;
        }
        if (!ControllerClient.existsInClients(id)) {
            System.out.println("Client doesn't exist! Please create it first!");
            return;
        }
        ArrayList<Museum> museums = Repository.getInstance().getMuseums();

        Museum wantedMuseum = new Museum("debug");

        for (Museum m1 : museums) {
            if (m1.getName().equals(nameMuseum)) {
                wantedMuseum = m1;
                break;
            }
        }

        List<Client> clients = wantedMuseum.getClients();
        boolean ok = false;
        for (Client c1 : clients) {
            if (c1.getId().equals(id)) {
                ok = true;
                break;
            }
        }
        if(!ok)
        {
            System.out.println("Client is not in museum!");
            return;
        }
        for (Client c2 : Repository.getInstance().getClients()) {
            if (c2.getId().equals(id)) {
                clients.remove(c2);
                wantedMuseum.setClients(clients);
                Repository.getInstance().setMuseums(museums);
                System.out.println("Client was removed from museum!");
                return;
            }
        }
    }

    static public void displayAllExhibits(String name) {
        ArrayList<Museum> museums = Repository.getInstance().getMuseums();
        if (!existsInMuseums(name)) {
            System.out.println("The museum does not exist, please try again using an new name!");
            return;
        }
        for (Museum m : museums) {
            if (name.equals(m.getName())) {
                List<Exhibit> exhibits = m.getAllExhibits();
                for (Exhibit e: exhibits) {
                    ViewExhibit.displayExhibit(e);
                }
            }
        }
    }

    static public void displayTotalVisits(String name) {
        ArrayList<Museum> museums = Repository.getInstance().getMuseums();
        if (!existsInMuseums(name)) {
            System.out.println("The museum does not exist, please try again using an new name!");
            return;
        }
        Museum m2 = new Museum("debug");
        for(Museum m1 : Repository.getInstance().getMuseums())
        {
            if(m1.getName().equals(name))
            {
                m2 = m1;
            }
        }

        int count = 0;
        for(Client client : Repository.getInstance().getClients())
        {
            for(Ticket tickets2 : client.getVisits())
            {
                List<Block> blocks2 = new ArrayList<>(tickets2.getPermits());
                blocks2.retainAll(m2.getBlocks());
                if(!blocks2.isEmpty())
                {
                    count ++;
                }
            }
        }
        System.out.println("The total number of visits to the museum is: " + count);
    }

    public static void display(String name) {
        if (!existsInMuseums(name)) {
            System.out.println("Wrong name, please try again using an existing one!");
        }

        Museum m2 = new Museum("debug");
        for(Museum m1 : Repository.getInstance().getMuseums())
        {
            if(m1.getName().equals(name))
            {
                m2 = m1;
            }
        }

        System.out.println("Museum " + name + "\n");
        List<Block> blocks = m2.getBlocks();

        System.out.println("Blocks:");
        for(Block b: blocks) {
            System.out.println(b.getName() + " with ID " + b.getId());
        }

        System.out.println("\n");
        List<Client> clients = m2.getClients();

        System.out.println("\n");
        System.out.println("Clients:");
        for(Client c: clients) {
            System.out.println(c.getName() + " with id " + c.getId());
        }
        System.out.println("\n");
    }
}
