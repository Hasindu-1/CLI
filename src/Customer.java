public class Customer implements Runnable {
    private int customerId;

    private int customerRetrievalRate;

    private  int totalTickets;
    private  final TicketPool tPool;


    public Customer(int customerId, int customerRetrievalRate , TicketPool tPool,int totalTickets) {
        this.customerId = customerId;
        this.customerRetrievalRate=customerRetrievalRate;
        this.tPool = tPool;
        this.totalTickets=totalTickets;
    }


    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            tPool.removeTicket(customerId);
            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int customerId){
        this.customerId=customerId;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }









}
