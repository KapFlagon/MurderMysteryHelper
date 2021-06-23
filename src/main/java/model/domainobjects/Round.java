package model.domainobjects;

import java.util.ArrayList;
import java.util.List;

public class Round {


    // Variables
    private String name = "";
    private List<String> details = new ArrayList<String>();
    private String func = "";
    private boolean has_audio = false;
    private String audio_file = "";


    // Constructors


    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDetails() {
        return details;
    }
    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getFunc() {
        return func;
    }
    public void setFunc(String func) {
        this.func = func;
    }

    public boolean isHas_audio() {
        return has_audio;
    }
    public void setHas_audio(boolean has_audio) {
        this.has_audio = has_audio;
    }

    public String getAudio_file() {
        return audio_file;
    }
    public void setAudio_file(String audio_file) {
        this.audio_file = audio_file;
    }

    // Initialisation methods


    // Other methods


}
