package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) throws IOException {
        //Menu menu = new Menu();
        //menu.start();
        launch();

        //AppController ctrl = new AppController();
        //System.out.println(ctrl.getTopHeadlinesAustria());
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
