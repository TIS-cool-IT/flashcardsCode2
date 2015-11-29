package sample;

/**
 * Basic Flashcard class
 */
public class Flashcard {

    private int id;
    private boolean reversed;
    private Category category;
    private FlashcardFace answer;
    private FlashcardFace question;
    private String flashcardDirectory;


    Flashcard(int id, boolean reversed, Category category,
              FlashcardFace question, FlashcardFace answer) {
        this.id = id;
        this.reversed = reversed;
        this.category = category;
        this.question = question;
        this.answer = answer;
        this.flashcardDirectory = category.getCategoryDirectory() + "\\" + Integer.toString(this.id);
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
