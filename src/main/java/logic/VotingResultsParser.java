package logic;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import model.domainobjects.GameData;
import model.domainobjects.Player;
import model.domainobjects.VotingResult;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VotingResultsParser {


    // Variables
    private GameData gameData;
    private CSVReader csvReader;
    private CsvToBeanBuilder votingResultCsvToBeanBuilder;
    private List<String> logs;


    // Constructors
    public VotingResultsParser(GameData gameData) {
        this.gameData = gameData;
        logs = new ArrayList<String>();
    }

    // Getters and Setters



    // Initialisation methods


    // Other methods
    public void parseVotingResultsCSVFile(File file) throws IOException {
        csvReader = new CSVReader(new FileReader(file));
        votingResultCsvToBeanBuilder = new CsvToBeanBuilder(csvReader).withType(VotingResult.class);
        List<VotingResult> votingResults = votingResultCsvToBeanBuilder.build().parse();
        for(VotingResult votingResult : votingResults) {
            for(Player player : gameData.getActivePlayerList()) {
                if(player.getPlayer_character().getCharacter_name().equals(votingResult.getCharacterName())){
                    votingResult.setPlayerName(player.getPlayer_name());
                }
            }
        }

        gameData.setVotingResults(votingResults);
    }

}
