import java.util.Vector;
import java.util.logging.Logger;

public class TicketPool {
    private int maxTicket;// Maximum size of the ticket pool
    public int totalTicketsAdded ;// Counter for the total number of tickets added
    private int totalTickets;
    private final  Vector<Ticket> ticketPool =new Vector<>();//For store Tickets
    private int totalticketsold ;
    private static final Logger logger = Logger.getLogger(Main.class.getName());


    public TicketPool(int maxTicket,int totalTickets){
        this.maxTicket=maxTicket;
        this.totalTickets=totalTickets;
        totalTicketsAdded = 0;
        totalticketsold = 0;

    }



    public synchronized boolean addTickets(Ticket vendorName) {
        // set that only added ticket TotalTicket count.
        while (totalTicketsAdded < totalTickets) {
            // Wait if the ticket pool is full.
            while (ticketPool.size() >= maxTicket) {
                try {

                    System.out.println(String.format(
                            "Vendor '%s' is waiting as Ticket Pool is full.",
                            Thread.currentThread().getName()
                    ));

                    wait(); // Wait until customers buy tickets.
                } catch (InterruptedException e) {
                    throw new RuntimeException("Vendor interrupted", e);
                }
            }

            // Recheck the global ticket limit
            if (totalTicketsAdded >= totalTickets) {
                System.out.println(vendorName + " cannot add tickets. Global ticket limit reached.");
                return false; // Indicate that no more tickets can be added
            }

            // Add a new ticket to the pool
            Ticket ticket = new Ticket(totalTicketsAdded + 1, " Event Simple ", 500);
            ticketPool.add(ticket);
            totalTicketsAdded++;
            System.out.println("\n================ VENDOR ACTIONS ================");
            System.out.println(String.format(
                    "Vendor '%s' added Ticket #%d | Current Pool Size: %d | Total Tickets Added: %d",
                    Thread.currentThread().getName(), // Vendor name
                    ticket.getTicketId(),             // Ticket ID
                    ticketPool.size(),                // Current size of the pool
                    totalTicketsAdded                 // Total tickets added so far
            ));


            logger.info( Thread.currentThread().getName()+" Vendor added");

            // Notify all other threads that vendor added tickets.
            notifyAll();
            return true; // Set that the ticket was successfully added.
        }

        // check the total tickets limit won't exceed
        System.out.println(vendorName + " cannot add tickets. Global ticket limit reached.");
        return false;
    }


    public synchronized Ticket buyTicket(){
        while(ticketPool.isEmpty()){
            if (totalTicketsAdded >= totalTickets) {
                return null; // No more tickets will be added
            }
            try{
                System.out.println("\n================ CUSTOMER ACTIONS ================");
                System.out.println(Thread.currentThread().getName() +" Current Ticket pool is empty ...Customer  waiting");
                wait();


            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return null;
            }
        }

        Ticket ticket = ticketPool.remove(0);
        totalticketsold++;
        System.out.println("\n================ TICKET PURCHASES ================");
        System.out.println(String.format(
                "Ticket purchased by '%s' | Ticket Details: %s | Customer: %s",
                Thread.currentThread().getName(),
                ticket,
                Vendor.globalV
        ));
        System.out.println(String.format(
                "Current Pool Size: %d | Total Tickets Sold: %d",
                ticketPool.size(),
                totalticketsold
        ));

        logger.info(Thread.currentThread().getName() +" customer bought");
        notifyAll();//notify all waiting threads.
        return ticket;
    }

}