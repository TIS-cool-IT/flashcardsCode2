package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BookScreenController {

    @FXML
    TextArea showFile;


    public void init(File inputFile) {
        try {
            Scanner s = new Scanner(new File(String.valueOf(inputFile))).useDelimiter("\\s+");
            while (s.hasNext()) {
                System.out.println("ts");
                if (s.hasNextInt()) { // check if next token is an int
                    showFile.appendText(s.nextInt() + " "); // display the found integer
                } else {
                    showFile.appendText(s.next() + " "); // else read the next token
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        System.out.println("idem na showfile =====================");
       showFile.setText("lpppppppppppppppppppppppppppppppppppppppppp");
    }




}
