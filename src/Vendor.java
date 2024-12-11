import java.util.logging.*;
public class Vendor implements Runnable {

    private Configure c1; //From configure file
    private  final TicketPool tPool;// The ticket pool which is shared among Vendor and customers.
    public static int globalV;// total tickets value
    public static int vendorId;
    private static final Logger logger = Logger.getLogger(Main.class.getName());


    public Vendor(int totalTickets,TicketPool tPool,Configure c1) {
        globalV=totalTickets;
        this.tPool = tPool;
        this.c1=c1;
        vendorId=1;
    }

    @Override
    public void run(){
        //Adding Total Tickets
        while(globalV > 0){
            try{
            Ticket ticket =  new Ticket(vendorId,"event 1",500);
            tPool.addTickets(ticket);
            vendorId++;
            globalV--;

                Thread.sleep(c1.getTicketReleaseRate());

            }catch (InterruptedException e){
                System.out.println("Vendor Error :");
                logger.warning("Vendor interrupted: " + e.getMessage());
                throw new RuntimeException(e);


            }
        }
        logger.info( " has released all tickets.");
    }




}