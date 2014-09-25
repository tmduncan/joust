package com.joust.codalot.domain;

import java.util.ArrayList;
import java.util.List;

public class Codalot {
    private List<Knight> knights;

    public Codalot() {
        knights = new ArrayList<>();
    }

    protected void setKnights(List<Knight> knights) {
        this.knights.addAll(knights);
    }

    protected void addKnight(Knight knight) {
        this.knights.add(knight);
    }

    public void clearKnights() {
        knights.clear();
    }

    public void addKnightToTrainingYard(Knight knight) {
        knights.add(knight);
        knight.setPosition(Position.TRAINING_YARD);
    }

    public void addKnightToTavern(Knight knight) {
        knights.add(knight);
        knight.setPosition(Position.TAVERN);
    }

    public void process() {
        for (Knight knight : knights) {
            knight.incrementStamina(knight.isInPostion(Position.TAVERN) ? 1 : -1);
            knight.incrementXp((knight.isInPostion(Position.TRAINING_YARD)) ? 1 : 0);
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

    public int calculateEarnedXp() {
        int total = 0;
        for (Knight knight : knights) {
            total += knight.getXp();
        }
        return total;
    }
}
