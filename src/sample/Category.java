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
    private String categoryDirectory;
    private final String DIRECTORY = "C:\\FlashCard\\Categories\\"; //TODO DIRECTORY ukladania dať do hlavnej triedy


    public Category(int id, String title) {
        idOfCategory = id;
        titleOfCategory = title;
        categoryDirectory = Integer.toString(idOfCategory);
        makeDirectory(categoryDirectory);
    }

    public int getId() {
        return idOfCategory;
    }


    public void addFlashcard(Flashcard flashcard) {
        listOfFlashcards.add(flashcard);
        makeDirectory(flashcard.getFlashcardDirectory());
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
