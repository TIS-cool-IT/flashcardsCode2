package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import static sample.Main.backToLauncher;
import static sample.Main.toAddScreen;
import static sample.Main.toEditOneScreen;

public class EditScreenController {

    @FXML
    Button backBtn;

    public void finishEditing(){
        backToLauncher();
    }

    public void openAddScreen(){
        toAddScreen();
    }

    public void openEditOneScreen(){
        toEditOneScreen();
    }
}
