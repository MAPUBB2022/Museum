package controllers;
import classes.*;
import repository.Repository;
import java.util.ArrayList;


public class ControllerClient {
    static public boolean existsInClients(String id) {
        ArrayList<Client> clients =  Repository.getInstance().getClients();
        for(Client c: clients)
        {
            if(id.equals(c.getId())){
                return true;
            }
        }
        return false;
    }
}
