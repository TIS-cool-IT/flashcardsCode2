package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application implements Serializable{
    public static Stage primStage = null;
    public static Stage sounderStage = null;
    public static Parent editParent = null;
    public static Parent editOneParent = null;
    public static Parent launcherParent = null;
    public static Parent addParent = null;
    public static Parent presentationParent = null;
    public static Scene sounderScene = null;
    private static ArrayList<Category> categories = new ArrayList<>();
            //public static Scene skuska = null;

            // passing id
    //public static int categoryId;

    //public static Category editCategory;
    public static int idOfSelectedCategory = -1;

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
        //loadCategories();
    }

    public static void addCategory(Category cat) throws IOException {
        categories.add(cat);
        try {
            saveCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadCategories();
    }

    public static ArrayList<Category> getCategories() throws IOException {
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

    public static void setCategories(int index, Category cat){
        categories.set(index,cat);
    }




}
