package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
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
        for(Category cat : Main.getCategories()){
            if(!categories.contains(cat)) categories.add(cat);
        }
        table.setItems(categories);
    }


    public void editBtnClicked() throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            //ukladam si kategoriu do statickej premennej editCategory
            //editCategory = table.getSelectionModel().getSelectedItem();
            idOfSelectedCategory = table.getSelectionModel().getSelectedItem().getId();
            //editCategory = Main.getCategories().get(skuska.getId()-1);
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
            //Main.saveCategories();
            Main.loadCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for(Category cat : Main.getLoadedCategories()){
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
