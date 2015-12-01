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

import java.io.IOException;
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
    private TableView<Flashcard> table1;

    @FXML
    TableColumn idCol;
    @FXML
    TableColumn titleCol;
    @FXML
    TextField nameOfCategory;



        // TODO nahadzat prvky do flashcardsForactualCategory - hadze error - nejak to osefovat
    private ObservableList<Flashcard> flashcardsForActualCategory = FXCollections.observableArrayList();



    public void finishEditing() throws IOException {
        FXMLLoader.load(getClass().getResource("launcherScreen.fxml"));
        backToLauncher();
    }

    public void openAddScreen(){
        toAddScreen();
    }

    public void openEditOneScreen(){
        toEditOneScreen();
    }

    public EditScreenController() throws IOException {
        if (idOfSelectedCategory != -1) {
            System.out.println("zvolena kategoria id: " + Main.getCategories().get(idOfSelectedCategory-1).getId());
            System.out.println(Main.getCategories().get(idOfSelectedCategory-1).getTitleOfCategory());
            // nameOfCategory.setText(editCategory.getTitleOfCategory()); // TODO opravit
            System.out.println("ukladaam to teraz");
            //Main.saveCategories();
            Main.loadCategories();
            System.out.println("id: " + (idOfSelectedCategory-1));
            System.out.println("flashcards v id: " + Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().size());
            for(Flashcard fc : Main.getCategories().get(idOfSelectedCategory-1).getFlashcards()){

                flashcardsForActualCategory.add(fc);
                System.out.println("added");
            }
            //TODO spravit zobrazovanie v table1
            //table1.getItems().setAll(flashcardsForActualCategory);
            //table1.setItems(flashcardsForActualCategory);
        }


    }

    @FXML
    public void setCategoryName() throws IOException {
        nameOfCategory.setText(Main.getCategories().get(idOfSelectedCategory-1).getTitleOfCategory());
    }

}
