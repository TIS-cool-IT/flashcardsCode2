package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.FileChooser;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.getPrimaryStage;

public class SounderScreenController implements Initializable {

    private Sounder zvuk;
    @FXML
    private Label lab;

    public void otvor() throws Exception {
        String[] types = new String[3];
        types[0] = "*.mp3";
        types[1] = "*.wav";
        types[2] = "*.au";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("View Sounds");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Sounds", types)
        );

        File selectedFile =  fileChooser.showOpenDialog(getPrimaryStage());
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            zvuk = new Sounder(selectedFile);
            Scene sounderScene = Main.getSounderScene();
            Slider slid = (Slider) sounderScene.lookup("#startOf");
            slid.setMax(zvuk.getDuration());
            Slider slid1 = (Slider) sounderScene.lookup("#endOf");
            slid1.setMax(zvuk.getDuration());
            Label lab = (Label) sounderScene.lookup("#nameLabel");
            lab.setText("Selected file: " + selectedFile.getAbsolutePath());
            Label labStart = (Label) sounderScene.lookup("#startOfLabelTime");
            labStart.setText("00:00:00");
            Label labEnd = (Label) sounderScene.lookup("#endOfLabelTime");
            labEnd.setText("00:00:00");
        }
    }

    @FXML synchronized
    public void dragStartSlider() throws IOException {
        if(zvuk.isPlaying()) zvuk.stop();
        Scene sounderScene = Main.getSounderScene();
        Slider slid = (Slider) sounderScene.lookup("#startOf");
        lab = (Label) sounderScene.lookup("#startOfLabelTime");

        int seconds = (int) Math.round(slid.getValue());
        int hours = seconds / 60 / 60;
        int minutes = seconds / 60;
        int secs = seconds - ((hours*60*60)+(minutes*60));

        String hour = Integer.toString(hours);
        if(hour.length()==1) hour = "0"+Integer.toString(hours);
        String minute = Integer.toString(minutes);
        if(minute.length()==1) minute = "0"+Integer.toString(minutes);
        String sec = Integer.toString(secs);
        if(sec.length()==1) sec = "0"+Integer.toString(secs);
        String cas = hour+":"+minute+":"+sec;
        lab.setText(cas);
        Slider slid1 = (Slider) sounderScene.lookup("#endOf");
        if(slid.getValue() > slid1.getValue()) slid1.setValue(seconds + 1);
        dragEndTime();
    }

    @FXML synchronized
    public void dragEndTime() throws IOException {
        if(zvuk.isPlaying()) zvuk.stop();
        Scene sounderScene = Main.getSounderScene();
        Slider slid = (Slider) sounderScene.lookup("#endOf");
        Slider slider = (Slider) sounderScene.lookup("#startOf");
        lab = (Label) sounderScene.lookup("#endOfLabelTime");
        if(slid.getValue() < slider.getValue()) slid.setValue(slider.getValue()+1);

        int seconds = (int) Math.round(slid.getValue());
        int hours = seconds / 60 / 60;
        int minutes = seconds / 60;
        int secs = seconds - ((hours*60*60)+(minutes*60));

        String hour = Integer.toString(hours);
        if(hour.length()==1) hour = "0"+Integer.toString(hours);
        String minute = Integer.toString(minutes);
        if(minute.length()==1) minute = "0"+Integer.toString(minutes);
        String sec = Integer.toString(secs);
        if(sec.length()==1) sec = "0"+Integer.toString(secs);
        String cas = hour+":"+minute+":"+sec;
        lab.setText(cas);
    }

    @FXML synchronized
    public void play() throws Exception {
        if(zvuk.isPlaying()) zvuk.stop();
        Scene sounderScene = Main.getSounderScene();
        Slider slid = (Slider) sounderScene.lookup("#startOf");
        Slider slid1 = (Slider) sounderScene.lookup("#endOf");
        zvuk.play(slid.getValue(),slid1.getValue());

    }

    @FXML synchronized
    public void stopPlaying() throws IOException {
        zvuk.stop();
    }


    @FXML synchronized
    public void saveAndCut() throws Exception {
        if(zvuk.isPlaying()) zvuk.stop();
        Scene sounderScene = Main.getSounderScene();
        Slider slid = (Slider) sounderScene.lookup("#startOf");
        Slider slid1 = (Slider) sounderScene.lookup("#endOf");
        if(zvuk.cutAndSave(slid.getValue(), slid1.getValue())) {
            JOptionPane.showMessageDialog(new JFrame(), "Record was saved.");
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Record has more than 10 minutes!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
