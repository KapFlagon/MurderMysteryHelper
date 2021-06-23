package logic;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import model.domainobjects.GameCharacter;
import model.domainobjects.GameData;
import model.domainobjects.Player;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CharacterListParser {


    // Variables
    private Random random;
    private GameData gameData;
    private CSVReader csvReader;
    private CsvToBeanBuilder characterCsvToBeanBuilder;
    private List<String> logs;


    // Constructors
    public CharacterListParser(GameData gameData) {
        random = new Random();
        this.gameData = gameData;
        logs = new ArrayList<String>();
    }


    // Getters and Setters
    public List<String> getLogs() {
        return logs;
    }
    public void setLogs(List<String> logs) {
        this.logs = logs;
    }


    // Initialisation methods


    // Other methods
    public void parseCharacterCSVFile(File file) throws IOException {
        csvReader = new CSVReader(new FileReader(file));
        characterCsvToBeanBuilder = new CsvToBeanBuilder(csvReader).withType(GameCharacter.class);
        gameData.setAllCharactersList(characterCsvToBeanBuilder.build().parse());
        logs.add("Assigning characters to players");
        for(Player player : gameData.getActivePlayerList()) {
            player.setPlayer_character(pickRandomCharacter());
        }
        logs.add("Characters CSV fully parsed and processed");
    }


    private GameCharacter pickRandomCharacter() {
        GameCharacter gameCharacter = null;
        int bounds = gameData.getMandatoryCharactersList().size() + gameData.getAllCharactersList().size() - 1;
        int randomValue = random.nextInt(bounds);
        if(gameData.getMandatoryCharactersList().size() > 0) {
            return gameData.getMandatoryCharactersList().remove(gameData.getMandatoryCharactersList().size() - 1);
        } else {
            return gameData.getAllCharactersList().remove(gameData.getAllCharactersList().size() - 1);
        }
    }


}
