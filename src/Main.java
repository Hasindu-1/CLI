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

        //file creation
        c1.saveToFile("File.json");
        System.out.println("file written saved");

        //file Reading
        System.out.println(Configure.readFile("File.json"));
        System.out.println("file read successful: ");

        //Ticket pool
        TicketPool t1 = new TicketPool();

        //create Vendor (object)
        Vendor v1 = new Vendor(1,10,1000,t1);

        Thread vendorThread1 = new Thread(v1);

        ////create Customer (object)
        Customer cu1 = new Customer(1,2000,t1);

        Thread  customerThread1= new Thread(cu1);



        //Start threads
        vendorThread1.start();
        customerThread1.start();





    }
}