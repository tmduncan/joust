package com.joust.codalot.service;

import com.joust.codalot.domain.Codalot;
import com.joust.codalot.domain.Knight;
import com.joust.codalot.domain.game.CodalotGameParameters;
import com.joust.codalot.domain.game.CodalotGameResult;
import com.joust.codalot.domain.game.GameDuration;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service("codalotGameService")
public class CodalotGameServiceImpl implements CodalotGameService {
    private static int GAME_DURATION_DAYS = 1;

    private static final Logger LOG = LoggerFactory.getLogger(CodalotGameServiceImpl.class);
    @Override
    public CodalotGameResult play(CodalotGameParameters parameters) {

        DateTime gameStart = DateTime.now();
        GameDuration gameDuration = new GameDuration(gameStart, gameStart.plusDays(GAME_DURATION_DAYS));

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

        for (DateTime date : gameDuration)
        {
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

        gameResult.setMessage(String.format("Total XP earned by all %d knights: %d", knights.size(), codalot.calculateEarnedXp()));
        gameResult.setFinished(true);

        return gameResult;
    }
}
