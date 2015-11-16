package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.toEditScreen;
import static sample.Main.toPresentationScreen;

public class LauncherScreenController implements Initializable {

    public void editBtnClicked(){
        toEditScreen();
    }

    public void presentationBtnClicked(){
        toPresentationScreen();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
