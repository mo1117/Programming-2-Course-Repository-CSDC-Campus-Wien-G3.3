package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("NewsApp");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("gui.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch(IOException e){
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.show();
    }
}
