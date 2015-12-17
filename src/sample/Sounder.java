package sample;

import jAudioFeatureExtractor.jAudioTools.AudioSamples;
import jdk.internal.dynalink.beans.StaticClass;

import java.io.*;
import javax.sound.sampled.*;

import static sample.Main.*;

public class Sounder {

    private AudioSamples as;
    private Clip clip;
    private boolean isPlaying = false;
    private String fileName = "";
    private AudioInputStream audi;
    private File subor;
    double dlzka;
    double[][] samples1;

    public Sounder(File subor) throws Exception {
        AudioInputStream ais = AudioSystem.getAudioInputStream(subor);
        fileName = subor.getName();
        as = new AudioSamples(ais,"",false);
        this.subor = subor;
        dlzka = as.getDuration();
        samples1 = as.getSamplesChannelSegregated(0,dlzka);
    }

    public String getFileName(){
        return fileName;
    }

    public double getDuration(){
        return as.getDuration();
    }

    synchronized
    public void play(double start, double end) throws Exception {
        double[][] samples = as.getSamplesChannelSegregated(start, end);
        as.setSamples(samples);
        audi = as.getAudioInputStreamChannelSegregated();
        as.setSamples(samples1);
        AudioFormat af = audi.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, af);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(audi);
        clip.start();
        isPlaying = true;
    }

    public boolean cutAndSave(double start, double end) throws Exception {
        AudioSamples audiosample = as.getCopyOfAudioSamples();
        double[][] samples = audiosample.getSamplesChannelSegregated(start, end);
        audiosample.setSamples(samples);
        if(checkBeforeSave(audiosample)) {
            File theDir = new File("C:\\FlashCard\\tmp_files");
            if (!theDir.exists()) {
                boolean result = false;
                try{
                    theDir.mkdir();
                    result = true;
                }
                catch(SecurityException se){
                    //handle it
                }
                if(result) {
                    System.out.println("DIR created");
                }
            }
//            String dir = Main.getCategories().get(Main.getIdOfSelectedCategory()).getTitleOfCategory();
//            audiosample.saveAudio(new File("C:\\FlashCard\\" + dir + "\\" + Integer.toString(Main.getIdOfSelectedFlashcard()) +  "\\tmp_files\\tmp_record.wav"), true, AudioFileFormat.Type.WAVE, false);
            audiosample.saveAudio(new File("C:\\FlashCard\\tmp_files\\tmp_record_" + subor.getName() +".wav"), true, AudioFileFormat.Type.WAVE, false);
            return true;
        }
        return false;
    }


    public void stop() throws IOException {
        clip.close();
        audi.close();
        isPlaying = false;
    }

    public boolean checkBeforeSave(AudioSamples audiosample){
        if(audiosample.getDuration()>(10*60)) return false;
        return true;
    }

    public boolean isPlaying(){
        return isPlaying;
    }
}