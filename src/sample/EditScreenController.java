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
    private TableView<Category> table;

    @FXML
    TableColumn idCol;
    @FXML
    TableColumn titleCol;
    @FXML
    TextField nameOfCategory;



        // TODO nahadzat prvky do flashcardsForactualCategory - hadze error - nejak to osefovat
    private final ObservableList<Flashcard> flashcardsForActualCategory = FXCollections.observableArrayList();



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
            Main.saveCategories();
            Main.loadCategories();
            System.out.println(Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().size());
            for(Category fc : Main.getCategories()){

                //flashcardsForActualCategory.add(fc);
                System.out.println(fc.getFlashcards().size());
            }
        }

//        table.getItems().setAll(this.flashcardsForActualCategory);
    }

    @FXML
    public void setCategoryName() throws IOException {
        nameOfCategory.setText(Main.getCategories().get(idOfSelectedCategory-1).getTitleOfCategory());
    }

}
