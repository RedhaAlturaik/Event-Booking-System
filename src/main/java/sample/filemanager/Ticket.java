package sample.filemanager;

import java.util.Date;

class Ticket{
    private String eventTitle;
    private int numTickets;
    private Date bookingDate;

    // Constructor
    public Ticket(int numTickets) {
        this.numTickets = numTickets;

    }

    // Getters

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int tickets){
        this.numTickets = tickets + 1;
    }
}