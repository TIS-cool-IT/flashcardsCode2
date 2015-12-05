package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static sample.Main.*;

public class EditScreenController implements Initializable{

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

    public void openEditOneScreen(){
        toEditOneScreen();
    }



    public EditScreenController() throws IOException{
        if (idOfSelectedCategory != -1) {
            System.out.println("inicializujem screen");
            //Main.saveCategories();
            Main.loadCategories();
            /*for(Flashcard fc : Main.getCategories().get(idOfSelectedCategory-1).getFlashcards()){
                //flashcardsForActualCategory.add(fc);
                System.out.println("added");
            }*/
            //TODO spravit zobrazovanie v table1
            //table1.setItems(flashcardsForActualCategory);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        nameOfCategoryInput.appendText(title);
    }

    @FXML
    protected void addPerson(ActionEvent event) {
        table1.getItems().clear();
        ObservableList<TableRecord> data = table1.getItems();
        nameOfCategoryInput.setText(Main.getCategories().get(idOfSelectedCategory-1).getTitleOfCategory());
        //System.out.println("title: " + Main.getCategories().get(idOfSelectedCategory-1).getTitleOfCategory());
        for(Flashcard fc : Main.getCategories().get(idOfSelectedCategory-1).getFlashcards()){
            //TODO nie je lepsie ukladat miesto IMAGE a SOUND skorej FILE?
            /*ArrayList<String> imagesQ = new ArrayList<>();
            ArrayList<String> imagesA = new ArrayList<>();
            ArrayList<String> soundsQ = new ArrayList<>();
            ArrayList<String> soundsA = new ArrayList<>();
            for(Image i : fc.getQuestion().getImages()){
                imagesQ.add(i.);
            }*/
            data.add(new TableRecord(fc.getId(),
                    fc.getQuestion().getText(),
                    fc.getAnswer().getText(),
                    fc.getQuestion().getImages().toString(),
                    fc.getAnswer().getImages().toString(),
                    fc.getQuestion().getSounds().toString(),
                    fc.getAnswer().getSounds().toString(),
                    Boolean.toString(fc.getReversed())
            ));
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("EdiScreenCont INITIALIZE");
        idCol.setStyle("-fx-alignment: CENTER;");
        tvQuestion.setStyle("-fx-alignment: CENTER;");
        tvAnswer.setStyle("-fx-alignment: CENTER;");
        tvQImage.setStyle("-fx-alignment: CENTER;");
        tvAImage.setStyle("-fx-alignment: CENTER;");
        tvQSound.setStyle("-fx-alignment: CENTER;");
        tvASound.setStyle("-fx-alignment: CENTER;");
        tvReversed.setStyle("-fx-alignment: CENTER;");

        if (idOfSelectedCategory != -1) {
            idCategory = Main.getCategories().get(idOfSelectedCategory - 1).getId();
            title = Main.getCategories().get(idOfSelectedCategory - 1).getTitleOfCategory();
            System.out.println("zvolena kategoria id: " + idCategory + ",title: " + title);
            // TODO nefunguje nastavenie textu v textfielde...
            //TODO funguje len po kliknuti na REFRESH
            //nameOfCategoryInput.setText("arasid");

            ObservableList<TableRecord> data = table1.getItems();

            idCol.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("id"));
            tvQuestion.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("question"));
            tvAnswer.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("answer"));
            tvQImage.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("imagesQ"));
            tvAImage.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("imagesA"));
            tvQSound.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("soundsQ"));
            tvASound.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("soundsA"));
            tvReversed.setCellValueFactory(new PropertyValueFactory<TableRecord,String>("reversed"));

            table1.setItems(data);
            table1.getColumns().addAll(idCol, tvQuestion, tvAnswer, tvQImage, tvAImage, tvQSound, tvASound, tvReversed);

        }
    }
}
