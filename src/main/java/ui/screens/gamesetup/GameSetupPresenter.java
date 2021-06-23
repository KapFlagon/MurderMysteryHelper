package ui.screens.gamesetup;

import com.opencsv.exceptions.CsvException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import logic.CharacterListParser;
import logic.PlayerListParser;
import logic.RoundJsonParser;
import model.domainobjects.GameData;
import model.domainobjects.Player;
import ui.screens.roundscreen.RoundScreenPresenter;
import ui.screens.roundscreen.RoundScreenView;
import utils.StageUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameSetupPresenter implements Initializable {

    // JavaFX injected node variables
    // Step 1 group
    @FXML
    private Label step1Lbl;
    @FXML
    private Text step1Txt;
    @FXML
    private Button uploadPlayersFileBtn;

    // Step 2 group
    @FXML
    private Label step2Lbl;
    @FXML
    private Text step2Txt;
    @FXML
    private Button uploadGameCharactersFileBtn;

    // Step 3 group
    @FXML
    private Label step3Lbl;
    @FXML
    private Text step3Txt;
    @FXML
    private Button selectCharacterPdfDirBtn;

    // Step 4 group
    @FXML
    private Label step4Lbl;
    @FXML
    private Text step4Txt;
    @FXML
    private Button selectOutputDirBtn;

    // Step 5 group
    @FXML
    private Label step5Lbl;
    @FXML
    private Text step5Txt;
    @FXML
    private Button selectRoundsFileBtn;


    // Step 6 group
    @FXML
    private Label step6Lbl;
    @FXML
    private Text step6Txt;
    @FXML
    private Button selectAudioDirBtn;


    // Step 7 group
    @FXML
    private Label step7Lbl;
    @FXML
    private Text step7Txt;
    @FXML
    private Button generateFilesBtn;

    @FXML
    private VBox logVBox;
    @FXML
    private Label stateLbl;
    @FXML
    private Button nextScreenBtn;

    // Other variables
    private GameData gameData;
    private SimpleBooleanProperty playerCsvFileUploaded;
    private SimpleBooleanProperty characterCsvFileUploaded;
    private SimpleBooleanProperty characterPdfDirSelected;
    private SimpleBooleanProperty outputDirSelected;
    private SimpleBooleanProperty roundsFileUploaded;
    private SimpleBooleanProperty playerFilesGenerated;
    private SimpleBooleanProperty audioDirSelected;
    private FileChooser csvFileChooser;
    private FileChooser jsonFileChooser;
    private DirectoryChooser directoryChooser;
    private PlayerListParser playerListParser;
    private CharacterListParser characterListParser;

    // Constructors

    // Getters & Setters
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerCsvFileUploaded = new SimpleBooleanProperty(false);
        playerCsvFileUploaded.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    uploadPlayersFileBtn.setText("File uploaded");
                    uploadPlayersFileBtn.setDisable(true);
                    step2Lbl.setDisable(false);
                    step2Txt.setDisable(false);
                    uploadGameCharactersFileBtn.setDisable(false);
                    stateLbl.setText("5 steps remaining!");
                }
            }
        });

        characterCsvFileUploaded = new SimpleBooleanProperty(false);
        characterCsvFileUploaded.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    uploadGameCharactersFileBtn.setText("File uploaded");
                    uploadGameCharactersFileBtn.setDisable(true);
                    step3Lbl.setDisable(false);
                    step3Txt.setDisable(false);
                    selectCharacterPdfDirBtn.setDisable(false);
                    stateLbl.setText("4 steps remaining!");
                }
            }
        });

        characterPdfDirSelected = new SimpleBooleanProperty(false);
        characterPdfDirSelected.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                selectCharacterPdfDirBtn.setText("Directory selected");
                selectCharacterPdfDirBtn.setDisable(true);
                step4Lbl.setDisable(false);
                step4Txt.setDisable(false);
                selectOutputDirBtn.setDisable(false);
                stateLbl.setText("3 steps remaining!");
            }
        });

        outputDirSelected = new SimpleBooleanProperty(false);
        outputDirSelected.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                selectOutputDirBtn.setText("Directory selected");
                selectOutputDirBtn.setDisable(true);
                step5Lbl.setDisable(false);
                step5Txt.setDisable(false);
                selectRoundsFileBtn.setDisable(false);
                stateLbl.setText("2 step remaining!");
            }
        });

        roundsFileUploaded = new SimpleBooleanProperty(false);
        roundsFileUploaded.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    selectRoundsFileBtn.setText("File selected");
                    selectRoundsFileBtn.setDisable(true);
                    step6Lbl.setDisable(false);
                    step6Txt.setDisable(false);
                    selectAudioDirBtn.setDisable(false);
                    stateLbl.setText("2 steps remaining!");
                }
            }
        });

        audioDirSelected = new SimpleBooleanProperty(false);
        audioDirSelected.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    selectAudioDirBtn.setText("Directory selected");
                    selectAudioDirBtn.setDisable(true);
                    step7Lbl.setDisable(false);
                    step7Txt.setDisable(false);
                    generateFilesBtn.setDisable(false);
                    stateLbl.setText("1 step remaining!");
                }
            }
        });

        playerFilesGenerated = new SimpleBooleanProperty(false);
        playerFilesGenerated.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    generateFilesBtn.setDisable(true);
                    uploadGameCharactersFileBtn.setDisable(true);
                    nextScreenBtn.setDisable(false);
                    stateLbl.setText("Done! You're good to go to the next screen!");
                }
            }
        });

        step2Lbl.setDisable(true);
        step2Txt.setDisable(true);
        step3Lbl.setDisable(true);
        step3Txt.setDisable(true);
        step4Lbl.setDisable(true);
        step4Txt.setDisable(true);
        step5Lbl.setDisable(true);
        step5Txt.setDisable(true);
        step6Lbl.setDisable(true);
        step6Txt.setDisable(true);
        step7Lbl.setDisable(true);
        step7Txt.setDisable(true);
        uploadGameCharactersFileBtn.setDisable(true);
        selectCharacterPdfDirBtn.setDisable(true);
        selectOutputDirBtn.setDisable(true);
        selectRoundsFileBtn.setDisable(true);
        selectAudioDirBtn.setDisable(true);
        generateFilesBtn.setDisable(true);
        nextScreenBtn.setDisable(true);
        stateLbl.setText("7 steps remaining!");

        csvFileChooser = new FileChooser();
        csvFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv files", "*.csv"));
        jsonFileChooser = new FileChooser();
        jsonFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("json files", "*.json"));
        directoryChooser = new DirectoryChooser();
    }

    // UI event methods
    public void uploadPlayersFile() throws IOException, CsvException {
        File csvFile = csvFileChooser.showOpenDialog(StageUtils.getMainStage());
        playerListParser = new PlayerListParser(gameData);
        playerListParser.parsePlayerCSVFile(csvFile);
        for(String logString : playerListParser.getLogs()) {
            updateLog(logString);
        }
        playerCsvFileUploaded.set(true);
    }

    public void uploadGameCharactersFile() throws IOException {
        File csvFile = csvFileChooser.showOpenDialog(StageUtils.getMainStage());
        characterListParser = new CharacterListParser(gameData);
        characterListParser.parseCharacterCSVFile(csvFile);
        for(String logString : characterListParser.getLogs()) {
            updateLog(logString);
        }
        characterCsvFileUploaded.set(true);
    }

    public void selectCharacterPdfDir() {
        File characterPdfDirectoryFile = directoryChooser.showDialog(StageUtils.getMainStage());
        gameData.setCharacterPdfDirectory(characterPdfDirectoryFile);
        updateLog("Character Pdf Directory selected");
        characterPdfDirSelected.set(true);
    }

    public void selectOutputDir() {
        File outputDirectoryFile = directoryChooser.showDialog(StageUtils.getMainStage());
        gameData.setOutputDirectory(outputDirectoryFile);
        updateLog("Output Directory selected");
        outputDirSelected.set(true);
    }

    public void selectRoundsFile() throws IOException {
        File jsonFile = jsonFileChooser.showOpenDialog(StageUtils.getMainStage());
        updateLog("JSON file read");
        RoundJsonParser roundJsonParser = new RoundJsonParser();
        gameData.setRoundsList(roundJsonParser.readJsonFile(jsonFile));
        updateLog("JSON file parsed");
        roundsFileUploaded.set(true);
    }

    public void generateFiles() throws IOException {
        for(Player player : gameData.getActivePlayerList()){
            // Create a temp folder in the output directory
            Path playerFolderPath = Paths.get(gameData.getOutputDirectory().toString(), player.getPlayer_name());
            Files.createDirectories(playerFolderPath);
            // Copy the pdf file to the temp folder
            Path originalPathForPDF = Paths.get(gameData.getCharacterPdfDirectory().toString(), player.getPlayer_character().getCharacter_sheet_file_name());
            Files.copy(originalPathForPDF, playerFolderPath.resolve(originalPathForPDF.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            // Create a text file named 'letter', containing only the letter assigned to the player.
            Path letterFilePath = Paths.get(playerFolderPath.toString(), "letter.txt");
            List<String> lines = Arrays.asList(String.valueOf((player.getAssigned_letter())));
            Files.write(letterFilePath, lines, StandardCharsets.UTF_8);
            // Zip the folder
            zipFolder(playerFolderPath);
            // Delete the original temp folder (?)
            deleteDirectory(playerFolderPath);
            // Write murdererletter into a file as a backup
            // Create a text file named 'letter', containing only the letter assigned to the player.
            Path murdererLetterFilePath = Paths.get(gameData.getOutputDirectory().toString(), "MurdererLetter.txt");
            List<String> lines2 = Arrays.asList(String.valueOf((gameData.getMurdererLetter())));
            Files.write(murdererLetterFilePath, lines2, StandardCharsets.UTF_8);
        }
        playerFilesGenerated.set(true);
    }

    public void selectAudioDir() {
        File audioDirectoryFile = directoryChooser.showDialog(StageUtils.getMainStage());
        gameData.setAudioDirectory(audioDirectoryFile);
        updateLog("Audio Directory selected");
        audioDirSelected.set(true);
    }

    public void goToNextScreen() {
        RoundScreenView roundScreenView = new RoundScreenView();
        RoundScreenPresenter roundScreenPresenter = (RoundScreenPresenter) roundScreenView.getPresenter();
        roundScreenPresenter.setGameData(gameData);
        StageUtils.changeCurrentScene(roundScreenView.getView());
    }

    public void updateLog(String logText) {
        Label logLabel = new Label(logText);
        logVBox.getChildren().add(logLabel);
    }

    // Other methods
    public void printAllDetails() {
        for(Player player: gameData.getActivePlayerList()){
            updateLog("Player " + player.getPlayer_name() + " is playing as character " + player.getPlayer_character().getCharacter_name() + " and assigned as letter " + player.getAssigned_letter());
        }
        boolean murdererFound = false;
        while(!murdererFound) {
            for(Player player : gameData.getActivePlayerList()) {
                if (player.getAssigned_letter() == gameData.getMurdererLetter()) {
                    updateLog("Murderer is letter " + gameData.getMurdererLetter() + " and is player " + player.getPlayer_name() + " playing as character " + player.getPlayer_character().getCharacter_name() + " with letter " + player.getAssigned_letter());
                    murdererFound = true;
                }
            }
        }
    }


    public void zipFolder(Path source) throws IOException {

        // get folder name as zip file name
        String zipFileName = source.toString() + ".zip";
        //System.out.println("zipFileName: " + zipFileName);

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            Files.walkFileTree(source, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {

                    // only copy files, no symbolic links
                    if (attributes.isSymbolicLink()) {
                        return FileVisitResult.CONTINUE;
                    }

                    try (FileInputStream fis = new FileInputStream(file.toFile())) {
                        Path targetFile = source.relativize(file);
                        //System.out.println("targetFile: " + targetFile.toString());
                        zos.putNextEntry(new ZipEntry(targetFile.toString()));

                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }

                        // if large file, throws out of memory
                        //byte[] bytes = Files.readAllBytes(file);
                        //zos.write(bytes, 0, bytes.length);

                        zos.closeEntry();

                        //System.out.printf("Zip file : %s%n", file);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.err.printf("Unable to zip : %s%n%s%n", file, exc);
                    return FileVisitResult.CONTINUE;
                }
            });

        }
    }

    public static void deleteDirectory(Path path)
            throws IOException {

        Files.walkFileTree(path,
                new SimpleFileVisitor<>() {

                    // delete directories or folders
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir,
                                                              IOException exc)
                            throws IOException {
                        Files.delete(dir);
                        //System.out.printf("Directory is deleted : %s%n", dir);
                        return FileVisitResult.CONTINUE;
                    }

                    // delete files
                    @Override
                    public FileVisitResult visitFile(Path file,
                                                     BasicFileAttributes attrs)
                            throws IOException {
                        Files.delete(file);
                        //System.out.printf("File is deleted : %s%n", file);
                        return FileVisitResult.CONTINUE;
                    }
                }
        );

    }


}