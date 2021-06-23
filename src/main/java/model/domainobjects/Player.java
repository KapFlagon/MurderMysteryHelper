package model.domainobjects;

import com.opencsv.bean.CsvBindByPosition;

public class Player {


    // Variables
    @CsvBindByPosition(position = 0)
    private String player_name;
    @CsvBindByPosition(position = 1)
    private boolean playing_game;
    private GameCharacter player_character;
    private char assigned_letter;


    // Constructors


    // Getters and Setters
    public String getPlayer_name() {
        return player_name;
    }
    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }


    public boolean isPlaying_game() {
        return playing_game;
    }
    public void setPlaying_game(boolean playing_game) {
        this.playing_game = playing_game;
    }


    public GameCharacter getPlayer_character() {
        return player_character;
    }
    public void setPlayer_character(GameCharacter player_character) {
        this.player_character = player_character;
    }

    public char getAssigned_letter() {
        return assigned_letter;
    }
    public void setAssigned_letter(char assigned_letter) {
        this.assigned_letter = assigned_letter;
    }


    // Initialisation methods


    // Other methods


}
