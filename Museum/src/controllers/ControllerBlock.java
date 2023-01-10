package controllers;

import classes.*;
import repository.database.*;
import views.ViewBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ControllerBlock {

    public static void add(String blockName, String museumName) throws ClassNotFoundException {
        Museum museum = MuseumDB.getInstance().findById(museumName);
        if (museum == null) {
            System.out.println("Museum does not exist!");
            return;
        }
        BlockDB.getInstance().add(new Block(blockName, museum));
        MuseumDB.getInstance().findById(museumName).addBlock(BlockDB.getInstance().findByName(blockName));
    }

    public static void delete(String blockId) throws ClassNotFoundException {
        Block oldBlock = BlockDB.getInstance().findById(blockId);
        if (oldBlock.getMuseum() == null) {
            Museum museumTheBlockWasIn = oldBlock.getMuseum();
            museumTheBlockWasIn.deleteBlock(oldBlock);
        }
        BlockDB.getInstance().remove(blockId);

    }

    public static void update(String blockId, String blockName) throws ClassNotFoundException {
        if (!BlockDB.getInstance().checkIfExists(blockId)) {
            System.out.println("This block does not exist!");
            return;
        }
        Block b = BlockDB.getInstance().findById(blockId);
        b.setName(blockName);
        BlockDB.getInstance().updateName(blockId, blockName);
    }

    public static void addEx(String blockId, String exId) throws ClassNotFoundException {
        if (!BlockDB.getInstance().checkIfExists(blockId)) {
            System.out.println("This block does not exist!");
            return;
        }
        if (!ExhibitDB.getInstance().checkIfExists(exId)) {
            System.out.println("This exhibit does not exist!");
            return;
        }

        Exhibit theExhibit = ExhibitDB.getInstance().findById(exId);

        if (!(theExhibit.getLocation() == null)) {
            System.out.println("The exhibit already exists in a Block");
            return;
        }

        Block b = BlockDB.getInstance().findById(blockId);
        b.addExhibit(ExhibitDB.getInstance().findById(exId));
        Exhibit exToAdd = ExhibitDB.getInstance().findById(exId);
        ExhibitDB.getInstance().changeLocation(blockId, exToAdd);

        changeBlockLists(b);
    }

    public static void changeBlockLists(Block b) {
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
    }

    public static void remEx(String blockId, String exId) throws ClassNotFoundException {
        if (!BlockDB.getInstance().checkIfExists(blockId)) {
            System.out.println("This block does not exist!");
            return;
        }
        if (!ExhibitDB.getInstance().checkIfExists(exId)) {
            System.out.println("This exhibit does not exist!");
            return;
        }

        Exhibit theExhibit = ExhibitDB.getInstance().findById(exId);
        if (theExhibit.getLocation() == null) {
            System.out.println("The exhibit is in no block at the moment!");
            return;
        }
        if (Objects.equals(theExhibit.getLocation().getName(), "null")) {
            System.out.println("The exhibit is in no block at the moment!");
            return;
        }


        Block b = BlockDB.getInstance().findById(blockId);
        b.deleteExhibit(ExhibitDB.getInstance().findById(exId));
        ExhibitDB.getInstance().deleteBlock(exId);
        ExhibitDB.getInstance().findById(exId).setLocation(null);
    }

    public static void display(String blockId) throws ClassNotFoundException {
        Block b = BlockDB.getInstance().findById(blockId);
        if (b == null) {
            System.out.println("This block does not exist, try again using a different ID");
            return;
        }
        ViewBlock.displayBlock(b);
    }

    public static List<Block> sort() throws ClassNotFoundException {
        List<Block> lb1 = BlockDB.getInstance().getAllBlocks();
        Collections.sort(lb1);
        for (Block b : lb1) {
            ControllerBlock.display(b.getId());
        }
        return lb1;
    }

    public static List<Block> filterByExhibit(int minNumberExhibit) throws ClassNotFoundException {
        List<Block> filteredBlocks = new java.util.ArrayList<>(Collections.emptyList());
        for (Block b : Collections.unmodifiableList(BlockDB.getInstance().getAllBlocks())) {
            if (b.getExhibits().size() > minNumberExhibit) {
                display(b.getId());
                filteredBlocks.add(b);
            }
        }
        return filteredBlocks;
    }

    public static List<Block> filterByArtMovement(String artMovementName) throws ClassNotFoundException {
        ArtMovement artMovementToCheck = ArtMovementDB.getInstance().findByName(artMovementName);
        List<Block> filteredBlocks = new java.util.ArrayList<>(Collections.emptyList());
        for (Block b : Collections.unmodifiableList(BlockDB.getInstance().getAllBlocks())) {
            if (b.getMovements().contains(artMovementToCheck)) {
                display(b.getId());
                filteredBlocks.add(b);
            }
        }
        return filteredBlocks;
    }

    public static List<Block> filterByArtist(String artistName) throws ClassNotFoundException {
        Artist artistToCheck = ArtistDB.getInstance().findByName(artistName);
        List<Block> filteredBlocks = new java.util.ArrayList<>(Collections.emptyList());
        for (Block b : Collections.unmodifiableList(BlockDB.getInstance().getAllBlocks())) {
            if (b.getArtists().contains(artistToCheck)) {
                display(b.getId());
                filteredBlocks.add(b);
            }
        }
        return filteredBlocks;
    }

    public static void multipleFilters(int minNumberExhibit, String artMovementName, String artistName) {
    }
}
