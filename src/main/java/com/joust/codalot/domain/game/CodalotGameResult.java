package com.joust.codalot.domain.game;

import java.io.Serializable;

public class CodalotGameResult implements Serializable {
    private boolean finished = false;
    private int knightCount;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
