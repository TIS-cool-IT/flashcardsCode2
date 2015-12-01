package sample;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for managing flashcard attributes
 */
public class FlashcardFace implements Serializable{

    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<Sounder> sounds = new ArrayList<>(); // TODO pridat nazov triedy na spravu zvuku
    private String text;

    public FlashcardFace(String text, ArrayList<Image> images, ArrayList<Sounder> sounds) {
        this.text = text;
        this.images = images;
        this.sounds = sounds;
    }


    public ArrayList<Image> getImages() {
        return images;
    }

    public void addImage(Image image) {
        images.add(image);
    }

    public void deleteImage(Image image) {
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
