package at.ac.fhcampuswien.guicontroller;

import at.ac.fhcampuswien.AppController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public void buttonHeadlinesAustriaClicked() {
        vbox.setVisible(true);
        seeArticle.setVisible(true);
        downloadArticle.setVisible(true);
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
        vbox.getChildren().clear();
        label.setText("There are currently " + (ctrl.getArticleCount()) + " Article(s) available!");
        vbox.getChildren().add(label);
        vbox.setVisible(true);
    }

    @FXML
    public void seeArticleClicked() {
        String url = new String();
        if (this.topHeadlines)
            url = ctrl.getTopHeadlinesAustria().get(this.count).getUrl();
        else
            url = ctrl.getAllNewsBitcoin().get(this.count).getUrl();
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void downloadArticleClicked() {
        String url = new String();
        if (this.topHeadlines)
            url = ctrl.getTopHeadlinesAustria().get(this.count).getUrl();
        else
            url = ctrl.getAllNewsBitcoin().get(this.count).getUrl();

        //Code for textfile download here
    }

    @FXML
    public void buttonQuitClicked() {
        stage = (Stage) buttonQuit.getScene().getWindow();
        stage.close();
    }
}