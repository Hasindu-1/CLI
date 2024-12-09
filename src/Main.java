import java.util.Scanner;

public  class Main {



    public static void menu(Scanner input) {
        boolean loop = true;

        while (loop) { // Use a loop to keep displaying the menu
            try {
                System.out.println("\n+----------------------------------------------+");
                System.out.println("|      Admin Panel For TicketPlace System      |");
                System.out.println("+----------------------------------------------+");
                System.out.println("\n1. Configure System\n2. Start Simulation\n3. Stop System\n0. Exit");
                System.out.print("\nEnter your choice: ");

                String choice = input.nextLine(); // Get user input

                switch (choice) {
                    case "1":
                        createNewConfig(); // Call configuration method
                        loop=false;
                        break;
                    case "2":
                        LoadCong(); // Call load configuration method
                        loop = false;
                        break;
                    case "3":
                        System.out.println("Stopping the system...");
                        loop = false; // Stop the menu loop
                        break;
                    case "0":
                        System.out.println("Exiting the system. Goodbye!");
                        loop = false; // Exit the menu loop
                        break;
                    default:
                        System.out.println("\tInvalid choice. Please select a valid option...");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again!");
                input.nextLine(); // Clear the invalid input
            }
        }
    }


    public static  void createNewConfig(){
        Scanner input = new Scanner(System.in);
        int totalTickets = Configure.validity(input,"Total Number of Tickets",100);
        int ticketReleaseRate = Configure.validity(input,"Ticket Release Rate" ,totalTickets);
        int customerRetrievalRate = Configure.validity(input,"Customer Retrieval Rate",totalTickets);
        int maxTicketCapacity = Configure.validity(input,"Maximum Ticket Capacity",totalTickets);

        Configure c1 = new Configure(totalTickets,ticketReleaseRate,customerRetrievalRate,maxTicketCapacity);




            System.out.println("Enter the file name that want to create.");
            String s1 = input.next();
            //file creation
            c1.saveToFile(s1);
            //c1.savetxtfile(s1);
            System.out.println( "file written saved");
            String show = c1.toString();
            System.out.println(show);

            systemStart(totalTickets,ticketReleaseRate,customerRetrievalRate,maxTicketCapacity,c1);



    }

    public static void LoadCong(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the file to Load");
        while(true){
            String fileName = input.next();

            Configure config = Configure.readFile(fileName);
            if(config != null){
                System.out.println(Configure.readFile(fileName));
                System.out.println("file read successful: ");
                systemStart(config.getTotalTickets(), config.getTicketReleaseRate(),config.getCustomerRetrievalRate(),config.getMaxTicketCapacity(),config);
                break;


            }else {
                System.out.println("Error ~ Failed to Load. Please check the file name and Try Again.");
            }

        }






    }


    public static void systemStart(int totalTickets,int ticketReleaseRate,int customerRetrievalRate ,int maxTicketCapacity,Configure c1){
        System.out.println(totalTickets );
        System.out.println(ticketReleaseRate );
        System.out.println(customerRetrievalRate);
        System.out.println(maxTicketCapacity);

        //Ticket pool
        TicketPool t1 = new TicketPool(maxTicketCapacity,totalTickets);


        //create Vendor (object)
        Vendor v1 = new Vendor(totalTickets,t1,c1);

        Thread vendorThread1 = new Thread(v1);

        Vendor v2 = new Vendor(totalTickets,t1,c1);
        Thread vendorThread2 = new Thread(v2);

        Vendor v3 = new Vendor(totalTickets,t1,c1);
        Thread vendorThread3 = new Thread(v3);



        ////create Customer (object)
        Customer cu1 = new Customer(t1,totalTickets,customerRetrievalRate);
        Thread  customerThread1= new Thread(cu1,"customer 1");

        Customer cu2 = new Customer(t1,totalTickets,customerRetrievalRate);
        Thread customerThread2 =new Thread(cu1,"customer 2");



        //Start threads
        vendorThread1.start();
        vendorThread2.start();
        vendorThread3.start();
        customerThread1.start();
        customerThread2.start();
        System.out.println("Simulation started! Threads are running...\n");
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------Ticketing System-----------");
        menu(input);










    }


}