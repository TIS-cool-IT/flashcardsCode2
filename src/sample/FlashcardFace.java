package sample;


import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for managing flashcard attributes
 */
public class FlashcardFace implements Serializable{

    private ArrayList<File> images = new ArrayList<>();
    private ArrayList<File> sounds = new ArrayList<>(); // TODO pridat nazov triedy na spravu zvuku
    private String text;

    public FlashcardFace(String text, ArrayList<File> images, ArrayList<File> sounds) {
        this.text = text;
        this.images = images;
        this.sounds = sounds;
    }


    public ArrayList<File> getImages() {
        return images;
    }

    public void addImage(File image) {
        images.add(image);
    }

    public void deleteImage(File image) {
        images.remove(image);
    }

    public ArrayList getSounds() { // TODO pridat nazov triedy na spravu zvuku
        return sounds;
    }

//    public void addSound(sound) { // TODO pridat nazov triedy na spravu zvuku
//        sounds.add(sound);
//    }

    public void deleteSound() {// TODO pridat nazov triedy na spravu zvuku

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }




}
