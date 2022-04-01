package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class App extends Application {
    public static void main(String[] args) {
         //Menu menu = new Menu();
         //menu.start();
         launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("NewsApp");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
