package ui.screens.playerchecklist;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.domainobjects.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerChecklistPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private Label characterNameLbl;
    @FXML
    private Button randomStarterBtn;
    @FXML
    private VBox contentVBox;

    // Other variables
    private List<Player> playerList;

    // Constructors

    // Getters & Setters
    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> passedPlayerList) {
        this.playerList = new ArrayList<Player>();
        this.playerList.addAll(passedPlayerList);
        for(Player player : playerList) {
            String playerName = player.getPlayer_name();
            String characterName = player.getPlayer_character().getCharacter_name();
            CheckBox checkBox = new CheckBox("Player: " + playerName + ", Character: " + characterName);
            checkBox.setWrapText(true);
            contentVBox.getChildren().add(checkBox);
        }
    }

    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // UI event methods
    public void randomStarter() {
        Collections.shuffle(playerList);
        characterNameLbl.setText(playerList.get(0).getPlayer_character().getCharacter_name());
    }

    // Other methods


}