public class Vendor implements Runnable {

    private int vendorId;




    private int totalTickets;

    private int ticketReleaseRate;

    //From configure file
    private Configure c1;

    private  final TicketPool tPool;



    public Vendor(int vendorIds,TicketPool tPool,Configure c1) {
        this.vendorId = vendorIds;

        this.tPool = tPool;
        this.c1=c1;
    }

    @Override
    public void run(){

            for (int j = 0; j < c1.getTotalTickets(); j++) {
                // Construct each ticket string dynamically
                String ticket = getVendorId() +"   Vendor add Ticket-"+ j ;
                tPool.addTickets(ticket);

//                // Add the ticket to the TicketPool
//                if (!tPool.addTickets(ticket)) {
//                    // Stop if the global ticket limit is reached
//                    System.out.println("Vendor " + vendorId + " stopped adding tickets");
//                    break;
//                }
                try {
                    Thread.sleep(c1.getTicketReleaseRate());

                }catch (InterruptedException e){
                    System.out.println("case Vendor");
                    throw new RuntimeException(e);


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