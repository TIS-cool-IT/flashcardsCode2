package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static sample.Main.*;
import static sample.Main.getPrimaryStage;

public class EditOneScreenController {

    private boolean recording = false;

    JavaSoundRecorder recorder = new JavaSoundRecorder();

    HashMap<String, File> inputFiles = new HashMap<String, File>() {{
        put("QImage1",null); put("QSound1", null); put("QImage2", null); put("QSound2", null);
        put("AImage1",null); put("ASound1", null); put("AImage2", null); put("ASound2", null);
    }};

    public void finishEditing(){
        toEditScreen();
    }

    public void onRightArrowClicked(){
        if (idOfSelectedFlashcard == (Main.getCategories().get(idOfSelectedCategory-1).getCount() - 1)) {
            idOfSelectedFlashcard = 0;
        } else {
            idOfSelectedFlashcard = idOfSelectedFlashcard + 1;
        }
        clearFilesFields();
        init();
    }

    public void onRightPressed(){
        ImageView iv =(ImageView) Main.getPrimaryStage().getScene().lookup("#rightArrow");
        iv.setImage(new Image("r2.png"));
    }

    public void onRightReleased(){
        ImageView iv =(ImageView) Main.getPrimaryStage().getScene().lookup("#rightArrow");
        iv.setImage(new Image("r1.png"));
    }


    public void onLeftArrowClicked(){
        if (idOfSelectedFlashcard > 0) {
            idOfSelectedFlashcard = idOfSelectedFlashcard - 1;
        } else {
            idOfSelectedFlashcard = Main.getCategories().get(idOfSelectedCategory-1).getCount() - 1;
        }
        clearFilesFields();
        init();
    }

    public void onLeftPressed(){
        ImageView iv =(ImageView) Main.getPrimaryStage().getScene().lookup("#leftArrow");
        iv.setImage(new Image("l2.png"));
    }

    public void onLeftReleased(){
        ImageView iv =(ImageView) Main.getPrimaryStage().getScene().lookup("#leftArrow");
        iv.setImage(new Image("l1.png"));
    }

    public void saveChanges(){
        System.out.println("Saving...");
    }

    public void openAudio() throws IOException {
        Main.openAudioBook();
    }

    @FXML synchronized
    public void recordSoundQ() throws IOException, LineUnavailableException {
        Scene addScene = Main.getPrimaryStage().getScene();
        if(!recording) {
            Button but = (Button) addScene.lookup("#BtnQRecord");
            but.setStyle("-fx-text-fill: red;");
            recording = true;
//            Flashcard card = Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().get(idOfSelectedFlashcard);
//            recorder.beginRecording(new File("C:\\FlashCard\\Categories\\" + card.getFlashcardDirectory() + "nahravka.wav"));
            recorder.beginRecording(new File("C:\\FlashCard\\tmp_files\\nahravka.wav"));
            System.out.println("recording started!!!!");
        }
        else{
            Button but = (Button) addScene.lookup("#BtnQRecord");
            but.setStyle("-fx-text-fill: #292929;");
            recorder.endRecording();
            recording = false;
            System.out.println("recording stopped!!!!");
        }
    }

    @FXML synchronized
    public void recordSoundA() throws IOException, LineUnavailableException {
        Scene addScene = Main.getPrimaryStage().getScene();
        if(!recording) {
            Button but = (Button) addScene.lookup("#BtnARecord");
            but.setStyle("-fx-text-fill: red;");
            recording = true;
            recorder.beginRecording(new File("C:\\FlashCard\\tmp_files\\nahravka.wav"));
            System.out.println("recording started!!!!");
        }
        else{
            Button but = (Button) addScene.lookup("#BtnARecord");
            but.setStyle("-fx-text-fill: #292929;");
            recorder.endRecording();
            recording = false;
            System.out.println("recording stopped!!!!");
        }
    }

    public void init() {
        if(idOfSelectedFlashcard != -1){
            Flashcard selected = Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().get(idOfSelectedFlashcard);
            TextField tvQ = (TextField) Main.getPrimaryStage().getScene().lookup("#InputQText");
            tvQ.setText(selected.getQuestion().getText());

            TextField tvA = (TextField) Main.getPrimaryStage().getScene().lookup("#InputAText");
            tvA.setText(selected.getAnswer().getText());

            ArrayList<File> questionImages = selected.getQuestion().getImages();

            if(questionImages.get(0) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQImage1");
                btn.setText(questionImages.get(0).getName());
            }
            if(questionImages.get(1) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQImage2");
                btn.setText(questionImages.get(1).getName());
            }

            ArrayList<File> answerImages = selected.getAnswer().getImages();

            if(answerImages.get(0) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnAImage1");
                btn.setText(answerImages.get(0).getName());
            }
            if(answerImages.get(1) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnAImage2");
                btn.setText(answerImages.get(1).getName());
            }

            ///////////////////////////////////////////////////////////////////////////////////
            ArrayList<File> questionSounds = selected.getQuestion().getSounds();

            if(questionSounds.get(0) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQSound1");
                btn.setText(questionSounds.get(0).getName());
            }
            if(questionSounds.get(1) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQSound2");
                btn.setText(questionSounds.get(1).getName());
            }

            ArrayList<File> answerSounds = selected.getAnswer().getSounds();

            if(answerSounds.get(0) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnASound1");
                btn.setText(answerSounds.get(0).getName());
            }
            if(answerSounds.get(1) != null) {
                Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnASound2");
                btn.setText(answerSounds.get(1).getName());
            }

            CheckBox cb = (CheckBox) Main.getPrimaryStage().getScene().lookup("#checkboxReverse");
            if(selected.getReversed()) cb.setSelected(true);
            else cb.setSelected(false);


        }
    }

    private void clearFilesFields() {
        Button but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+"QImage1");
        but.setText("Upload file");
        but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+"QImage2");
        but.setText("Upload file");
        but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+"QSound1");
        but.setText("Upload file");
        but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+"QSound2");
        but.setText("Upload file");
        but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+"AImage1");
        but.setText("Upload file");
        but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+"AImage2");
        but.setText("Upload file");
        but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+"ASound1");
        but.setText("Upload file");
        but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+"ASound2");
        but.setText("Upload file");
    }


    private void chooseImage(String nameOfImage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile =  fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        if (selectedFile != null) {
            inputFiles.put(nameOfImage, selectedFile);
            Button but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+nameOfImage);
            but.setText(selectedFile.getName());
        }
    }

    private void chooseSound(String nameOfSound) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile =  fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setTitle("View Sounds");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );
        if (selectedFile != null) {
            inputFiles.put(nameOfSound, selectedFile);
            Button but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+nameOfSound);
            but.setText(selectedFile.getName());
        }
    }

    @FXML
    public void addQImage1() {
        chooseImage("QImage1");
    }

    @FXML
    public void addQImage2() {
        chooseImage("QImage2");
    }

    @FXML
    public void addQSound1() {
        chooseSound("QSound1");
    }

    @FXML
    public void addQSound2() {
        chooseSound("QSound2");
    }

    @FXML
    public void addAImage1() {
        chooseImage("AImage1");
    }

    @FXML
    public void addAImage2() {
        chooseImage("AImage2");
    }

    @FXML
    public void addASound1() {
        chooseSound("ASound1");
    }

    @FXML
    public void addASound2() {
        chooseSound("ASound2");
    }


}
