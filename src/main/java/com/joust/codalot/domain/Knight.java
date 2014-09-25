package com.joust.codalot.domain;

public class Knight extends Citizen {
    private int xp;
    private int stamina;

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

    public static class KnightBuilder {

        Knight knight = new Knight();

        KnightBuilder withXp(int xp) {
            this.knight.setXp(xp);
            return this;
        }

        KnightBuilder withStamina(int stamina) {
            this.knight.setStamina(stamina);
            return this;
        }

        KnightBuilder withPosition(Position position) {
            this.knight.setPosition(position);
            return this;
        }

        Knight build() {
            return this.knight;
        }


    }
}
