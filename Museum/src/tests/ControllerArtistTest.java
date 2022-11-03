package tests;

import classes.Artist;
import controllers.ControllerArtist;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import repository.inmemory.ArtistRepositoryMemory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
                throw new RuntimeException(e);
            }
            try {
                dateDiedNicu = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
                throw new RuntimeException(e);
            }
            try {
                dateDiedNicu = format.parse("2-06-1519");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ControllerArtist.add("Nicu", dateBornNicu, dateDiedNicu);
        }
        ControllerArtist.delete("A1002");
        ControllerArtist.delete("NonExistent"); // does not exist
    }

}