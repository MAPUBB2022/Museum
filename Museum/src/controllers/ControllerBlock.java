package controllers;

import classes.Block;
import repository.Repository;

import java.util.ArrayList;

public class ControllerBlock {
    static public boolean existsInBlocks(String id) {
        ArrayList<Block> blocks = Repository.getInstance().getBlocks();

        for (Block b : blocks) {
            if (id.equals(b.getId())) {
                return true;
            }
        }
        return false;
    }
}
