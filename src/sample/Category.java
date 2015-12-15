package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import static sample.Main.*;

/**
 * Category class
 */

public class Category implements Serializable{
    private int idOfCategory;
    private int countOfFlashcars;
    private String titleOfCategory;
    private ArrayList<Flashcard> listOfFlashcards = new ArrayList<>();
    private String categoryDirectory;
    private final String DIRECTORY = "C:\\FlashCard\\Categories\\"; //TODO DIRECTORY ukladania dať do hlavnej triedy


    public Category(int id, String title) {
        idOfCategory = id;
        titleOfCategory = title;
        categoryDirectory = titleOfCategory;
        countOfFlashcars = 0;
    }

    public int getId() {
        return idOfCategory;
    }


    public void addFlashcard(Flashcard flashcard) {
        listOfFlashcards.add(flashcard);
        countOfFlashcars++;
    }

    public int getCount() {
        return countOfFlashcars;
    }

    public ArrayList<Flashcard> getFlashcards() {
        return listOfFlashcards;
    }

    public boolean setTitleOfCategory(String newTitleOfCategory) {
        if (!newTitleOfCategory.trim().equals("")) {
            // rename directory
            File oldName = new File(DIRECTORY + titleOfCategory);
            File newName = new File(DIRECTORY + newTitleOfCategory);
            boolean isFileRenamed = oldName.renameTo(newName);
            if(isFileRenamed) {
                System.out.println("Category Directory has been renamed");
                titleOfCategory = newTitleOfCategory;
                setCategoryDirectory(titleOfCategory);
                return true;
            } else {
                System.out.println("Error renaming Category Directory");
            }
        }
        System.out.println("Wrong name Category Directory");
        return false;
    }

    public String getTitleOfCategory() {
        return titleOfCategory;
    }

    public String getCategoryDirectory() {
        return categoryDirectory;
    }

    public void setCategoryDirectory(String categoryDirectory) {
        this.categoryDirectory = categoryDirectory;
    }

    public boolean makeDirectory(String dir) {
        boolean result = false;
        String path = DIRECTORY + dir + "\\";
        File theDir = new File(path);

        if (!theDir.exists()) {
            System.out.println("creating directory: " + dir);
            try{
                result = theDir.mkdirs();
            }
            catch(SecurityException se){
                //handle it
            }
            if(result) {
                System.out.println("DIR created");
            } else {
                System.out.println("ERROR DIR name");
            }
        } else {
            System.out.println("DIR exists");
        }
        return result;
    }

    public void removeFlashcard(Flashcard card){
        listOfFlashcards.remove(card);
        countOfFlashcars--;
    }


}
