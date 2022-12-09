package tests;


import classes.Block;
import classes.Museum;
import classes.Ticket;
import controllers.ControllerBlock;
import controllers.ControllerClient;
import controllers.ControllerMuseum;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import repository.inmemory.BlockRepositoryMemory;
import repository.inmemory.ClientRepositoryMemory;
import repository.inmemory.TicketRepositoryMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@TestMethodOrder(OrderAnnotation.class)
class ControllerMuseumTest {

    @Test
    @Order(1)
    void add() {
        ControllerMuseum.add("Antipa"); // exists already
        ControllerMuseum.add("Istoric"); // new one
    }

    @Test
    @Order(2)
    void delete() {
        ControllerMuseum.delete("Istoric"); // deletes a museum
        ControllerMuseum.delete("NonExistent"); // does not exist
    }

    @Test
    @Order(3)
    void changeName() {
        ControllerMuseum.changeName("Istoric", "Antipa"); // trying to change to a name that already exists
        ControllerMuseum.changeName("Antipa", "Etnografic"); // updates name
        ControllerMuseum.changeName("NonExistent", "IDK"); // the name does not exist
        ControllerMuseum.changeName("Etnografic", "Antipa"); // updates name
    }

    @Test
    @Order(4)
    void addBlock() {
        ControllerMuseum.addBlock("Antipa", "B1000"); // Adds a new block
        ControllerMuseum.addBlock("Antipa", "RandomBlock"); // Adds an existing block that does not exist
        ControllerMuseum.addBlock("Antipa", "B1000"); // Adds an existing block (duplicate)
    }

    @Test
    @Order(4)
    void deleteBlock() {
        ControllerMuseum.deleteBlock("Antipa", "B1000"); // Removes a block
        ControllerMuseum.deleteBlock("Antipa", "RandomBlock"); // Removes an existing block that does not exist
        ControllerMuseum.deleteBlock("Antipa", "B1000"); // Removes a block that does not exist in the museum
    }

    @Test
    @Order(4)
    void addClient() {
        ControllerMuseum.addClient("Antipa", "C1000"); // Adds a new client
        ControllerMuseum.addClient("Antipa", "C1000"); // Adds a client that already exists in the museum
        ControllerMuseum.addClient("Antipa", "ClientThatDoesNotExistByID"); // Adds a client that already exists in the museum
    }

    @Test
    @Order(5)
    void deleteClient() {
        ControllerMuseum.deleteClient("Antipa", "C1000"); // Removes a client
        ControllerMuseum.deleteClient("Antipa", "C1000"); //  Removes an existing client that does not exist
        ControllerMuseum.deleteClient("Antipa", "ClientThatDoesNotExistByID"); //  Removes a client that does not exist in the museum
    }

    @Test
    @Order(6)
    void displayTotalVisits() {
        ControllerMuseum.addClient("Antipa", "C1000");
        ControllerMuseum.addBlock("Antipa", "B1000");
        ControllerMuseum.displayTotalVisits("Antipa");
        ControllerMuseum.displayTotalVisits("Amtipe"); // does not exist
    }

    @Test
    @Order(7)
    void display() {
        ControllerMuseum.display("Antipa");
        ControllerMuseum.display("Amtipe");
    }

    @Test
    @Order(8)
    void displayAllExhibits() {
        ControllerBlock.addEx("B1002", "E1000");
        ControllerMuseum.addBlock("Antipa", "B1002");
        ControllerMuseum.displayAllExhibits("Antipa");
        ControllerMuseum.displayAllExhibits("Amtipe"); // does not exist
    }

