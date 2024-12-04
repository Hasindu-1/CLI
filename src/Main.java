import java.util.Scanner;

public  class Main {

    public static void menu(Scanner input) {
        boolean loop = true;
        while (loop) {
            System.out.println("\n+----------------------------------------------+\n|      Admin Panel For TicketPlace System      |\n+----------------------------------------------+");
            System.out.println("\n1. Configure System\n2. Start Simulation\n3. Stop System\n0. Exit");
            System.out.print("\nEnter your choice : ");
            switch (input.nextLine()) {
                case "1":
                    createNewConfig();
                    break;
                case "2":
                    LoadCong();
                    break;
                case "3":
                    loop = false;
                    break;
                default:
                    System.out.println("\tInvalid choice...");
            }

        }
    }

    public static  void createNewConfig(){
        Scanner input = new Scanner(System.in);
        int totalTickets = Configure.validity(input,"Total Number of Tickets",10);
        int ticketReleaseRate = Configure.validity(input,"Ticket Release Rate" ,totalTickets);
        int customerRetrievalRate = Configure.validity(input,"Customer Retrieval Rate",totalTickets);
        int maxTicketCapacity = Configure.validity(input,"Maximum Ticket Capacity",totalTickets);

        Configure c1 = new Configure(totalTickets,ticketReleaseRate,customerRetrievalRate,maxTicketCapacity);

        System.out.println("Enter the file name that want to create.");
        String s1 = input.next();
        //file creation
        c1.saveToFile(s1);
        System.out.println(s1+"file written saved");

    }

    public static void LoadCong(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the file to Load");
        String fileName = input.next();
        if (fileName != null){
            //file Reading
            System.out.println(Configure.readFile("File.json"));
            System.out.println("file read successful: ");

        }



    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------Ticketing System-----------");
        System.out.println("****************************************");
        System.out.println("                 Menu                       ");
        System.out.println("****************************************");
        menu(input);




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