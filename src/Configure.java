import java.util.Scanner;

public class Configure {

    private int totalTickets ;
    private int ticketReleaseRate;
    private int customerRetrievalRate;

    private int  maxTicketCapacity;

    public Configure(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public  static int  validity(Scanner input, String para){
        int number=-1;
        while(number <=0){
            System.out.println("Enter the "+para + " ");
            if(input.hasNextInt()) {
                number = input.nextInt();

                if (number < 1) {
                    System.out.println("Entered " + para + " must be a positive number");


                }
            }
            else{
                System.out.println("Invalid input "+para+"Please try again");
                input.next();
            }

        }
        return number;


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

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }





}
