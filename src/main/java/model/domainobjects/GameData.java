package model.domainobjects;

import java.io.File;
import java.util.*;

public class GameData {


    // Variables
    private List<Player> activePlayerList;
    private List<GameCharacter> allCharactersList;
    private List<GameCharacter> mandatoryCharactersList;
    private char murdererLetter;
    private File characterPdfDirectory;
    private File outputDirectory;
    private File audioDirectory;
    private List<Round> roundsList;
    private int currentRound;
    private List<VotingResult> votingResults;

    // Constructors
    public GameData() {
        activePlayerList = new ArrayList<Player>();
        allCharactersList = new ArrayList<GameCharacter>();
        mandatoryCharactersList = new ArrayList<GameCharacter>();
        murdererLetter = ' ';
        roundsList = new ArrayList<Round>();
        currentRound = 0;
        votingResults = new ArrayList<VotingResult>();
    }


    // Getters and Setters
    public List<Player> getActivePlayerList() {
        return activePlayerList;
    }
    public void setActivePlayerList(List<Player> activePlayerList) {
        this.activePlayerList = activePlayerList;
    }

    public List<GameCharacter> getAllCharactersList() {
        return allCharactersList;
    }
    public void setAllCharactersList(List<GameCharacter> allCharactersList) {
        this.allCharactersList = allCharactersList;
        updateMandatoryCharacterList();
    }

    public List<GameCharacter> getMandatoryCharactersList() {
        return mandatoryCharactersList;
    }
    public void setMandatoryCharactersList(List<GameCharacter> mandatoryCharactersList) {
        this.mandatoryCharactersList = mandatoryCharactersList;
    }

    public char getMurdererLetter() {
        return murdererLetter;
    }
    public void setMurdererLetter(char murdererLetter) {
        this.murdererLetter = murdererLetter;
    }

    public File getCharacterPdfDirectory() {
        return characterPdfDirectory;
    }
    public void setCharacterPdfDirectory(File characterPdfDirectory) {
        this.characterPdfDirectory = characterPdfDirectory;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }
    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public File getAudioDirectory() {
        return audioDirectory;
    }
    public void setAudioDirectory(File audioDirectory) {
        this.audioDirectory = audioDirectory;
    }

    public List<Round> getRoundsList() {
        return roundsList;
    }
    public void setRoundsList(List<Round> roundsList) {
        this.roundsList = roundsList;
    }

    public int getCurrentRound() {
        return currentRound;
    }
    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public List<VotingResult> getVotingResults() {
        return votingResults;
    }
    public void setVotingResults(List<VotingResult> votingResults) {
        this.votingResults = votingResults;
    }


    // Initialisation methods


    // Other methods
    public void updateMandatoryCharacterList() {
        Iterator<GameCharacter> gameCharacterIterator = allCharactersList.listIterator();
        while (gameCharacterIterator.hasNext()) {
            GameCharacter tempGameCharacter = gameCharacterIterator.next();
            if (tempGameCharacter.isMandatory_character()) {
                mandatoryCharactersList.add(tempGameCharacter);
                gameCharacterIterator.remove();
            }
        }
    }

}
