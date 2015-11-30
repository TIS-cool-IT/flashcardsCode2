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

import javax.swing.*;
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

    private final ObservableList<Category> categories = FXCollections.observableArrayList();

    public void addCategoryBtnClicked() throws IOException {
        System.out.println(categoryName.getText());
        Main.addCategory((new Category(categories.size()+1, categoryName.getText())));
        JOptionPane.showMessageDialog(new JFrame(), "Category '" + categoryName.getText() + "' was saved!");
        table.getItems().clear();
        try {
            for(Category cat : Main.getCategories()){
                if(!categories.contains(cat)) categories.add(cat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        table.setItems(categories);


    }


    public void editBtnClicked() throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            //ukladam si kategoriu do statickej premennej editCategory
            editCategory = table.getSelectionModel().getSelectedItem();
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
        try {
            for(Category cat : Main.getCategories()){
                categories.add(cat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("je tu " + categories.size() + " kategorii");
        table.getItems().setAll(categories);

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
