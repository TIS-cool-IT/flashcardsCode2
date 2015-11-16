package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import static sample.Main.backToLauncher;

public class EditScreenController {

    @FXML
    Button backBtn;

    public void finishEditing(){
        backToLauncher();
    }
}
