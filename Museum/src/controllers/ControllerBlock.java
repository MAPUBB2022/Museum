package controllers;

import classes.Block;
import classes.Client;
import repository.inmemory.BlockRepositoryMemory;
import repository.inmemory.ClientRepositoryMemory;
import repository.inmemory.ExhibitRepositoryMemory;
import views.ViewBlock;
import views.ViewClient;

public class ControllerBlock {

    public static void add(String blockName) {
        BlockRepositoryMemory.getInstance().add(new Block(blockName));
    }

    public static void delete(String blockId) {
        BlockRepositoryMemory.getInstance().remove(blockId);
    }

    public static void update(String blockId, String blockName) {
        if(!BlockRepositoryMemory.getInstance().checkIfExists(blockId))
        {
            System.out.println("This block does not exist!");
            return;
        }
        Block b = new Block(blockName);
        b.setArtists(BlockRepositoryMemory.getInstance().findById(blockId).getArtists());
        b.setExhibits(BlockRepositoryMemory.getInstance().findById(blockId).getExhibits());
        b.setMovements(BlockRepositoryMemory.getInstance().findById(blockId).getMovements());
        BlockRepositoryMemory.getInstance().update(blockId, b);
    }

    public static void addEx(String blockId, String exId) {
        if(!BlockRepositoryMemory.getInstance().checkIfExists(blockId))
        {
            System.out.println("This block does not exist!");
            return;
        }
        Block b = BlockRepositoryMemory.getInstance().findById(blockId);
        b.addExhibit(ExhibitRepositoryMemory.getInstance().findById(exId));
        BlockRepositoryMemory.getInstance().update(blockId, b);
    }

    public static void remEx(String blockId, String exId) {
        if(!BlockRepositoryMemory.getInstance().checkIfExists(blockId))
        {
            System.out.println("This block does not exist!");
            return;
        }
        Block b = BlockRepositoryMemory.getInstance().findById(blockId);
        b.deleteExhibit(ExhibitRepositoryMemory.getInstance().findById(exId));
        BlockRepositoryMemory.getInstance().update(blockId, b);
    }

    public static void display(String blockId) {
        Block b = BlockRepositoryMemory.getInstance().findById(blockId);
        if (b == null) {
            System.out.println("This block does not exist, try again using a different ID");
            return;
        }
        ViewBlock.displayBlock(b);
    }
}
