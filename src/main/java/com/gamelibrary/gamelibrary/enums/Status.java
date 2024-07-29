package com.gamelibrary.gamelibrary.enums;

public enum Status {
    OFFLINE("Offline"),
    ONLINE("Online"),
    BUSY("Busy"),
    AWAY("Away"),
    SNOOZE("Snooze"),
    TRADE("Looking To Trade"),
    PLAY("Looking To Play");

    public final String label;

    Status(String label) {
        this.label = label;
    }
}
