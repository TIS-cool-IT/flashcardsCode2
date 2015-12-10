package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static sample.Main.*;
import static sample.PresentationScreenController.getAnswers;
import static sample.PresentationScreenController.getCategoryTitle;

public class AfterPresentationController {
    // pop up
    @FXML
    Label labelCorrect;

    @FXML
    Label labelWrong;

    @FXML
    Label labelTotal;

    @FXML
    Label categoryName;


    public void init(){
        fillPopup();


    }

    public void fillPopup(){
        int[] answers = getAnswers();
        int correctAnswers = answers[0];
        int wrongAnswers = answers[1];
        labelCorrect.setText(String.valueOf(correctAnswers));
        labelWrong.setText(String.valueOf(wrongAnswers));
        labelTotal.setText(String.valueOf(wrongAnswers + correctAnswers));
        categoryName.setText(getCategoryTitle());
    }
    public void closePopup(){
        getPopupPresentationStage().close();
        backToLauncher();

    }
}
