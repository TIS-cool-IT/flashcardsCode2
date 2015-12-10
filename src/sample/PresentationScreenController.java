package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import static sample.Main.*;


public class PresentationScreenController  {
    // if flashcard has one or none image
    @FXML
    Group rectOneImage;

    @FXML
    Label label1;

    @FXML
    ImageView image1;

    // if flashcard has two images
    @FXML
    Group rectTwoImages;

    @FXML
    Label label2;

    @FXML
    ImageView image21;


    @FXML
    ImageView image22;

    // presentation
    @FXML
    Label presentationStatus;

    @FXML
    Group presentationGroup;

    @FXML
    ImageView settings;

    @FXML
    ImageView sound;

    @FXML
    Button rightBtn;

    @FXML
    Button wrongBtn;

    @FXML
    Group buttonsGroup;

    @FXML
    Label flippingLabel;

    // settings
    @FXML
    Group settingsGroup;

    @FXML
    ComboBox fontSizeComboBox;

    @FXML
    CheckBox soundAutoCheckbox;




    private Image s1 = new Image("s1.png");
    private Image s2 = new Image("s2.png");
    private Image sound1 = new Image("sound.png");
    private Image sound2 = new Image("sound2.png");

    //settings
    private boolean playSoundsAutomatically = true;
    private double fontSize = 16;

    // presentation status
    private int numberOfFlashcards = 0;
    private static int correctAnswers;
    private static int wrongAnswers;
    private boolean answering = false;
    private static boolean randomOrder = false;
    private ArrayList<Flashcard> allFlashcards;
    private int actualFlashcardOrder;
    private int numberOfAllFlashcards = 0;
    private FlashcardFace actualFace = null;
    private ArrayList<Integer> usedIDinRandomOrder;
    private Random randomizer = new Random();


    private static Category category;
    private ArrayList<Flashcard> flashcards;
    private FlashcardFace questionFace;
    private FlashcardFace answerFace;


    public void finishPresentation(){
        getAfterPresentationController().init();
        getPopupPresentationStage().show();
    }

    public void saveSettings(){
        playSoundsAutomatically = soundAutoCheckbox.isSelected();
        Object selectedItem = fontSizeComboBox.getSelectionModel().getSelectedItem();
        if ( selectedItem != null){
            fontSize = Double.parseDouble(selectedItem.toString());
        }
        else {
            fontSize = 16;
        }
        label1.setFont(new Font(fontSize));
        label2.setFont(new Font(fontSize));
    }

    public void onSettingsClicked(){
        if (presentationGroup.isVisible()){
            presentationGroup.setVisible(false);
            settingsGroup.setVisible(true);
        }
        else {
            presentationGroup.setVisible(true);
            settingsGroup.setVisible(false);
        }
    }

    public void showButtons(){
        if (buttonsGroup.isVisible()){
            buttonsGroup.setVisible(false);
        }
        else {
            buttonsGroup.setVisible(true);
        }
    }

    public void onSettingsPressed(){
        settings.setImage(s2);
    }

    public void onSettingsReleased() { settings.setImage(s1);}

    public void onSoundPressed(){
        sound.setImage(sound2);
    }

    public void onSoundReleased(){
        sound.setImage(sound1);
    }

    public void onSoundClicked() {
        ArrayList sounds = actualFace.getSounds();
        String path = "C:\\Users\\Betka\\Documents\\GitHub\\flashcardsCode2\\src\\sample\\bip.mp3";
        playRecords(path);

    }

    public void playRecords(String path){
        Media hit = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }

    // all flashcards with reversed
    public ArrayList<Flashcard> getAllFlashcards(){
        ArrayList<Flashcard> allflashcards = new ArrayList<Flashcard>();
        FlashcardFace newAnswer, newQuestion;
        int idDefault = numberOfFlashcards;
        for (Flashcard flashcard : flashcards){
           if (flashcard.getReversed()){
                Flashcard newFlashcard = new Flashcard(idDefault,true,category,null,null);
                newQuestion = flashcard.getAnswer();
                newAnswer = flashcard.getQuestion();
                newFlashcard.setAnswer(newAnswer);
                newFlashcard.setQuestion(newQuestion);
                allflashcards.add(newFlashcard);
            }
            allflashcards.add(flashcard);
        }
        numberOfAllFlashcards = allflashcards.size();
        return allflashcards;
    }


    public void rightAnswer(){
        correctAnswers++;
        afterButtonClicked();
    }

