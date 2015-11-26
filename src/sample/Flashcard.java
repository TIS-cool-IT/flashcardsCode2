package sample;

/**
 * Basic Flashcard class
 */
public class Flashcard {

    private int id;
    private String titleOfFlashcard;
    private boolean reversed;
    private Category category;
    private FlashcardFace answer;
    private FlashcardFace question;
    private String flashcardDirectory;


    Flashcard(int id, String title, boolean reversed, Category category,
              FlashcardFace answer, FlashcardFace question) {
        this.id = id;
        this.titleOfFlashcard = title;
        this.reversed = reversed;
        this.category = category;
        this.answer = answer;
        this.question = question;
        this.flashcardDirectory = category.getCategoryDirectory() + "\\" + Integer.toString(this.id);
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitleOfFlashcard() {
        return titleOfFlashcard;
    }

    public void setTitleOfFlashcard(String newTitleOfFlashcard) {
        titleOfFlashcard = newTitleOfFlashcard;
    }

    public Category getFlashcardCategory() {
        return category;
    }

    public void setFlashcardCategory(Category category) {
        this.category = category;
    }

    public FlashcardFace getAnswer() {
        return answer;
    }

    public void setAnswer(FlashcardFace answer) {
        this.answer = answer;
    }

    public FlashcardFace getQuestion() {
        return question;
    }

    public void setQuestion(FlashcardFace question) {
        this.question = question;
    }

    public String getFlashcardDirectory() {
        return flashcardDirectory;
    }

    public void setFlashcardDirectory(String flashcardDirectory) {
        this.flashcardDirectory = flashcardDirectory;
    }
}
