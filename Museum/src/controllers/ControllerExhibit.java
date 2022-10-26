package controllers;

import classes.Exhibit;
import repository.Repository;

import java.util.ArrayList;

public class ControllerExhibit {
    static public boolean existsInExhibit(String id) {
        ArrayList<Exhibit> exhibits = Repository.getInstance().getExhibits();
        for (Exhibit e : exhibits) {
            if (id.equals(e.getId())) {
                return true;
            }
        }
        return false;
    }
}
