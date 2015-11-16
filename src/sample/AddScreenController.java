package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AddScreenController {

    @FXML
    Label myLabel;

    public void changeT(MouseEvent m){

        myLabel.setText("bum");
        System.out.println("clicked change text");
    }
}
