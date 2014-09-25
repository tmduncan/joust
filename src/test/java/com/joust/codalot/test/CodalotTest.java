package com.joust.codalot.test;

import org.junit.Test;

public class CodalotTest {


    @Test
    public void thatXpIsCalculatedCorrectly(){
        Codalot codalot = new BasicCodalot();

        codalot.setKnight(0, KnightPosition.TAVERN);
        codalot.setKnight(1, KnightPosition.TAVERN);
        codalot.setKnight(2, KnightPosition.TRAINING_YARD);
        codalot.setKnight(3, KnightPosition.TRAINING_YARD);
        codalot.setKnight(4, KnightPosition.TRAINING_YARD);
        codalot.setKnight(5, KnightPosition.TRAINING_YARD);
        codalot.process();

        assert(codalot.calculateEarnedXp() == 4);
    }
}
