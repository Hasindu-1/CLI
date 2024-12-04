public class Vendor implements Runnable {

    private int vendorId;


//    private int ticketsPerRelease;


//    private int retrievalInterval;

    //From configure file
    private int totalTickets;

    private int ticketReleaseRate;

    private  final TicketPool tPool;


    public Vendor(int vendorIds,int ticketReleaseRate ,int totaltickets,TicketPool tPool) {
        this.vendorId = vendorIds;
        this.totalTickets=totaltickets;
        this.ticketReleaseRate=ticketReleaseRate;
        this.tPool = tPool;
    }

    @Override
    public void run(){

            for (int j = 0; j < totalTickets; j++) {
                // Construct each ticket string dynamically
                String ticket = getVendorId() +"   Vendor add Ticket-"+ j ;

                // Add the ticket to the TicketPool
                tPool.addTickets(ticket);
                try {
                    Thread.sleep(ticketReleaseRate * 1000);

                }catch (Exception e){
                    System.out.println("case Vendor");


                }
            }







    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }






}