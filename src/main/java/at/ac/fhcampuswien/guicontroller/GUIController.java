package at.ac.fhcampuswien.guicontroller;

import at.ac.fhcampuswien.AppController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

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
    public void buttonHeadlinesAustriaClicked() throws IOException {
        vbox.setVisible(true);
        //Left Button is invisible if we are on page 1 - topHeadlines = true because we are looking at the topHeadlines

        if(this.bitcoin)
            count=0;

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
    public void buttonBitcoinClicked() throws IOException {
        vbox.setVisible(true);
        //Left Button is invisible if we are on page 1 - bitcoin = true because we are looking at the bitcoin news

        if(this.topHeadlines)
            count=0;

        if (count == 0 ||false)
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
    public void buttonCustomNewsClicked() {

    }

    @FXML
    public void buttonLeftClicked() throws IOException {
        this.count--;
        if (this.topHeadlines)
            buttonHeadlinesAustriaClicked();
        else
            buttonBitcoinClicked();
    }

    @FXML
    public void buttonRightClicked() throws IOException {
        this.count++;
        if (this.topHeadlines)
            buttonHeadlinesAustriaClicked();
        else
            buttonBitcoinClicked();
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