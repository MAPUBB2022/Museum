package tests;

import controllers.ControllerExhibit;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import repository.inmemory.ExhibitRepositoryMemory;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerExhibitTest {

    @Test
    @Order(1)
    void addArtifact() {
        ControllerExhibit.addArtifact("Art1", new Date(10,10,1000), "B1001", "Dacia", 130000);
        assertEquals(ExhibitRepositoryMemory.getInstance().findById("E1004").getName(), "Art1");
    }

    @Test
    @Order(2)
    void addPainting() {
        ControllerExhibit.addPainting("Paint1", new Date(10,10,1000), "B1001", "A1001", "V1000", 1000500);
        assertEquals(ExhibitRepositoryMemory.getInstance().findById("E1005").getName(), "Paint1");
    }

    @Test
    @Order(3)
    void addStatue() {
        ControllerExhibit.addStatue("Stat1", new Date(10,10,1000), "B1001", "A1000", "V1000", 3800000);
        assertEquals(ExhibitRepositoryMemory.getInstance().findById("E1006").getName(), "Stat1");
    }

    @Test
    @Order(4)
    void updatePrice() {
        ControllerExhibit.updatePrice("E1005", 800000);
        assertEquals(ExhibitRepositoryMemory.getInstance().findById("E1005").getPrice(), 800000);
    }

    @Test
    @Order(5)
    void updateDate() {
        Date d = new Date(2,2,500);
        ControllerExhibit.updateDate("E1006", d);
        assertEquals(ExhibitRepositoryMemory.getInstance().findById("E1006").getCreation(), d);
    }

    @Test
    @Order(6)
    void updateName() {
        ControllerExhibit.updateName("E1004", "Art2");
        assertEquals(ExhibitRepositoryMemory.getInstance().findById("E1004").getName(), "Art2");
    }

    @Test
    @Order(7)
    void display() {
        ControllerExhibit.display("E1005");
        ControllerExhibit.display("E1100");
    }

    @Test
    @Order(8)
    void delete() {
        ControllerExhibit.delete("E1005");
        ControllerExhibit.delete("E1100");
    }

}