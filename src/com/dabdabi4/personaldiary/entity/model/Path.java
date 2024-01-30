package com.dabdabi4.personaldiary.entity.model;

public enum Path {
    USER_JSON("src//com//dabdabi4//personaldiary//repository//UserData.json");
    // TAG_JSON("src//com.dabdabi4.personaldiary//repository//TagData.json");

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
