package at.ac.fhcampuswien.guicontroller;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import at.ac.fhcampuswien.NewsAPIException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class GUIController {

    private AppController ctrl = AppController.getInstance();
    private List<Article> articles;
    private Stage stage;
    private int count = 0;
    private boolean topHeadlines = false, bitcoin = false, descriptionLength = false, fifteenBool = false;

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

    @FXML
    private Button seeArticle = new Button();

    @FXML
    private Button downloadArticles = new Button();

    @FXML
    private Button fifteen = new Button();

    @FXML
    private Button provider = new Button();

    @FXML
    private Button author = new Button();

    @FXML
    private Button source = new Button();

    @FXML
    private Button desLength = new Button();


    @FXML
    public void buttonHeadlinesAustriaClicked() {
        vbox.setVisible(true);
        seeArticle.setVisible(true);
        downloadArticles.setVisible(true);
        fifteen.setVisible(true);
        provider.setVisible(true);
        source.setVisible(true);
        author.setVisible(true);
        desLength.setVisible(true);
        //Left Button is invisible if we are on page 1 - topHeadlines = true because we are looking at the topHeadlines
        //if we changed from bitcoinNews to topHeadlines - set count to 0

        if (this.bitcoin)
            count = 0;

        if (count == 0)
            buttonLeft.setVisible(false);
        else
            buttonLeft.setVisible(true);
        buttonRight.setVisible(true);

        this.topHeadlines = true;
        this.bitcoin = false;
        this.descriptionLength = false;
        this.fifteenBool = false;

        //clear the vbox
        vbox.getChildren().clear();
        StringBuilder text = new StringBuilder();

        if (ctrl.getTopHeadlinesAustria().size() != 0)
            text.append(ctrl.getTopHeadlinesAustria().get(this.count));

        //if there are no more articles disable the right button
        if (ctrl.getTopHeadlinesAustria().size() - 1 == this.count)
            buttonRight.setVisible(false);

        label.setText(text.toString());
        vbox.getChildren().add(label);
    }

    @FXML
    public void buttonBitcoinClicked() {
        vbox.setVisible(true);
        seeArticle.setVisible(true);
        downloadArticles.setVisible(true);
        fifteen.setVisible(true);
        provider.setVisible(true);
        source.setVisible(true);
        author.setVisible(true);
        desLength.setVisible(true);
        //Left Button is invisible if we are on page 1 - bitcoin = true because we are looking at the bitcoin news
        //If we changed from topHeadlines to bitcoinNews - set count to 0

        if (this.topHeadlines)
            count = 0;

        if (count == 0)
            buttonLeft.setVisible(false);
        else
            buttonLeft.setVisible(true);
        buttonRight.setVisible(true);

        this.bitcoin = true;
        this.topHeadlines = false;
        this.descriptionLength = false;
        this.fifteenBool = false;

        //clear the vbox
        vbox.getChildren().clear();
        StringBuilder text = new StringBuilder();

        if (ctrl.getAllNewsBitcoin().size() != 0)
            text.append(ctrl.getAllNewsBitcoin().get(this.count));

        //if there are no more articles disable the right button
        if (ctrl.getAllNewsBitcoin().size() - 1 == this.count)
            buttonRight.setVisible(false);

        label.setText(text.toString());
        vbox.getChildren().add(label);
    }

    @FXML
    public void buttonLeftClicked() {
        this.count--;
        if(this.fifteenBool)
            fifteenClicked();
        else if (this.descriptionLength)
            desLengthClicked();
        else if (this.bitcoin)
            buttonBitcoinClicked();
        else
            buttonHeadlinesAustriaClicked();
    }

    @FXML
    public void buttonRightClicked() {
        this.count++;
        if(this.fifteenBool)
            fifteenClicked();
        else if (this.descriptionLength)
            desLengthClicked();
        else if (this.bitcoin)
            buttonBitcoinClicked();
        else
            buttonHeadlinesAustriaClicked();
    }

    @FXML
    public void buttonCountClicked() {
        seeArticle.setVisible(false);
        downloadArticles.setVisible(false);
        buttonRight.setVisible(false);
        buttonLeft.setVisible(false);
        fifteen.setVisible(false);
        provider.setVisible(false);
        source.setVisible(false);
        author.setVisible(false);
        desLength.setVisible(false);
        vbox.getChildren().clear();
        try {
            label.setText("There are currently " + (ctrl.getArticleCount()) + " Article(s) available!");
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void seeArticleClicked() {
        String url = new String();
        try {
            if (this.topHeadlines)
                url = ctrl.getTopHeadlinesAustria().get(this.count).getUrl();
            else
                url = ctrl.getAllNewsBitcoin().get(this.count).getUrl();
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void downloadArticlesClicked() {
        //Code for textfile download here
        try {
            BufferedWriter writer;
            if (this.topHeadlines) {
                writer = new BufferedWriter(new FileWriter(ctrl.getTopHeadlinesAustria().get(this.count).getTitle() + ".txt"));
                writer.write(ctrl.getTopHeadlinesAustria().get(this.count).toString());
                System.out.println("Check the folder where this project is stored!");
            } else {
                writer = new BufferedWriter(new FileWriter(ctrl.getAllNewsBitcoin().get(this.count).getTitle() + ".txt"));
                writer.write(ctrl.getAllNewsBitcoin().get(this.count).toString());
                System.out.println("Check the folder where this project is stored!");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void buttonQuitClicked() {
        stage = (Stage) buttonQuit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void fifteenClicked() {
        //reset count
        //this boolean is used for the buttons
        if(!this.fifteenBool)
            count = 0;

        if (this.count == 0)
            buttonLeft.setVisible(false);
        else
            buttonLeft.setVisible(true);

        this.fifteenBool = true;
        this.descriptionLength = false;
        buttonRight.setVisible(true);
        //clear the vbox
        vbox.getChildren().clear();
        StringBuilder text = new StringBuilder();
        if (this.bitcoin) {
            this.articles = ctrl.getHeadlineSmallerFifteen(ctrl.getAllNewsBitcoin());
            if (this.articles.size() != 0)
                text.append(this.articles.get(this.count));
            else
                text.append("No article with a title length smaller than 15 available!");
            if (articles.size() - 1 == this.count)
                buttonRight.setVisible(false);
            if (articles.size() == 0)
                buttonRight.setVisible(false);
        }
        else {
            this.articles = ctrl.getHeadlineSmallerFifteen(ctrl.getTopHeadlinesAustria());
            if (this.articles.size() != 0)
                text.append(this.articles.get(this.count));
            else
                text.append("No article with a title length smaller than 15 available!");
            if (articles.size() - 1 == this.count)
                buttonRight.setVisible(false);
            if (articles.size() == 0)
                buttonRight.setVisible(false);
            }

            label.setText(text.toString());
            vbox.getChildren().add(label);
        }


    @FXML
    public void desLengthClicked() {
        //reset count
        //this boolean is used for the buttons
        if (!this.descriptionLength)
            this.count = 0;

        if (this.count == 0)
            buttonLeft.setVisible(false);
        else
            buttonLeft.setVisible(true);

        buttonRight.setVisible(true);
        this.descriptionLength = true;
        this.fifteenBool = false;
        //clear the vbox
        vbox.getChildren().clear();
        StringBuilder text = new StringBuilder();

        if (this.bitcoin) {

            this.articles = ctrl.SortByDescriptionLength(ctrl.getAllNewsBitcoin());
            if (this.articles.size() != 0)
                text.append(articles.get(this.count));
            if (articles.size() - 1 == this.count)
                buttonRight.setVisible(false);

            label.setText(text.toString());
            vbox.getChildren().add(label);
        } else {

            this.articles = ctrl.SortByDescriptionLength(ctrl.getTopHeadlinesAustria());
            if (this.articles.size() != 0)
                text.append(articles.get(this.count));
            if (articles.size() - 1 == this.count)
                buttonRight.setVisible(false);

            label.setText(text.toString());
            vbox.getChildren().add(label);
        }
    }

    @FXML
    public void authorClicked() {
        buttonLeft.setVisible(false);
        buttonRight.setVisible(false);
        vbox.getChildren().clear();

        label.setText(ctrl.getLongestAuthorName());
        vbox.getChildren().add(label);
    }

    @FXML
    public void providerClicked() {
        buttonLeft.setVisible(false);
        buttonRight.setVisible(false);
        vbox.getChildren().clear();

        label.setText(ctrl.getNYT());
        vbox.getChildren().add(label);
    }

    @FXML
    public void sourceClicked() throws NewsAPIException { //Falls Fehler ist throws entfernen
        buttonLeft.setVisible(false);
        buttonRight.setVisible(false);
        vbox.getChildren().clear();

        label.setText(ctrl.getMostArticles());
        vbox.getChildren().add(label);
    }
}