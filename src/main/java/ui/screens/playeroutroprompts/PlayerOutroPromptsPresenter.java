package ui.screens.playeroutroprompts;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.domainobjects.Player;

import java.net.URL;
import java.util.*;

public class PlayerOutroPromptsPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private Button nextBtn;
    @FXML
    private VBox playerListVBox;

    // Other variables
    private List<Player> playerList;
    private char murdererLetter;

    // Constructors

    // Getters & Setters
    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> passedPlayerList) {
        this.playerList = new ArrayList<Player>();
        this.playerList.addAll(passedPlayerList);
        Collections.shuffle(this.playerList);
        Player murderer = null;
        for(int iterator = 0; iterator < playerList.size(); iterator++) {
            if(playerList.get(iterator).getAssigned_letter() == murdererLetter) {
                murderer = playerList.get(iterator);
            }
        }
        playerList.remove(murderer);
        playerList.add(murderer);
    }

    public char getMurdererLetter() {
        return murdererLetter;
    }

    public void setMurdererLetter(char murdererLetter) {
        this.murdererLetter = murdererLetter;
    }

    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // UI event methods
    public void displayNext() {
        if(playerList.size() > 0) {
            Player player = playerList.remove(0);
            String playerName = player.getPlayer_name();
            String characterName = player.getPlayer_character().getCharacter_name();
            char playerLetter = player.getAssigned_letter();
            Label label = buildLabel(playerName, characterName, playerLetter);
            label.setWrapText(true);
            playerListVBox.getChildren().add(label);
        } else {
            nextBtn.setText("That's everyone!");
            nextBtn.setDisable(true);
        }
    }

    // Other methods
    public Label buildLabel(String playerName, String characterName, char letter) {
        String full = "Player: " + playerName + ", Character: " + characterName +  ", letter: " + String.valueOf(letter);
        return new Label(full);
    }

}