package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primStage = null;
    public static Scene editScene = null;
    public static Scene launcherScene = null;
    public static Scene addScene = null;
    public static Scene presentationScene = null;


    @Override
    public void start(Stage primaryStage) throws Exception{
        addScene = new Scene(FXMLLoader.load(getClass().getResource("addscreen.fxml")));
        editScene = new Scene(FXMLLoader.load(getClass().getResource("editScreen.fxml")));
        presentationScene = new Scene(FXMLLoader.load(getClass().getResource("presentationScreen.fxml")));
        launcherScene = new Scene(FXMLLoader.load(getClass().getResource("launcherScreen.fxml")));
        primaryStage.setTitle("Flashcards");
        primaryStage.setScene(launcherScene);
        primaryStage.show();
        primStage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }


    public static void backToLauncher(){
        primStage.setScene(launcherScene);
    }

    public static void toEditScreen(){
        primStage.setScene(editScene);
    }

    public static void toPresentationScreen(){
        primStage.setScene(presentationScene);
    }






}
