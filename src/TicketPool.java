import java.util.Vector;
import java.util.logging.Logger;

public class TicketPool {
    private int maxTicket;// Maximum size of the ticket pool
    public int totalTicketsAdded =0; // Counter for the total number of tickets added
    private int totalTickets;
    private final  Vector<Ticket> ticketPool =new Vector<>();
    private int totalticketsold =0;
    private static final Logger logger = Logger.getLogger(Main.class.getName());


    public TicketPool(int maxTicket,int totalTickets){
        this.maxTicket=maxTicket;
        this.totalTickets=totalTickets;

    }



    public synchronized boolean addTickets(Ticket vendorName) {
        // Check if the total ticket limit has been reached
        while (totalTicketsAdded < totalTickets) {
            // Wait if the ticket pool is full
            while (ticketPool.size() >= maxTicket) {
                try {

                    System.out.println(String.format(
                            "Vendor '%s' is waiting as Ticket Pool is full.",
                            Thread.currentThread().getName()
                    ));

                    wait(); // Wait until there's space in the pool
                } catch (InterruptedException e) {
                    throw new RuntimeException("Vendor interrupted", e);
                }
            }

            // Re-check the global ticket limit after waking up
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


            logger.info("Vendoer added");

            // Notify other threads (e.g., customers) that a ticket has been added
            notifyAll();
            return true; // Indicate that the ticket was successfully added
        }

        // If global ticket limit has been reached before entering the loop
        System.out.println(vendorName + " cannot add tickets. Global ticket limit reached.");
        return false;
    }


    public synchronized Ticket buyTicket(){

        while(ticketPool.isEmpty()){
            try{
                System.out.println("\n================ CUSTOMER ACTIONS ================");
                System.out.println(Thread.currentThread().getName() +" Current Ticket pool is empty ...Customer  waiting");
                if(Customer.ticketAvailability == true){
                    wait();
                    break;
                }else{
                    break;
                }

//                break;
            }catch (Exception e){
                throw new RuntimeException();
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

        logger.info("customer bought");
        notifyAll();

        return ticket;
}

}