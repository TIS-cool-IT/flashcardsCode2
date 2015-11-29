package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import javax.sound.sampled.LineUnavailableException;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sample.Main.getPrimaryStage;
import static sample.Main.toEditScreen;

public class AddScreenController {

    private boolean recording = false;
    @FXML
    private JavaSoundRecorder recorder = new JavaSoundRecorder();

    @FXML
    TextField inputQText;
    @FXML
    TextField inputAText;
    @FXML
    CheckBox checkboxReverse;


    public void finishAdding(){
        toEditScreen(); //TODO poslat id kategorie
    }



    public void addBtnClicked(){
        //kontrola, ci sa moze flashcard vytvorit
        // TODO na vytvorenie flashcard treba skontrolovat, ci sa moze vytvorit tj. novu funkciu
        // TODO treba vytvorit 2 FlashcardFace -> najpr skontrolovat, ci sa mozu vytvorit tj. dalsia funkcia

        new Flashcard(1, checkboxReverse.isSelected(), new Category(2, "title"),
                new FlashcardFace(inputQText.getText(), new ArrayList<>(), new ArrayList<>()),
                new FlashcardFace(inputAText.getText(), new ArrayList<>(), new ArrayList<>())
        );//TODO filedialog - images, sounds

        if (checkboxReverse.isSelected()) { //ak je reversed true, vytvorim aj opacnu flashcard
            new Flashcard(1, checkboxReverse.isSelected(), new Category(2, "title"),
                    new FlashcardFace(inputAText.getText(), new ArrayList<>(), new ArrayList<>()),
                    new FlashcardFace(inputQText.getText(), new ArrayList<>(), new ArrayList<>())
            );
        }
        // TODO id priradovat podla posledneho id v subore + 1
        // TODO category - pozriet do suboru, kde su vsetky kategorie a porovnat na zaklade premennej categoryID
        // TODO ulozit flashcardu do suboru

        toEditScreen();

    }

    @FXML
    public void addQImage1() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    public void addQImage2() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    public void addQSound1() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setTitle("View Sounds");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );
    }

    @FXML
    public void addQSound2() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setTitle("View Sounds");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );
    }

    @FXML
    public void addAImage1() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    public void addAImage2() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    public void addASound1() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setTitle("View Sounds");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );
    }

    @FXML
    public void addASound2() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setTitle("View Sounds");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );
    }


    @FXML synchronized
    public void recordSoundQ() throws IOException, LineUnavailableException {
        Scene addScene = Main.getPrimaryStage().getScene();
        if(!recording) {
            Button but = (Button) addScene.lookup("#BtnQRecord");
            but.setStyle("-fx-text-fill: red;");
            recording = true;
            recorder.beginRecording(new File("C:\\FlashCard\\nahravka.wav"));
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

        // uladanie suborov FlashcardFace (text, obrazky, zvuky) -> kam dat takuto funkciu?? sem alebo Flashcard?

    @FXML synchronized
    public void recordSoundA() throws IOException, LineUnavailableException {
        Scene addScene = Main.getPrimaryStage().getScene();
        if(!recording) {
            Button but = (Button) addScene.lookup("#BtnARecord");
            but.setStyle("-fx-text-fill: red;");
            recording = true;
            recorder.beginRecording(new File("C:\\FlashCard\\nahravka.wav"));
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

    @FXML
    public void openAudio() throws IOException {
        Main.openAudioBook();
    }




}
