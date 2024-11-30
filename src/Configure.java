import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        return + '\n'+ "Configure Details" + '\n'+
                "TotalTickets = " + totalTickets + '\n' +
                "TicketReleaseRate = " + ticketReleaseRate + '\n' +
                "CustomerRetrievalRate = " + customerRetrievalRate + '\n' +
                "MaxTicketCapacity = " + maxTicketCapacity + '\n' ;
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
