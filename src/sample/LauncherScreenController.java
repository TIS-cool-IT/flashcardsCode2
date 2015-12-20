package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
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
        for(Category cat : Main.getCategories()){
            if(!categories.contains(cat)) categories.add(cat);
        }
        table.setItems(categories);
    }

    @FXML
    protected void deleteCategory() throws IOException {
        if (savedSelectedCategoryId()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Category");
            String s = "Do you want permanently delete category?";
            alert.setContentText(s);

            Optional<ButtonType> result = alert.showAndWait();

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.out.println(table.getSelectionModel().getSelectedItem().getTitleOfCategory());
                ArrayList<Category> al = Main.getCategories();
                al.remove(table.getSelectionModel().getSelectedItem());
                Main.setNewCategories(al);

                // recursive deleting directories and files
                File file = new File("C:\\FlashCard\\Categories\\" + table.getSelectionModel().getSelectedItem().getTitleOfCategory());
                String[] children = file.list();
                if (children != null) {
                    for (int i = 0; i < children.length; i++) {
                        Category.deleteDirectory(new File(children[i]));
                    }
                }
                Category.deleteDirectory(file);

                init();
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Please, select category to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void editBtnClicked() throws IOException {
        if (savedSelectedCategoryId()){
            FXMLLoader.load(getClass().getResource("editScreen.fxml"));
            toEditScreen();
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Please, select category to edit", "Error", JOptionPane.ERROR_MESSAGE);
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

    public void presentationBtnClicked() throws IOException {
        if (savedSelectedCategoryId()) {
            if(Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().size()==0){
                JOptionPane.showMessageDialog(new JFrame(), "Category has 0 flashcards", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                toPresentationScreen();
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Please, select category to presentation", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void init() {
        idCol.setStyle("-fx-alignment: CENTER;");
        idCol.setSortable(false);
        countCol.setStyle("-fx-alignment: CENTER;");
        countCol.setSortable(false);
        titleCol.setStyle("-fx-alignment: CENTER;");
        titleCol.setSortable(false);
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
    }


}
