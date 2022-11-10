package tests;

import classes.Artist;
import classes.Block;
import classes.Exhibit;
import classes.Painting;
import controllers.ControllerBlock;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import repository.inmemory.BlockRepositoryMemory;
import repository.inmemory.ExhibitRepositoryMemory;

import java.util.ArrayList;
import java.util.List;

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
    @Order(3)
    void update() {
        Block b1 = BlockRepositoryMemory.getInstance().findById("B1004");
        b1.setName("BlockEast");
        BlockRepositoryMemory.getInstance().update("B1004", b1);
        assertEquals(BlockRepositoryMemory.getInstance().findById("B1004").getName(), "BlockEast");
    }

    @Test
    @Order(4)
    void addEx() {
        ControllerBlock.addEx("B1004", "E1000");
        List<Exhibit> le1 = new ArrayList<>();
        le1.add(ExhibitRepositoryMemory.getInstance().findById("E1000"));
        assertEquals(BlockRepositoryMemory.getInstance().findById("B1004").getExhibits(), le1);

        ControllerBlock.addEx("B1004", "E1000");
        assertEquals(BlockRepositoryMemory.getInstance().findById("B1004").getExhibits(), le1);

        List<Artist> la1 = new ArrayList<>();
        Painting p = (Painting) ExhibitRepositoryMemory.getInstance().findById("E1000");
        la1.add(p.getPainter());

        assertEquals(BlockRepositoryMemory.getInstance().findById("B1004").getArtists(), la1);

    }

    @Test
    @Order(5)
    void display() {
        ControllerBlock.display("B1004");
        ControllerBlock.display("B1100");
    }

    @Test
    @Order(6)
    void remEx() {
        ControllerBlock.remEx("B1004", "E1000");

        List<Exhibit> le1 = new ArrayList<>();
        List<Artist> la1 = new ArrayList<>();
        assertEquals(BlockRepositoryMemory.getInstance().findById("B1004").getExhibits(), le1);
        assertEquals(BlockRepositoryMemory.getInstance().findById("B1004").getArtists(), la1);

        ControllerBlock.remEx("B1004", "E1000");
    }
}