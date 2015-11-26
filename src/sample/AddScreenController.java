package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import static sample.Main.toEditScreen;

public class AddScreenController {

    public void finishAdding(){
        toEditScreen(); //TODO poslat id kategorie
    }

    public void addBtnClicked() {
        // TODO vytvorit flashcardu, ulozit
        // na vytvorenie flashcard treba skontrolovat, ci sa moze vytvorit tj. novu funkciu
        // treba vytvorit 2 FlashcardFace -> najpr skontrolovat, ci sa mozu vytvorit tj. dalsia funkcia

        // uladanie suborov FlashcardFace (text, obrazky, zvuky) -> kam dat takuto funkciu?? sem alebo Flashcard?

        toEditScreen(); // TODO poslat id kategorie
    }

}
