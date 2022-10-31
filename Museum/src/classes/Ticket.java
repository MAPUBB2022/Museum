package classes;

import java.util.List;

public class Ticket {
    private static int counter = 1000;
    private final String id;
    private double price;
    private List<Block> permits;

    private Client guest;

    public Ticket(List<Block> permits, Client guest) {
        this.id = "T" + counter++;
        this.permits = permits;
        this.price = 2.5 * permits.size();
        this.guest = guest;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Ticket.counter = counter;
    }

    public void addPermit(Block permitToAdd) {
        if (permits.contains(permitToAdd)) {
            System.out.println("Duplicate!");
        } else {
            permits.add(permitToAdd);
            this.price += 2.5;
            System.out.println("Added:" + permitToAdd.getName());
        }
    }

    public void deletePermit(Block permitToRemove) {
        if (permits.contains(permitToRemove)) {
            System.out.println("Removed:" + permitToRemove.getName());
            this.price -= 2.5;
            permits.remove(permitToRemove);
        } else {
            System.out.println("No such classes.Block was found!");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public List<Block> getPermits() {
        return permits;
    }

    public void setPermits(List<Block> permits) {
        this.permits = permits;
    }

    public Client getGuest() {
        return guest;
    }

    public void setGuest(Client guest) {
        this.guest = guest;
    }
}
