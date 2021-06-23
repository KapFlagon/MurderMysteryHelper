package ui.screens.votingresultsupload;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import logic.VotingResultsParser;
import model.domainobjects.GameData;
import model.domainobjects.Player;
import model.domainobjects.VotingResult;
import utils.StageUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class VotingResultsUploadPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private Button uploadResultsBtn;
    @FXML
    private VBox playerCharacterListVBox;

    // Other variables
    private GameData gameData;
    private FileChooser csvFileChooser;
    private SimpleBooleanProperty fileUploaded;


    // Constructors

    // Getters & Setters
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
        for(Player player : gameData.getActivePlayerList()) {
             Label tempLabel = new Label("Player: " + player.getPlayer_name() + ", character: " + player.getPlayer_character().getCharacter_name());
             playerCharacterListVBox.getChildren().add(tempLabel);
        }
    }

    public boolean isFileUploaded() {
        return fileUploaded.get();
    }

    public SimpleBooleanProperty fileUploadedProperty() {
        return fileUploaded;
    }

    public void setFileUploaded(boolean fileUploaded) {
        this.fileUploaded.set(fileUploaded);
    }

    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        csvFileChooser = new FileChooser();
        csvFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv files", "*.csv"));
        fileUploaded = new SimpleBooleanProperty(false);
        fileUploaded.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    uploadResultsBtn.setText("file uploaded");
                    uploadResultsBtn.setDisable(true);
                }
            }
        });
    }

    // UI event methods
    public void uploadResults() throws IOException {
        File csvFile = csvFileChooser.showOpenDialog(StageUtils.getMainStage());
        VotingResultsParser votingResultsParser = new VotingResultsParser(gameData);
        votingResultsParser.parseVotingResultsCSVFile(csvFile);
        fileUploaded.set(true);
    }


}