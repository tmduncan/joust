package com.joust.codalot.test;

import com.joust.codalot.domain.Position;
import org.junit.Test;

public class CodalotTest {


    @Test
    public void thatXpIsCalculatedCorrectly(){
        Codalot codalot = new BasicCodalot();

        codalot.setKnight(0, Position.TAVERN);
        codalot.setKnight(1, Position.TAVERN);
        codalot.setKnight(2, Position.TRAINING_YARD);
        codalot.setKnight(3, Position.TRAINING_YARD);
        codalot.setKnight(4, Position.TRAINING_YARD);
        codalot.setKnight(5, Position.TRAINING_YARD);
        codalot.process();

        assert(codalot.calculateEarnedXp() == 4);
    }
}
