package driver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.domainobjects.GameData;
import ui.screens.audioplayer.AudioPlayerPresenter;
import ui.screens.audioplayer.AudioPlayerView;
import ui.screens.gamesetup.GameSetupPresenter;
import ui.screens.gamesetup.GameSetupView;
import ui.screens.votingresultsupload.VotingResultsUploadPresenter;
import ui.screens.votingresultsupload.VotingResultsUploadView;
import utils.StageUtils;

import java.io.File;

public class MurderMysteryHelperApp extends Application {

    private int appMinheight = 400;
    private int appMinWidth = 600;
    private Scene currentScene;
    //private GameRepository gameRepository;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StageUtils.setMainStage(primaryStage);
        GameData gameData = new GameData();
        fullGame(gameData);
        primaryStage.setTitle("Murder Mystery Helper App");
        primaryStage.setScene(StageUtils.getCurrentScene());
        primaryStage.show();
    }

    public void fullGame(GameData gameData) {
        GameSetupView view = new GameSetupView();
        GameSetupPresenter presenter = (GameSetupPresenter) view.getPresenter();
        presenter.setGameData(gameData);
        Scene scene = new Scene(view.getView());
        StageUtils.setCurrentScene(scene);
    }

}
