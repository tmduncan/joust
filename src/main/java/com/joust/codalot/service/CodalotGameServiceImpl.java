package com.joust.codalot.service;

import com.joust.codalot.domain.Citizen;
import com.joust.codalot.domain.Codalot;
import com.joust.codalot.domain.King;
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

    private static final Logger LOG = LoggerFactory.getLogger(CodalotGameServiceImpl.class);

    @Override
    public CodalotGameResult play(CodalotGameParameters parameters) {


        CodalotGameResult gameResult = new CodalotGameResult();

        if (null == parameters) {
            parameters = new CodalotGameParameters();
        }

        DateTime gameStart = DateTime.now();
        GameDuration gameDuration = new GameDuration(gameStart, gameStart.plusDays(parameters.getGameDurationDays()));

        gameResult.setKnightCount(parameters.getKnightCount());

        Codalot codalot = new Codalot(gameStart);

        ArrayList<Citizen> citizens = new ArrayList<>();

        //Add the knights
        for (int i = 0; i < parameters.getKnightCount(); ++i) {
            citizens.add(new Knight());
        }

        //Add the king
        citizens.add(new King());

        Random random = new Random(1);

        for (DateTime dateHour : gameDuration) {
            codalot.clearCitizens();
            for (Citizen citizen : citizens) {

                int randomVal;

                if(citizen.isRoyalty()){
                    randomVal = random.nextInt(3);
                } else {
                    randomVal = random.nextInt(4);
                }
                if (randomVal == 0) {
                    codalot.addCitizenToTrainingYard(citizen);
                } else if (randomVal == 1) {
                    codalot.addCitizenToTavern(citizen);
                } else if (randomVal == 2) {
                    codalot.addCitizenToDamselInDistressSite(citizen);
                } else if (randomVal == 3) {
                    codalot.addCitizenToRoundTable(citizen);
                }
            }
            codalot.process(dateHour);
            gameResult.incrementHourPlayed();
        }

        codalot.grantBonusXp();

        gameResult.setMessage(String.format("Total XP earned by all %d noble citizens of codalot: %d and XP earned by royalty: %d - played over %d days",
                citizens.size(),
                codalot.calculateEarnedXp(),
                codalot.calculateRoyalEarnedXp(),
                gameDuration.getGameInterval()));
        gameResult.setFinished(true);

        return gameResult;
    }
}
