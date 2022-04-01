package at.ac.fhcampuswien.guicontroller;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.util.LinkedList;
import java.util.List;

public class GUIController {

    private AppController ctrl = new AppController();

    List<Article> articles = new LinkedList(){{
        add(new Article("Author1", "Article1"));
        add(new Article("Author1", "Bitcoin1"));
        add(new Article("Author1", "Article2"));
        add(new Article("Author1", "Bitcoin2"));
    }};

    {
        ctrl.setArticles(articles);
    }

    private Label label = new Label();
    private Font font = new Font("Serif", 20);
    private Stage stage;

    @FXML
    private VBox vbox = new VBox();

    @FXML
    private AnchorPane anchorPane = new AnchorPane();

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
        vbox.setVisible(false);
        vbox.getChildren().clear();
        vbox.setAlignment(Pos.TOP_CENTER);
        label.setFont(font);
        StringBuilder text = new StringBuilder();
        for(Article x : ctrl.getTopHeadlinesAustria())
            text.append(x.toString());
        label.setText(text.toString());
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void buttonBitcoinClicked() {
        vbox.setVisible(false);
        vbox.getChildren().clear();
        vbox.setAlignment(Pos.TOP_CENTER);
        label.setFont(font);
        StringBuilder text = new StringBuilder();
        for(Article x : ctrl.getAllNewsBitcoin())
            text.append(x.toString());
        label.setText(text.toString());

        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void buttonCountClicked() {
        vbox.getChildren().clear();
        label.setFont(font);
        label.setText("There are currently " + (ctrl.getArticleCount()) + " Article(s) available!");
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void buttonQuitClicked() {
        stage = (Stage) buttonQuit.getScene().getWindow();
        stage.close();
    }
}
