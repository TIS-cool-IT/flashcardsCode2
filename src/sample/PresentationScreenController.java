package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static sample.Main.backToLauncher;


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

    @FXML
    ImageView settings;

    private Image s1 = new Image("s1.png");
    private Image s2 = new Image("s2.png");

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
}
