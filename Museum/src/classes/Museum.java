package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        boolean exists = false;
        for (Client c : this.clients) {
            if (Objects.equals(c.getId(), clientToAdd.getId())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            clients.add(clientToAdd);
            System.out.println("Added:" + clientToAdd.getName());
        } else {
            System.out.println("Client already exists in museum");
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
