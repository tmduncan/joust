package com.joust.codalot.domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Codalot {
    private List<Knight> knights;
    private DateTime yeOldeGameDateTime;

    public Codalot(DateTime yeOldeGameDateTime) {
        knights = new ArrayList<>();
        setYeOldeGameDateTime(yeOldeGameDateTime);
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

    public DateTime getYeOldeGameDateTime() {
        return yeOldeGameDateTime;
    }

    public void setYeOldeGameDateTime(DateTime yeOldeGameDateTime) {
        this.yeOldeGameDateTime = yeOldeGameDateTime;
    }

    public boolean isNewDayInTheKingdom(DateTime dateTime){
        boolean retValue = true;
        if (dateTime.withTimeAtStartOfDay().isEqual(yeOldeGameDateTime.withTimeAtStartOfDay())){
            retValue = false;
        }
        yeOldeGameDateTime = dateTime;
        return retValue;
    }

    public void process(DateTime dateHour) {
        for (Knight knight : knights) {
            if (isNewDayInTheKingdom(dateHour)){
                knight.wakeUp();
            }

            if (knight.isInPostion(Position.DAMSEL_IN_DISTRESS_SITE)){
                knight.incrementStamina(1);
                knight.incrementXp(1);
            } else {
                knight.incrementStamina(knight.isInPostion(Position.TAVERN) ? 1 : -1);
                knight.incrementXp((knight.isInPostion(Position.TRAINING_YARD)) ? 1 : 0);
            }
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

    public int calculateEarnedStamina() {
        int total = 0;
        for (Knight knight : knights) {
            total += knight.getStamina();
        }
        return total;
    }

    //For testing
    protected void process() {
        this.process(DateTime.now());
    }
}
