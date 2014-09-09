package com.joust.codalot.test;

public class CodalotTest {

    public static void main(String args[]) {
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
