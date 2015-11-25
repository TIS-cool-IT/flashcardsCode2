package sample;

import java.io.File;
import java.util.ArrayList;

/**
 * Category class
 */

public class Category {
    private int idOfCategory;
    private String titleOfCategory;
    private ArrayList<Flashcard> listOfFlashcards = new ArrayList<Flashcard>();
    private final String DIRECTORY = "C:\\FlashCard\\Categories\\";


    public Category(int id, String title) {
        idOfCategory = id;
        titleOfCategory = title;
    }

    public int getId() {
        return idOfCategory;
    }


    public void addFlashcard(Flashcard flashcard) {
        listOfFlashcards.add(flashcard);
    }

    public int getCount() {
        return listOfFlashcards.size();
    }

    public ArrayList<Flashcard> getFlashcards() {
        return listOfFlashcards;
    }

    public void setTitleOfCategory(String newTitleOfCategory) {
        titleOfCategory = newTitleOfCategory;
    }

    public String getTitleOfCategory() {
        return titleOfCategory;
    }

    public boolean saveCategory() {
        boolean result = false;
        String path = DIRECTORY + Integer.toString(idOfCategory) + "\\";
        File theDir = new File(path);

        if (!theDir.exists()) {
            System.out.println("creating directory: " + Integer.toString(idOfCategory));
            try{
                theDir.mkdirs();
                result = true;
            }
            catch(SecurityException se){
                //handle it
            }
            if(result) {
                System.out.println("DIR created");
            }
        } else {
            System.out.println("DIR exists");
        }
        return result;
    }
}
