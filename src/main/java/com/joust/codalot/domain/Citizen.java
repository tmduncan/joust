package com.joust.codalot.domain;

public abstract class Citizen {
    private int xp;
    private int stamina;
    private boolean exhausted;
    private int hoursAtRoundTable = 0;

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isInPostion(Position position) {
        return getPosition().equals(position);
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public boolean isExhausted() {
        return exhausted;
    }

    public void setExhausted(boolean exhausted) {
        this.exhausted = exhausted;
    }

    public int getHoursAtRoundTable() {
        return hoursAtRoundTable;
    }

    public void setHoursAtRoundTable(int hoursAtRoundTable) {
        this.hoursAtRoundTable = hoursAtRoundTable;
    }

    public void wakeUp() {
        setExhausted(false);

        if (!isRoundTableGranted()) {
            setHoursAtRoundTable(0);
        }
    }

    public void incrementXp(int xp) {
        if (isRoyalty() || getHoursAtRoundTable() >= 3) {
            if (getStamina() >= 0 && !isExhausted()) {
                setXp(getXp() + xp);
            }
        }
    }

    public void incrementRoundTableVisit(int visit) {
        setHoursAtRoundTable(getHoursAtRoundTable() + visit);

    }

    public void incrementStamina(int stamina) {
        setStamina(getStamina() + stamina);
        if (getStamina() < 0) {
            setExhausted(true);
        }
    }

    public abstract boolean isRoyalty();


    //For Testing
    private boolean roundTableGranted;

    protected boolean isRoundTableGranted() {
        return roundTableGranted;
    }

    protected void setRoundTableGranted(boolean roundTableGranted) {
        this.roundTableGranted = roundTableGranted;
    }
}
