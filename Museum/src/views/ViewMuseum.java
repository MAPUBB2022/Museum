package views;

import classes.Block;
import classes.Client;
import classes.Museum;
import repository.Repository;

import java.util.List;

public class ViewMuseum {
    public static void allInformationDisplay(String name) {
        Museum m2 = new Museum("debug");
        for (Museum m1 : Repository.getInstance().getMuseums()) {
            if (m1.getName().equals(name)) {
                m2 = m1;
            }
        }

        System.out.println("Museum " + name + "\n");
        List<Block> blocks = m2.getBlocks();

        System.out.println("Blocks:");
        for (Block b : blocks) {
            System.out.println(b.getName() + " with ID " + b.getId());
        }

        System.out.println("\n");
        List<Client> clients = m2.getClients();

        System.out.println("\n");
        System.out.println("Clients:");
        for (Client c : clients) {
            System.out.println(c.getName() + " with id " + c.getId());
        }
        System.out.println("\n");
    }
}
