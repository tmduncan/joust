package com.joust.codalot.test;

import com.joust.codalot.domain.Knight;
import com.joust.codalot.domain.Position;

import java.util.ArrayList;

public class BasicCodalot implements Codalot{

    private ArrayList<Knight> knights;

    public BasicCodalot() {
        knights = new ArrayList<>();
        for (int i = 0; i < 6; ++i) {
            knights.add(new Knight());
        }
    }

    @Override
    public void setKnight(int id, Position position) {
        Knight knight = knights.get(id);
        knight.setInTavern(false);
        knight.setInTrainingYard(false);
        switch (position) {
            case TAVERN: {
                knight.setInTavern(true);
                break;
            }
            case TRAINING_YARD: {
                knight.setInTrainingYard(true);
                break;
            }
        }
    }

    @Override
    public void process() {
        for (Knight knight : knights) {
            knight.incrementStamina(knight.isInTavern() ? 1 : -1);
            knight.incrementXp(knight.isInTrainingYard() ? 1 : 0);
        }
    }

    @Override
    public int calculateEarnedXp() {
        int total = 0;
        for (Knight knight : knights) {
            total += knight.getXp();
        }
        return total;
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


}
