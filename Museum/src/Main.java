import classes.*;
import menus.Menu;
import repository.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Museum myMuseum = new Museum("Antipa");
//        Block block1 = new Block("Art Gallery1");
//        Block block2 = new Block("Art Gallery2");
//        Block block3 = new Block("classes.Block 3");
//        myMuseum.addBlock(block1);
//        myMuseum.addBlock(block2);
//        myMuseum.addBlock(block3);
//
        Artist leonardo = new Artist("Leonardo Da Vinci", new Date(1452,Calendar.APRIL,15), new Date(1519,Calendar.JUNE,2));
        Artist michelangelo = new Artist("Michelangelo Buonarroti", new Date(1475,Calendar.MARCH,6), new Date(1564,Calendar.FEBRUARY,18));
        ArtMovement renaissance = new ArtMovement("Renaissance",  new Date(1300, Calendar.JANUARY,1),  new Date(1699,Calendar.DECEMBER,31));
//        Painting paintingMonaLisa = new Painting("Mona Lisa", new Date(1503, Calendar.NOVEMBER,24), block1, leonardo, renaissance, 10);
//        Painting paintingCreationOfAdam = new Painting("Creation of Adam", new java.sql.Date(1510, Calendar.MARCH, 28), block1, michelangelo, renaissance, 0.5);
//        Statue statueEquesterian = new Statue("Equesterian classes.Statue", new java.sql.Date(1510, Calendar.FEBRUARY, 23), block2, leonardo, renaissance, 19);
//
//        myMuseum.addClient(c1);
//        myMuseum.addClient(c2);
//        myMuseum.addClient(c3);
//        List<Block> lb1 = new ArrayList<>();
//        lb1.add(block1);
//        Ticket t1 = new Ticket(lb1);
//        lb1.add(block2);
//        Ticket t2 = new Ticket(lb1);
//        lb1.add(block3);
//        Ticket t3 = new Ticket(lb1);
//        c1.addVisit(t1);
//        c1.addVisit(t2);
//        c3.addVisit(t1);
//        c2.addVisit(t3);
//        c2.addVisit(t2);
//        c2.addVisit(t1);
//        c2.addVisit(t1);
//        c2.addVisit(t3);
//        System.out.println(myMuseum.calcTotalVisits());
//        c1.addVisit(t3);
//        System.out.println(myMuseum.calcTotalVisits());
//
//        renaissance.addArtist(michelangelo);
//        renaissance.addArtist(leonardo);
//        renaissance.displayRandomArtist();
//        michelangelo.presentSelf();
//        michelangelo.isFamous();
//
//        c3.presentSelf();
//        c3.isFamous();
//        c1.steal(paintingMonaLisa);
//        c2.addExhibitToFavorites(paintingMonaLisa);
//
//        List<Exhibit> allMyExhibits = myMuseum.getAllExhibits();
//        if(allMyExhibits.get(0) == paintingMonaLisa)
//            System.out.println("All working good!");
//
//        block1.displayAllExhibitsInformation();
//        block3.displayAllExhibitsInformation();

        Block block1 = new Block("Art Gallery1");
        Painting paintingMonaLisa = new Painting("Mona Lisa", new Date(1503, Calendar.NOVEMBER,24), block1, leonardo, renaissance, 10);
        Painting paintingCreationOfAdam = new Painting("Creation of Adam", new java.sql.Date(1510, Calendar.MARCH, 28), block1, michelangelo, renaissance, 0.5);

        ArrayList<Block> lb1 = Repository.getInstance().getBlocks();
        block1.addExhibit(paintingMonaLisa);
        lb1.add(block1);
        Repository.getInstance().setBlocks(lb1);
        Block b2 = new Block("Proba");
        List<Block> lb2 = new ArrayList<Block>();
        lb2.add(b2);

        Client c1 = new Client("Joita Razvan");
        Client c2 = new Client("Raul Oprisor");
        Client c3 = new Client("Andrei Moldovan");
        ArrayList<Client> clients = Repository.getInstance().getClients();
        clients.add(c1);
        clients.add(c2);
        clients.add(c3);
        Repository.getInstance().setClients(clients);
        Ticket t1 = new Ticket(lb1);
        Ticket t2 = new Ticket(lb2);
        c1.addVisit(t1);
        c2.addVisit(t2);

        ArrayList<Artist> artists = new ArrayList<>();
        artists.add(leonardo);
        artists.add(michelangelo);
        Repository.getInstance().setArtists(artists);


        Menu myMenu = Menu.getInstance();
        myMenu.showMenuOptions();
    }
}