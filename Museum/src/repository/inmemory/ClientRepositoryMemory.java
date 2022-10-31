package repository.inmemory;

import classes.Client;
import classes.Ticket;
import repository.ICrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientRepositoryMemory implements ICrudRepository<String, Client> {
    private static ClientRepositoryMemory single_instance = null;
    private final List<Client> allClients = new ArrayList<>();

    public static ClientRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new ClientRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate() {
        ClientRepositoryMemory.getInstance().add(new Client("Oprisor Raul"));
        ClientRepositoryMemory.getInstance().add(new Client("Joita Razvan"));
        ClientRepositoryMemory.getInstance().add(new Client("Moldovan Andrei"));
    }

    @Override
    public void add(Client entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The client already exists, please try again using an new one!");
            return;
        }
        allClients.add(entity);
        System.out.println("Added client!");
    }

    @Override
    public void remove(String s) {
        if(!checkIfExists(s))
        {
            System.out.println("The client does not exist, please try again using an existing one!");
            return;
        }
        this.allClients.removeIf(c -> Objects.equals(s, c.getId()));
        System.out.println("The client has been removed!");
    }

    @Override
    public void update(String s, Client newEntity) {
        if(!checkIfExists(s))
        {
            System.out.println("The client does not exist, please try again using an existing one!");
            return;
        }
        Client newClient = this.findById(s);
        for(Client oldClient : this.allClients)
        {
            if (oldClient.getId().equals(s))
            {
                oldClient.setFavorites(newClient.getFavorites());
                oldClient.setName(newClient.getName());
                System.out.println("The client has been updated!");
                return;
            }
        }
    }

    @Override
    public void updateName(String s, String newId) {
        // This method doesn't work for classes with id
    }

    @Override
    public Client findById(String s) {
        for (Client c : allClients) {
            if (s.equals(c.getId())) {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean checkIfExists(String s) {
        for (Client c : allClients) {
            if (s.equals(c.getId())) {
                return true;
            }
        }
        return false;
    }
}
