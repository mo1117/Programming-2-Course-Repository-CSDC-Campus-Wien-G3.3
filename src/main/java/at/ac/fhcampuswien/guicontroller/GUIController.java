package at.ac.fhcampuswien.guicontroller;

import at.ac.fhcampuswien.App;
import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.LinkedList;
import java.util.List;

public class GUIController {

    private AppController ctrl = new AppController();

    List<Article> articles = new LinkedList(){{
        add(new Article("Author1", "Article1"));
    }};

    {
        ctrl.setArticles(articles);
    }

    private Label label = new Label("Hallo");
    private Font font = new Font("Serif", 15);

    @FXML
    private VBox vbox = new VBox();

    @FXML
    private Button buttonHeadlines;

    @FXML
    private Button buttonBitcoin;

    @FXML
    private Button buttonCount;

    @FXML
    private Button buttonQuit;

    @FXML
    public void buttonHeadlinesClicked() {
    }

    @FXML
    public void buttonBitcoinClicked() {

    }

    @FXML
    public void buttonCountClicked() {
        vbox.getChildren().clear();
        label.setFont(font);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void buttonQuitClicked() {
    }
}
