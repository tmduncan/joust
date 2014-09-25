package com.joust.codalot.domain.game;

import com.joust.codalot.domain.Codalot;

public class CodalotGameResult{
    private boolean finished = false;
    private int knightCount;

    Codalot codalot;

    public Codalot getCodalot() {
        return codalot;
    }

    public void setCodalot(Codalot codalot) {
        this.codalot = codalot;
    }

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
}
