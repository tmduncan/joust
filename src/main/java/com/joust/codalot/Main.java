package com.joust.codalot;

import com.joust.codalot.domain.Codalot;
import com.joust.codalot.domain.Knight;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    private Main() { }

    public static void main(String[] args) {
        Codalot codalot = new Codalot();

        ArrayList<Knight> knights = new ArrayList<>();
        for (int i = 0; i < 6; ++i) {
            knights.add(new Knight());
        }

        Random random = new Random(1);
        for (int i = 0; i < 24; ++i) {
            codalot.clearKnights();
            for (Knight knight : knights) {
                int randomVal = random.nextInt(2);
                if (randomVal == 0) {
                    codalot.addKnightToTrainingYard(knight);
                } else if (randomVal == 1) {
                    codalot.addKnightToTavern(knight);
                }
            }
            codalot.process();
        }
        codalot.grantBonusXp();

        int totalXp = 0;
        for (Knight knight : knights) {
            totalXp += knight.getXp();
        }
        System.out.println(String.format("Total XP earned by all %d knights: %d", knights.size(), totalXp));
    }




}
