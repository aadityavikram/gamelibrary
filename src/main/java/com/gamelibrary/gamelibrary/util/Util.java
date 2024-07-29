package com.gamelibrary.gamelibrary.util;

import com.gamelibrary.gamelibrary.enums.Status;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Component
public class Util {

    public String getDateTime(String timeInSeconds) {
        Date date = new Date(Integer.parseInt(timeInSeconds)* 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy HH:mm", Locale.ENGLISH);
        return sdf.format(date);
    }

    public String getCurrentStatus(String personaState) {
        return switch (personaState) {
            case "0" -> Status.OFFLINE.label;
            case "1" -> Status.ONLINE.label;
            case "2" -> Status.BUSY.label;
            case "3" -> Status.AWAY.label;
            case "4" -> Status.SNOOZE.label;
            case "5" -> Status.TRADE.label;
            case "6" -> Status.PLAY.label;
            default -> "";
        };
    }
}
