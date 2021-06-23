package ui.screens.gameresults.charts;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.domainobjects.GameData;
import model.domainobjects.Player;
import model.domainobjects.VotingStats;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class GameResultsChartsPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private BarChart voteChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    // Other variables
    private VotingStats votingStats;


    // Constructors

    // Getters & Setters
    public void setVotingStats(VotingStats votingStats) {
        this.votingStats = votingStats;
        CategoryAxis xAxis = new CategoryAxis();
        XYChart.Series dataSeries = new XYChart.Series();
        for(Map.Entry<Player,Integer> entry: votingStats.getVoteCountMap().entrySet()) {
            String name = entry.getKey().getPlayer_name() + " \n " + entry.getKey().getPlayer_character().getCharacter_name();
            int count = entry.getValue();
            dataSeries.getData().add(new XYChart.Data(name, count));
        }
        voteChart.getData().add(dataSeries);
    }


    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // UI event methods

    // Other methods


}