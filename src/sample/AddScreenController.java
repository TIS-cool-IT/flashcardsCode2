package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.LineUnavailableException;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

import static sample.Main.toEditScreen;

public class AddScreenController {

    private boolean recording = false;
    @FXML
    private JavaSoundRecorder recorder = new JavaSoundRecorder();

    public void finishAdding(){
        toEditScreen(); //TODO poslat id kategorie
    }

    public void addBtnClicked() {
        // TODO vytvorit flashcardu, ulozit
        // na vytvorenie flashcard treba skontrolovat, ci sa moze vytvorit tj. novu funkciu
        // treba vytvorit 2 FlashcardFace -> najpr skontrolovat, ci sa mozu vytvorit tj. dalsia funkcia
    @FXML synchronized
    public void recordSoundQ() throws IOException, LineUnavailableException {
        Scene addScene = Main.getAddScene();
        if(!recording) {
            Button but = (Button) addScene.lookup("#BtnQRecord");
            but.setStyle("-fx-text-fill: red;");
            recording = true;
            recorder.beginRecording(new File("C:\\Users\\stefa\\Documents\\GitHub\\flashcardsCode2\\src\\nahravka.wav"));
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
    public void recordSoundQ() throws IOException, LineUnavailableException {
        Scene addScene = Main.getAddScene();
        if(!recording) {
            Button but = (Button) addScene.lookup("#BtnQRecord");
            but.setStyle("-fx-text-fill: red;");
            recording = true;
            recorder.beginRecording(new File("C:\\Users\\stefa\\Documents\\GitHub\\flashcardsCode2\\src\\nahravka.wav"));
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

        toEditScreen(); // TODO poslat id kategorie
    @FXML synchronized
    public void recordSoundA() throws IOException, LineUnavailableException {
        Scene addScene = Main.getAddScene();
        if(!recording) {
            Button but = (Button) addScene.lookup("#BtnARecord");
            but.setStyle("-fx-text-fill: red;");
            recording = true;
            recorder.beginRecording(new File("C:\\Users\\stefa\\Documents\\GitHub\\flashcardsCode2\\src\\nahravka.wav"));
            System.out.println("recording started!!!!");
        }
        else{
            Button but = (Button) addScene.lookup("#BtnARecord");
            but.setStyle("-fx-text-fill: #292929;");
            recorder.endRecording();
            recording = false;
            System.out.println("recording stopped!!!!");
        }
    @FXML synchronized
    public void recordSoundA() throws IOException, LineUnavailableException {
        Scene addScene = Main.getAddScene();
        if(!recording) {
            Button but = (Button) addScene.lookup("#BtnARecord");
            but.setStyle("-fx-text-fill: red;");
            recording = true;
            recorder.beginRecording(new File("C:\\Users\\stefa\\Documents\\GitHub\\flashcardsCode2\\src\\nahravka.wav"));
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
    public void openAudioBook(){
        Main.openAudioBook();
    }


    @FXML
    public void openAudioBook(){
        Main.openAudioBook();
    }



}
