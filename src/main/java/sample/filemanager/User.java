package sample.filemanager;

import java.util.List;

class User {
    // Method to browse upcoming events
    public void browseEvents(List<Event> events) {
        System.out.println("Upcoming Events:");
        for (Event event : events) {
            System.out.println("- " + event.getEventID() + " | Title: " + event.getTitle() + " | Category: " + event.getCategory() +
                    " | Description: " + event.getDescription() +
                    " | Date: " + event.getDate() +
                    " | Time: " + event.getTime() +
                    " | Location: " + event.getLocation() +
                    " | Capacity: " + event.getCapacity() +
                    " | AvailableTickets: " + event.getAvailableTickets());
        }
    }

    // Method to view event details
    public void viewEventDetails(Event event) {
        System.out.println("Id: " + event.getID());
        System.out.println("Event: " + event.getTitle());
        System.out.println("Category: " + event.getCategory());
        System.out.println("Description: " + event.getDescription());
        System.out.println("Date: " + event.getDate());
        System.out.println("Time: " + event.getTime());
        System.out.println("Location: " + event.getLocation());
        System.out.println("Capacity: " + event.getCapacity());
        System.out.println("Available Tickets: " + event.getAvailableTickets());
    }

    // Method to book tickets
    public void bookTickets(Event event, int numTickets) {
        event.bookTickets(numTickets);
    }
}