    @Test
    @Order(10)
    void filterByExhibits() {
        int beginningCheck1 = ControllerMuseum.filterByExhibit(2).size();
        int beginningCheck2 = ControllerMuseum.filterByExhibit(0).size();

        ControllerMuseum.add("Museum5");
        ControllerMuseum.add("Museum6");
        ControllerBlock.add("Block1");
        Block block1 = BlockRepositoryMemory.getInstance().findByName("Block1");
        ControllerBlock.addEx(block1.getId(), "E1000");
        ControllerBlock.addEx(block1.getId(), "E1001");
        ControllerBlock.addEx(block1.getId(), "E1002");
        ControllerMuseum.addBlock("Museum5", block1.getId());
        ControllerBlock.add("Block2");
        Block block2 = BlockRepositoryMemory.getInstance().findByName("Block2");
        ControllerBlock.addEx(block2.getId(), "E1000");
        ControllerMuseum.addBlock("Museum6", block2.getId());

        int check = ControllerMuseum.filterByExhibit(10).size();
        if (check != 0)
            throw new AssertionError();
        int check1 = ControllerMuseum.filterByExhibit(2).size();
        System.out.println(check1);
        System.out.println(beginningCheck1);
        if (check1 != beginningCheck1 + 1)
            throw new AssertionError();
        int check2 = ControllerMuseum.filterByExhibit(0).size();
        System.out.println(check2);
        if (check2 != beginningCheck2 + 2)
            throw new AssertionError();

    }

    @Test
    @Order(12)
    void filterByVisits() {
        ControllerMuseum.add("Museum3");
        ControllerMuseum.add("Museum4");
        List<Block> blocks1 = new ArrayList<>();
        blocks1.add(new Block("B1"));
        blocks1.add(new Block("B2"));
        blocks1.add(new Block("B3"));
        TicketRepositoryMemory.getInstance().add(new Ticket(blocks1, ClientRepositoryMemory.getInstance().findById("C1004")));
        ControllerMuseum.addClient("Museum3", "C1004");
        blocks1.add(new Block("B4"));
        blocks1.add(new Block("B5"));
        blocks1.add(new Block("B6"));
        ControllerClient.addVisit("C1004", blocks1);
        TicketRepositoryMemory.getInstance().add(new Ticket(blocks1, ClientRepositoryMemory.getInstance().findById("C1003")));
        ControllerMuseum.addClient("Museum3", "C1004");

        int check = ControllerMuseum.filterByVisits(10).size();
        System.out.println(check);
        if (check != 0)
            throw new AssertionError();
        int check2 = ControllerMuseum.filterByVisits(2).size();
        if (check2 != 1)
            throw new AssertionError();
    }

    @Test
    @Order(9)
    void filterByClients() {
        ControllerMuseum.add("Museum1");
        ControllerMuseum.add("Museum2");
        ControllerClient.add("Client1");
        ControllerClient.add("Client2");
        ControllerClient.add("Client3");
        ControllerClient.add("Client4");
        ControllerClient.add("Client5");
        ControllerClient.add("Client6");
        ControllerMuseum.addClient("Museum1", "C1003");
        ControllerMuseum.addClient("Museum1", "C1004");
        ControllerMuseum.addClient("Museum1", "C1005");
        ControllerMuseum.addClient("Museum1", "C1006");
        ControllerMuseum.addClient("Museum1", "C1007");
        ControllerMuseum.addClient("Museum1", "C1008");
        ControllerMuseum.addClient("Museum2", "C1008");
        ControllerMuseum.addClient("Museum2", "C1007");
        int check = ControllerMuseum.filterByClients(10).size();
        System.out.println(check);
        if (check != 0)
            throw new AssertionError();
        int check1 = ControllerMuseum.filterByClients(3).size();
        if (check1 != 1)
            throw new AssertionError();
        int check2 = ControllerMuseum.filterByClients(1).size();
        if (check2 != 2)
            throw new AssertionError();
    }

    @Test
    void sort() {
        ControllerMuseum.add("A0");
        ControllerMuseum.add("Z9");
        List<Museum> listMuseum = ControllerMuseum.sort();
        if (!Objects.equals(listMuseum.get(0).getName(), "A0"))
            throw new AssertionError();
        if (!Objects.equals(listMuseum.get(listMuseum.size() - 1).getName(), "Z9"))
            throw new AssertionError();
    }
}