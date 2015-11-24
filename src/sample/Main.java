package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primStage = null;
    public static Parent editParent = null;
    public static Parent editOneParent = null;
    public static Parent launcherParent = null;
    public static Parent addParent = null;
    public static Parent presentationParent = null;

    // passing id
    public static int categoryId;
    @Override
    public void start(Stage primaryStage) throws Exception{
        addParent = FXMLLoader.load(getClass().getResource("addscreen.fxml"));
        editParent = FXMLLoader.load(getClass().getResource("editScreen.fxml"));
        editOneParent = FXMLLoader.load(getClass().getResource("editOneScreen.fxml"));
        presentationParent = FXMLLoader.load(getClass().getResource("presentationScreen.fxml"));
        launcherParent = FXMLLoader.load(getClass().getResource("launcherScreen.fxml"));

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("launcherScreen.fxml")));
        primaryStage.setTitle("Flashcards");
        primaryStage.setScene(scene);
        primaryStage.show();


        primStage = primaryStage;

    }


    public static void main(String[] args) {
        launch(args);
    }


    public static void backToLauncher(){
        primStage.getScene().setRoot(launcherParent);
    }


    public static void toEditScreen(){
        primStage.getScene().setRoot(editParent);
    }

    public static void toPresentationScreen(){
        primStage.getScene().setRoot(presentationParent);
    }

    public static void toAddScreen(){
        primStage.getScene().setRoot(addParent);
    }

    public static void toEditOneScreen(){
        primStage.getScene().setRoot(editOneParent);
    }







}
