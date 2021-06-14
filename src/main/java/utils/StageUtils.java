package utils;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageUtils {


    // Variables
    private static Stage mainStage;
    private static Scene currentScene;



    // Constructors




    // Getters and Setters
    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        StageUtils.mainStage = mainStage;
    }


    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void setCurrentScene(Scene currentScene) {
        StageUtils.currentScene = currentScene;
    }

    // Initialisation methods


    // Other methods


}
