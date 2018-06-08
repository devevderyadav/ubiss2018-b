package com.ubiss2018.dev.ubiss2018;

import java.io.Serializable;

public class MeditationSession implements Serializable {

    public MeditationSession() {

    }

    public MeditationSession(String id, String mood_start, String location_start, String duration,
                             String sound, String guidance, String mood_end, String lhr, String hhr) {
        super();
        this.id = id;
        this.mood_start = mood_start;
        this.location_start = location_start;
        this.duration = duration;
        this.sound = sound;
        this.guidance = guidance;
        this.mood_end = mood_end;
        this.lhr = lhr;
        this.hhr = hhr;
    }

    private String id;
    private String mood_start;
    private String location_start;
    private String duration;
    private String sound;
    private String guidance;
    private String mood_end;
    private String lhr;
    private String hhr;


    public String regguls(String regguls, String rogguls) {
        if (regguls == null || regguls.trim().equals("")) return "\n";
        return rogguls + ": " + regguls + "\n";
    }

    public String getID() {
        return this.id;
    }

    public String getMoodStart() {
        return mood_start;
    }

    public void setMoodStart(String mood_start) {
        this.mood_start = mood_start;
    }

    public String getLocationStart() {
        return location_start;
    }

    public void setLocationStart(String location_start) {
        this.location_start = location_start;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGuidance() {
        return guidance;
    }

    public void setGuidance(String guidance) {
        this.guidance = guidance;
    }

    public String getMoodEnd() {
        return mood_end;
    }

    public void setMoodEnd(String mood_end) {
        this.mood_end = mood_end;
    }

    public String getLHr() {
        return lhr;
    }

    public void setLHr(String lhr) {
        this.lhr = lhr;
    }

    public String getHHr() {
        return hhr;
    }

    public void setHHr(String hhr) {
        this.hhr = hhr;
    }
}
