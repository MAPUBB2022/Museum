package controllers;

import classes.*;
import repository.inmemory.ArtMovementRepositoryMemory;
import repository.inmemory.ArtistRepositoryMemory;
import repository.inmemory.BlockRepositoryMemory;
import repository.inmemory.ExhibitRepositoryMemory;
import views.ViewBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControllerBlock {

    public static void add(String blockName) {
        BlockRepositoryMemory.getInstance().add(new Block(blockName));
    }

    public static void delete(String blockId) {
        BlockRepositoryMemory.getInstance().remove(blockId);
    }

    public static void update(String blockId, String blockName) {
        if (!BlockRepositoryMemory.getInstance().checkIfExists(blockId)) {
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
        if (!BlockRepositoryMemory.getInstance().checkIfExists(blockId)) {
            System.out.println("This block does not exist!");
            return;
        }
        Block b = BlockRepositoryMemory.getInstance().findById(blockId);
        b.addExhibit(ExhibitRepositoryMemory.getInstance().findById(exId));

        List<Artist> la1 = new ArrayList<>();
        for (int i = 0; i < b.getExhibits().size(); i++) {
            if (b.getExhibits().get(i) instanceof Painting) {
                if (!la1.contains(((Painting) b.getExhibits().get(i)).getPainter())) {
                    la1.add(((Painting) b.getExhibits().get(i)).getPainter());
                }
            } else if (b.getExhibits().get(i) instanceof Statue) {
                if (!la1.contains(((Statue) b.getExhibits().get(i)).getSculptor())) {
                    la1.add(((Statue) b.getExhibits().get(i)).getSculptor());
                }
            }
        }
        List<ArtMovement> lam1 = new ArrayList<>();
        for (int i = 0; i < b.getExhibits().size(); i++) {
            if (b.getExhibits().get(i) instanceof Painting) {
                if (!lam1.contains(((Painting) b.getExhibits().get(i)).getArtMovement())) {
                    lam1.add(((Painting) b.getExhibits().get(i)).getArtMovement());
                }
            } else if (b.getExhibits().get(i) instanceof Statue) {
                if (!lam1.contains(((Statue) b.getExhibits().get(i)).getArtMovement())) {
                    lam1.add(((Statue) b.getExhibits().get(i)).getArtMovement());
                }
            }
        }
        b.setArtists(la1);
        b.setMovements(lam1);
        BlockRepositoryMemory.getInstance().update(blockId, b);
    }

    public static void remEx(String blockId, String exId) {
        if (!BlockRepositoryMemory.getInstance().checkIfExists(blockId)) {
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

    public static List<Block> sort()
    {
        List<Block> lb1 = BlockRepositoryMemory.getInstance().getAllBlocks();
        Collections.sort(lb1);
        for(Block b : lb1)
        {
            ControllerBlock.display(b.getId());
        }
        return lb1;
    }

    public static List<Block> filterByExhibit(int minNumberExhibit) {
        List<Block> filteredBlocks = new java.util.ArrayList<>(Collections.emptyList());
        for (Block b : Collections.unmodifiableList(BlockRepositoryMemory.getInstance().getAllBlocks())) {
            if (b.getExhibits().size() > minNumberExhibit) {
                display(b.getId());
                filteredBlocks.add(b);
            }
        }
        return filteredBlocks;
    }

    public static List<Block> filterByArtMovement(String artMovementName) {
        ArtMovement artMovementToCheck = ArtMovementRepositoryMemory.getInstance().findByName(artMovementName);
        List<Block> filteredBlocks = new java.util.ArrayList<>(Collections.emptyList());
        for (Block b : Collections.unmodifiableList(BlockRepositoryMemory.getInstance().getAllBlocks())) {
            if (b.getMovements().contains(artMovementToCheck)) {
                display(b.getId());
                filteredBlocks.add(b);
            }
        }
        return filteredBlocks;
    }

    public static List<Block> filterByArtist(String artistName) {
        Artist artistToCheck = ArtistRepositoryMemory.getInstance().findByName(artistName);
        List<Block> filteredBlocks = new java.util.ArrayList<>(Collections.emptyList());
        for (Block b : Collections.unmodifiableList(BlockRepositoryMemory.getInstance().getAllBlocks())) {
            if (b.getMovements().contains(artistToCheck)) {
                display(b.getId());
                filteredBlocks.add(b);
            }
        }
        return filteredBlocks;
    }
}
