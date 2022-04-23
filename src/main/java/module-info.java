module NewsApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp;
    requires com.google.gson;

    exports at.ac.fhcampuswien.guicontroller;
    opens at.ac.fhcampuswien.guicontroller to javafx.fxml;
    exports at.ac.fhcampuswien;
    opens at.ac.fhcampuswien to com.google.gson;
}
