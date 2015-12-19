package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static sample.Main.*;
import static sample.Main.getPrimaryStage;

public class EditOneScreenController {

    private boolean recording = false;
    private boolean recordingSaved = false;

    JavaSoundRecorder recorder = new JavaSoundRecorder();

    HashMap<String, File> inputFiles;

    @FXML
    Label heading;

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

    public void saveChanges() throws IOException {
        System.out.println("Saving...");


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save changes");
        alert.setContentText("Do you want save changes?");
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            Flashcard selected = Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().get(idOfSelectedFlashcard);

            for (File input : inputFiles.values()){
                if (input != null) {
                    File duplicate = new File("C:\\FlashCard\\Categories\\" + selected.getFlashcardDirectory()+"\\"+input.getName());
                    if(!duplicate.exists()) {
                        saveFaceFile(input, "C:\\FlashCard\\Categories\\" + selected.getFlashcardDirectory());
                    }
                    else{
                        //JOptionPane.showMessageDialog(new JFrame(), "File "+ input.getName()+  " can't be added twice.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            File record1 = new File("C:\\FlashCard\\tmp_files\\record1.wav");
            File record2 = new File("C:\\FlashCard\\tmp_files\\record2.wav");
            File record3 = new File("C:\\FlashCard\\tmp_files\\record3.wav");
            File record4 = new File("C:\\FlashCard\\tmp_files\\record4.wav");
            if(record1.exists()){
                record1.delete();
            }
            if(record2.exists()){
                record2.delete();
            }
            if(record3.exists()){
                record3.delete();
            }
            if(record4.exists()){
                record4.delete();
            }

            ArrayList<File> questionImages = new ArrayList<>();
            questionImages.add(inputFiles.get("BtnQImage1"));
            questionImages.add(inputFiles.get("BtnQImage2"));

            ArrayList<File> questionSounds = new ArrayList<>();
            questionSounds.add(inputFiles.get("BtnQSound1"));
            questionSounds.add(inputFiles.get("BtnQSound2"));

            System.out.println(inputFiles.get("#BtnQSound1"));
            System.out.println(inputFiles.get("#BtnQSound2"));

            selected.setQuestion(new FlashcardFace(((TextField) Main.getPrimaryStage().getScene().lookup("#InputQText")).getText(), questionImages, questionSounds));

            ArrayList<File> answerImages = new ArrayList<>();
            answerImages.add(inputFiles.get("BtnAImage1"));
            answerImages.add(inputFiles.get("BtnAImage2"));

            ArrayList<File> answerSounds = new ArrayList<>();
            answerSounds.add(inputFiles.get("BtnASound1"));
            answerSounds.add(inputFiles.get("BtnASound2"));

            selected.setAnswer(new FlashcardFace(((TextField) Main.getPrimaryStage().getScene().lookup("#InputAText")).getText(), answerImages, answerSounds));

            CheckBox cb = (CheckBox) Main.getPrimaryStage().getScene().lookup("#checkboxReverse");
            selected.setReversed(cb.isSelected());

            Main.saveCategories();
            Main.toEditScreen();
        }
    }

    public void saveFaceFile(File source, String dir){
        File dest = new File(dir + "\\" + source.getName());
        try {
            Files.copy(source.toPath(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openBook() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("View Texts");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        File selectedFile =  fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        if (selectedFile != null) {
            Main.openBook(selectedFile);
        }
    }

    public void openAudio() throws IOException {
        Main.openAudioBook();
    }

    @FXML synchronized
    public void recordSoundQ() throws IOException, LineUnavailableException {
        Scene addScene = Main.getPrimaryStage().getScene();
        if(inputFiles.get("BtnQSound1")==null || inputFiles.get("BtnQSound2")==null) {
            if (!recording) {
                Button but = (Button) addScene.lookup("#BtnQRecord");
                recordingSaved = false;
                but.setStyle("-fx-text-fill: red;");
                recording = true;
                if(inputFiles.get("BtnQSound1")==null) recorder.beginRecording(new File("C:\\FlashCard\\tmp_files\\record1.wav"));
                else recorder.beginRecording(new File("C:\\FlashCard\\tmp_files\\record2.wav"));
                System.out.println("recording started!!!!");
            } else {
                Button but = (Button) addScene.lookup("#BtnQRecord");
                but.setStyle("-fx-text-fill: #292929;");
                but = (Button) addScene.lookup("#BtnARecord");
                but.setStyle("-fx-text-fill: #292929;");

                if(inputFiles.get("BtnQSound1")==null){
                    but = (Button) addScene.lookup("#BtnQSound1");
                    but.setText("record.wav");
                    inputFiles.put("BtnQSound1",new File("C:\\FlashCard\\tmp_files\\record1.wav"));
                }
                else{
                    but = (Button) addScene.lookup("#BtnQSound2");
                    but.setText("record.wav");
                    inputFiles.put("BtnQSound2",new File("C:\\FlashCard\\tmp_files\\record2.wav"));
                }
                recorder.endRecording();
                recording = false;
                System.out.println("recording stopped!!!!");
                recordingSaved = true;
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Question already has 2 sounds", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML synchronized
    public void recordSoundA() throws IOException, LineUnavailableException {
        Scene addScene = Main.getPrimaryStage().getScene();
        if(inputFiles.get("BtnASound1")==null || inputFiles.get("BtnASound2")==null) {
            if(!recording) {
                recordingSaved = false;
                Button but = (Button) addScene.lookup("#BtnARecord");
                but.setStyle("-fx-text-fill: red;");
                recording = true;
                if(inputFiles.get("BtnASound1")==null) recorder.beginRecording(new File("C:\\FlashCard\\tmp_files\\record3.wav"));
                else recorder.beginRecording(new File("C:\\FlashCard\\tmp_files\\record4.wav"));
                System.out.println("recording started!!!!");
            }
            else{
                Button but = (Button) addScene.lookup("#BtnARecord");
                but.setStyle("-fx-text-fill: #292929;");
                but = (Button) addScene.lookup("#BtnQRecord");
                but.setStyle("-fx-text-fill: #292929;");

                if(inputFiles.get("BtnASound1")==null){
                    but = (Button) addScene.lookup("#BtnASound1");
                    but.setText("record.wav");
                    inputFiles.put("BtnASound1",new File("C:\\FlashCard\\tmp_files\\record3.wav"));
                }
                else{
                    but = (Button) addScene.lookup("#BtnASound2");
                    but.setText("record.wav");
                    inputFiles.put("BtnASound2",new File("C:\\FlashCard\\tmp_files\\record4.wav"));
                }

                recorder.endRecording();
                recording = false;
                System.out.println("recording stopped!!!!");
                recordingSaved = true;
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Answer already has 2 sounds", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void init() {
        if(idOfSelectedFlashcard != -1){
            heading.setText("You are editing flashcard, id: " +
                    Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().get(idOfSelectedFlashcard).getId() +
                    " in category: " + Main.getCategories().get(idOfSelectedCategory-1).getTitleOfCategory());
            inputFiles = new HashMap<String, File>() {{
                put("QImage1",null); put("QSound1", null); put("QImage2", null); put("QSound2", null);
                put("AImage1",null); put("ASound1", null); put("AImage2", null); put("ASound2", null);
            }};
            clearFilesFields();
            fillFields();
        }
    }

    public void fillFields(){
        Flashcard selected = Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().get(idOfSelectedFlashcard);
        TextField tvQ = (TextField) Main.getPrimaryStage().getScene().lookup("#InputQText");
        tvQ.setText(selected.getQuestion().getText());

        TextField tvA = (TextField) Main.getPrimaryStage().getScene().lookup("#InputAText");
        tvA.setText(selected.getAnswer().getText());

        ArrayList<File> questionImages = selected.getQuestion().getImages();

        if(questionImages.get(0) != null) {
            Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQImage1");
            btn.setText(questionImages.get(0).getName());
            inputFiles.put("BtnQImage1", new File(btn.getText()));
        }
        if(questionImages.get(1) != null) {
            Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQImage2");
            btn.setText(questionImages.get(1).getName());
            inputFiles.put("BtnQImage2", new File(btn.getText()));
        }

        ArrayList<File> answerImages = selected.getAnswer().getImages();

        if(answerImages.get(0) != null) {
            Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnAImage1");
            btn.setText(answerImages.get(0).getName());
            inputFiles.put("BtnAImage1", new File(btn.getText()));
        }
        if(answerImages.get(1) != null) {
            Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnAImage2");
            btn.setText(answerImages.get(1).getName());
            inputFiles.put("BtnAImage2", new File(btn.getText()));
        }

        ///////////////////////////////////////////////////////////////////////////////////
        ArrayList<File> questionSounds = selected.getQuestion().getSounds();

        if(questionSounds.get(0) != null) {
            Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQSound1");
            btn.setText(questionSounds.get(0).getName());
            inputFiles.put("BtnQSound1", new File(btn.getText()));
        }
        if(questionSounds.get(1) != null) {
            Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnQSound2");
            btn.setText(questionSounds.get(1).getName());
            inputFiles.put("BtnQSound2", new File(btn.getText()));
        }

        ArrayList<File> answerSounds = selected.getAnswer().getSounds();

        if(answerSounds.get(0) != null) {
            Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnASound1");
            btn.setText(answerSounds.get(0).getName());
            inputFiles.put("BtnASound1", new File(btn.getText()));
        }
        if(answerSounds.get(1) != null) {
            Button btn = (Button) Main.getPrimaryStage().getScene().lookup("#BtnASound2");
            btn.setText(answerSounds.get(1).getName());
            inputFiles.put("BtnASound2", new File(btn.getText()));
        }

        CheckBox cb = (CheckBox) Main.getPrimaryStage().getScene().lookup("#checkboxReverse");
        if(selected.getReversed()) cb.setSelected(true);
        else cb.setSelected(false);
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
            Button but = (Button) Main.getPrimaryStage().getScene().lookup("#"+nameOfImage);
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
            Button but = (Button) Main.getPrimaryStage().getScene().lookup("#"+nameOfSound);
            but.setText(selectedFile.getName());
        }
    }

    @FXML
    public void addQImage1() {
        chooseImage("BtnQImage1");
    }

    @FXML
    public void addQImage2() {
        chooseImage("BtnQImage2");
    }

    @FXML
    public void addQSound1() {
        chooseSound("BtnQSound1");
    }

    @FXML
    public void addQSound2() {
        chooseSound("BtnQSound2");
    }

    @FXML
    public void addAImage1() {
        chooseImage("BtnAImage1");
    }

    @FXML
    public void addAImage2() {
        chooseImage("BtnAImage2");
    }

    @FXML
    public void addASound1() {
        chooseSound("BtnASound1");
    }

    @FXML
    public void addASound2() {
        chooseSound("BtnASound2");
    }


}
