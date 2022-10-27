package repository.inmemory;

import classes.Block;
import repository.ICrudRepository;

import java.util.ArrayList;
import java.util.List;

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

    }

    @Override
    public void update(String s, Block newEntity) {

    }

    @Override
    public void updateName(String s, String newId) {

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
