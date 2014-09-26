package com.joust.codalot.domain;

public class King extends Citizen {

    public King() {
        setXp(0);
        setStamina(0);
    }

    @Override
    public boolean isRoyalty() {
        return true;
    }

    public static class KingBuilder {

        King king = new King();

        KingBuilder withXp(int xp) {
            this.king.setXp(xp);
            return this;
        }

        public KingBuilder withStamina(int stamina) {
            this.king.setStamina(stamina);
            return this;
        }

        public KingBuilder withPosition(Position position) {
            this.king.setPosition(position);
            return this;
        }

        public King build() {
            return this.king;
        }


    }


}
