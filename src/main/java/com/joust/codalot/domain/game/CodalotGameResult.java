package com.joust.codalot.domain.game;

import java.io.Serializable;

public class CodalotGameResult implements Serializable {
    private boolean finished = false;
    private int knightCount;
    private int gameDurationHours;
    private String message;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getKnightCount() {
        return knightCount;
    }

    public void setKnightCount(int knightCount) {
        this.knightCount = knightCount;
    }

    public int getGameDurationHours() {
        return gameDurationHours;
    }

    public void incrementHourPlayed() {
        this.gameDurationHours++;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
