package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static sample.Main.idOfSelectedCategory;
import static sample.Main.idOfSelectedFlashcard;
import static sample.Main.toEditScreen;

public class EditOneScreenController implements Initializable {

    @FXML
    TextField inputQText;

    @FXML
    ImageView rightArrow;

    @FXML
    ImageView leftArrow;

    private Image l1 = new Image("l1.png");
    private Image l2 = new Image("l2.png");
    private Image r1 = new Image("r1.png");
    private Image r2 = new Image("r2.png");

    public void finishEditing(){
        toEditScreen();
    }

    public void onRightArrowClicked(){
        //TODO open next flashcard
    }

    public void onRightPressed(){
        rightArrow.setImage(r2);
    }

    public void onRightReleased(){
        rightArrow.setImage(r1);
    }

    public void onLeftArrowClicked(){
        //TODO open previous flashcard
    }

    public void onLeftPressed(){
        leftArrow.setImage(l2);
    }

    public void onLeftReleased(){
        leftArrow.setImage(l1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(idOfSelectedFlashcard != -1){
            Flashcard selected = Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().get(idOfSelectedFlashcard);
            inputQText.setText("hnoj");
        }
    }
}
