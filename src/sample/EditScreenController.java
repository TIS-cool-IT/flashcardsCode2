package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.*;

public class EditScreenController{
    int idCategory;

    @FXML
    Button backBtn;


    @FXML
    private TableView<Category> table;

    @FXML
    TableColumn idCol;
    @FXML
    TableColumn titleCol;



    private final ObservableList<Category> flashcardsForActualCategory = FXCollections.observableArrayList(
//            new Flashcard(1, "firstTitle", false, , ,),
//            new Flashcard(1, "test"),
//            new Flashcard(1, "test"),
//            new Flashcard(1, "test")
    );

    public void finishEditing(){
        backToLauncher();
    }

    public void openAddScreen(){
        toAddScreen();
    }

    public void openEditOneScreen(){
        toEditOneScreen();
    }

    public EditScreenController() {
        idCategory = categoryId;
        System.out.print(idCategory);
//        table.getItems().setAll(this.flashcardsForActualCategory);
    }

}
