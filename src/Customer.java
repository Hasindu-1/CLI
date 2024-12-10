import java.util.logging.Logger;

public class Customer implements Runnable {
    private int customerRetrievalRate;//Frequency which the tickets will be removed from pool
    private  int totalTickets;
    private  final TicketPool tPool;//shared resource from Ticket pool for customer and Vendor.
    public static boolean ticketAvailability = true;
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public Customer( TicketPool tPool,int totalTickets,int customerRetrievalRate) {
        this.tPool = tPool;
        this.totalTickets=totalTickets;
        this.customerRetrievalRate=customerRetrievalRate;
    }



    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            try {
                Ticket ticket = tPool.buyTicket();
                if (ticket != null) {
                    //System.out.println(Thread.currentThread().getName() + " bought ticket: " + ticket);
                    Thread.sleep(customerRetrievalRate);
                } else {
                    break; // Exit if no more tickets are available
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warning("Customer interrupted: " + e.getMessage());
                break;
            }
        }
    }




}