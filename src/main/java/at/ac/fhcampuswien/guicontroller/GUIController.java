package at.ac.fhcampuswien.guicontroller;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.NewsAPIException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;

public class GUIController {

    private AppController ctrl = new AppController();
    private Stage stage;
    private int count = 0;
    private boolean topHeadlines = false, bitcoin = false;

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
    private Button downloadArticle = new Button();

    @FXML
    private Button fifteen = new Button();

    @FXML
    private Button provider = new Button();

    @FXML
    private Button author= new Button();

    @FXML
    private Button source = new Button();

    @FXML
    private Button desLength = new Button();



    @FXML
    public void buttonHeadlinesAustriaClicked() {
        vbox.setVisible(true);
        seeArticle.setVisible(true);
        downloadArticle.setVisible(true);
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
        downloadArticle.setVisible(true);
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
        if (this.topHeadlines)
            buttonHeadlinesAustriaClicked();
        else
            buttonBitcoinClicked();
    }

    @FXML
    public void buttonRightClicked() {
        this.count++;
        if (this.topHeadlines)
            buttonHeadlinesAustriaClicked();
        else
            buttonBitcoinClicked();
    }

    @FXML
    public void buttonCountClicked() {
        seeArticle.setVisible(false);
        downloadArticle.setVisible(false);
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
        }catch(NewsAPIException e){
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
        } catch(NewsAPIException e){
            System.out.println(e.getMessage());
        }
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void downloadArticleClicked() {
        String url = new String();
        try {
            if (this.topHeadlines)
                url = ctrl.getTopHeadlinesAustria().get(this.count).getUrl();
            else
                url = ctrl.getAllNewsBitcoin().get(this.count).getUrl();
        } catch(NewsAPIException e){
            System.out.println(e.getMessage());
        }

        /*Code for textfile download here
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }

    @FXML
    public void buttonQuitClicked() {
        stage = (Stage) buttonQuit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void fifteenClicked() {
        vbox.getChildren().clear();
        ctrl.getHeadlineSmallerFifteen(ctrl.currentList());

    }


    @FXML
    public void desLengthClicked() {
        vbox.getChildren().clear();
    }

    @FXML
    public void authorClicked() {
        vbox.getChildren().clear();

        label.setText(ctrl.getLongestAuthorName());
        vbox.getChildren().add(label);
    }

    @FXML
    public void providerClicked() {
        vbox.getChildren().clear();

        label.setText(ctrl.getNYT());
        vbox.getChildren().add(label);
    }

    @FXML
    public void sourceClicked() {

    }
}