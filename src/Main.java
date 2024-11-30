import java.util.Scanner;

public  class Main {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("**** Ticketing System ****");
        System.out.println("    Menu    ");
        System.out.println("****************************************");

        int totalTickets = Configure.validity(input,"Total Number of Tickets");
        int ticketReleaseRate = Configure.validity(input,"Ticket Release Rate");
        int customerRetrievalRate = Configure.validity(input,"Customer Retrieval Rate");
        int maxTicketCapacity = Configure.validity(input,"Maximum Ticket Capacity");

        Configure c1 = new Configure(totalTickets,ticketReleaseRate,customerRetrievalRate,maxTicketCapacity);

    }
}