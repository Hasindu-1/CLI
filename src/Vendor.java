public class Vendor implements Runnable {

    private int vendorId;

    private int ticketsPerRelease;


    private int retrievalInterval;

    private  final TicketPool tPool;


    public Vendor(int vendorId, int ticketsPerRelease, int retrievalInterval, TicketPool tPool) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.retrievalInterval = retrievalInterval;
        this.tPool = tPool;
    }

    @Override
    public void run(){


    }






    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getTicketsPerRelease() {
        return ticketsPerRelease;
    }

    public void setTicketsPerRelease(int ticketsPerRelease) {
        this.ticketsPerRelease = ticketsPerRelease;
    }

    public int getRetrievalInterval() {
        return retrievalInterval;
    }

    public void setRetrievalInterval(int retrievalInterval) {
        this.retrievalInterval = retrievalInterval;
    }


}