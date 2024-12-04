import java.util.Vector;

public class TicketPool {
    private int maxTicket;
    private final  Vector<String> ticketPool =new Vector<>();


    public TicketPool(int maxTicket){
        this.maxTicket=maxTicket;

    }


    public synchronized void addTickets(String ticket){
        int i = 0;

        while(ticketPool.size() >= maxTicket){
            try{
                System.out.println("Current Ticket pool is full ...Producer waiting");
                wait();
            }catch (Exception e){
                throw new RuntimeException();

            }
        }
        ticketPool.add(ticket);
        i++;
        System.out.println("add tick method  " + i);
        notifyAll();

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
        i++;
        System.out.println("customer "+customerID+" purchased ticket number " + i);
        notifyAll();

    }



}
