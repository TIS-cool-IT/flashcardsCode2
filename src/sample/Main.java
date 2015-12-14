package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application implements Serializable{
    public static Stage primStage = null;
    public static Stage sounderStage = null;
    public static Parent editParent = null;
    public static Parent editOneParent = null;
    public static Parent launcherParent = null;
    public static Parent addParent = null;
    public static Parent presentationParent = null;
    public static Scene sounderScene = null;
    public static Parent  popupPresentation = null;
    private static ArrayList<Category> categories = new ArrayList<>();

    public static int idOfSelectedCategory = -1;
    public static int idOfSelectedFlashcard = -1;

    public static Stage afterPresentationStage;

    // controllers
    private static PresentationScreenController presentationScreenController;
    public static AfterPresentationController afterPresentationController;
    public static EditScreenController editScreenController;
    public static EditOneScreenController editOneScreenController;
    public static LauncherScreenController launcherScreenController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        addParent = FXMLLoader.load(getClass().getResource("addscreen.fxml"));
        //editParent = FXMLLoader.load(getClass().getResource("editScreen.fxml"));
        //editOneParent = FXMLLoader.load(getClass().getResource("editOneScreen.fxml"));


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

        //launcherParent = FXMLLoader.load(getClass().getResource("launcherScreen.fxml"));
        sounderScene = new Scene(FXMLLoader.load(getClass().getResource("sounderScreen.fxml")));

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
    }

    public static void backToLauncher(){
        primStage.getScene().setRoot(launcherParent);
        launcherScreenController.init();
    }

    public static void toEditScreen(){
        primStage.getScene().setRoot(editParent);
        editScreenController.init();
    }

    public static void toPresentationScreen(){
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
