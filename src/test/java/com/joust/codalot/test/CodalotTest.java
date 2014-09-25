package com.joust.codalot.test;

import com.joust.codalot.domain.Codalot;
import com.joust.codalot.domain.Knight;
import com.joust.codalot.domain.TestCodalotBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.joust.codalot.domain.Position.TAVERN;
import static com.joust.codalot.domain.Position.TRAINING_YARD;

public class CodalotTest {


    @Test
    public void thatXpIsCalculatedCorrectly(){

        List<Knight> knights = new ArrayList<>(6);
        knights.add(0, new Knight.KnightBuilder().withPosition(TAVERN).build());
        knights.add(1, new Knight.KnightBuilder().withPosition(TAVERN).build());
        knights.add(2, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(3, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(4, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());
        knights.add(5, new Knight.KnightBuilder().withPosition(TRAINING_YARD).build());

        Codalot codalot = TestCodalotBuilder.createBasicCodalotWithKnights(knights);

        codalot.process();

        assert(codalot.calculateEarnedXp() == 4);
    }
}
