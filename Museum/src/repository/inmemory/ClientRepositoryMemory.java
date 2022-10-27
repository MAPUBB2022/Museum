package repository.inmemory;

import classes.Client;
import repository.ICrudRepository;

import java.util.ArrayList;
import java.util.List;

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

    }

    @Override
    public void update(String s, Client newEntity) {

    }

    @Override
    public void updateName(String s, String newId) {

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
