package tests;

import classes.ArtMovement;
import classes.Artist;
import controllers.ControllerArtMovement;
import controllers.ControllerArtist;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import repository.inmemory.ArtMovementRepositoryMemory;
import repository.inmemory.ArtistRepositoryMemory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@TestMethodOrder(OrderAnnotation.class)
class ControllerArtistTest {

    @org.junit.jupiter.api.Test
    void add() {
        if (!ArtistRepositoryMemory.getInstance().checkIfExists("A1002")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateBornNicu, dateDiedNicu;
            try {
                dateBornNicu = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new AssertionError();
            }
            try {
                dateDiedNicu = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new AssertionError();
            }
            ControllerArtist.add("Nicu", dateBornNicu, dateDiedNicu);
            assert true : (ArtistRepositoryMemory.getInstance().checkIfExists("A1002"));
        }
    }

    @org.junit.jupiter.api.Test
    void presentSelf() {
        ControllerArtist.presentSelf("A1000");
        ControllerArtist.presentSelf("NonExistent");
    }

    @org.junit.jupiter.api.Test
    void isFamous() {
        ControllerArtist.presentSelf("A1000");
        ControllerArtist.presentSelf("NonExistent");
    }

    @org.junit.jupiter.api.Test
    void changeName() {
        Artist artist = ArtistRepositoryMemory.getInstance().findById("A1000");
        ControllerArtist.changeName(artist.getId(), "Test Artist");
        Artist artistAfterChange = ArtistRepositoryMemory.getInstance().findById("A1000");
        String updatedName = artistAfterChange.getName();
        assert (updatedName.equals("Test Artist"));
        ControllerArtist.changeName(artist.getId(), "Leonardo Da Vinci");
        Artist artistAfterChange2 = ArtistRepositoryMemory.getInstance().findById("A1000");
        String updatedName2 = artistAfterChange2.getName();
        assert (updatedName2.equals("Leonardo Da Vinci"));
    }

