package Tests;

import classes.Museum;
import repository.inmemory.MuseumRepositoryMemory;

import static org.junit.jupiter.api.Assertions.*;

class ControllerArtistTest {

    @org.junit.jupiter.api.Test
    void add() {
    }

    @org.junit.jupiter.api.Test
    void presentSelf() {
    }

    @org.junit.jupiter.api.Test
    void isFamous() {
    }

    @org.junit.jupiter.api.Test
    void changeName() {
    }

    @org.junit.jupiter.api.Test
    void updateDateBorn() {
    }

    @org.junit.jupiter.api.Test
    void updateDateDied() {
    }

    @org.junit.jupiter.api.Test
    void addArtMovement() {
    }

    @org.junit.jupiter.api.Test
    void deleteArtMovement() {
    }

    @org.junit.jupiter.api.Test
    void addExhibit() {
    }

    @org.junit.jupiter.api.Test
    void deleteExhibit() {
    }

    @org.junit.jupiter.api.Test
    void display() {
    }


    @org.junit.jupiter.api.Test
    void delete() {
            MuseumRepositoryMemory.getInstance().remove("Antipa");
    }

}