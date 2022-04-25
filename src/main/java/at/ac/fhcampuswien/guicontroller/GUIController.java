package at.ac.fhcampuswien.guicontroller;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GUIController {

    private AppController ctrl = new AppController();
    private Stage stage;
    private boolean headlines, bitcoin;
    private static int count = 0;

    List<Article> articles = new LinkedList() {{
        add(new Article("Author1", "Article1"));
        add(new Article("Author1", "Bitcoin1"));
        add(new Article("Author1", "Article2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Spiderman2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
        add(new Article("Author1", "Bitcoin2"));
    }};

    public boolean getHeadlines() {
        return this.headlines;
    }

    public void setHeadlines(boolean bool) {
        this.headlines = bool;
    }

    public boolean getBitcoin() {
        return this.bitcoin;
    }

    public void setBitcoin(boolean bool) {
        this.bitcoin = bool;
    }

    @FXML
    private VBox vbox = new VBox();

    @FXML
    private Label label = new Label();

    @FXML
    private Button buttonQuit = new Button();

    @FXML
    private Button buttonRight = new Button();

    @FXML
    private Button buttonLeft = new Button();

    {
        ctrl.setArticles(articles);
        setBitcoin(false);
        setHeadlines(false);
    }

    @FXML
    public void buttonHeadlinesClicked() throws IOException {
        count = 0;
        buttonLeft.setVisible(false);
        vbox.getChildren().clear();
        vbox.setVisible(false);
        setBitcoin(false);
        StringBuilder text = new StringBuilder();
        if (ctrl.getTopHeadlinesAustria().size() > 5) {
            setHeadlines(true);
            buttonRight.setVisible(true);
            for (; count < 5; count++)
                text.append(ctrl.getTopHeadlinesAustria().get(count));
        } else
            for (Article x : ctrl.getTopHeadlinesAustria())
                text.append(x.toString());

        label.setText(text.toString());
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void buttonBitcoinClicked() {
        count = 0;
        buttonLeft.setVisible(false);
        setHeadlines(false);
        vbox.getChildren().clear();
        vbox.setVisible(false);
        StringBuilder text = new StringBuilder();
        if (ctrl.getAllNewsBitcoin().size() > 5) {
            setBitcoin(true);
            buttonRight.setVisible(true);
            for (; count < 5; count++)
                text.append(ctrl.getAllNewsBitcoin().get(count));
        } else {
            for (Article x : ctrl.getAllNewsBitcoin())
                text.append(x.toString());
        }
        label.setText(text.toString());
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void buttonLeftClicked() throws IOException{
        buttonLeft.setVisible(false);
        buttonRight.setVisible(true);
        StringBuilder text = new StringBuilder();
        count -= 5;
        do {
            count--;
        }
        while (count % 5 != 0);
        int i = count;

        vbox.getChildren().clear();
        vbox.setVisible(false);

        if (getBitcoin()) {
            if (count >= 5)
                buttonLeft.setVisible(true);
            for (; count < i + 5; count++)
                text.append(ctrl.getAllNewsBitcoin().get(count));
        } else if (getHeadlines()) {
            if (count >= 5)
                buttonLeft.setVisible(true);
            for (; count < i + 5; count++)
                text.append(ctrl.getTopHeadlinesAustria().get(count));
        }
        label.setText(text.toString());
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void buttonRightClicked() throws IOException{
        vbox.getChildren().clear();
        vbox.setVisible(false);
        buttonRight.setVisible(false);
        buttonLeft.setVisible(true);
        int i = count;

        StringBuilder text = new StringBuilder();
        if (getHeadlines())
            if (ctrl.getTopHeadlinesAustria().size() > count + 5) {
                buttonRight.setVisible(true);
                for (; count < i + 5; count++)
                    text.append(ctrl.getTopHeadlinesAustria().get(count));
            } else {
                for (; count < ctrl.getTopHeadlinesAustria().size(); count++)
                    text.append(ctrl.getTopHeadlinesAustria().get(count));
            }
        if (getBitcoin())
            if (ctrl.getAllNewsBitcoin().size() > count + 5) {
                buttonRight.setVisible(true);
                for (; count < i + 5; count++)
                    text.append(ctrl.getAllNewsBitcoin().get(count));
            } else {
                for (; count < ctrl.getAllNewsBitcoin().size(); count++)
                    text.append(ctrl.getAllNewsBitcoin().get(count));
            }
        label.setText(text.toString());
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void buttonCountClicked() {
        buttonRight.setVisible(false);
        buttonLeft.setVisible(false);
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
}