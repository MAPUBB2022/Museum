package classes;

import java.util.ArrayList;
import java.util.List;

public class Museum {
    private String name;
    private List<Block> blocks;
    private List<Client> clients;

    public Museum(String name) {
        this.name = name;
        this.blocks = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client clientToAdd) {
        if (clients.contains(clientToAdd)) {
            System.out.println("Duplicate!");
        } else {
            clients.add(clientToAdd);
            System.out.println("Added:" + clientToAdd.getName());
        }
    }

    public void deleteClient(Client clientToRemove) {
        if (clients.contains(clientToRemove)) {
            System.out.println("Removed:" + clientToRemove.getName());
            clients.remove(clientToRemove);
        } else {
            System.out.println("No such client was found!");
        }
    }

    public void addBlock(Block blockToAdd) {
        if (blocks.contains(blockToAdd)) {
            System.out.println("Duplicate!");
        } else {
            blocks.add(blockToAdd);
            System.out.println("Added:" + blockToAdd.getName());
        }
    }

    public void deleteBlock(Block blockToRemove) {
        if (blocks.contains(blockToRemove)) {
            System.out.println("Removed:" + blockToRemove.getName());
            blocks.remove(blockToRemove);
        } else {
            System.out.println("No such block was found!");
        }
    }

    public List<Exhibit> getAllExhibits() {
        List<Exhibit> lst = new ArrayList<>();
        for (Block block : this.blocks) {
            lst.addAll(block.getExhibits());
        }
        return lst;
    }
}
