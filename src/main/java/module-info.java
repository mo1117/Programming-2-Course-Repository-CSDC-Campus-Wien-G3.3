module NewsApp {
    requires javafx.controls;
    requires javafx.fxml;

    opens at.ac.fhcampuswien to javafx.fxml;
    exports at.ac.fhcampuswien;
    exports at.ac.fhcampuswien.guicontroller;
    opens at.ac.fhcampuswien.guicontroller to javafx.fxml;
}
