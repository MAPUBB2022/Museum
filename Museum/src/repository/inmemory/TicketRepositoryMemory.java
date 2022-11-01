package repository.inmemory;

import classes.Block;
import classes.Museum;
import classes.Ticket;
import repository.ICrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TicketRepositoryMemory implements ICrudRepository<String, Ticket> {

    private static TicketRepositoryMemory single_instance = null;

    private final ArrayList<Ticket> allTickets = new ArrayList<>();

    public static TicketRepositoryMemory getInstance() {
        if (single_instance == null) {
            single_instance = new TicketRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate() {
        List<Block> lb1 = new ArrayList<>();
        lb1.add(BlockRepositoryMemory.getInstance().findById("B1000"));
        TicketRepositoryMemory.getInstance().add(new Ticket(lb1, ClientRepositoryMemory.getInstance().findById("C1000")));

        List<Block> lb2 = new ArrayList<>();
        lb2.add(BlockRepositoryMemory.getInstance().findById("B1000"));
        lb2.add(BlockRepositoryMemory.getInstance().findById("B1002"));
        TicketRepositoryMemory.getInstance().add(new Ticket(lb2, ClientRepositoryMemory.getInstance().findById("C1000")));

        List<Block> lb3 = new ArrayList<>();
        lb3.add(BlockRepositoryMemory.getInstance().findById("B1001"));
        lb3.add(BlockRepositoryMemory.getInstance().findById("B1002"));
        TicketRepositoryMemory.getInstance().add(new Ticket(lb3, ClientRepositoryMemory.getInstance().findById("C1001")));
    }

    @Override
    public void add(Ticket entity) {
        if (checkIfExists(entity.getId())) {
            System.out.println("The ticket already exists, please try again using an new one!");
            return;
        }
        allTickets.add(entity);
        System.out.println("Added ticket!");
    }

    @Override
    public void remove(String s) {
        if (!checkIfExists(s)) {
            System.out.println("The ticket does not exist, please try again using an existing one!");
            return;
        }
        this.allTickets.removeIf(t -> Objects.equals(s, t.getId()));
        System.out.println("The ticket has been removed!");
    }

    @Override
    public void update(String s, Ticket newEntity) {
        if (!checkIfExists(s)) {
            System.out.println("The ticket does not exist, please try again using an existing one!");
            return;
        }
        Ticket newTick = this.findById(s);
        for (Ticket oldTick : this.allTickets) {
            if (oldTick.getId().equals(s)) {
                oldTick.setGuest(newTick.getGuest());
                oldTick.setPermits(newTick.getPermits());
                oldTick.setPrice(newTick.getPrice());
                System.out.println("The ticket has been updated!");
            }
        }
    }

    @Override
    public void updateName(String s, String newId) {
        // This method doesn't work for classes with id
    }

    @Override
    public Ticket findById(String s) {
        for (Ticket t : allTickets) {
            if (s.equals(t.getId())) {
                return t;
            }
        }
        return null;
    }

    @Override
    public boolean checkIfExists(String s) {
        for (Ticket t : allTickets) {
            if (s.equals(t.getId())) {
                return true;
            }
        }
        return false;
    }

    public int numVisits(Museum m) {
        int count = 0;
        for (Ticket ticket : this.allTickets) {
            List<Block> blocks2 = new ArrayList<>(ticket.getPermits());
            blocks2.retainAll(m.getBlocks());
            if (!blocks2.isEmpty()) {
                count++;
            }
        }
        return count;
    }
}
