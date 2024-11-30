public class Customer implements Runnable {
    private int customerId;

    private int retrievalInterval;
    private  final TicketPool tPool;


    public Customer(int customerId, int retrievalInterval, TicketPool tPool) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
        this.tPool = tPool;
    }


    @Override
    public void run() {

    }

    public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int customerId){
        this.customerId=customerId;
    }


    public int getRetrievalInterval() {
        return retrievalInterval;
    }

    public void setRetrievalInterval(int retrievalInterval) {
        this.retrievalInterval = retrievalInterval;
    }







}
