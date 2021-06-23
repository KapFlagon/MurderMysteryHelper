package model.domainobjects;

import com.opencsv.bean.CsvBindByPosition;

public class GameCharacter {


    // Variables
    @CsvBindByPosition(position = 0)
    private String character_name;
    @CsvBindByPosition(position = 1)
    private boolean mandatory_character;
    @CsvBindByPosition(position = 2)
    private String character_sheet_file_name;


    // Constructors


    // Getters and Setters
    public String getCharacter_name() {
        return character_name;
    }
    public void setCharacter_name(String character_name) {
        this.character_name = character_name;
    }


    public boolean isMandatory_character() {
        return mandatory_character;
    }
    public void setMandatory_character(boolean mandatory_character) {
        this.mandatory_character = mandatory_character;
    }

    public String getCharacter_sheet_file_name() {
        return character_sheet_file_name;
    }

    public void setCharacter_sheet_file_name(String character_sheet_file_name) {
        this.character_sheet_file_name = character_sheet_file_name;
    }

    // Initialisation methods


    // Other methods


}
