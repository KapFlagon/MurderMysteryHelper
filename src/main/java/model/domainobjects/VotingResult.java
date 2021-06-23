package model.domainobjects;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;
import utils.customimplementations.ZoneDateTimeConverter;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Date;

public class VotingResult implements Comparable<VotingResult> {


    // Variables
    @CsvBindByName(column = "Your character", required = true)
    private String characterName;
    @CsvBindByName(column = "The murderer's identity", required = true)
    private String allegedMurderer;
    @CsvCustomBindByName(column = "Timestamp", converter = ZoneDateTimeConverter.class, required = true)
    private ZonedDateTime zonedDateTime;
    // 2021/06/17 8:51:09 AM GMT+1       US local
    @CsvBindByName(column = "The murderer's motive", required = true)
    private String motive;
    private String playerName;



    // Constructors


    // Getters and Setters
    public String getCharacterName() {
        return characterName;
    }
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getAllegedMurderer() {
        return allegedMurderer;
    }
    public void setAllegedMurderer(String allegedMurderer) {
        this.allegedMurderer = allegedMurderer;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // Initialisation methods


    // Other methods
    @Override
    public String toString() {
        return "VotingResult{" +
                "characterName='" + characterName + '\'' +
                ", allegedMurderer='" + allegedMurderer + '\'' +
                ", zonedDateTime=" + zonedDateTime +
                '}';
    }

    @Override
    public int compareTo(VotingResult o) {
        return this.getZonedDateTime().toInstant().compareTo(o.getZonedDateTime().toInstant());
    }
}
