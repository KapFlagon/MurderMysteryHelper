package ui.screens.todolist;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ToDoListPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private VBox toDoListVBox;

    // Other variables
    private List<String> checklistItemsStringList;

    // Constructors

    // Getters & Setters
    public List<String> getChecklistItemsStringList() {
        return checklistItemsStringList;
    }
    public void setChecklistItemsStringList(List<String> checklistItemsStringList) {
        this.checklistItemsStringList = checklistItemsStringList;
        for (String checklistString : checklistItemsStringList) {
            CheckBox checkbox = new CheckBox();
            checkbox.setText(checklistString);
            checkbox.setWrapText(true);
            toDoListVBox.getChildren().add(checkbox);
        }
    }

    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checklistItemsStringList = new ArrayList<String>();
    }

    // UI event methods

    // Other methods


}