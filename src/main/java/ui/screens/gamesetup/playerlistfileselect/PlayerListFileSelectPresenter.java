package ui.screens.gamesetup.playerlistfileselect;

import com.opencsv.exceptions.CsvException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import logic.PlayerListParser;
import model.domainobjects.GameData;
import model.domainobjects.Player;
import utils.StageUtils;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class PlayerListFileSelectPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn playingColumn;
    @FXML
    private TextField fileTextField;
    @FXML
    private Text fileSelectFeedbackTxt;


    // Other variables
    private GameData gameData;
    private Path filePathEvaluator;
    private SimpleBooleanProperty fileProvided;
    private FileChooser csvFileChooser;
    private PlayerListParser playerListParser;


    // Constructors


    // Getters & Setters
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public boolean isFileProvided() {
        return fileProvided.get();
    }
    public SimpleBooleanProperty fileProvidedProperty() {
        return fileProvided;
    }
    public void setFileProvided(boolean fileProvided) {
        this.fileProvided.set(fileProvided);
    }


    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        csvFileChooser = new FileChooser();
        csvFileChooser.setTitle("Select a Player list *.csv file");
        csvFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv files", "*.csv"));
        playerListParser = new PlayerListParser(gameData);
        // TODO Need to display success/fail for parsing the player .csv data.  
        createDummyTableData();
        addTextFieldValidation();
        fileProvided = new SimpleBooleanProperty(false);
    }


    // UI event methods
    public void openSelectFileDialog()throws IOException, CsvException {
        File csvFile = csvFileChooser.showOpenDialog(StageUtils.getMainStage());
        // TODO add null file check
        if(csvFile != null) {
            playerListParser = new PlayerListParser(gameData);
            playerListParser.parsePlayerCSVFile(csvFile);

            fileTextField.setText(csvFile.toString());
            for(String logString : playerListParser.getLogs()) {
                //updateLog(logString);
                // TODO add logging here
            }

        }
    }


    // Other methods
    public void addFileProvidedChangeListener(ChangeListener<Boolean> changeListener) {
        fileProvided.addListener(changeListener);
    }


    private void createDummyTableData() {
        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Player, String> p) {
                SimpleStringProperty ssp = new SimpleStringProperty(p.getValue().getPlayer_name());
                return ssp;
            }
        });
        playingColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Player, Boolean> param) {
                SimpleBooleanProperty simpleBooleanProperty = new SimpleBooleanProperty(param.getValue().isPlaying_game());
                return simpleBooleanProperty;
            }
        });

        List<Player> playersList = List.of(new Player("Jay Doe", true),new Player("Dany Bloggs", false));
        ObservableList<Player> playerObservableList = FXCollections.observableArrayList(playersList);
        tableView.setItems(playerObservableList);
    }

    private void addTextFieldValidation() {
        UnaryOperator<TextFormatter.Change> fileValidationFormatter = change -> {
            filePathEvaluator = Paths.get(change.getControlNewText());
            if(Files.isRegularFile(filePathEvaluator)){
                File file = filePathEvaluator.toFile();
                if(validateFieldTextAsPath(filePathEvaluator)) {
                    fileSelectFeedbackTxt.setText("Path points to a *.csv file");
                    setFileProvided(true);
                }
            } else {
                fileSelectFeedbackTxt.setText("Path does not point to a *.csv file");
                setFileProvided(false);
            }
            return change;
        };
        TextFormatter textFormatter = new TextFormatter(fileValidationFormatter);
        fileTextField.setTextFormatter(textFormatter);
    }

    private boolean validateFieldTextAsPath(Path filePath) {
        boolean returnValue = false;
        String fileExtension;
        int index = filePath.toString().lastIndexOf('.');
        if(index > 0) {
            fileExtension = filePath.toString().substring(index+1);
            if(fileExtension.equals("csv")){
                returnValue =  true;
                if(!Files.exists(filePath)) {
                    returnValue = false;
                }
            }
        }
        return returnValue;
    }


}