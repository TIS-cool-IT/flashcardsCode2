package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    @FXML
    TextField nameOfCategory;



        // TODO nahadzat prvky do flashcardsForactualCategory - hadze error - nejak to osefovat
//    private final List<Category> flashcardsForActualCategory = FXCollections.observableArrayList(
//            new Flashcard(1, true, editCategory,
//                    new FlashcardFace("question", FXCollections.observableArrayList(new ArrayList<E>()), FXCollections.observableArrayList(new ArrayList<E>())),
//                    new FlashcardFace("answer", FXCollections.observableArrayList(new ArrayList<E>()), FXCollections.observableArrayList(new ArrayList<E>()))
//            ),
//            new Flashcard(1, true, editCategory,
//                    new FlashcardFace("question", FXCollections.observableArrayList(new ArrayList<E>()), FXCollections.observableArrayList(new ArrayList<E>())),
//                    new FlashcardFace("answer", FXCollections.observableArrayList(new ArrayList<E>()), FXCollections.observableArrayList(new ArrayList<E>()))
//            )
//
//    );



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
        if (editCategory != null) {
            System.out.println("zvolena kategoria id: " + editCategory.getId());
            System.out.print(editCategory.getTitleOfCategory());
            // nameOfCategory.setText(editCategory.getTitleOfCategory()); TODO opravit
        }

//        table.getItems().setAll(this.flashcardsForActualCategory);

    }

}
