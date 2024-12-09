import java.util.Vector;

public class TicketPool {
    private int maxTicket;// Maximum size of the ticket pool

    public int totalTicketsAdded =0; //// Counter for the total number of tickets added

    private int totalTickets;
    private final  Vector<Ticket> ticketPool =new Vector<>();

    private int totalticketsold =0;





    public TicketPool(int maxTicket,int totalTickets){
        this.maxTicket=maxTicket;
        this.totalTickets=totalTickets;

    }


//    public synchronized void addTickets(Ticket ticket){
//
////        if (totalTicketsAdded >= totalTickets) {
////            System.out.println("Total ticket limit reached ... Producer stopping");
////            //return false; // Stop the producer from adding more tickets
////        }
//
//        while (ticketPool.size()>= maxTicket ) {
//
//
//            try {
//                System.out.println("Current Ticket pool is full ... Producer waiting");
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();//using this in CLI
//                throw new RuntimeException("Producer interrupted", e);//Client Server application show this error
//            }
//        }
//        ticketPool.add(ticket);
//        totalTicketsAdded ++;
//        notifyAll();//Will notify for all the threads that waiting
//        System.out.println("--------"+Thread.currentThread().getName()+ "has added a ticket to the Pool.Current Ticket pool size is "+ticketPool.size() );
//
//        //return true;
//
//    }

//    public synchronized void addTickets(Ticket ticket) {
//        // Check if the total ticket limit has been reached
//        while (totalTicketsAdded >= totalTickets || ticketPool.size() >= maxTicket) {
//            if (totalTicketsAdded >= totalTickets) {
//                System.out.println("Global ticket limit reached ... Vendor stopping");
//                return; // Stop the vendor from adding more tickets
//            }
//            try {
//                System.out.println("Current Ticket pool is full ... Vendor waiting");
//                wait(); // Wait if the pool is full
//            } catch (InterruptedException e) {
//                throw new RuntimeException("Vendor interrupted", e);
//            }
//        }
//        // Add the ticket to the pool
//        ticketPool.add(ticket);
//        totalTicketsAdded++;
//        System.out.println(Thread.currentThread().getName() + "======= added Ticket #" + ticket.getTicketId() +
//                " - Current Pool Size: " + ticketPool.size() +
//                " - Total Tickets Added: " + totalTicketsAdded);
//
//        notifyAll(); //
//
//
//
//    }

//    public synchronized boolean addTickets(Ticket vendorName) {
//        // Check if the total ticket limit has been reached
//        if ( totalTicketsAdded >= totalTickets) {
//            System.out.println(vendorName + " cannot add tickets. Global ticket limit reached.");
//            return false; // Indicate that no more tickets can be added
//        }
//
//        // Wait if the ticket pool is full
//        while (ticketPool.size() >= maxTicket) {
//            try {
//                System.out.println(vendorName + " waiting as Ticket Pool is full.");
//                wait(); // Wait until there's space in the pool
//            } catch (InterruptedException e) {
//                throw new RuntimeException("Vendor interrupted", e);
//            }
//        }
//
//        // Add the ticket to the pool
//        Ticket ticket = new Ticket(totalTicketsAdded + 1, "event simple",500);
//        ticketPool.add(ticket);
//        totalTicketsAdded++;
//        System.out.println(vendorName + "========== added Ticket #" + totalTicketsAdded + " - Current Pool Size: " + ticketPool.size() + " - Total Tickets Added: " + totalTicketsAdded);
//        System.out.println(" &&&&&& Ticket adde count is "+totalTicketsAdded);
//
//        // Notify other threads (e.g., customers) that a ticket has been added
//        notifyAll();
//        return true; // Indicate that the ticket was successfully added
//    }
//
//
//


    public synchronized boolean addTickets(Ticket vendorName) {
        // Check if the total ticket limit has been reached
        while (totalTicketsAdded < totalTickets) {
            // Wait if the ticket pool is full
            while (ticketPool.size() >= maxTicket) {
                try {
                    System.out.println(vendorName + " waiting as Ticket Pool is full.");
                    wait(); // Wait until there's space in the pool
                } catch (InterruptedException e) {
                    throw new RuntimeException("Vendor interrupted", e);
                }
            }

            // Re-check the global ticket limit after waking up
            if (totalTicketsAdded >= totalTickets) {
                System.out.println(vendorName + " cannot add tickets. Global ticket limit reached.");
                return false; // Indicate that no more tickets can be added
            }

            // Add a new ticket to the pool
            Ticket ticket = new Ticket(totalTicketsAdded + 1, "Event Simple", 500);
            ticketPool.add(ticket);
            totalTicketsAdded++;
            System.out.println(vendorName + " added Ticket #" + ticket.getTicketId() +
                    " - Current Pool Size: " + ticketPool.size() +
                    " - Total Tickets Added: " + totalTicketsAdded);

            // Notify other threads (e.g., customers) that a ticket has been added
            notifyAll();
            return true; // Indicate that the ticket was successfully added
        }

        // If global ticket limit has been reached before entering the loop
        System.out.println(vendorName + " cannot add tickets. Global ticket limit reached.");
        return false;
    }






    public synchronized Ticket buyTicket(){
        int i = 0;
        while(ticketPool.isEmpty()){
            try{
                System.out.println("Current Ticket pool is empty ...Customer  waiting");
                wait();
            }catch (Exception e){
                throw new RuntimeException();
            }
        }

        Ticket ticket = ticketPool.remove(0);
        totalticketsold++;
        System.out.println("Ticket bought by - " + Thread.currentThread().getName() + " - current size is - " + ticketPool.size() + " - Ticket is - " );
        System.out.println(" **********Ticket sold count is "+totalticketsold);
        notifyAll();

        return ticket;
    }








}
