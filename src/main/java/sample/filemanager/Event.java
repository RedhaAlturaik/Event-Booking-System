package sample.filemanager;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Date;

class Event{
    private final SimpleStringProperty EventID;
    private final SimpleStringProperty title;
    private SimpleStringProperty category;
    private SimpleStringProperty description;
    private final SimpleStringProperty date;
    private final SimpleStringProperty time;
    private final SimpleStringProperty location;
    private SimpleIntegerProperty capacity;

    private final SimpleIntegerProperty availableTickets;

    // Constructor
    public Event(String EventID,String title,String Category,String description, String location,String date, String time,int capacity, int availableTickets) {
        this.EventID = new SimpleStringProperty(EventID);
        this.title = new SimpleStringProperty(title);
        this.category = new SimpleStringProperty(Category);
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.location = new SimpleStringProperty(location);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.availableTickets = new SimpleIntegerProperty(availableTickets);
    }

    // Getters
    public String getID(){
        return EventID.get();
    }
    public SimpleStringProperty getEventID(){
        return EventID;
    }
    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }
    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public String getDescription(){
        return description.get();
    }
    public SimpleStringProperty DescriptionProperty(){
        return description;
    }
    public String getDate() {
        return date.get();
    }
    public SimpleStringProperty dateProperty(){
        return date;
    }
    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }
    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public int getCapacity(){
        return capacity.get();
    }
    public SimpleIntegerProperty CapacityProperty(){
        return capacity;
    }
    public int getAvailableTickets() {
        return availableTickets.get();
    }

    public SimpleIntegerProperty availableTicketsProperty() {
        return availableTickets;
    }

    public void bookTickets(int numTickets) {
    }


}