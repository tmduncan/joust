package com.joust.codalot.domain;

public class Knight {
    private int xp;
    private int stamina;
    private boolean isInTavern;
    private boolean isInTrainingYard;

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
        this.xp += xp;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void incrementStamina(int stamina) {
        this.stamina += stamina;
    }

    public boolean isInTavern() {
        return isInTavern;
    }

    public void setInTavern(boolean isInTavern) {
        this.isInTavern = isInTavern;
    }

    public boolean isInTrainingYard() {
        return isInTrainingYard;
    }

    public void setInTrainingYard(boolean isInTrainingYard) {
        this.isInTrainingYard = isInTrainingYard;
    }

    public static class KnightBuilder{

        Knight knight = new Knight();

        KnightBuilder withXp(int xp){
            this.knight.setXp(xp);
            return this;
        }

        KnightBuilder withStamina(int stamina){
            this.knight.setStamina(stamina);
            return this;
        }


        Knight build(){
            return this.knight;
        }


    }
}
