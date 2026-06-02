package sample.filemanager;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserScene extends Application {

    public static final String EventPath = "C:\\KFUPM\\Java\\events.txt";
    public static final String UserPath = "C:\\KFUPM\\Java\\user.txt";
    private List<Event> events = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws ParseException {


        events = parseFileToEvents(EventPath);

        TableColumn<Event,String> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(cellData -> cellData.getValue().getEventID());

        TableColumn<Event, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Event, String> CategoryColumn = new TableColumn<>("Category");
        CategoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());

        TableColumn<Event, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().DescriptionProperty());

        TableColumn<Event, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

        TableColumn<Event, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        TableColumn<Event, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());


        TableColumn<Event, Integer> CapacityColumn = new TableColumn<>("Capacity");
        CapacityColumn.setCellValueFactory(cellData -> cellData.getValue().CapacityProperty().asObject());

        TableColumn<Event, Integer> availableTicketsColumn = new TableColumn<>("Available Tickets");
        availableTicketsColumn.setCellValueFactory(cellData -> cellData.getValue().availableTicketsProperty().asObject());

        TableView<Event> eventTableView = new TableView<>();

        TableColumn<Event,Void> ButtonCol = new TableColumn<>("-");
        ButtonCol.setCellFactory(param -> new TableCell<>(){
            private final Button button = new Button("AddEvent");


            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        LocalDate localDate = LocalDate.now();
                        Event data = getTableView().getItems().get(getIndex());

                        String EventData = data.getID()
                                + "," + data.getTitle()
                                + "," + data.getCategory()
                                + "," + data.getDescription()
                                + "," + data.getLocation()
                                + "," + data.getDate()
                                + "," + data.getTime();

                        try {
                            FileWriter FW = new FileWriter(UserPath, true);
                            if (data.getDate().compareTo(localDate.toString()) >= 0) {
                                FW.write(EventData + "\n");
                                FW.close();
                                eventTableView.refresh();
                                System.out.println("Data added to the file: " + EventData);
                            } else {
                                eventTableView.refresh();
                                System.out.println("The event you want to add is not available");
                            }

                        } catch (IOException e) {
                            System.out.println("Error writing to the file " + e.getMessage());
                        }
                    }
                });
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }

        });



        eventTableView.getColumns().addAll(IdColumn,
                titleColumn,
                CategoryColumn,
                descriptionColumn,
                locationColumn,
                dateColumn,
                timeColumn,
                CapacityColumn,
                availableTicketsColumn,
                ButtonCol);


        eventTableView.getItems().addAll(getUpcomingEvents());

        VBox root = new VBox(eventTableView);
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("UserScene");
        primaryStage.show();

    }





    // Method to get upcoming events (exclude past events)
    private List<Event> getUpcomingEvents() throws ParseException {
        List<Event> upcomingEvents = new ArrayList<>();

        for (Event event : events) {
            upcomingEvents.add(event);
        }
        return upcomingEvents;
    }

    private static List<Event> parseFileToEvents(String filename) {
        List<Event> events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 9){
                    String eventID = parts[0];
                    String title = parts[1];
                    String category = parts[2];
                    String description = parts[3];
                    String location = parts[6];
                    String date = parts[4];
                    String time = parts[5];
                    int capacity = Integer.parseInt(parts[7]);
                    int availableTickets = Integer.parseInt(parts[8]);

                    Event event = new Event(eventID, title, category, description, location, date, time, capacity, availableTickets);
                    events.add(event);
                } else {
                    System.out.println("Invalid data format in the file: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return events;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
