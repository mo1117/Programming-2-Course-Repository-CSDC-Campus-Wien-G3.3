package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import at.ac.fhcampuswien.NewsApi;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
         //Menu menu = new Menu();
         //menu.start();
         //launch();

        NewsApi api = new NewsApi();
        try {
            System.out.println(api.request("https://newsapi.org/v2/everything?q=putin&apiKey=2f62c3bf2ba84f97adb6eaf246dccced"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
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
