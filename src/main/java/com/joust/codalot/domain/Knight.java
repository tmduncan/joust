package com.joust.codalot.domain;

public class Knight extends Citizen {
    private int xp;
    private int stamina;
    private boolean exhausted;

    public Knight() {
        xp = 0;
        stamina = 0;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void incrementXp(int xp) {
        if (getStamina() >= 0  && !isExhausted()) {
            this.xp += xp;
        }
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

    public void incrementStamina(int stamina) {
        this.stamina += stamina;
        if (getStamina() < 0){
            setExhausted(true);
        }
    }

    public void wakeUp(){
        setExhausted(false);
    }

    public static class KnightBuilder {

        Knight knight = new Knight();

        KnightBuilder withXp(int xp) {
            this.knight.setXp(xp);
            return this;
        }

        public KnightBuilder withStamina(int stamina) {
            this.knight.setStamina(stamina);
            return this;
        }

        public KnightBuilder withPosition(Position position) {
            this.knight.setPosition(position);
            return this;
        }

        public Knight build() {
            return this.knight;
        }


    }
}
