package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application implements Serializable{
    public static Stage primStage = null;
    public static Stage sounderStage = null;
    public static Stage bookStage = null;

    public static Parent editParent = null;
    public static Parent editOneParent = null;
    public static Parent launcherParent = null;
    public static Parent addParent = null;
    public static Parent presentationParent = null;
    public static Parent bookParent = null;


    public static Scene sounderScene = null;
    public static Scene bookScene = null;
    public static Parent  popupPresentation = null;
    private static ArrayList<Category> categories = new ArrayList<>();

    public static int idOfSelectedCategory = -1;
    public static int idOfSelectedFlashcard = -1;
    public static JavaSoundRecorder recorder = new JavaSoundRecorder();
    public static double fontSize = 16;
    public static boolean playSoundsAutomatically = true;

    public static Stage afterPresentationStage;

    // controllers
    private static PresentationScreenController presentationScreenController;
    public static AfterPresentationController afterPresentationController;
    public static EditScreenController editScreenController;
    public static EditOneScreenController editOneScreenController;
    public static LauncherScreenController launcherScreenController;
    public static BookScreenController bookScreenController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        addParent = FXMLLoader.load(getClass().getResource("addscreen.fxml"));

        // presentation controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("presentationScreen.fxml"));
        presentationParent = loader.load();
        presentationScreenController =  loader.getController();

        // after presentation controller
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("afterPresentation.fxml"));
        popupPresentation  = loader2.load();
        afterPresentationController = loader2.getController();
        afterPresentationStage = new Stage();
        Scene sceneForPopup = new Scene(popupPresentation);
        afterPresentationStage.setScene(sceneForPopup);
        afterPresentationStage.getScene().setRoot(popupPresentation);

        //edit controller
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("editScreen.fxml"));
        editParent = loader3.load();
        editScreenController = loader3.getController();

        //editOne controller
        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("editOneScreen.fxml"));
        editOneParent = loader4.load();
        editOneScreenController = loader4.getController();

        //laucher controller
        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("launcherScreen.fxml"));
        launcherParent = loader5.load();
        launcherScreenController = loader5.getController();

        //book screen controller
        FXMLLoader loader6 = new FXMLLoader(getClass().getResource("bookScreen.fxml"));
        bookParent = loader6.load();
        bookScreenController = loader6.getController();

        //launcherParent = FXMLLoader.load(getClass().getResource("launcherScreen.fxml"));
        sounderScene = new Scene(FXMLLoader.load(getClass().getResource("sounderScreen.fxml")));
        bookScene =  new Scene(FXMLLoader.load(getClass().getResource("bookScreen.fxml")));


        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("launcherScreen.fxml")));
        primaryStage.setTitle("Flashcards");
        primaryStage.setScene(scene);
        primaryStage.show();
        primStage = primaryStage;
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        backToLauncher();
    }

    public static void addCategory(Category category) throws IOException {
        categories.add(category);
        try {
            saveCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadCategories();
    }

    public static ArrayList<Category> getCategories() {
        //loadCategories();
        return categories;
    }

    public static ArrayList<Category> getLoadedCategories() throws IOException {
        loadCategories();
        return categories;
    }

    public static void saveCategories() throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\FlashCard\\categories.bin");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(categories);
        fos.close();
        out.close();
    }

    public static void loadCategories() throws IOException {
        File binFile = new File("C:\\FlashCard\\categories.bin");
        // create a bin file
        if (!binFile.exists()) {
            System.out.print("creating file: categories.bin ");
            System.out.println(binFile.createNewFile());
            saveCategories();
        }

        FileInputStream fos = new FileInputStream("C:\\FlashCard\\categories.bin");
        ObjectInputStream out = new ObjectInputStream(fos);
        try {
            categories = (ArrayList<Category>)out.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        fos.close();
        out.close();
    }

    public static void saveSettings() throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\FlashCard\\settings.bin");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeBoolean(playSoundsAutomatically);
        out.writeDouble(fontSize);
        out.flush();
        fos.close();
        out.close();
    }

    public static void loadSetting() throws IOException {
        File binFile = new File("C:\\FlashCard\\settings.bin");
        // create a bin file
        if (!binFile.exists()) {
            System.out.print("creating file: settings.bin ");
            System.out.println(binFile.createNewFile());
            saveSettings();
        }

        FileInputStream fos = new FileInputStream("C:\\FlashCard\\settings.bin");
        ObjectInputStream out = new ObjectInputStream(fos);
        playSoundsAutomatically = out.readBoolean();
        fontSize = out.readDouble();
        fos.close();
        out.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage(){
        return primStage;
    }

    public static Scene getSounderScene(){
        return sounderScene;
    }

    public static void openAudioBook() throws IOException {
        sounderStage = new Stage();
        sounderStage.setTitle("Sounder");
        sounderStage.setScene(sounderScene);
        sounderStage.show();
        sounderStage.toFront();
    }

    public static void openBook(File file) throws IOException {
        bookStage = new Stage();
        bookStage.setTitle("Book");
        bookStage.setScene(sounderScene);
        bookStage.getScene().setRoot(bookParent);
        bookScreenController.init(file);
        bookStage.show();
    }

    public static void backToLauncher(){
        primStage.getScene().setRoot(launcherParent);
        launcherScreenController.init();
    }

    public static void toEditScreen(){
        primStage.getScene().setRoot(editParent);
        editScreenController.init();
    }

    public static void toPresentationScreen() throws IOException {
        loadSetting();
        primStage.getScene().setRoot(presentationParent);
        presentationScreenController.showDialog();
        presentationScreenController.init();
    }

    public static void toAddScreen(){
        primStage.getScene().setRoot(addParent);
    }

    public static void toEditOneScreen(){
        primStage.getScene().setRoot(editOneParent);
        editOneScreenController.init();
    }

    public static void setCategories(int index, Category cat) throws IOException {
        categories.set(index,cat);
        saveCategories();
    }

    public static void setNewCategories(ArrayList<Category> cat) throws IOException {
        categories = cat;
        saveCategories();
    }

    public static Stage getPopupPresentationStage(){
        return afterPresentationStage;
    }

    public static AfterPresentationController getAfterPresentationController(){
        return afterPresentationController;
    }

}
