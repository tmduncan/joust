package com.joust.codalot.test;

import java.util.ArrayList;

public class BasicCodalot implements Codalot{

    private ArrayList<Knight> knights;

    public BasicCodalot() {
        knights = new ArrayList<Knight>();
    }

    @Override
    public void setKnight(int id, KnightPosition position) {

    }

    @Override
    public void process() {
        for (Knight knight : knights) {
            knight.incrementStamina(knight.isInTavern ? 1 : -1);
            knight.incrementXp(knight.isInTrainingYard ? 1 : 0);
        }
    }

    @Override
    public int calculateEarnedXp() {
        return 0;
    }

    public void clearKnights() {
        knights.clear();
    }

    public void addKnightToTrainingYard(Knight knight) {
        knights.add(knight);
        knight.setInTrainingYard(true);
        knight.setInTavern(false);
    }

    public void addKnightToTavern(Knight knight) {
        knights.add(knight);
        knight.setInTavern(true);
        knight.setInTrainingYard(false);
    }

    public void grantBonusXp() {
        int bonusKnights = 0;
        for (Knight knight : knights) {
            if (knight.getXp() >= 3) {
                bonusKnights++;
            }
        }
        if (bonusKnights == 3) {
            for (Knight knight : knights) {
                if (knight.getXp() >= 3) {
                    knight.setXp(knight.getXp() + 5);
                }
            }
        }
        if (bonusKnights == 5) {
            for (Knight knight : knights) {
                if (knight.getXp() >= 3) {
                    knight.setXp(knight.getXp() + 10);
                }
            }
        }
        if (bonusKnights == 6) {
            for (Knight knight : knights) {
                if (knight.getXp() >= 3) {
                    knight.setXp(knight.getXp() + 20);
                }
            }
        }
    }

    public static class Knight {
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
    }
}
