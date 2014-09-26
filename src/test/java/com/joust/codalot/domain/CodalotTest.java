package com.joust.codalot.domain;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.joust.codalot.domain.Position.DAMSEL_IN_DISTRESS_SITE;
import static com.joust.codalot.domain.Position.TAVERN;
import static com.joust.codalot.domain.Position.TRAINING_YARD;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CodalotTest {
    List<Knight> knights;
    Codalot underTest;

    @Before
    public void setup(){
        underTest = TestCodalotBuilder.createBasicCodalot();
    }


    @Test
    public void thatXpIsCalculatedCorrectly() {

        givenKnightsInPosition(4, TAVERN);

        underTest.setKnights(knights);
        underTest.process();
        underTest.clearKnights();

        relocateKnights(knights, TRAINING_YARD);
        underTest.setKnights(knights);
        underTest.process();


        assertThat(underTest.calculateEarnedXp(), is(4));

    }

    @Test
    public void thatXpIsCalculatedWith3KnightBonusCorrectly() {

        givenKnightsInPosition(3, TAVERN);

        underTest.setKnights(knights);
        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.clearKnights();

        relocateKnights(knights, TRAINING_YARD);
        underTest.setKnights(knights);

        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.grantBonusXp();
        assertThat(underTest.calculateEarnedXp(), is(24));
    }

    @Test
    public void thatXpIsCalculatedWith5KnightBonusCorrectly() {

        givenKnightsInPosition(5, TAVERN);

        underTest.setKnights(knights);
        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.clearKnights();

        relocateKnights(knights, TRAINING_YARD);
        underTest.setKnights(knights);

        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.grantBonusXp();
        assertThat(underTest.calculateEarnedXp(), is(65));
    }

    @Test
    public void thatXpIsCalculatedWith6KnightBonusCorrectly() {

        givenKnightsInPosition(6, TAVERN);

        underTest.setKnights(knights);
        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.clearKnights();

        relocateKnights(knights, TRAINING_YARD);
        underTest.setKnights(knights);

        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.grantBonusXp();
        assertThat(underTest.calculateEarnedXp(), is(138));
    }

    @Test
    public void thatXpIsNotAwardedWithoutStamina() {
        givenKnightsInPosition(1, TRAINING_YARD);

        underTest.setKnights(knights);

        underTest.process();

        assertThat(underTest.calculateEarnedXp(), is(0));
    }

    @Test
    public void thatXpIsAwardedWithStamina() {
        givenKnightsInPosition(1, TAVERN);

        underTest.setKnights(knights);

        underTest.process();
        underTest.clearKnights();

        relocateKnights(knights, TRAINING_YARD);
        underTest.setKnights(knights);
        underTest.process();

        assertThat(underTest.calculateEarnedXp(), is(1));
    }

    @Test
    public void thatXpAndStaminaIsAwardedForSavingDamsel() {
        givenKnightsInPosition(1, DAMSEL_IN_DISTRESS_SITE);

        underTest.setKnights(knights);

        underTest.process();

        assertThat(underTest.calculateEarnedXp(), is(1));
        assertThat(underTest.calculateEarnedStamina(), is(1));
    }

    @Test
    public void thatXpIsNotAwardedForNegativeStaminaWithSameDay() {
        givenKnightsInPosition(1, TRAINING_YARD);

        underTest.setKnights(knights);

        //Day 1 - Hour 1
        underTest.process(underTest.getYeOldeGameDateTime());

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(-1));

        //Day 1 - Hour 2
        relocateKnights(knights, TAVERN);
        DateTime nextHour = underTest.getYeOldeGameDateTime().plusHours(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(0));

        //Day 1 - Hour 3
        relocateKnights(knights, TAVERN);
        nextHour = underTest.getYeOldeGameDateTime().plusHours(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(1));

        //Day 1 - Hour 4
        relocateKnights(knights, TAVERN);
        nextHour = underTest.getYeOldeGameDateTime().plusHours(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(2));

    }

    @Test
    public void thatXpIsAwardedForNegativeStaminaNextDay() {
        givenKnightsInPosition(1, TRAINING_YARD);

        underTest.setKnights(knights);

        //Day 1 - Hour 1
        underTest.process(underTest.getYeOldeGameDateTime());

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(-1));

        //Day 1 - Hour 2
        relocateKnights(knights, TAVERN);
        DateTime nextHour = underTest.getYeOldeGameDateTime().plusHours(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(0));

        //Day 2 - Hour 1
        relocateKnights(knights, TAVERN);
        nextHour = underTest.getYeOldeGameDateTime().plusDays(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(1));

        //Day 2 - Hour 2
        relocateKnights(knights, TRAINING_YARD);
        nextHour = underTest.getYeOldeGameDateTime().plusHours(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(1));
        assertThat(underTest.calculateEarnedStamina(), is(0));



    }

    private void givenKnightsInPosition(int knightCount, Position position){
        knights = new ArrayList<>(knightCount);
        for (int i=0; i<knightCount; i++){
            knights.add(i, new Knight.KnightBuilder().withPosition(position).build());
        }
    }

    private void relocateKnights(List<Knight> knights, Position position){
        for (Knight knight : knights){
            knight.setPosition(position);
        }
    }
}
