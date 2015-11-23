package sample;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static sample.Main.toEditScreen;

public class EditOneScreenController {

    @FXML
    ImageView rightArrow;

    @FXML
    ImageView leftArrow;

    Image l1 = new Image("l1.png");
    Image l2 = new Image("l2.png");
    Image r1 = new Image("r1.png");
    Image r2 = new Image("r2.png");

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
}
