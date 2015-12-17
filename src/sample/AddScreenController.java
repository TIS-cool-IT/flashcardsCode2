package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

import java.io.*;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import static sample.Main.*;

public class AddScreenController implements Serializable{

    private boolean recordingQ = false;
    private boolean recordingA = false;

    HashMap<String, File> inputFiles = new HashMap<String, File>() {{
        put("QImage1",null); put("QSound1", null); put("QImage2", null); put("QSound2", null);
        put("AImage1",null); put("ASound1", null); put("AImage2", null); put("ASound2", null);
    }};


    public void finishAdding(){
        toEditScreen(); //TODO poslat id kategorie
        //folderChecker.stop();
    }


    public void addBtnClicked() throws IOException {

        if(recordingA || recordingQ){
            JOptionPane.showMessageDialog(new JFrame(), "You are still recording sounds", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Scene addScene = Main.getPrimaryStage().getScene();
        TextField inputQText = (TextField) addScene.lookup("#InputQText");
        TextField inputAText = (TextField) addScene.lookup("#BtnAText");
        CheckBox check = (CheckBox) addScene.lookup("#CheckboxReverse");


        if (correctInput()) {

            FlashcardFace questionFace = new FlashcardFace(inputQText.getText(),
                    new ArrayList() { {add(inputFiles.get("QImage1")); add(inputFiles.get("QImage2"));} },
                    new ArrayList() { {add(inputFiles.get("QSound1")); add(inputFiles.get("QSound2"));} });
            FlashcardFace answerFace = new FlashcardFace(inputAText.getText(),
                    new ArrayList() { {add(inputFiles.get("AImage1")); add(inputFiles.get("AImage2"));} },
                    new ArrayList() { {add(inputFiles.get("ASound1")); add(inputFiles.get("ASound2"));} });

            Flashcard card = new Flashcard(Main.getCategories().get(idOfSelectedCategory-1).getFlashcards().size()+1, check.isSelected(), Main.getCategories().get(idOfSelectedCategory-1), questionFace, answerFace);

            if (check.isSelected()) { //ak je reversed true, vytvorim aj opacnu flashcard
                new Flashcard(1, check.isSelected(), Main.getCategories().get(idOfSelectedCategory-1), answerFace, questionFace);
            }
            ArrayList<Category> al = Main.getCategories();

            al.get(idOfSelectedCategory-1).addFlashcard(card);
            Main.setCategories(idOfSelectedCategory-1,al.get(idOfSelectedCategory-1));
            // save files to directory
            for (File input : inputFiles.values()){
                if (input != null) {
                    File duplicate = new File("C:\\FlashCard\\Categories\\" + card.getFlashcardDirectory()+"\\"+input.getName());
                    if(!duplicate.exists()) {
                        saveFaceFile(input, "C:\\FlashCard\\Categories\\" + card.getFlashcardDirectory());
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "File "+ input.getName()+  " can't be added twice.", "Warning", JOptionPane.WARNING_MESSAGE);
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

            // save txt files to directory
            saveTxtFile("question", inputQText.getText(), "C:\\FlashCard\\Categories\\" + card.getFlashcardDirectory());
            saveTxtFile("answer", inputAText.getText(), "C:\\FlashCard\\Categories\\" + card.getFlashcardDirectory());

            JOptionPane.showMessageDialog(new JFrame(), "Flashcard was saved!");

            // clear fields values
            inputQText.clear();
            inputAText.clear();
            inputFiles.clear();
            clearFilesFields();


            toEditScreen();
        }
        else {
            JOptionPane.showMessageDialog(new JFrame(), "Flashcard can't be saved!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Nastala chyba pri vytvarani falshcard");
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

    private void saveTxtFile(String nameOfFile, String text, String dir) {
        FileOutputStream fop = null;
        File file = new File(dir.trim() + "\\" + nameOfFile + ".txt");
        System.out.println(file.getAbsolutePath());
        try {
            fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("TXT file created");
            }
            fop.write(text.getBytes());
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean correctInput() {
        Scene addScene = Main.getPrimaryStage().getScene();
        TextField inputQText = (TextField) addScene.lookup("#InputQText");
        TextField inputAText = (TextField) addScene.lookup("#BtnAText");
        if (inputQText.getText().equals(""))
            if (inputFiles.get("QImage1") == null)
                if (inputFiles.get("QImage2") == null)
                    if (inputFiles.get("QSound1") == null)
                        if (inputFiles.get("QSound2") == null)
                            return false;
        if (inputAText.getText().equals(""))
            if (inputFiles.get("AImage1") == null)
                if (inputFiles.get("AImage2") == null)
                    if (inputFiles.get("ASound1") == null)
                        if (inputFiles.get("ASound2") == null)
                            return false;
        return true;
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


    @FXML synchronized
    public void recordSoundQ() throws IOException, LineUnavailableException {
        if(recordingA) return;
        Scene addScene = Main.getPrimaryStage().getScene();
        if(inputFiles.get("QSound1")==null || inputFiles.get("QSound2")==null) {
            if (!recordingQ) {
                Button but = (Button) addScene.lookup("#BtnQRecord");
                but.setStyle("-fx-text-fill: red;");
                recordingQ = true;
                if(inputFiles.get("QSound1")==null) recorder.beginRecording(new File("C:\\FlashCard\\tmp_files\\record1.wav"));
                else recorder.beginRecording(new File("C:\\FlashCard\\tmp_files\\record2.wav"));
                System.out.println("recording started!!!!");
            } else {
                Button but = (Button) addScene.lookup("#BtnQRecord");
                but.setStyle("-fx-text-fill: #292929;");
                but = (Button) addScene.lookup("#BtnARecord");
                but.setStyle("-fx-text-fill: #292929;");

                if(inputFiles.get("QSound1")==null){
                    but = (Button) addScene.lookup("#BtnQSound1");
                    but.setText("record.wav");
                    inputFiles.put("QSound1",new File("C:\\FlashCard\\tmp_files\\record1.wav"));
                }
                else{
                    but = (Button) addScene.lookup("#BtnQSound2");
                    but.setText("record.wav");
                    inputFiles.put("QSound2",new File("C:\\FlashCard\\tmp_files\\record2.wav"));
                }
                recorder.endRecording();
                recordingQ = false;
                System.out.println("recording stopped!!!!");
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Question already has 2 sounds", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML synchronized
    public void recordSoundA() throws IOException, LineUnavailableException {
        if(recordingQ) return;
        Scene addScene = Main.getPrimaryStage().getScene();
        if(inputFiles.get("ASound1")==null || inputFiles.get("ASound2")==null) {
            if (!recordingA) {
                Button but = (Button) addScene.lookup("#BtnARecord");
                but.setStyle("-fx-text-fill: red;");
                recordingA = true;
                if(inputFiles.get("ASound1")==null) recorder.beginRecording(new File("C:\\FlashCard\\tmp_files\\record3.wav"));
                else recorder.beginRecording(new File("C:\\FlashCard\\tmp_files\\record4.wav"));
                System.out.println("recording started!!!!");
            } else {
                recorder.endRecording();
                recordingA = false;
                System.out.println("recording stopped!!!!");
                Button but = (Button) addScene.lookup("#BtnARecord");
                but.setStyle("-fx-text-fill: #292929;");
                but = (Button) addScene.lookup("#BtnARecord");
                but.setStyle("-fx-text-fill: #292929;");

                if(inputFiles.get("ASound1")==null){
                    but = (Button) addScene.lookup("#BtnASound1");
                    but.setText("record.wav");
                    inputFiles.put("ASound1",new File("C:\\FlashCard\\tmp_files\\record3.wav"));
                }
                else{
                    but = (Button) addScene.lookup("#BtnASound2");
                    but.setText("record.wav");
                    inputFiles.put("ASound2",new File("C:\\FlashCard\\tmp_files\\record4.wav"));
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Question already has 2 sounds", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    public void openAudio() throws IOException {
        Main.openAudioBook();
    }

    @FXML
    public void openBook() throws  IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("View Texts");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        File selectedFile =  fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        if (selectedFile != null) {
            // open book

        }
    }

    private void clearFilesFields() {
        for (String inputFile : inputFiles.keySet()) {
            Button but = (Button) Main.getPrimaryStage().getScene().lookup("#Btn"+inputFile);
            but.setText("Upload file");
        }
    }

}
