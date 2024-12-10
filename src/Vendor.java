import java.util.logging.*;
public class Vendor implements Runnable {
    //From configure file
    private Configure c1;
    private  final TicketPool tPool;// The ticket pool which is shared among Vendor and customers.
    public static int globalV;
    public  static int initialGlobalV;
    public static int vendorId = 1;
    private static final Logger logger = Logger.getLogger(Main.class.getName());


    public Vendor(int totalTickets,TicketPool tPool,Configure c1) {
        globalV=totalTickets;
        initialGlobalV=totalTickets;
        this.tPool = tPool;
        this.c1=c1;
    }

    @Override
    public void run(){
        while(globalV > 0){
            Ticket ticket =  new Ticket(vendorId,"event 1",500);
            tPool.addTickets(ticket);
            vendorId++;
            globalV--;
            try {
                Thread.sleep(c1.getTicketReleaseRate());

            }catch (InterruptedException e){
                System.out.println("case Vendor");
                logger.warning("Vendor interrupted: " + e.getMessage());
                throw new RuntimeException(e);


            }
        }
        logger.info( " has released all tickets.");
    }




}