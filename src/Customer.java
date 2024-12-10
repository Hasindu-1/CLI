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
        for (int i = 0; i < Vendor.initialGlobalV ; i++) {

            Ticket ticket = tPool.buyTicket();

            if(Vendor.globalV == 0){
                ticketAvailability = false;
            }

            try {
                Thread.sleep(customerRetrievalRate);


            } catch (InterruptedException e) {
                logger.warning("Customer interrupted: " + e.getMessage());
                throw new RuntimeException(e);

            }

        }
    }

}