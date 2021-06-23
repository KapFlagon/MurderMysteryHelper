package ui.screens.playerintroprompts;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.domainobjects.GameCharacter;
import model.domainobjects.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerIntroPromptsPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private Label nextCharacterLbl;
    @FXML
    private VBox playerListVBox;
    @FXML
    private Button nextCharacterBtn;

    // Other variables
    private List<Player> playerList;

    // Constructors

    // Getters & Setters
    public Label getNextCharacterLbl() {
        return nextCharacterLbl;
    }

    public void setNextCharacterLbl(Label nextCharacterLbl) {
        this.nextCharacterLbl = nextCharacterLbl;
    }

    public VBox getPlayerListVBox() {
        return playerListVBox;
    }

    public void setPlayerListVBox(VBox playerListVBox) {
        this.playerListVBox = playerListVBox;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> passedPlayerList) {
        this.playerList = new ArrayList<Player>();
        this.playerList.addAll(passedPlayerList);
        Collections.shuffle(playerList);
    }

    public Button getNextCharacterBtn() {
        return nextCharacterBtn;
    }

    public void setNextCharacterBtn(Button nextCharacterBtn) {
        this.nextCharacterBtn = nextCharacterBtn;
    }

    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // UI event methods
    public void displayNextCharacter() {
        if(playerList.size() > 0) {
            Player player = playerList.remove(0);
            String playerName = player.getPlayer_name();
            String characterName = player.getPlayer_character().getCharacter_name();
            Label label = new Label("Player: " + playerName + ", Character: " + characterName);
            label.setWrapText(true);
            playerListVBox.getChildren().add(label);
        } else {
            nextCharacterBtn.setText("No more characters!");
            nextCharacterBtn.setDisable(true);
        }
    }

    // Other methods


}