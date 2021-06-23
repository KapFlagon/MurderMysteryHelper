package logic;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import model.domainobjects.GameData;
import model.domainobjects.Player;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PlayerListParser {


    // Variables
    private Random random;
    private GameData gameData;
    private char fullAlphabet[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private CSVReader csvReader;
    private CsvToBeanBuilder playerCsvToBeanBuilder;
    private List<Character> alphabetBag;
    private List<String> logs;


    // Constructors
    public PlayerListParser(GameData gameData) {
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
    private void initAlphabetBag() {
        alphabetBag = new ArrayList<Character>();
        for(int iterator = 0; iterator < gameData.getActivePlayerList().size(); iterator++) {
            alphabetBag.add(fullAlphabet[iterator]);
        }
        for(int iterator = 0; iterator < 3; iterator++) {
            Collections.shuffle(alphabetBag);
        }
        logs.add("Alphabet Bag initialized and shuffled");
    }


    // Other methods
    public void parsePlayerCSVFile(File file) throws IOException {
        csvReader = new CSVReader(new FileReader(file));
        playerCsvToBeanBuilder = new CsvToBeanBuilder(csvReader).withType(Player.class);
        List<Player> allPlayerList = playerCsvToBeanBuilder.build().parse();
        logs.add("All Players read from file");
        parseActivePlayers(allPlayerList);
        initAlphabetBag();
        mapPlayerToLetter();
        selectMurderer();
        logs.add("Player CSV fully parsed and processed");
    }

    private void parseActivePlayers(List<Player> allPlayerList) {
        for (Player player : allPlayerList) {
            if (player.isPlaying_game()) {
                gameData.getActivePlayerList().add(player);
            }
        }
        logs.add("Active Players successfully parsed");
    }

    private void mapPlayerToLetter(){
        for(int iterator = 0; iterator < gameData.getActivePlayerList().size(); iterator++) {
            gameData.getActivePlayerList().get(iterator).setAssigned_letter(alphabetBag.get(iterator));
        }
        logs.add("All players mapped to a letter");
    }

    private void selectMurderer() {
        random = new Random();
        gameData.setMurdererLetter(alphabetBag.remove(random.nextInt(alphabetBag.size())));
        logs.add("Random murderer selected");
    }

}
