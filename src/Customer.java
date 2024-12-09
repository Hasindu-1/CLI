public class Customer implements Runnable {


    private int customerRetrievalRate;//Frequency which the tickets will be removed from pool

    private int quantity;//Quantity customer wiling to purchase

    private  int totalTickets;


    private  final TicketPool tPool;//shared resource from Ticket pool for customer and Vendor.


    public Customer( TicketPool tPool,int totalTickets,int customerRetrievalRate) {
        this.tPool = tPool;
        this.totalTickets=totalTickets;
        this.customerRetrievalRate=customerRetrievalRate;
    }




    @Override
    public void run() {
        for (int i = 0; i < Vendor.initialGlobalV ; i++) {


                Ticket ticket = tPool.buyTicket();
                System.out.println("Ticket bought by" + Thread.currentThread().getName()+"Ticket is "+ticket);

                try {
                    Thread.sleep(customerRetrievalRate);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);

                }

        }
    }


    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }










}
