package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import static sample.Main.toEditScreen;

public class AddScreenController {

    public void finishAdding(){
        toEditScreen(); //TODO poslat id kategorie
    }

    public void addBtnClicked() {
        // TODO vytvorit flashcardu, ulozit
        toEditScreen(); // TODO poslat id kategorie
    }

}
