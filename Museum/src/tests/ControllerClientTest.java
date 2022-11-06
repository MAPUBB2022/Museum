package tests;

import classes.Block;
import classes.Exhibit;
import controllers.ControllerClient;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import repository.inmemory.BlockRepositoryMemory;
import repository.inmemory.ClientRepositoryMemory;
import repository.inmemory.ExhibitRepositoryMemory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerClientTest {

    @Test
    @Order(1)
    void add() {
        ControllerClient.add("Proba1"); // Adds client
        assertEquals(ClientRepositoryMemory.getInstance().findById("C1003").getName(), "Proba1");

    }

    @Test
    @Order(2)
    void update() {
        ControllerClient.update("C1003", "Proba2");
        assertEquals(ClientRepositoryMemory.getInstance().findById("C1003").getName(), "Proba2");
    }

    @Test
    @Order(3)
    void addVisit() {
        List<Block> lb1 = new ArrayList<>();
        lb1.add(BlockRepositoryMemory.getInstance().findById("B1001"));
        ControllerClient.addVisit("C1003", lb1);
        assertEquals(ClientRepositoryMemory.getInstance().findById("C1003").getVisits().size(), 1);
    }

    @Test
    @Order(4)
    void addFav() {
        ControllerClient.addFav("C1003", "E1001");
        ControllerClient.addFav("C1003", "E1001");
        List<Exhibit> le1 = new ArrayList<>();
        le1.add(ExhibitRepositoryMemory.getInstance().findById("E1001"));
        assertEquals(ClientRepositoryMemory.getInstance().findById("C1003").getFavorites(), le1);
    }

    @Test
    @Order(5)
    void remFav() {
        ControllerClient.remFav("C1003", "E1001");
        ControllerClient.remFav("C1003", "E1001");
        List<Exhibit> le1 = new ArrayList<>();
        assertEquals(ClientRepositoryMemory.getInstance().findById("C1003").getFavorites(), le1);
    }

    @Test
    @Order(6)
    void display() {
        ControllerClient.display("C1003");
        ControllerClient.display("C1100");
    }

    @Test
    @Order(7)
    void delete() {
        ControllerClient.delete("C1003");
        ControllerClient.delete("C1100");
    }
}