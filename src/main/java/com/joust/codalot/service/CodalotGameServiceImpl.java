package com.joust.codalot.service;

import com.joust.codalot.domain.Codalot;
import com.joust.codalot.domain.Knight;
import com.joust.codalot.domain.game.CodalotGameParameters;
import com.joust.codalot.domain.game.CodalotGameResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

public class CodalotGameServiceImpl implements CodalotGameService {
    private static final Logger LOG = LoggerFactory.getLogger(CodalotGameServiceImpl.class);
    @Override
    public CodalotGameResult play(CodalotGameParameters parameters) {

        CodalotGameResult gameResult = new CodalotGameResult();

        if (null == parameters){
            parameters = new CodalotGameParameters();
        }

        gameResult.setKnightCount(parameters.getKnightCount());

        Codalot codalot = new Codalot();

        ArrayList<Knight> knights = new ArrayList<>();
        for (int i = 0; i < parameters.getKnightCount(); ++i) {
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

        LOG.info("Total XP earned by all {} knights: {}", knights.size(), codalot.calculateEarnedXp());

        gameResult.setFinished(true);
        gameResult.setCodalot(codalot);

        return gameResult;
    }
}
