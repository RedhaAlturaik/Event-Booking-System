package sample.filemanager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;



public class FileManager{
    private static final String FILE_PATH = "C:\\KFUPM\\Java\\events.txt";
    private static final String TEMP_PATH = "C:\\KFUPM\\Java\\events.txt";
    int capacity; // number of available ticket

    public void writeEvent(String title, String category, String description, String date, String time, String location, String MaxCapacity,String AvailableCapacity,String path){
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            // Append event data to the file
            int id = readEventsLastId(path);
            writer.println(id + "," + title + "," + category + "," + description + "," + date + "," + time + "," + location + "," + MaxCapacity + "," + AvailableCapacity);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public String[] readEvents(String eventId, String path) {
        String[] events = new String[9];
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into event fields
                String[] fields = line.split(",");
                if (eventId.equals("-1") || fields[0].equals(eventId)) {
                    // Copy the fields into the events array
                    System.arraycopy(fields, 0, events, 0, fields.length);
                    // Return the events array if eventId is found
                    if (fields[0].equals(eventId)) {
                        return events;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events; // Return the events array after the loop completes
    }

    public int readEventsLastId(String path){
        int firstCharacter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into event fields
                String[] fields = line.split(",");
                firstCharacter = Integer.parseInt(fields[0]) + 1;
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return firstCharacter;
    }
    public void DeleteEvent(String eventId){
        String[] DeletedEvent = readEvents(eventId,FILE_PATH);
        File tempFile = new File(TEMP_PATH);
        File originalFile = new File(FILE_PATH);
        Integer i = 0;
        String[] event = readEvents("0",FILE_PATH);
        try {
            while (event[0] != null) {
                if (!event[0].equals(DeletedEvent[0])) {
                    writeEvent(event[1], event[2], event[3], event[4], event[5], event[6], event[7],event[8], TEMP_PATH);
                }
                i = i + 1;
                String id = String.valueOf(i);
                event = readEvents(id, FILE_PATH);
            }
            // Delete the original file
            if (originalFile.delete()) {
                // Rename the temp file to the original file name
                if (!tempFile.renameTo(originalFile)) {
                    System.out.println("Could not rename the file");
                }
            } else {
                System.out.println("Could not delete the original file");
            }
        }
        catch (Exception e) {
            // Handle the exception
            e.printStackTrace(); // Or you can log the exception or perform any other action
        }

    }
    public void EditEvent(String eventId, String[] newValues){
        String[] event = readEvents(eventId, FILE_PATH);
        for(int i = 0; i < event.length; i++) {
            // Update specific fields with new values
            for (int j = 0; j < newValues.length; j++) {
                if (newValues[j] != null && !newValues[j].isEmpty()) {
                    event[j + 1] = newValues[j]; // Adjust index by 1 to skip event ID

                }
            }
            break; // No need to continue looping
        }
        DeleteEvent(eventId);
        writeEvent(event[1],event[2],event[3],event[4],event[5],event[6],event[7],event[8],FILE_PATH);
    }


//    public static void main(String[] args) {
//        FileManager manager = new FileManager();
//        //manager.EditEvent("2",new String[]{"Event 5","Category 6","Description 6","2024-05-01","10:00","Location 6","100","10"});
//    }
}