package com.joust.codalot.domain;

public class Knight extends Citizen {
    public Knight() {
        setXp(0);
        setStamina(0);
    }

    @Override
    boolean isRoyalty() {
        return false;
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
