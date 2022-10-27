package views;

import classes.Block;
import classes.Client;
import classes.Museum;

import java.util.List;

public class ViewMuseum {
    public static void allInformationDisplay(Museum m2) {
        System.out.println("Museum " + m2.getName() + "\n");
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
