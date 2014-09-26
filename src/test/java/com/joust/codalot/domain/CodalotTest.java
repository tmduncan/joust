package com.joust.codalot.domain;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.joust.codalot.domain.Position.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CodalotTest {
    List<Citizen> citizens;
    Codalot underTest;

    @Before
    public void setup() {
        underTest = TestCodalotBuilder.createBasicCodalot();
    }


    @Test
    public void thatXpIsCalculatedCorrectly() {

        givenKnightsInPositionWithRoundTableXP(4, TAVERN);

        underTest.setCitizens(citizens);
        underTest.process();
        underTest.clearCitizens();

        relocateCitizens(citizens, TRAINING_YARD);
        underTest.setCitizens(citizens);
        underTest.process();


        assertThat(underTest.calculateEarnedXp(), is(4));

    }

    @Test
    public void thatXpIsCalculatedWith3KnightBonusCorrectly() {

        givenKnightsInPositionWithRoundTableXP(3, TAVERN);

        underTest.setCitizens(citizens);
        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.clearCitizens();

        relocateCitizens(citizens, TRAINING_YARD);
        underTest.setCitizens(citizens);

        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.grantBonusXp();
        assertThat(underTest.calculateEarnedXp(), is(24));
    }

    @Test
    public void thatXpIsCalculatedWith5KnightBonusCorrectly() {

        givenKnightsInPositionWithRoundTableXP(5, TAVERN);

        underTest.setCitizens(citizens);
        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.clearCitizens();

        relocateCitizens(citizens, TRAINING_YARD);
        underTest.setCitizens(citizens);

        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.grantBonusXp();
        assertThat(underTest.calculateEarnedXp(), is(65));
    }

    @Test
    public void thatXpIsCalculatedWith6KnightBonusCorrectly() {

        givenKnightsInPositionWithRoundTableXP(6, TAVERN);

        underTest.setCitizens(citizens);
        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.clearCitizens();

        relocateCitizens(citizens, TRAINING_YARD);
        underTest.setCitizens(citizens);

        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.grantBonusXp();
        assertThat(underTest.calculateEarnedXp(), is(138));
    }

    @Test
    public void thatXpIsNotAwardedWithoutStamina() {
        givenKnightsInPositionWithRoundTableXP(1, TRAINING_YARD);

        underTest.setCitizens(citizens);

        underTest.process();

        assertThat(underTest.calculateEarnedXp(), is(0));
    }

    @Test
    public void thatXpIsAwardedWithStamina() {
        givenKnightsInPositionWithRoundTableXP(1, TAVERN);

        underTest.setCitizens(citizens);

        underTest.process();
        underTest.clearCitizens();

        relocateCitizens(citizens, TRAINING_YARD);
        underTest.setCitizens(citizens);
        underTest.process();

        assertThat(underTest.calculateEarnedXp(), is(1));
    }

    @Test
    public void thatXpAndStaminaIsAwardedForSavingDamsel() {
        givenKnightsInPositionWithRoundTableXP(1, DAMSEL_IN_DISTRESS_SITE);

        underTest.setCitizens(citizens);

        underTest.process();

        assertThat(underTest.calculateEarnedXp(), is(1));
        assertThat(underTest.calculateEarnedStamina(), is(1));
    }

    @Test
    public void thatXpIsNotAwardedForNegativeStaminaWithSameDay() {
        givenKnightsInPositionWithRoundTableXP(1, TRAINING_YARD);

        underTest.setCitizens(citizens);

        //Day 1 - Hour 1
        underTest.process(underTest.getYeOldeGameDateTime());

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(-1));

        //Day 1 - Hour 2
        relocateCitizens(citizens, TAVERN);
        DateTime nextHour = underTest.getYeOldeGameDateTime().plusHours(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(0));

        //Day 1 - Hour 3
        relocateCitizens(citizens, TAVERN);
        nextHour = underTest.getYeOldeGameDateTime().plusHours(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(1));

        //Day 1 - Hour 4
        relocateCitizens(citizens, TAVERN);
        nextHour = underTest.getYeOldeGameDateTime().plusHours(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(2));

    }

    @Test
    public void thatXpIsAwardedForNegativeStaminaNextDay() {
        givenKnightsInPositionWithRoundTableXP(1, TRAINING_YARD);

        underTest.setCitizens(citizens);

        //Day 1 - Hour 1
        underTest.process(underTest.getYeOldeGameDateTime());

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(-1));

        //Day 1 - Hour 2
        relocateCitizens(citizens, TAVERN);
        DateTime nextHour = underTest.getYeOldeGameDateTime().plusHours(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(0));

        //Day 2 - Hour 1
        relocateCitizens(citizens, TAVERN);
        nextHour = underTest.getYeOldeGameDateTime().plusDays(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(0));
        assertThat(underTest.calculateEarnedStamina(), is(1));

        //Day 2 - Hour 2
        relocateCitizens(citizens, TRAINING_YARD);
        nextHour = underTest.getYeOldeGameDateTime().plusHours(1);
        underTest.process(nextHour);

        assertThat(underTest.calculateEarnedXp(), is(1));
        assertThat(underTest.calculateEarnedStamina(), is(0));
    }

    @Test
    public void thatXpIsAwaredToKing() {
        givenKingInPosition(TAVERN);

        underTest.setCitizens(citizens);

        underTest.process();
        underTest.clearCitizens();

        relocateCitizens(citizens, TRAINING_YARD);
        underTest.setCitizens(citizens);
        underTest.process();

        assertThat(underTest.calculateRoyalEarnedXp(), is(1));
    }

    @Test
    public void thatXpIsCalculatedSepareatlyForRoyals() {
        givenKnightsInPositionWithRoundTableXP(1, TAVERN);
        citizens.add(givenValidKingInPosition(TAVERN));

        underTest.setCitizens(citizens);

        underTest.process();
        underTest.clearCitizens();

        relocateCitizens(citizens, TRAINING_YARD);
        underTest.setCitizens(citizens);
        underTest.process();

        assertThat(underTest.calculateEarnedXp(), is(1));
        assertThat(underTest.calculateRoyalEarnedXp(), is(1));
    }

    @Test
    public void thatBonusXpIsCalculatedToIncludeRoyals() {

        givenKnightsInPositionWithRoundTableXP(2, TAVERN);
        citizens.add(givenValidKingInPosition(TAVERN));

        underTest.setCitizens(citizens);
        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.clearCitizens();

        relocateCitizens(citizens, TRAINING_YARD);
        underTest.setCitizens(citizens);

        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.grantBonusXp();
        assertThat(underTest.calculateEarnedXp(), is(16));
        assertThat(underTest.calculateRoyalEarnedXp(), is(8));
    }

    @Test
    public void thatXpIsCalculatedCorrectlyWithoutRoundTableXP() {

        givenKnightsInPosition(1, TAVERN);

        underTest.setCitizens(citizens);
        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.clearCitizens();

        relocateCitizens(citizens, TRAINING_YARD);
        underTest.setCitizens(citizens);

        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.grantBonusXp();
        assertThat(underTest.calculateEarnedXp(), is(0));

    }

    @Test
    public void thatXpIsCalculatedCorrectlyWithRoundTableXP() {

        givenKnightsInPosition(1, ROUND_TABLE);

        underTest.setCitizens(citizens);
        for (int i = 0; i < 3; i++) {
            underTest.process();
        }
        underTest.clearCitizens();

        relocateCitizens(citizens, TAVERN);
        underTest.setCitizens(citizens);

        underTest.process();
        underTest.clearCitizens();

        relocateCitizens(citizens, TRAINING_YARD);
        underTest.setCitizens(citizens);

        underTest.process();

        underTest.grantBonusXp();
        assertThat(underTest.calculateEarnedXp(), is(1));

    }

    private void givenKnightsInPosition(int knightCount, Position position) {
        citizens = new ArrayList<>(knightCount);
        for (int i = 0; i < knightCount; i++) {
            citizens.add(i, givenValidKnightInPosition(position));
        }
    }

    private void givenKnightsInPositionWithRoundTableXP(int knightCount, Position position) {
        givenKnightsInPosition(knightCount, position);
        givenRoundTableXPGranted();
    }

    private void givenRoundTableXPGranted() {
        for (Citizen citizen : citizens) {
            citizen.setRoundTableGranted(true);
            citizen.setHoursAtRoundTable(3);
        }
    }

    private Knight givenValidKnightInPosition(Position position) {
        return new Knight.KnightBuilder().withPosition(position).build();
    }

    private void givenKingInPosition(Position position) {
        citizens = new ArrayList<>();
        citizens.add(givenValidKingInPosition(position));
    }

    private King givenValidKingInPosition(Position position) {
        return new King.KingBuilder().withPosition(position).build();

    }

    private void relocateCitizens(List<Citizen> citizens, Position position) {
        for (Citizen citizen : citizens) {
            citizen.setPosition(position);
        }
    }
}
