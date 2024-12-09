import java.math.BigDecimal;

public class Vendor implements Runnable {



    private int totalTickets;

    private int ticketReleaseRate;

    //From configure file
    private Configure c1;

    private  final TicketPool tPool;// The ticket pool which is shared among Vendor and customers.

    public static int globalV;

    public  static int initialGlobalV;

    public int id;








    public Vendor(int totalTickets,TicketPool tPool,Configure c1) {
        //this.totalTickets=totalTickets;
        globalV=totalTickets;
        initialGlobalV=totalTickets;

        this.tPool = tPool;
        this.c1=c1;
    }

    @Override
    public void run(){
        while(globalV > 0){
            Ticket ticket =  new Ticket(id,"event 1",500);
            tPool.addTickets(ticket);
            globalV--;
            try {
                Thread.sleep(c1.getTicketReleaseRate());

            }catch (InterruptedException e){
                System.out.println("case Vendor");
                throw new RuntimeException(e);


            }
        }
    }

//    @Override
//    public void run(){
//
//            for (int j = 1; j <= c1.getTotalTickets(); j++) {
//
//
//                Ticket ticket =new Ticket(j,"event simple",500);
//                boolean added = tPool.addTickets(ticket);
//                // Construct each ticket string dynamically
//                //tPool.addTickets(ticket);
//
//                if (!added) {
//                    System.out.println( j+ " stopped adding tickets (Global limit reached)");
//                    break;
//                }
//
//
////                // Add the ticket to the TicketPool
////                if (!tPool.addTickets(ticket)) {
////                    // Stop if the global ticket limit is reached
////                    System.out.println("Vendor " + vendorId + " stopped adding tickets");
////                    break;
////                }
//                try {
//                    Thread.sleep(c1.getTicketReleaseRate());
//
//                }catch (InterruptedException e){
//                    System.out.println("case Vendor");
//                    throw new RuntimeException(e);
//
//
//                }
//            }
//
//
//    }





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