package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import static sample.Main.*;


public class LauncherScreenController implements Initializable {


    @FXML
    TextField categoryName;

    @FXML
    private TableView<Category> table;

    @FXML
    TableColumn idCol;
    @FXML
    TableColumn titleCol;



    private final ObservableList<Category> categories = FXCollections.observableArrayList(
            new Category(1, "test"),
            new Category(2, "first cat"),
            new Category(3, "first cat")
    );




    public void addCategoryBtnClicked(){
        System.out.println(categoryName.getText());
        categories.add(new Category(1, categoryName.getText())); // TODO id namiesto 1
        //TODO reinicalizovat controler?

    }


    public void editBtnClicked() throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Category category = table.getSelectionModel().getSelectedItem();
            //System.out.print(category.getId());

//            FXMLLoader loader = new FXMLLoader(
//                    getClass().getResource("editscreen.fxml"));
//            EditScreenController controller =
//                    loader.<EditScreenController>getController();
//            controller.initData(category);

            //categoryId = category.getId();
            //musim refreshnut controler kvoli aktualizacii dat
            FXMLLoader.load(getClass().getResource("editScreen.fxml"));
            toEditScreen();
        }
    }

    public void presentationBtnClicked(){
        toPresentationScreen();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.getItems().setAll(this.categories);

//        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
//                //Check whether item is selected and set value of selected item to Label
//                if(table.getSelectionModel().getSelectedItem() != null)
//                {
//
//                    Category category = table.getSelectionModel().getSelectedItem();
//                    //System.out.println(category.getId());
//
//                }
//            }
//        });
    }


}
