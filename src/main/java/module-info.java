module NewsApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires okhttp3;
    requires com.google.gson;

    opens at.ac.fhcampuswien to javafx.fxml, com.google.gson;
    exports at.ac.fhcampuswien;
    exports at.ac.fhcampuswien.guicontroller;
    opens at.ac.fhcampuswien.guicontroller to javafx.fxml;
    exports at.ac.fhcampuswien.enums;
    opens at.ac.fhcampuswien.enums to com.google.gson, javafx.fxml;
}
