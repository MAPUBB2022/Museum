package repository.inmemory;

import classes.Block;
import classes.Exhibit;
import classes.Painting;
import classes.Statue;
import repository.ICrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockRepositoryMemory implements ICrudRepository<String, Block> {
    private static BlockRepositoryMemory single_instance = null;
    private final List<Block> allBlocks = new ArrayList<>();

    public static BlockRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new BlockRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate() {
        BlockRepositoryMemory.getInstance().allBlocks.add(new Block("Art Gallery1"));
        BlockRepositoryMemory.getInstance().allBlocks.add(new Block("Art Gallery2"));
        BlockRepositoryMemory.getInstance().allBlocks.add(new Block("Art Gallery3"));
    }

    @Override
    public void add(Block entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The block already exists, please try again using an new one!");
            return;
        }
        allBlocks.add(entity);
        System.out.println("Added block!");
    }

    @Override
    public void remove(String s) {
        if (!checkIfExists(s)) {
            System.out.println("The block does not exist, please try again using an existing one!");
            return;
        }
        this.allBlocks.removeIf(c -> Objects.equals(s, c.getId()));
        System.out.println("The block has been removed!");
    }

    @Override
    public void update(String s, Block newEntity) {
        if (!checkIfExists(s)) {
            System.out.println("The block does not exist, please try again using an existing one!");
            return;
        }
        Block newBlock = this.findById(s);
        for (Block oldBlock : this.allBlocks) {
            if (oldBlock.getId().equals(s)) {
                oldBlock.setName(newBlock.getName());
                oldBlock.setExhibits(newBlock.getExhibits());
                newBlock.setMovements(new ArrayList<>());
                newBlock.setArtists(new ArrayList<>());
                for (Exhibit exhibit : oldBlock.getExhibits()) {
                    if (exhibit instanceof Painting) {
                        if (!newBlock.getMovements().contains(((Painting) exhibit).getArtMovement())) {
                            newBlock.addMovement(((Painting) exhibit).getArtMovement());
                        }
                        if (!newBlock.getArtists().contains(((Painting) exhibit).getPainter())) {
                            newBlock.addArtist(((Painting) exhibit).getPainter());
                        }
                    } else if (exhibit instanceof Statue) {
                        if (!newBlock.getMovements().contains(((Statue) exhibit).getArtMovement())) {
                            newBlock.addMovement((((Statue) exhibit).getArtMovement()));
                        }
                        if (!newBlock.getArtists().contains(((Statue) exhibit).getSculptor())) {
                            newBlock.addArtist(((Statue) exhibit).getSculptor());
                        }
                    }
                }
                oldBlock.setMovements(newBlock.getMovements());
                oldBlock.setArtists(newBlock.getArtists());
                System.out.println("The block has been updated!");
                return;
            }
        }
    }

    @Override
    public void updateName(String s, String newId) {
        // This method doesn't work for classes with id
    }

    @Override
    public Block findById(String s) {
        for (Block b : allBlocks) {
            if (s.equals(b.getId())) {
                return b;
            }
        }
        return null;
    }

    public Block findByName(String s) {
        for (Block b : allBlocks) {
            if (s.equals(b.getName())) {
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean checkIfExists(String s) {
        for (Block b : allBlocks) {
            if (s.equals(b.getId())) {
                return true;
            }
        }
        return false;
    }
}
