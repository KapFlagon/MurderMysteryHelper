package ui.screens.gameresults.validation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import model.domainobjects.VotingResult;
import model.domainobjects.VotingStats;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VoteResultValidationPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private VBox vBox;

    // Other variables
    private List<VotingResult> preliminaryCorrectVotes;


    // Constructors



    // Getters & Setters
    public void setPreliminaryCorrectVotes(List<VotingResult> preliminaryCorrectVotes) {
        this.preliminaryCorrectVotes = preliminaryCorrectVotes;
        for(VotingResult vote : preliminaryCorrectVotes) {
            String text = "Vote time: + " + vote.getZonedDateTime() + "\nVoter: " + vote.getCharacterName() + "\nPlayer: " + vote.getPlayerName() + "\nMotive: " + vote.getMotive();
            CheckBox checkBox = new CheckBox(text);
            checkBox.setWrapText(true);
            vBox.getChildren().add(checkBox);
        }
    }

    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // UI event methods

    // Other methods


}