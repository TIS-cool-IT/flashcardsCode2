package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.security.auth.callback.Callback;
import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

import static sample.Main.*;


public class LauncherScreenController {

    @FXML
    TextField categoryName;

    @FXML
    private TableView<Category> table;

    @FXML
    TableColumn idCol;

    @FXML
    TableColumn titleCol;

    @FXML
    TableColumn countCol;

   // private ArrayList<Category> categories = new ArrayList<Category>();

    private final ObservableList<Category> categories = FXCollections.observableArrayList();

    public void addCategoryBtnClicked() throws IOException {
        String newCategoryName = categoryName.getText().trim();

        System.out.println(newCategoryName);
        if(!newCategoryName.equals("")) {
            Category newCategory;
            if(categories.size() == 0){
                newCategory = new Category(1, categoryName.getText().trim());
            }
            else{
                newCategory = new Category(categories.get(categories.size()-1).getId()+1, newCategoryName);
            }
            // make category directory
            if (newCategory.makeDirectory(newCategory.getCategoryDirectory())) {
                Main.addCategory(newCategory);
                JOptionPane.showMessageDialog(new JFrame(), "Category '" + newCategoryName + "' was saved!");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Category name can't includes characters: \\/:*?\"<>|",
                        "Wrong new name", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Category name can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
        table.getItems().clear();
        //TODO zda sa ze toto vymazavanie nefunguje po pridani mi ukaze tie co boli pred tym dva krat
        // TODO co je toto vo forcykle robit vkuse dopyt po categoriach...treba ulozit do premennej..
        for(Category cat : Main.getCategories()){
            if(!categories.contains(cat)) categories.add(cat);
        }
        table.setItems(categories);
    }

    @FXML
    protected void deleteCategory() throws IOException {
        /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Category");
        String s = "Do you want permanently delete category?";
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            System.out.println(table.getSelectionModel().getSelectedItem().getTitleOfCategory());
            ArrayList<Category> al = Main.getCategories();
            al.remove(table.getSelectionModel().getSelectedItem());
            Main.setNewCategories(al);
            init();
        }*/
    }

    public void editBtnClicked() throws IOException {
        if (savedSelectedCategoryId()){
            FXMLLoader.load(getClass().getResource("editScreen.fxml"));
            toEditScreen();
        }
    }

    public boolean savedSelectedCategoryId() {
        if (table.getSelectionModel().getSelectedItem() != null) {
            idOfSelectedCategory = table.getSelectionModel().getSelectedIndex();
            idOfSelectedCategory++;
            return true;
        }
        return false;
    }

    public void presentationBtnClicked(){
        if (savedSelectedCategoryId()) {
            System.out.println("btn clicked id:" + idOfSelectedCategory);
            toPresentationScreen();
        }
    }


    public void init() {
        idCol.setStyle("-fx-alignment: CENTER;");
        countCol.setStyle("-fx-alignment: CENTER;");
        titleCol.setStyle("-fx-alignment: CENTER;");
        categories.removeAll();
        table.getItems().clear();
        try {
            for(Category cat : Main.getLoadedCategories()) {
                categories.add(cat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        table.setItems(categories);

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
