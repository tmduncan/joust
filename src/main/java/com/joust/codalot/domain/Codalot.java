package com.joust.codalot.domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Codalot {
    private List<Citizen> citizens;
    private DateTime yeOldeGameDateTime;

    public Codalot(DateTime yeOldeGameDateTime) {
        citizens = new ArrayList<>();
        setYeOldeGameDateTime(yeOldeGameDateTime);
    }

    protected void setCitizens(List<Citizen> citizens) {
        this.citizens.addAll(citizens);
    }

    protected void addCitizen(Citizen citizen) {
        this.citizens.add(citizen);
    }

    public void clearCitizens() {
        citizens.clear();
    }

    public void addCitizenToTrainingYard(Citizen citizen) {
        citizens.add(citizen);
        citizen.setPosition(Position.TRAINING_YARD);
    }

    public void addCitizenToTavern(Citizen citizen) {
        citizens.add(citizen);
        citizen.setPosition(Position.TAVERN);
    }

    public DateTime getYeOldeGameDateTime() {
        return yeOldeGameDateTime;
    }

    public void setYeOldeGameDateTime(DateTime yeOldeGameDateTime) {
        this.yeOldeGameDateTime = yeOldeGameDateTime;
    }

    public boolean isNewDayInTheKingdom(DateTime dateTime) {
        boolean retValue = true;
        if (dateTime.withTimeAtStartOfDay().isEqual(yeOldeGameDateTime.withTimeAtStartOfDay())) {
            retValue = false;
        }
        yeOldeGameDateTime = dateTime;
        return retValue;
    }

    public void process(DateTime dateHour) {
        for (Citizen citizen : citizens) {
            if (isNewDayInTheKingdom(dateHour)) {
                citizen.wakeUp();
            }

            if (citizen.isInPostion(Position.DAMSEL_IN_DISTRESS_SITE)) {
                citizen.incrementStamina(1);
                citizen.incrementXp(1);
            } else {
                citizen.incrementStamina(citizen.isInPostion(Position.TAVERN) ? 1 : -1);
                citizen.incrementXp((citizen.isInPostion(Position.TRAINING_YARD)) ? 1 : 0);
            }
        }
    }

    public void grantBonusXp() {
        int bonusCitizen = 0;
        for (Citizen citizen : citizens) {
            if (citizen.getXp() >= 3) {
                bonusCitizen++;
            }
        }
        if (bonusCitizen == 3) {
            for (Citizen citizen : citizens) {
                if (citizen.getXp() >= 3) {
                    citizen.setXp(citizen.getXp() + 5);
                }
            }
        }
        if (bonusCitizen == 5) {
            for (Citizen citizen : citizens) {
                if (citizen.getXp() >= 3) {
                    citizen.setXp(citizen.getXp() + 10);
                }
            }
        }
        if (bonusCitizen == 6) {
            for (Citizen citizen : citizens) {
                if (citizen.getXp() >= 3) {
                    citizen.setXp(citizen.getXp() + 20);
                }
            }
        }
    }

    public int calculateRoyalEarnedXp() {
        int total = 0;
        for (Citizen citizen : citizens) {
            if (citizen.isRoyalty()) {
                total += citizen.getXp();
            }
        }
        return total;
    }

    public int calculateEarnedXp() {
        int total = 0;
        for (Citizen citizen : citizens) {
            if (!citizen.isRoyalty()) {
                total += citizen.getXp();
            }
        }
        return total;
    }

    public int calculateEarnedStamina() {
        int total = 0;
        for (Citizen citizen : citizens) {
            total += citizen.getStamina();
        }
        return total;
    }

    //For testing
    protected void process() {
        this.process(DateTime.now());
    }
}
