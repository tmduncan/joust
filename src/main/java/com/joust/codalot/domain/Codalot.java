package com.joust.codalot.domain;

import java.util.ArrayList;

public class Codalot {
    private ArrayList<Knight> knights;

    public Codalot() {
        knights = new ArrayList<>();
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

    public void process() {
        for (Knight knight : knights) {
            knight.incrementStamina(knight.isInTavern() ? 1 : -1);
            knight.incrementXp(knight.isInTrainingYard() ? 1 : 0);
        }
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
}
