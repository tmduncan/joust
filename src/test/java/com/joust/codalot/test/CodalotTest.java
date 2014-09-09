package com.joust.codalot.test;

import com.joust.codalot.Main;

public class CodalotTest {

    public static void main(String args[]) {
        Codalot codalot = new BasicCodalot();

        codalot.setKnight(1, KnightPosition.TAVERN);
        codalot.setKnight(2, KnightPosition.TAVERN);
        codalot.setKnight(3, KnightPosition.TRAINING_YARD);
        codalot.setKnight(4, KnightPosition.TRAINING_YARD);
        codalot.setKnight(5, KnightPosition.TRAINING_YARD);
        codalot.setKnight(6, KnightPosition.TRAINING_YARD);
        codalot.process();


    }
}
