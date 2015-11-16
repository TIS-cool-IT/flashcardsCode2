package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;

import static sample.Main.backToLauncher;

public class PresentationScreenController {
    @FXML
    Group rectOneImage;

    @FXML
    Group rectTwoImages;

    @FXML
    Label presentationStatus;

    public void finishPresentation(){
        backToLauncher();
    }
}
