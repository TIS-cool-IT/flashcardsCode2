package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.Main.backToLauncher;
import static sample.Main.idOfSelectedCategory;


public class PresentationScreenController implements Initializable {
    @FXML
    Group rectOneImage;

    @FXML
    Group rectTwoImages;

    @FXML
    Label presentationStatus;

    @FXML
    Group presentationGroup;

    @FXML
    Group settingsGroup;

    @FXML
    ImageView settings;

    @FXML
    Button rightBtn;

    @FXML
    Button wrongBtn;

    private Image s1 = new Image("s1.png");
    private Image s2 = new Image("s2.png");

    private int numberOfFlashcards;
    private int correctAnswers;
    private int wrongAnswers;
    private Category category;

    public void finishPresentation(){
        backToLauncher();
    }

    public void onSettingsClicked(){
        if (presentationGroup.isVisible()){
            presentationGroup.setVisible(false);
            settingsGroup.setVisible(true);
        }
        else {
            presentationGroup.setVisible(true);
            settingsGroup.setVisible(false);
        }
    }

    public void onSettingsPressed(){
        settings.setImage(s2);
    }

    public void onSettingsReleased(){
        settings.setImage(s1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(idOfSelectedCategory != -1) category = Main.getCategories().get(idOfSelectedCategory-1);
        correctAnswers = 0;
    }

    public void rightAnswer(){
        correctAnswers++;
        updatePresentationStatus();
    }

    public void wrongAnswer(){
        correctAnswers--;
        updatePresentationStatus();
    }

    public void setWrongAnswers(){
        wrongAnswers++;
        updatePresentationStatus();
    }

    public void updatePresentationStatus(){
        int seenFlashcards = correctAnswers + wrongAnswers;
        presentationStatus.setText(String.valueOf(seenFlashcards) + "/" + category.getCount());
    }

    public void flipToAnswer(){
        rightBtn.setVisible(true);
        wrongBtn.setVisible(true);
    }
}
