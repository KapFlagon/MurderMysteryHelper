package ui.screens.murdererletterreveal;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MurdererLetterRevealPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private Label murdererLetterLbl;
    @FXML
    private Button revealMurdererLetterBtn;

    // Other variables
    private char murdererLetter;
    private SimpleBooleanProperty murdererLetterRevealed;

    // Constructors

    // Getters & Setters
    public Label getMurdererLetterLbl() {
        return murdererLetterLbl;
    }
    public void setMurdererLetterLbl(Label murdererLetterLbl) {
        this.murdererLetterLbl = murdererLetterLbl;
    }

    public char getMurdererLetter() {
        return murdererLetter;
    }
    public void setMurdererLetter(char murdererLetter) {
        this.murdererLetter = murdererLetter;
    }

    public boolean isMurdererLetterRevealed() {
        return murdererLetterRevealed.get();
    }
    public SimpleBooleanProperty murdererLetterRevealedProperty() {
        return murdererLetterRevealed;
    }
    public void setMurdererLetterRevealed(boolean murdererLetterRevealed) {
        this.murdererLetterRevealed.set(murdererLetterRevealed);
    }

    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        murdererLetterRevealed = new SimpleBooleanProperty(false);
        murdererLetterRevealed.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                revealMurdererLetterBtn.setText("Murderer revealed");
                revealMurdererLetterBtn.setDisable(true);
            }
        });
    }

    // UI event methods
    public void revealMurdererLetter() {
        murdererLetterLbl.setText(String.valueOf(murdererLetter));
        murdererLetterRevealed.set(true);
    }

    // Other methods


}