package ui.screens.roundscreen;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import model.domainobjects.GameData;
import model.domainobjects.Round;
import model.domainobjects.VotingStats;
import ui.screens.audioplayer.AudioPlayerPresenter;
import ui.screens.audioplayer.AudioPlayerView;
import ui.screens.gameresults.charts.GameResultsChartsPresenter;
import ui.screens.gameresults.charts.GameResultsChartsView;
import ui.screens.gameresults.validation.VoteResultValidationPresenter;
import ui.screens.gameresults.validation.VoteResultValidationView;
import ui.screens.murdererletterreveal.MurdererLetterRevealPresenter;
import ui.screens.murdererletterreveal.MurdererLetterRevealView;
import ui.screens.playerchecklist.PlayerChecklistPresenter;
import ui.screens.playerchecklist.PlayerChecklistView;
import ui.screens.playerintroprompts.PlayerIntroPromptsPresenter;
import ui.screens.playerintroprompts.PlayerIntroPromptsView;
import ui.screens.playeroutroprompts.PlayerOutroPromptsPresenter;
import ui.screens.playeroutroprompts.PlayerOutroPromptsView;
import ui.screens.todolist.ToDoListPresenter;
import ui.screens.todolist.ToDoListView;
import ui.screens.votingresultsupload.VotingResultsUploadPresenter;
import ui.screens.votingresultsupload.VotingResultsUploadView;
import utils.StageUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class RoundScreenPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Label roundNumLbl;
    @FXML
    private Button nextRoundBtn;
    @FXML
    private TextArea roundText;
    @FXML
    private SplitPane roundSplitPane;

    // Other variables
    private GameData gameData;
    private Round roundData;
    private SimpleBooleanProperty canProceedToNextRound;


    // Constructors

    // Getters & Setters
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
        roundData = gameData.getRoundsList().get(gameData.getCurrentRound());
        roundNumLbl.setText("Round " + (gameData.getCurrentRound() + 1));
        ToDoListView toDoListView= new ToDoListView();
        ToDoListPresenter toDoListPresenter = (ToDoListPresenter) toDoListView.getPresenter();
        toDoListPresenter.setChecklistItemsStringList(roundData.getDetails());
        roundSplitPane.getItems().add(toDoListView.getView());
        if(roundData.isHas_audio()){
            if(roundData.getAudio_file() != "") {
                try{
                    AudioPlayerView audioPlayerView = new AudioPlayerView();
                    AudioPlayerPresenter audioPlayerPresenter = (AudioPlayerPresenter) audioPlayerView.getPresenter();
                    audioPlayerPresenter.setGameData(gameData);
                    audioPlayerPresenter.setFileString(roundData.getAudio_file());
                    roundSplitPane.getItems().add(audioPlayerView.getView());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        switch(roundData.getFunc()) {
            case "":
                break;
            case "RevealMurdererLetter":
                canProceedToNextRound.set(false);
                MurdererLetterRevealView murdererLetterRevealView = new MurdererLetterRevealView();
                MurdererLetterRevealPresenter murdererLetterRevealPresenter = (MurdererLetterRevealPresenter) murdererLetterRevealView.getPresenter();
                murdererLetterRevealPresenter.setMurdererLetter(gameData.getMurdererLetter());
                murdererLetterRevealPresenter.murdererLetterRevealedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if(newValue) {
                            canProceedToNextRound.set(true);
                        }
                    }
                });
                roundSplitPane.getItems().add(murdererLetterRevealView.getView());
                break;
            case "SingleRandomList":
                PlayerIntroPromptsView playerIntroPromptsView = new PlayerIntroPromptsView();
                PlayerIntroPromptsPresenter playerIntroPromptsPresenter = (PlayerIntroPromptsPresenter) playerIntroPromptsView.getPresenter();
                playerIntroPromptsPresenter.setPlayerList(gameData.getActivePlayerList());
                roundSplitPane.getItems().add(playerIntroPromptsView.getView());
                break;
            case "playerChecklist":
                PlayerChecklistView playerChecklistView = new PlayerChecklistView();
                PlayerChecklistPresenter playerChecklistPresenter = (PlayerChecklistPresenter) playerChecklistView.getPresenter();
                playerChecklistPresenter.setPlayerList(gameData.getActivePlayerList());
                roundSplitPane.getItems().add(playerChecklistView.getView());
                break;
            case "VotingInput":
                canProceedToNextRound.set(false);
                VotingResultsUploadView votingResultsUploadView = new VotingResultsUploadView();
                VotingResultsUploadPresenter votingResultsUploadPresenter = (VotingResultsUploadPresenter) votingResultsUploadView.getPresenter();
                votingResultsUploadPresenter.setGameData(gameData);
                votingResultsUploadPresenter.fileUploadedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            canProceedToNextRound.set(true);
                        }
                    }
                });
                roundSplitPane.getItems().add(votingResultsUploadView.getView());
                break;
            case "FinalStatementsList":
                PlayerOutroPromptsView playerOutroPromptsView = new PlayerOutroPromptsView();
                PlayerOutroPromptsPresenter playerOutroPromptsPresenter = (PlayerOutroPromptsPresenter) playerOutroPromptsView.getPresenter();
                playerOutroPromptsPresenter.setMurdererLetter(gameData.getMurdererLetter());
                playerOutroPromptsPresenter.setPlayerList(gameData.getActivePlayerList());
                roundSplitPane.getItems().add(playerOutroPromptsView.getView());
                break;
            case "VotingResults":
                VotingStats votingStats = new VotingStats(gameData);

                VoteResultValidationView voteResultValidationView = new VoteResultValidationView();
                VoteResultValidationPresenter voteResultValidationPresenter = (VoteResultValidationPresenter) voteResultValidationView.getPresenter();
                voteResultValidationPresenter.setPreliminaryCorrectVotes(votingStats.getCorrectGuessesList());

                GameResultsChartsView gameResultsChartsView = new GameResultsChartsView();
                GameResultsChartsPresenter gameResultsChartsPresenter = (GameResultsChartsPresenter) gameResultsChartsView.getPresenter();
                gameResultsChartsPresenter.setVotingStats(votingStats);

                roundSplitPane.getItems().add(voteResultValidationView.getView());
                roundSplitPane.getItems().add(gameResultsChartsView.getView());
                break;
        }
        gameData.setCurrentRound(gameData.getCurrentRound()+1);
    }

    public void setRoundData(Round roundData) {
        this.roundData = roundData;
    }

    public BorderPane getMainBorderPane() {
        return mainBorderPane;
    }
    public void setMainBorderPane(BorderPane mainBorderPane) {
        this.mainBorderPane = mainBorderPane;
    }

    public Label getRoundNumLbl() {
        return roundNumLbl;
    }
    public void setRoundNumLbl(Label roundNumLbl) {
        this.roundNumLbl = roundNumLbl;
    }

    public Button getNextRoundBtn() {
        return nextRoundBtn;
    }
    public void setNextRoundBtn(Button nextRoundBtn) {
        this.nextRoundBtn = nextRoundBtn;
    }

    public TextArea getRoundText() {
        return roundText;
    }
    public void setRoundText(TextArea roundText) {
        this.roundText = roundText;
    }

    public SplitPane getRoundSplitPane() {
        return roundSplitPane;
    }
    public void setRoundSplitPane(SplitPane roundSplitPane) {
        this.roundSplitPane = roundSplitPane;
    }

    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canProceedToNextRound = new SimpleBooleanProperty(true);
        canProceedToNextRound.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    nextRoundBtn.setDisable(false);
                } else {
                    nextRoundBtn.setDisable(true);
                }
            }
        });
    }

    // UI event methods
    public void goToNextRound() {
        if(gameData.getCurrentRound() >= gameData.getRoundsList().size()) {
            // Final game over screen
        } else {
            RoundScreenView roundScreenView = new RoundScreenView();
            RoundScreenPresenter roundScreenPresenter = (RoundScreenPresenter) roundScreenView.getPresenter();
            roundScreenPresenter.setGameData(gameData);
            StageUtils.changeCurrentScene(roundScreenView.getView());
        }
    }

    // Other methods


}