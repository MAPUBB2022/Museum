package tests;


import controllers.ControllerBlock;
import controllers.ControllerMuseum;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class ControllerMuseumTest {

    @Test
    @Order(1)
    void add() {
        ControllerMuseum.add("Antipa"); // exists already
        ControllerMuseum.add("Istoric"); // new one
    }

    @Test
    @Order(2)
    void delete() {
        ControllerMuseum.delete("Istoric"); // deletes a museum
        ControllerMuseum.delete("NonExistent"); // does not exist
    }

    @Test
    @Order(3)
    void changeName() {
        ControllerMuseum.changeName("Istoric", "Antipa"); // trying to change to a name that already exists
        ControllerMuseum.changeName("Antipa", "Etnografic"); // updates name
        ControllerMuseum.changeName("NonExistent", "IDK"); // the name does not exist
        ControllerMuseum.changeName("Etnografic", "Antipa"); // updates name
    }

    @Test
    @Order(4)
    void addBlock() {
        ControllerMuseum.addBlock("Antipa", "B1000"); // Adds a new block
        ControllerMuseum.addBlock("Antipa", "RandomBlock"); // Adds an existing block that does not exist
        ControllerMuseum.addBlock("Antipa", "B1000"); // Adds an existing block (duplicate)
    }

    @Test
    @Order(4)
    void deleteBlock() {
        ControllerMuseum.deleteBlock("Antipa", "B1000"); // Removes a block
        ControllerMuseum.deleteBlock("Antipa", "RandomBlock"); // Removes an existing block that does not exist
        ControllerMuseum.deleteBlock("Antipa", "B1000"); // Removes a block that does not exist in the museum
    }

    @Test
    @Order(4)
    void addClient() {
        ControllerMuseum.addClient("Antipa", "C1000"); // Adds a new client
        ControllerMuseum.addClient("Antipa", "C1000"); // Adds a client that already exists in the museum
        ControllerMuseum.addClient("Antipa", "ClientThatDoesNotExistByID"); // Adds a client that already exists in the museum
    }

    @Test
    @Order(5)
    void deleteClient() {
        ControllerMuseum.deleteClient("Antipa", "C1000"); // Removes a client
        ControllerMuseum.deleteClient("Antipa", "C1000"); //  Removes an existing client that does not exist
        ControllerMuseum.deleteClient("Antipa", "ClientThatDoesNotExistByID"); //  Removes a client that does not exist in the museum
    }

    @Test
    @Order(6)
    void displayTotalVisits() {
        ControllerMuseum.addClient("Antipa", "C1000");
        ControllerMuseum.addBlock("Antipa", "B1000");
        ControllerMuseum.displayTotalVisits("Antipa");
        ControllerMuseum.displayTotalVisits("Amtipe"); // does not exist
    }

    @Test
    @Order(7)
    void display() {
        ControllerMuseum.display("Antipa");
        ControllerMuseum.display("Amtipe");
    }

    @Test
    @Order(8)
    void displayAllExhibits() {
        ControllerBlock.addEx("B1002", "E1000");
        ControllerMuseum.addBlock("Antipa", "B1002");
        ControllerMuseum.displayAllExhibits("Antipa");
        ControllerMuseum.displayAllExhibits("Amtipe"); // does not exist
    }
}