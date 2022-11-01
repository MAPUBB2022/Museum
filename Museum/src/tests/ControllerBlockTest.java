package tests;

import controllers.ControllerBlock;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import repository.inmemory.BlockRepositoryMemory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(OrderAnnotation.class)
class ControllerBlockTest {

    @Test
    @Order(1)
    void add() {
        ControllerBlock.add("BlockVest"); // Adds new Block
        ControllerBlock.add("BlockVest"); // Can add block with the same name because it has an ID
        assertEquals(BlockRepositoryMemory.getInstance().findById("B1003").getName(), BlockRepositoryMemory.getInstance().findById("B1004").getName());
    }

    @Test
    @Order(2)
    void delete() {
        ControllerBlock.delete("B1003"); // Successfully deletes block
        ControllerBlock.delete("B1005"); // Can't delete block that doesn't exist
    }

    @Test
    void update() {
    }

    @Test
    void addEx() {
    }

    @Test
    void remEx() {
    }

    @Test
    void display() {
    }
}