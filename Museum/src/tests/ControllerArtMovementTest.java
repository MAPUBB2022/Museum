package tests;

import classes.ArtMovement;
import classes.Artist;
import controllers.ControllerArtMovement;
import controllers.ControllerArtist;
import org.junit.jupiter.api.Test;
import repository.inmemory.ArtMovementRepositoryMemory;
import repository.inmemory.ArtistRepositoryMemory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerArtMovementTest {

    @Test
    void add() {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("Baroque")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateStartedBaroque, dateEndedBaroque;
            try {
                dateStartedBaroque = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                dateEndedBaroque = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ControllerArtMovement.add("Baroque", dateStartedBaroque, dateEndedBaroque);
        }
        assert true : (ArtistRepositoryMemory.getInstance().checkIfExists("V1001"));
    }

    @Test
    void delete() {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("Baroque")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateStartedBaroque, dateEndedBaroque;
            try {
                dateStartedBaroque = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                dateEndedBaroque = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ControllerArtMovement.add("Baroque", dateStartedBaroque, dateEndedBaroque);
        }
        ControllerArtMovement.delete("Baroque");
        ControllerArtMovement.delete("NonExistent"); // does not exist
    }

    @Test
    void changeName() {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("Baroque")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateStartedBaroque, dateEndedBaroque;
            try {
                dateStartedBaroque = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                dateEndedBaroque = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ControllerArtMovement.add("Baroque", dateStartedBaroque, dateEndedBaroque);
        }
        assertTrue(ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("Baroque"));
        ControllerArtMovement.changeName("Baroque", "NewBaroque");
        assertTrue(ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("NewBaroque"));
    }

    @Test
    void updateDateCreated() {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("Baroque")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateStartedBaroque, dateEndedBaroque;
            try {
                dateStartedBaroque = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                dateEndedBaroque = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ControllerArtMovement.add("Baroque", dateStartedBaroque, dateEndedBaroque);
        }
        ArtMovement artMovement = ArtMovementRepositoryMemory.getInstance().findByName("Baroque");
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        Date dateUpdate;
        try {
            dateUpdate = format.parse("01-01-23");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ControllerArtMovement.updateDateCreated(artMovement.getName(), dateUpdate);
        artMovement = ArtMovementRepositoryMemory.getInstance().findByName("Baroque");
        assert (artMovement.getStartDate()).equals(dateUpdate);
    }

    @Test
    void updateDateEnded() {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("Baroque")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateStartedBaroque, dateEndedBaroque;
            try {
                dateStartedBaroque = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                dateEndedBaroque = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ControllerArtMovement.add("Baroque", dateStartedBaroque, dateEndedBaroque);
        }
        ArtMovement artMovement = ArtMovementRepositoryMemory.getInstance().findByName("Baroque");
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        Date dateUpdate;
        try {
            dateUpdate = format.parse("03-03-03");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ControllerArtMovement.updateDateEnded(artMovement.getName(), dateUpdate);
        artMovement = ArtMovementRepositoryMemory.getInstance().findByName("Baroque");
        assert (artMovement.getEndDate()).equals(dateUpdate);
    }

    @Test
    void deleteArtist() {
        ControllerArtMovement.delete("Baroque");
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("Baroque")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateStartedBaroque, dateEndedBaroque;
            try {
                dateStartedBaroque = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                dateEndedBaroque = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ControllerArtMovement.add("Baroque", dateStartedBaroque, dateEndedBaroque);
        }
        ControllerArtMovement.addArtist("Baroque", "A1000");
        ControllerArtMovement.addArtist("Baroque", "A1000");
        ArtMovement artMovement = ArtMovementRepositoryMemory.getInstance().findByName("Baroque");
        assertFalse(artMovement.getArtists().isEmpty());
        ControllerArtMovement.deleteArtist("Baroque", "A1000");
        assertTrue(artMovement.getArtists().isEmpty());
    }

    @Test
    void addArtist() {

        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("Baroque")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateStartedBaroque, dateEndedBaroque;
            try {
                dateStartedBaroque = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                dateEndedBaroque = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ControllerArtMovement.add("Baroque", dateStartedBaroque, dateEndedBaroque);
        }
        ArtMovement artMovement = ArtMovementRepositoryMemory.getInstance().findByName("Baroque");
        assertTrue(artMovement.getArtists().isEmpty());
        ArrayList<Artist> artMovementArtists;
        ControllerArtMovement.addArtist("Baroque", "A1000");
        ControllerArtMovement.addArtist("Baroque", "A1000");
        artMovement = ArtMovementRepositoryMemory.getInstance().findByName("Baroque");
        artMovementArtists = (ArrayList<Artist>) artMovement.getArtists();
        assert (artMovement.getArtists()).equals(artMovementArtists);
        assertFalse(artMovement.getArtists().isEmpty());
    }

    @Test
    void display() {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("Baroque")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateStartedBaroque, dateEndedBaroque;
            try {
                dateStartedBaroque = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                dateEndedBaroque = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ControllerArtMovement.add("Baroque", dateStartedBaroque, dateEndedBaroque);
        }
        ControllerArtMovement.display("Baroque");
        ControllerArtMovement.addArtist("Baroque", "A1000");
        ControllerArtMovement.display("Baroque");
        ControllerArtMovement.display("NonExistent");
    }

    @Test
    void displayRandomArtist() {
        if (!ArtMovementRepositoryMemory.getInstance().checkIfExistsByName("Baroque")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateStartedBaroque, dateEndedBaroque;
            try {
                dateStartedBaroque = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                dateEndedBaroque = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ControllerArtMovement.add("Baroque", dateStartedBaroque, dateEndedBaroque);
        }
        ControllerArtMovement.displayRandomArtist("Baroque");
        ControllerArtMovement.addArtist("Baroque", "A1000");
        ControllerArtMovement.displayRandomArtist("Baroque");
        ControllerArtMovement.displayRandomArtist("NonExistent");

    }

    @Test
    void sort() {
        Date dateFirst, dateLast;
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        try {
            dateFirst = format.parse("01-01-1000");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        try {
            dateLast = format.parse("03-06-3000");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        ControllerArtMovement.add("Primul Art Movement", dateFirst, dateFirst);
        ControllerArtMovement.add("Ultimul Art Movement", dateLast, dateLast);
        List<ArtMovement> listaSortata = ControllerArtMovement.sort();
        if (!Objects.equals(listaSortata.get(0).getName(), "Primul Art Movement")) {
            throw new AssertionError();
        }
        if (!Objects.equals(listaSortata.get(listaSortata.size() - 1).getName(), "Ultimul Art Movement")) {
            throw new AssertionError();
        }
    }

    @Test
    void filterByDate() {
        Date dateArtMovement, dateForChecking, dateForTryingToThrowOff;
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        try {
            dateForChecking = format.parse("01-01-2000");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        try {
            dateForTryingToThrowOff = format.parse("03-06-1978");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        try {
            dateArtMovement = format.parse("03-04-2000");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        int sizeBornBefore = ControllerArtist.filterByBorn(dateForChecking).size();

        ControllerArtMovement.add("Mare Artist", dateArtMovement, dateArtMovement);
        ControllerArtMovement.add("Mic Artist", dateForTryingToThrowOff, dateForTryingToThrowOff);

        int sizeBornAfter = ControllerArtMovement.filterByDate(dateForChecking).size();
        if (sizeBornBefore == sizeBornAfter - 1) {
            throw new AssertionError();
        }
    }
}