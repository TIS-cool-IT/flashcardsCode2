package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BookScreenController {

    @FXML
    TextArea showFile;


    public void init(File inputFile) {
        String text = "";
        try {
            Scanner s = new Scanner(new File(String.valueOf(inputFile))).useDelimiter("\n");
            while (s.hasNext()) {
                text += s.next()+"\n";
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        showFile.setText(text);
        showFile.setEditable(false);
    }




}
