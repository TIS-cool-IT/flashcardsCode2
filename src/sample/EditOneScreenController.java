package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static sample.Main.idOfSelectedCategory;
import static sample.Main.idOfSelectedFlashcard;
import static sample.Main.toEditScreen;

public class EditOneScreenController {

    /*@FXML
    TextField inputQText;

    @FXML
    ImageView rightArrow;

    @FXML
    ImageView leftArrow;*/

    /*private Image l1 = new Image("l1.png");
    private Image l2 = new Image("l2.png");
    private Image r1 = new Image("r1.png");
    private Image r2 = new Image("r2.png");*/

    public void finishEditing(){
        toEditScreen();
    }

    public void onRightArrowClicked(){
        if (idOfSelectedFlashcard == (Main.getCategories().get(idOfSelectedCategory-1).getCount() - 1)) {
            idOfSelectedFlashcard = 0;
        } else {
            idOfSelectedFlashcard = idOfSelectedFlashcard + 1;
        }
        init();
    }

    public void onRightPressed(){
        ImageView iv =(ImageView) Main.getPrimaryStage().getScene().lookup("#rightArrow");
        iv.setImage(new Image("r2.png"));
    }

    public void onRightReleased(){
        ImageView iv =(ImageView) Main.getPrimaryStage().getScene().lookup("#rightArrow");
        iv.setImage(new Image("r1.png"));
    }


    public void onLeftArrowClicked(){
        if (idOfSelectedFlashcard > 0) {
            idOfSelectedFlashcard = idOfSelectedFlashcard - 1;
        } else {
            idOfSelectedFlashcard = Main.getCategories().get(idOfSelectedCategory-1).getCount() - 1;
        }
        init();
    }

    public void onLeftPressed(){
        ImageView iv =(ImageView) Main.getPrimaryStage().getScene().lookup("#leftArrow");
        iv.setImage(new Image("l2.png"));
    }

    public void onLeftReleased(){
        ImageView iv =(ImageView) Main.getPrimaryStage().getScene().lookup("#leftArrow");
        iv.setImage(new Image("l1.png"));
    }

    public void saveChanges(){
        System.out.println("Saving...");
    }

    public void init() {
        if(idOfSelectedFlashcard != -1){
            Flashcard selected = Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().get(idOfSelectedFlashcard);
            TextField tvQ = (TextField) Main.getPrimaryStage().getScene().lookup("#InputQText");
            tvQ.setText(selected.getQuestion().getText());

            TextField tvA = (TextField) Main.getPrimaryStage().getScene().lookup("#InputAText");
            tvA.setText(selected.getAnswer().getText());

            ArrayList<File> questionImages = selected.getQuestion().getImages();

            if(questionImages.get(0) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQImage1");
                btn.setText(questionImages.get(0).getName());
            }
            if(questionImages.get(1) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQImage2");
                btn.setText(questionImages.get(1).getName());
            }

            ArrayList<File> answerImages = selected.getAnswer().getImages();

            if(answerImages.get(0) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnAImage1");
                btn.setText(answerImages.get(0).getName());
            }
            if(answerImages.get(1) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnAImage2");
                btn.setText(answerImages.get(1).getName());
            }

            ///////////////////////////////////////////////////////////////////////////////////
            ArrayList<File> questionSounds = selected.getQuestion().getSounds();

            if(questionSounds.get(0) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQSound1");
                btn.setText(questionSounds.get(0).getName());
            }
            if(questionSounds.get(1) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQSound2");
                btn.setText(questionSounds.get(1).getName());
            }

            ArrayList<File> answerSounds = selected.getAnswer().getSounds();

            if(answerSounds.get(0) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnASound1");
                btn.setText(answerSounds.get(0).getName());
            }
            if(answerSounds.get(1) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnASound2");
                btn.setText(answerSounds.get(1).getName());
            }

            CheckBox cb = (CheckBox) Main.getPrimaryStage().getScene().lookup("#checkboxReverse");
            if(selected.getReversed()) cb.setSelected(true);
            else cb.setSelected(false);


        }
    }
}
