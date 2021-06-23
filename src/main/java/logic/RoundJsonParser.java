package logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.domainobjects.GameData;
import model.domainobjects.Round;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RoundJsonParser {


    // Variables
    private ObjectMapper objectMapper;


    // Constructors
    public RoundJsonParser() {
        objectMapper = new ObjectMapper();
    }


    // Getters and Setters


    // Initialisation methods


    // Other methods
    public List<Round> readJsonFile(File file) throws IOException {
        return objectMapper.readValue(file, new TypeReference<List<Round>>() {});
    }

}
