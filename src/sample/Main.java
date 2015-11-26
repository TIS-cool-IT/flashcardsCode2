package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage primStage = null;
    public static Stage sounderStage = null;
    public static Parent editParent = null;
    public static Parent editOneParent = null;
    public static Parent launcherParent = null;
    public static Parent addParent = null;
    public static Parent presentationParent = null;
    public static Scene sounderScene = null;
    //public static Scene skuska = null;

    // passing id
    public static int categoryId;
    @Override
    public void start(Stage primaryStage) throws Exception{
        addParent = FXMLLoader.load(getClass().getResource("addscreen.fxml"));
        editParent = FXMLLoader.load(getClass().getResource("editScreen.fxml"));
        editOneParent = FXMLLoader.load(getClass().getResource("editOneScreen.fxml"));
        presentationParent = FXMLLoader.load(getClass().getResource("presentationScreen.fxml"));
        launcherParent = FXMLLoader.load(getClass().getResource("launcherScreen.fxml"));
        sounderScene = new Scene(FXMLLoader.load(getClass().getResource("sounderScreen.fxml")));

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("launcherScreen.fxml")));
        //skuska = new Scene(FXMLLoader.load(getClass().getResource("sounderScreen.fxml")));
        primaryStage.setTitle("Flashcards");
        primaryStage.setScene(scene);
        primaryStage.show();
        primStage = primaryStage;
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
    }

    /*public static Scene getSounderParent(){
        return sounder;
    }

    public static Scene getAddParent(){
        return addScene;
    }*/

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

    /*public static void openAudioBook(){
        sounderStage = new Stage();
        sounderStage.setTitle("Sounder");
        sounderStage.setScene(new Scene(sounder);
        sounderStage.show();
    }
*/




}
