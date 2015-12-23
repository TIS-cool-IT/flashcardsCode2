package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static sample.Main.*;

public class EditScreenController{

    private int idCategory;
    private String title;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<TableRecord> table1;


    @FXML
    private TableColumn<TableRecord, String> idCol;

    @FXML
    private TableColumn<TableRecord, String> tvQuestion;

    @FXML
    private TableColumn<TableRecord, String> tvAnswer;

    @FXML
    private TableColumn<TableRecord, String> tvQImage;

    @FXML
    private TableColumn<TableRecord, String> tvAImage;

    @FXML
    private TableColumn<TableRecord, String> tvQSound;

    @FXML
    private TableColumn<TableRecord, String> tvASound;

    @FXML
    private TableColumn<TableRecord, String> tvReversed;

    @FXML
    private TextField nameOfCategoryInput;

        // TODO nahadzat prvky do flashcardsForactualCategory - hadze error - nejak to osefovat
    private ObservableList<TableRecord> flashcardsForActualCategory = FXCollections.observableArrayList();



    public void finishEditing() throws IOException {
        FXMLLoader.load(getClass().getResource("launcherScreen.fxml"));
        Main.saveCategories();
        backToLauncher();
    }

    public void openAddScreen(){
        toAddScreen();

    }

    public void openEditOneScreen() throws IOException {
        if (savedSelectedFlashcard()){
            FXMLLoader.load(getClass().getResource("editOneScreen.fxml"));
            toEditOneScreen();
        }
    }

    public void saveChanges() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Rename Category");
        alert.setContentText("Do you want change category name from \"" + title + "\" to \"" + nameOfCategoryInput.getText()+"\"?");
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            if (Main.getCategories().get(idOfSelectedCategory-1).setTitleOfCategory(nameOfCategoryInput.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Category name was changed to \"" + nameOfCategoryInput.getText() + "\"");
                Main.saveCategories();
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Category name can't be changed to \"" + nameOfCategoryInput.getText() + "\"",
                        "Wrong new name",
                        JOptionPane.ERROR_MESSAGE);
                nameOfCategoryInput.setText(title);
            }
        }
    }

    public boolean savedSelectedFlashcard() {
        if (table1.getSelectionModel().getSelectedItem() != null) {
            idOfSelectedFlashcard = table1.getSelectionModel().getSelectedIndex();
            return true;
        }
        return false;
    }


    public void deleteFlashcard() {
        if (table1.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Flashcard");
            String s = "Do you want permanently delete selected Flashcard?";
            alert.setContentText(s);

            Optional<ButtonType> result = alert.showAndWait();

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                Flashcard card = Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().get(table1.getSelectionModel().getSelectedIndex());
                Main.getCategories().get(idOfSelectedCategory-1).removeFlashcard(card);

                deleteDirectory(new File("C:\\FlashCard\\Categories\\" + card.getFlashcardDirectory()));
                init();
            }
        }
    }

    public static boolean deleteDirectory(File directory) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
        return(directory.delete());
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        nameOfCategoryInput.appendText(title);
    }

    protected void addFlashcardToTable() {
        table1.getItems().clear();
        ObservableList<TableRecord> data = table1.getItems();
        nameOfCategoryInput.setText(Main.getCategories().get(idOfSelectedCategory-1).getTitleOfCategory());
        //System.out.println("title: " + Main.getCategories().get(idOfSelectedCategory-1).getTitleOfCategory());
        for(Flashcard fc : Main.getCategories().get(idOfSelectedCategory-1).getFlashcards()){
            //TODO nie je lepsie ukladat miesto IMAGE a SOUND skorej FILE?
            ArrayList<String> imagesQ = new ArrayList<>();
            ArrayList<String> imagesA = new ArrayList<>();
            ArrayList<String> soundsQ = new ArrayList<>();
            ArrayList<String> soundsA = new ArrayList<>();
            for(File i : fc.getQuestion().getImages()){
                if(i != null)
                imagesQ.add(i.getName());
            }
            for(File i : fc.getQuestion().getSounds()){
                if(i != null)
                soundsQ.add(i.getName());
            }
            for(File i : fc.getAnswer().getImages()){
                if(i != null)
                imagesA.add(i.getName());
            }
            for(File i : fc.getAnswer().getSounds()){
                if(i != null)
                soundsA.add(i.getName());
            }
            data.add(new TableRecord(fc.getId(),
                    fc.getQuestion().getText(),
                    fc.getAnswer().getText(),
                    imagesQ.toString(),
                    imagesA.toString(),
                    soundsQ.toString(),
                    soundsA.toString(),
                    Boolean.toString(fc.getReversed())
            ));
        }

    }

    public void init() {
        idCol.setStyle("-fx-alignment: CENTER;");
        idCol.setSortable(false);
        tvQuestion.setStyle("-fx-alignment: CENTER;");
        tvQuestion.setSortable(false);
        tvAnswer.setStyle("-fx-alignment: CENTER;");
        tvAnswer.setSortable(false);
        tvQImage.setStyle("-fx-alignment: CENTER;");
        tvQImage.setSortable(false);
        tvAImage.setStyle("-fx-alignment: CENTER;");
        tvAImage.setSortable(false);
        tvQSound.setStyle("-fx-alignment: CENTER;");
        tvQSound.setSortable(false);
        tvASound.setStyle("-fx-alignment: CENTER;");
        tvASound.setSortable(false);
        tvReversed.setStyle("-fx-alignment: CENTER;");
        tvReversed.setSortable(false);

        if (idOfSelectedCategory != -1) {
            System.out.println(idOfSelectedCategory);
            idCategory = Main.getCategories().get(idOfSelectedCategory - 1).getId();
            title = Main.getCategories().get(idOfSelectedCategory - 1).getTitleOfCategory();
            System.out.println("zvolena kategoria id: " + idCategory + ",title: " + title);
            // TODO nefunguje nastavenie textu v textfielde...
            //TODO funguje len po kliknuti na REFRESH
            //nameOfCategoryInput.setText("arasid");

            ObservableList<TableRecord> data = table1.getItems();
            File f = new File("prd");

            idCol.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("id"));
            tvQuestion.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("question"));
            tvAnswer.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("answer"));
            tvQImage.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("imagesQ"));
            tvAImage.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("imagesA"));
            tvQSound.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("soundsQ"));
            tvASound.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("soundsA"));
            tvReversed.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("reversed"));

            table1.setItems(data);
            //table1.getColumns().addAll(idCol, tvQuestion, tvAnswer, tvQImage, tvAImage, tvQSound, tvASound, tvReversed);
            addFlashcardToTable();
        }
    }

}
