package model.domainobjects;

import java.util.*;

public class VotingStats {


    // Variables
    private GameData gameData;
    private List<VotingResult> allVoteList;
    private List<VotingResult> correctGuessesList;
    private Map<Player, Integer> voteCountMap;


    // Constructors
    public VotingStats(GameData gameData) {
        this.gameData = gameData;
        allVoteList = gameData.getVotingResults();
        correctGuessesList = new ArrayList<VotingResult>();
        voteCountMap = new HashMap<>();
        parseVotesForCorrectGuesses(allVoteList);
        parseVoteCounts(allVoteList);
    }


    // Getters and Setters
    public List<VotingResult> getAllVoteList() {
        return allVoteList;
    }

    public void setAllVoteList(List<VotingResult> allVoteList) {
        this.allVoteList = allVoteList;
        parseVotesForCorrectGuesses(allVoteList);
        parseVoteCounts(allVoteList);
    }

    public List<VotingResult> getCorrectGuessesList() {
        return correctGuessesList;
    }

    public void setCorrectGuessesList(List<VotingResult> correctGuessesList) {
        this.correctGuessesList = correctGuessesList;
    }

    public Map<Player, Integer> getVoteCountMap() {
        return voteCountMap;
    }

    public void setVoteCountMap(Map<Player, Integer> voteCountMap) {
        this.voteCountMap = voteCountMap;
    }


    // Initialisation methods


    // Other methods
    private void parseVotesForCorrectGuesses(List<VotingResult> allVoteList) {
        Player murderer = findMurderer();
        for(VotingResult votingResult : allVoteList) {
            String voteAllegedMurderer = votingResult.getAllegedMurderer();
            String actualMurderer = murderer.getPlayer_character().getCharacter_name();
            if(voteAllegedMurderer.equals(actualMurderer)) {
                correctGuessesList.add(votingResult);
            }
        }
        Collections.sort(correctGuessesList);
        /*System.out.println("all correct guesses, in order");
        for(VotingResult result : correctGuessesList) {
            System.out.println(result.toString());
        }*/
    }

    private Player findMurderer() {
        Player murderer = null;
        for(Player player : gameData.getActivePlayerList()) {
            if(player.getAssigned_letter() == gameData.getMurdererLetter()) {
                murderer = player;
            }
        }
        return murderer;
    }

    private void parseVoteCounts(List<VotingResult> allVoteList) {
        Map<Player, Integer> unsortedVoteCountMap = new HashMap<>();
        for(Player player : gameData.getActivePlayerList()) {
            int individualVoteCount = 0;
            for(VotingResult votingResult : allVoteList) {
                if(votingResult.getAllegedMurderer().equals(player.getPlayer_character().getCharacter_name())) {
                    individualVoteCount++;
                }
            }
            unsortedVoteCountMap.put(player, individualVoteCount);
        }
        voteCountMap = sortMapByValue(unsortedVoteCountMap);
    }

    private Map<Player, Integer> sortMapByValue(Map<Player, Integer> unsortedVoteCountMap) {
        List<Map.Entry<Player, Integer>> list =
                new LinkedList<Map.Entry<Player, Integer>>(unsortedVoteCountMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Player, Integer>>() {
            public int compare(Map.Entry<Player, Integer> o1,
                               Map.Entry<Player, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Player, Integer> sortedMap = new LinkedHashMap<Player, Integer>();
        for (Map.Entry<Player, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

}
