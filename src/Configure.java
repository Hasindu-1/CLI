import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

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

    public  static int  validity(Scanner input, String para,int totalTickets){
        int number=-1;


        while(number <=0){
            System.out.println("Enter the "+para + " ");


            if(input.hasNextInt()) {
                number = input.nextInt();

                if (number < 1) {
                    System.out.println("Entered " + para + " must be a positive number");
                    number = -1;


                }
                if (para.equals("Maximum Ticket Capacity")) {
                    if (number > totalTickets) {
                        System.out.println("The Maximum Ticket Capacity must not exceed the Total Number of Tickets (" + totalTickets + ").");
                        number = -1; // Reset the number for re-entry
                    }
                } else if (para.equals("Ticket Release Rate")) {
                    if(number > totalTickets){
                        System.out.println("Ticket Release Rate must not exceed the Total Number of Tickets (" + totalTickets + ").");
                        number = -1;
                    }

                } else if (para.equals("Customer Retrieval Rate")) {
                    if(number > totalTickets){
                        System.out.println("Customer Retrieval Rate the Total Number of Tickets (" + totalTickets + ").");
                        number = -1;
                    }

                }


            }
            else{
                System.out.println("Invalid input "+para+"Please try again");
                input.next();
            }

        }
        return number;


    }


    public void saveToFile(String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)){
            gson.toJson(this,writer);

        } catch (IOException exception) {
            System.out.println("There is a error called - "+exception);
        }
    }

    public static Configure readFile(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, Configure.class);


        } catch (IOException exception) {
            System.out.println("There is an error Reading this file- " + exception);
            return null;
        }
    }




    @Override
    public String toString() {
        return   "|****************************************|\n"+
                "|            Configure Details           |\n" +
                "|****************************************|\n"+
                        String.format("| %-25s : %-10d |\n", "Total Tickets", totalTickets) +
                        String.format("| %-25s : %-10d |\n", "Ticket Release Rate", ticketReleaseRate) +
                        String.format("| %-25s : %-10d |\n", "Customer Retrieval Rate", customerRetrievalRate) +
                        String.format("| %-25s : %-10d |\n", "Max Ticket Capacity", maxTicketCapacity)+


                        "|****************************************|\n" ;
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
