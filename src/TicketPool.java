import java.util.Vector;

public class TicketPool {
    private int maxTicket;// Maximum size of the ticket pool

    public int totalTicketsAdded =0; //// Counter for the total number of tickets added

    private int totalTickets;
    private final  Vector<String> ticketPool =new Vector<>();




    public TicketPool(int maxTicket,int totalTickets){
        this.maxTicket=maxTicket;
        this.totalTickets=totalTickets;

    }


    public synchronized void addTickets(String ticket){

//        if (totalTicketsAdded >= totalTickets) {
//            System.out.println("Total ticket limit reached ... Producer stopping");
//            //return false; // Stop the producer from adding more tickets
//        }

        while (ticketPool.size() >= maxTicket ) {


            try {
                System.out.println("Current Ticket pool is full ... Producer waiting");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Producer interrupted", e);
            }
        }
        ticketPool.add(ticket);
        totalTicketsAdded ++;
        notify();


        System.out.println("Ticket added by - " + Thread.currentThread().getName() + " - current size is - " + ticketPool.size());
        //return true;

    }




    public synchronized void removeTicket(int customerID){
        int i = 0;
        while(ticketPool.isEmpty()){
            try{
                System.out.println("Current Ticket pool is empty ...Customer "+customerID+" waiting");
                wait();
            }catch (Exception e){
                throw new RuntimeException();
            }
        }
        ticketPool.remove(0);
        System.out.println("Ticket bought by - " + Thread.currentThread().getName() + " - current size is - " + ticketPool.size() + " - Ticket is - " );
        notifyAll();

    }






}
