public class Customer implements Runnable {
    private int customerId;

    private int customerRetrievalRate;

    private  int totalTickets;

    private Configure c1;
    private  final TicketPool tPool;


    public Customer(int customerId , TicketPool tPool,Configure c1) {
        this.customerId = customerId;
//        this.customerRetrievalRate=customerRetrievalRate;
        this.tPool = tPool;
//        this.totalTickets=totalTickets;
        this.c1=c1;
    }


//    @Override
//    public void run() {
//        for (int i = 0; i < c1.getTotalTickets(); i++) {
//            tPool.removeTicket(customerId);
//            //System.out.println("Ticket is - " + (i+1) + " - Customer name is - " + Thread.currentThread().getName());
//
//            try {
//                Thread.sleep(c1.getCustomerRetrievalRate());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//
//    }

    @Override
    public void run() {
        for (int i = 0; i < c1.getTotalTickets(); i++) {
            synchronized (tPool) {
                tPool.removeTicket(customerId);

                try {
                    Thread.sleep(c1.getCustomerRetrievalRate());

                } catch (InterruptedException e) {
                    System.out.println(e);
                    break;
                }
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
