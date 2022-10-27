package repository.inmemory;

import classes.Museum;

import java.util.ArrayList;

public class MuseumRepositoryMemory implements repository.ICrudRepository<String, Museum> {
    private static MuseumRepositoryMemory single_instance = null;
    private final ArrayList<Museum> allMuseums = new ArrayList<>();

    public static MuseumRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new MuseumRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate() {
        MuseumRepositoryMemory.getInstance().add(new Museum("Antipa"));
    }

    @Override
    public void add(Museum entity) {
        if (checkIfExists(entity.getName())) {
            System.out.println("The museum already exists, please try again using an new one!");
            return;
        }
        allMuseums.add(entity);
        System.out.println("Added museum!");
    }

    @Override
    public void remove(String s) {
        boolean found = false;
        Museum museumToDelete = null;
        for (Museum m : allMuseums) {
            if (m.getName().equals(s)) {
                found = true;
                museumToDelete = m;
            }
        }
        if (found) {
            allMuseums.remove(museumToDelete);
            System.out.println("The museum has been removed!");
            return;
        }
        System.out.println("The museum does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String s, Museum newEntity) {

        if (checkIfExists(newEntity.getName()) && !(newEntity.getName().equals(s))) {
            System.out.println("The name of the new entity exists!");
            return;
        }

        boolean found = false;
        Museum museumToUpdate = null;
        for (Museum m : allMuseums) {
            if (m.getName().equals(s)) {
                found = true;
                museumToUpdate = m;
            }
        }
        if (found) {
            allMuseums.remove(museumToUpdate);
            allMuseums.add(newEntity);
            return;
        }
        System.out.println("The museum you want to update does not exist!");
    }

    @Override
    public void updateName(String s, String newID) {
        if (checkIfExists(newID)) {
            System.out.println("The name you wanted to change to already exists!");
            return;
        }

        boolean found = false;
        Museum museumToUpdate = null;
        for (Museum m : allMuseums) {
            if (m.getName().equals(s)) {
                found = true;
                museumToUpdate = m;
            }
        }
        if (found) {
            allMuseums.remove(museumToUpdate);
            museumToUpdate.setName(newID);
            allMuseums.add(museumToUpdate);
            System.out.println("Updated name!");
            return;
        }
        System.out.println("The museum you want to update does not exist!");
    }

    @Override
    public Museum findById(String s) {
        for (Museum m : allMuseums) {
            if (s.equals(m.getName())) {
                return m;
            }
        }
        return null;
    }

    @Override
    public boolean checkIfExists(String s) {
        for (Museum m : allMuseums) {
            if (s.equals(m.getName())) {
                return true;
            }
        }
        return false;
    }
}