    @org.junit.jupiter.api.Test
    void updateDateBorn() {
        Artist artist = ArtistRepositoryMemory.getInstance().findById("A1000");
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        Date dateUpdate;
        try {
            dateUpdate = format.parse("15-04-1452");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        ControllerArtist.updateDateBorn(artist.getId(), dateUpdate);
        artist = ArtistRepositoryMemory.getInstance().findById("A1000");
        assert (artist.getBirthDate()).equals(dateUpdate);
    }

    @org.junit.jupiter.api.Test
    void updateDateDied() {
        Artist artist = ArtistRepositoryMemory.getInstance().findById("A1000");
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        Date dateUpdate;
        try {
            dateUpdate = format.parse("15-04-1469");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        ControllerArtist.updateDateDied(artist.getId(), dateUpdate);
        artist = ArtistRepositoryMemory.getInstance().findById("A1000");
        assert (artist.getDeathDate()).equals(dateUpdate);
    }

    @org.junit.jupiter.api.Test
    void addArtMovement() {
        Artist artist = ArtistRepositoryMemory.getInstance().findById("A1000");
        ControllerArtist.addArtMovement(artist.getId(), "V1000");
        ControllerArtist.addArtMovement(artist.getId(), "V1000"); // Duplicate
        ControllerArtist.addArtMovement(artist.getId(), "NonExistent");
        ControllerArtist.addArtMovement("NonExistent", "NonExistent");
    }

    @org.junit.jupiter.api.Test
    void deleteArtMovement() {
        Artist artist = ArtistRepositoryMemory.getInstance().findById("A1000");
        ControllerArtist.addArtMovement(artist.getId(), "V1000"); // In case it does not exist=> It will be added, else it will display Duplicate
        ControllerArtist.deleteArtMovement(artist.getId(), "V1000");
        ControllerArtist.deleteArtMovement(artist.getId(), "NonExistent");
        ControllerArtist.deleteArtMovement("NonExistent", "NonExistent");
    }

    @org.junit.jupiter.api.Test
    void addExhibit() {
        Artist artist = ArtistRepositoryMemory.getInstance().findById("A1000");
        ControllerArtist.addExhibit(artist.getId(), "E1001");
        ControllerArtist.addExhibit(artist.getId(), "E1001"); // Duplicate
        ControllerArtist.addExhibit(artist.getId(), "NonExistent");
        ControllerArtist.addExhibit("NonExistent", "NonExistent");
    }

    @org.junit.jupiter.api.Test
    void deleteExhibit() {
        Artist artist = ArtistRepositoryMemory.getInstance().findById("A1000");
        ControllerArtist.addExhibit(artist.getId(), "E1001"); // In case it does not exist=> It will be added, else it will display Duplicate
        ControllerArtist.deleteExhibit(artist.getId(), "E1001");
        ControllerArtist.deleteExhibit(artist.getId(), "NonExistent");
        ControllerArtist.deleteExhibit("NonExistent", "NonExistent");
    }

    @org.junit.jupiter.api.Test
    void display() {
        ControllerArtist.display("A1000");
        ControllerArtist.display("NonExistent");
    }


    @org.junit.jupiter.api.Test
    void delete() {
        // In case the add does not run before to delete:
        if (!ArtistRepositoryMemory.getInstance().checkIfExists("A1002")) {
            DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
            Date dateBornNicu, dateDiedNicu;
            try {
                dateBornNicu = format.parse("15-04-1452");
            } catch (ParseException e) {
                throw new AssertionError();
            }
            try {
                dateDiedNicu = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new AssertionError();
            }
            ControllerArtist.add("Nicu", dateBornNicu, dateDiedNicu);
        }
        ControllerArtist.delete("A1002");
        ControllerArtist.delete("NonExistent"); // does not exist
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
        ControllerArtist.add("Primul Artist", dateFirst, null);
        ControllerArtist.add("Ultimul Artist", dateLast, null);
        List<Artist> listaSortata = ControllerArtist.sort();
        if (!Objects.equals(listaSortata.get(0).getName(), "Primul Artist")) {
            throw new AssertionError();
        }
        if (!Objects.equals(listaSortata.get(listaSortata.size() - 1).getName(), "Ultimul Artist")) {
            throw new AssertionError();
        }
    }

    @Test
    void filterByExhibit() {
        int numberOfPersonsWithTwoExhibits = ControllerArtist.filterByExhibit(2).size();
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        Date dateArtistulMare;
        try {
            dateArtistulMare = format.parse("15-04-1452");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        ControllerArtist.add("Masa Artist", dateArtistulMare, null);
        Artist artist = ArtistRepositoryMemory.getInstance().findByName("Masa Artist");
        ControllerArtist.addExhibit(artist.getId(), "E1000");
        ControllerArtist.addExhibit(artist.getId(), "E1001");
        ControllerArtist.addExhibit(artist.getId(), "E1002");
        int numberOfPersonsWithTwoExhibitsAfter = ControllerArtist.filterByExhibit(2).size();
        if (numberOfPersonsWithTwoExhibits != numberOfPersonsWithTwoExhibitsAfter - 1) {
            throw new AssertionError();
        }
    }

    @Test
    void filterByBorn() {
        Date dateArtistulMare, dateForChecking, dateForTryingToThrowOff;
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
            dateArtistulMare = format.parse("03-04-2000");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        int sizeBornBefore = ControllerArtist.filterByBorn(dateForChecking).size();

        ControllerArtist.add("Mare Artist", dateArtistulMare, null);
        ControllerArtist.add("Mic Artist", dateForTryingToThrowOff, null);

        int sizeBornAfter = ControllerArtist.filterByBorn(dateForChecking).size();
        if (sizeBornBefore != sizeBornAfter - 1) {
            throw new AssertionError();
        }
    }

    @Test
    void filterByDead() {
        int sizeDeadBefore = ControllerArtist.filterByDead().size();
        Date dateArtistulMare;
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        try {
            dateArtistulMare = format.parse("15-04-1452");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        ControllerArtist.add("Marele Artist", dateArtistulMare, null);
        int sizeDeadAfter = ControllerArtist.filterByDead().size();
        if (sizeDeadBefore != sizeDeadAfter - 1) {
            throw new AssertionError();
        }
    }

    @Test
    void filterByArtMovement() {
        Date dateArtist, startDateArtMovement, endDateArtMovement;
        DateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        try {
            dateArtist = format.parse("15-04-1452");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        try {
            startDateArtMovement = format.parse("15-04-1445");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        try {
            endDateArtMovement = format.parse("07-03-1497");
        } catch (ParseException e) {
            throw new AssertionError();
        }
        ControllerArtist.add("Copac", dateArtist, null);
        ControllerArtMovement.add("Frunza", startDateArtMovement, endDateArtMovement);
        if (ControllerArtist.filterByArtMovement("Frunza").size() != 0) {
            throw new AssertionError();
        }
        Artist idArtist = ArtistRepositoryMemory.getInstance().findByName("Copac");
        ArtMovement idArtMovement = ArtMovementRepositoryMemory.getInstance().findByName("Frunza");
        ControllerArtist.addArtMovement(idArtist.getId(), idArtMovement.getId());
        if (ControllerArtist.filterByArtMovement("Frunza").size() != 1) {
            throw new AssertionError();
        }
    }
}