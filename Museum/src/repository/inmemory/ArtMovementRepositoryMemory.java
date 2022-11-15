package repository.inmemory;

import classes.ArtMovement;
import repository.ICrudRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ArtMovementRepositoryMemory implements ICrudRepository<String, ArtMovement> {

    private static ArtMovementRepositoryMemory single_instance = null;
    private final ArrayList<ArtMovement> allArtMovements = new ArrayList<>();

    public static ArtMovementRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new ArtMovementRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate() {
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        Date startedDate, endedDate;
        try {
            startedDate = format.parse("01-01-1300");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            endedDate = format.parse("31-12-1699");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ArtMovementRepositoryMemory.getInstance().add(new ArtMovement("Renaissance", startedDate, endedDate));
    }

    @Override
    public void add(ArtMovement entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The art movement already exists, please try again using an new one!");
            return;
        }
        allArtMovements.add(entity);
        System.out.println("Added art movement!");
    }

    @Override
    public void remove(String s) {
        boolean found = false;
        ArtMovement amToDelete = null;
        for (ArtMovement am : allArtMovements) {
            if (am.getName().equals(s)) {
                found = true;
                amToDelete = am;
            }
        }
        if (found) {
            allArtMovements.remove(amToDelete);
            System.out.println("The art movement has been removed!");
            return;
        }
        System.out.println("The art movement does not exist, please try again using an existing one!");
    }

    @Override
    public void update(String s, ArtMovement newEntity) {
        boolean found = false;
        ArtMovement artMovementToUpdate = null;
        for (ArtMovement am : allArtMovements) {
            if (am.getName().equals(s)) {
                found = true;
                artMovementToUpdate = am;
            }
        }
        if (found) {
            allArtMovements.remove(artMovementToUpdate);
            allArtMovements.add(newEntity);
            return;
        }
        System.out.println("The art movement you want to update does not exist!");
    }

    @Override
    public void updateName(String s, String newId) {
        boolean found = false;
        ArtMovement artMovementToUpdate = null;
        for (ArtMovement am : allArtMovements) {
            if (am.getName().equals(s)) {
                found = true;
                artMovementToUpdate = am;
            }
        }
        if (found) {
            allArtMovements.remove(artMovementToUpdate);
            artMovementToUpdate.setName(newId);
            allArtMovements.add(artMovementToUpdate);
            System.out.println("Updated name!");
            return;
        }
        System.out.println("The art movement you want to update does not exist!");
    }

    @Override
    public ArtMovement findById(String s) {
        for (ArtMovement am : allArtMovements) {
            if (s.equals(am.getId())) {
                return am;
            }
        }
        return null;
    }

    public ArtMovement findByName(String s) {
        for (ArtMovement am : allArtMovements) {
            if (s.equals(am.getName())) {
                return am;
            }
        }
        return null;
    }

    @Override
    public boolean checkIfExists(String s) {
        for (ArtMovement a : allArtMovements) {
            if (s.equals(a.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfExistsByName(String s) {
        for (ArtMovement a : allArtMovements) {
            if (s.equals(a.getName())) {
                return true;
            }
        }
        return false;
    }

    public void updateDateStarted(String name, Date newDate) {
        for (ArtMovement amToUpdate : allArtMovements) {
            if (name.equals(amToUpdate.getName())) {
                allArtMovements.remove(amToUpdate);
                amToUpdate.setStartDate(newDate);
                allArtMovements.add(amToUpdate);
                System.out.println("Updated date!");
                return;
            }
        }
        System.out.println("Wrong name, please try again using an existing one!");
    }

    public void updateDateEnded(String name, Date newDate) {
        for (ArtMovement amToUpdate : allArtMovements) {
            if (name.equals(amToUpdate.getName())) {
                allArtMovements.remove(amToUpdate);
                amToUpdate.setEndDate(newDate);
                allArtMovements.add(amToUpdate);
                System.out.println("Updated date!");
                return;
            }
        }
        System.out.println("Wrong name, please try again using an existing one!");
    }

    public ArrayList<ArtMovement> getList() {
        return allArtMovements;
    }
}
