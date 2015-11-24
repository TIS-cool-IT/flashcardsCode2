package sample;

import java.util.ArrayList;

/**
 * Category class
 */

public class Category {
    private int idOfCategory;
    private String titleOfCategory;
    private ArrayList<Flashcard> listOfFlashcards = new ArrayList<Flashcard>();


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


}
