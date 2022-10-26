package controllers;

import classes.Exhibit;
import java.util.ArrayList;
import repository.Repository;

public class ControllerExhibit {
    static public boolean existsInExhibit (String id) {
        ArrayList<Exhibit> exhibits =  Repository.getInstance().getExhibits();
        for(Exhibit e: exhibits)
        {
            if(id.equals(e.getId())){
                return true;
            }
        }
        return false;
    }
}
