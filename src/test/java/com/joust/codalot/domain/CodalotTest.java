package com.joust.codalot.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.joust.codalot.domain.Position.TAVERN;
import static com.joust.codalot.domain.Position.TRAINING_YARD;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CodalotTest {

    @Test
    public void thatXpIsCalculatedCorrectly() {

        List<Knight> knights = new ArrayList<>(6);
        knights.add(0, new Knight.KnightBuilder().withPosition(TAVERN).build());
        knights.add(1, new Knight.KnightBuilder().withPosition(TAVERN).build());
        knights.add(2, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(3, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(4, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(5, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());

        Codalot codalot = TestCodalotBuilder.createBasicCodalotWithKnights(knights);

        codalot.process();

        assertThat(codalot.calculateEarnedXp(), is(4));

    }

    @Test
    public void thatXpIsCalculatedWith3KnightBonusCorrectly() {

        List<Knight> knights = new ArrayList<>(3);
        knights.add(0, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(1, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(2, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());

        Codalot codalot = TestCodalotBuilder.createBasicCodalotWithKnights(knights);

        for (int i=0; i<3; i++){
            codalot.process();
        }
        codalot.grantBonusXp();
        assertThat(codalot.calculateEarnedXp(), is(24));
    }

    @Test
    public void thatXpIsCalculatedWith5KnightBonusCorrectly() {

        List<Knight> knights = new ArrayList<>(5);
        knights.add(0, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(1, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(2, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(3, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(4, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());

        Codalot codalot = TestCodalotBuilder.createBasicCodalotWithKnights(knights);

        for (int i=0; i<3; i++){
            codalot.process();
        }
        codalot.grantBonusXp();
        assertThat(codalot.calculateEarnedXp(), is(65));
    }

    @Test
    public void thatXpIsCalculatedWith6KnightBonusCorrectly() {

        List<Knight> knights = new ArrayList<>(6);
        knights.add(0, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(1, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(2, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(3, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(4, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(5, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());

        Codalot codalot = TestCodalotBuilder.createBasicCodalotWithKnights(knights);

        for (int i=0; i<3; i++){
            codalot.process();
        }
        codalot.grantBonusXp();
        assertThat(codalot.calculateEarnedXp(), is(138));
    }
}
