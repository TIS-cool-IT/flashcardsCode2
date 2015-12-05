package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by stefa on 03.12.2015.
 */
public class TableRecord {
    //private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty question = new SimpleStringProperty("");
    private final SimpleStringProperty answer = new SimpleStringProperty("");
    private final SimpleStringProperty imagesQ = new SimpleStringProperty("");
    private final SimpleStringProperty imagesA = new SimpleStringProperty("");
    private final SimpleStringProperty soundsQ = new SimpleStringProperty("");
    private final SimpleStringProperty soundsA = new SimpleStringProperty("");
    private final SimpleStringProperty reversed = new SimpleStringProperty("");
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();

    public TableRecord() {
        this(0, "", "", "", "", "", "","");
    }

    public TableRecord(int id, String question, String answer, String imagesQ, String imagesA, String soundsQ, String soundsA, String reversed) {
        setId(id);
        setQuestion(question);
        setAnswer(answer);
        setImagesQ(imagesQ);
        setImagesA(imagesA);
        setSoundsQ(soundsQ);
        setSoundsA(soundsA);
        setReversed(reversed);
    }

    public String getReversed(){
        return reversed.get();
    }

    public void setReversed(String s){
        reversed.set(s);
    }

    public String getSoundsQ() {
        return soundsQ.get();
    }

    public void setSoundsQ(String fName) {
        soundsQ.set(fName);
    }

    public String getSoundsA() {
        return soundsA.get();
    }

    public void setSoundsA(String fName) {
        soundsA.set(fName);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int fName) {
        id.set(fName);
    }

    public String getImagesA(){
        return imagesA.get();
    }

    public void setImagesA(String s){
        imagesA.set(s);
    }

    public String getImagesQ(){
        return imagesQ.get();
    }

    public void setImagesQ(String s){
        imagesQ.set(s);
    }

    public String getQuestion() {
        return question.get();
    }

    public void setQuestion(String fName) {
        question.set(fName);
    }

    public String getAnswer() {
        return answer.get();
    }

    public void setAnswer(String fName) {
        answer.set(fName);
    }
}