    public void wrongAnswer(){
        wrongAnswers++;
        afterButtonClicked();
    }

    public void afterButtonClicked(){
        updatePresentationStatus();
        startPresentation();
        showButtons();
    }


    public void updatePresentationStatus(){
        if (actualFlashcardOrder < numberOfAllFlashcards) {
            int seenFlashcards = correctAnswers + wrongAnswers + 1;
            presentationStatus.setText(String.valueOf(seenFlashcards) + "/" + String.valueOf(numberOfAllFlashcards));
        }
    }


    public void init() {
        initSettings();
        correctAnswers = 0;
        wrongAnswers  = 0;
        label1.setFont(new Font(fontSize));
        label2.setFont(new Font(fontSize));
        if(idOfSelectedCategory != -1) {
            category = Main.getCategories().get(idOfSelectedCategory - 1);
            flashcards = category.getFlashcards();
            numberOfFlashcards = flashcards.size();
            allFlashcards = getAllFlashcards();
            actualFlashcardOrder = 0;
            usedIDinRandomOrder = new ArrayList<Integer>();
            for (int i = 0; i <allFlashcards.size(); i++){
                System.out.println(i+ " " + allFlashcards.get(i).getQuestion().getText()  +  "   " + allFlashcards.get(i).getAnswer().getText());
            }
            startPresentation();
        }
    }

    public void startPresentation(){
        if (actualFlashcardOrder >= numberOfAllFlashcards || numberOfAllFlashcards == usedIDinRandomOrder.size()){
            finishPresentation();
        }
        else {
            answering = false;
            flippingLabel.setVisible(true
            );
            updatePresentationStatus();
            Flashcard flashcard = getNextFlashcard();
            answerFace = flashcard.getAnswer();
            questionFace = flashcard.getQuestion();
            fillFace(answerFace);
        }
    }

    public Flashcard getNextFlashcard(){
        Flashcard result = null;
        if (!randomOrder){
            result = allFlashcards.get(actualFlashcardOrder);
            actualFlashcardOrder++;
        }
        else {
            int random = randomizer.nextInt(numberOfAllFlashcards);
            System.out.println("before while random : " + random);
            while (true){
                if (!usedIDinRandomOrder.contains(random)){
                    System.out.println("result : " + random);
                    System.out.println("---------------------");
                    usedIDinRandomOrder.add(random);
                    return allFlashcards.get(random);
                }
                random = randomizer.nextInt(numberOfAllFlashcards);

            }
        }
        return result;
    }

    public void fillFace(FlashcardFace face){
        actualFace = face;
        if (face.getImages().size() < 2){
            System.out.println("0 alebo 1 obrazok");
            rectTwoImages.setVisible(false);
            rectOneImage.setVisible(true);
            label1.setText(face.getText().toString());
            //Image i = face.getImages().get(0);
            //image1.setImage(getImage(file1));

        }
        else {
            rectTwoImages.setVisible(true);
            rectOneImage.setVisible(false);
            label2.setText(face.getText().toString());

        }
    }

    public WritableImage getImage(File file){
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(file);
        } catch (IOException ex) {
            System.out.println("Image failed to load.");
        }

        WritableImage wr = null;
        if (bf != null) {
            wr = new WritableImage(bf.getWidth(), bf.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < bf.getWidth(); x++) {
                for (int y = 0; y < bf.getHeight(); y++) {
                    pw.setArgb(x, y, bf.getRGB(x, y));
                }
            }
        }
        return wr;
    }

    private void continuePresentation() {
        flippingLabel.setVisible(false);
        answering = true;
        showButtons();
        fillFace(questionFace);

    }

    public void initSettings(){
        fontSizeComboBox.getItems().clear();
        soundAutoCheckbox.setSelected(playSoundsAutomatically);
        fontSizeComboBox.getItems().addAll("16","20","22","24","28");
        fontSizeComboBox.setPromptText(String.valueOf(fontSize));
    }


    public void onRectClicked(){
        if (!answering) {
            continuePresentation();
        }

    }


    public static int[] getAnswers(){
        return new int[] {correctAnswers,wrongAnswers};
    }

    public static String getCategoryTitle(){
        return category.getTitleOfCategory();
    }

    public static void showDialog(){
        int choice = JOptionPane.showOptionDialog(null,"Play flashcards in ","Presentation",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null, new String[] {"Random order", "Created order"}, "Created order");
        randomOrder = (choice == 0);

    }



}
