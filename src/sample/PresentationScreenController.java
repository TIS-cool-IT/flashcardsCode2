package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;

import static sample.Main.backToLauncher;
import static sample.Main.toSettingsScreen;

public class PresentationScreenController {
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
}
