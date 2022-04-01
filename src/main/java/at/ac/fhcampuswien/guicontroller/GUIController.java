package at.ac.fhcampuswien.guicontroller;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.LinkedList;
import java.util.List;

public class GUIController {

    private AppController ctrl = new AppController();
    private Stage stage;

    List<Article> articles = new LinkedList(){{
        add(new Article("Author1", "Article1"));
        add(new Article("Author1", "Bitcoin1"));
        add(new Article("Author1", "Article2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
    }};

    {
        ctrl.setArticles(articles);
    }


    @FXML
    private VBox vbox = new VBox();

    @FXML
    private Label label = new Label();

    @FXML
    private Button buttonQuit;

    @FXML
    public void buttonHeadlinesClicked() {
        vbox.getChildren().clear();
        vbox.setVisible(false);
        StringBuilder text = new StringBuilder();
        for(Article x : ctrl.getTopHeadlinesAustria())
            text.append(x.toString());
        label.setText(text.toString());
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void buttonBitcoinClicked() {
        vbox.getChildren().clear();
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
        label.setText("There are currently " + (ctrl.getArticleCount()) + " Article(s) available!");
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void buttonQuitClicked() {
        stage = (Stage) buttonQuit.getScene().getWindow();
        stage.close();
    }
    //test
}
