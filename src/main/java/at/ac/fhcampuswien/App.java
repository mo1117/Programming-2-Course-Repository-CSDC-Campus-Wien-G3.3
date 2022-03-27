package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("NewsApp");
        StackPane layout = new StackPane();

        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
