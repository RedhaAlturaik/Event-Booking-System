module sample.filemanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.filemanager to javafx.fxml;
    exports sample.filemanager;
}