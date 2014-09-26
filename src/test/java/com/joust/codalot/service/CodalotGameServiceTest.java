package com.joust.codalot.service;

import com.joust.codalot.domain.game.CodalotGameParameters;
import com.joust.codalot.domain.game.CodalotGameResult;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CodalotGameServiceTest {

    CodalotGameService underTest = new CodalotGameServiceImpl();

    @Test
    public void thatServiceWillPlayWithDefaultParameters() {
        CodalotGameResult result = underTest.play(null);

        assertNotNull(result);

        assertTrue(result.isFinished());
        assertThat(12, is(result.getKnightCount()));
    }


    @Test
    public void thatServiceWillPlayWithAcceptParameters() {
        CodalotGameParameters parameters = new CodalotGameParameters();
        parameters.setKnightCount(15);
        CodalotGameResult result = underTest.play(parameters);

        assertNotNull(result);

        assertTrue(result.isFinished());
        assertThat(result.getKnightCount(), is(15));
        assertThat(result.getGameDurationHours(), is(24));
    }

    @Test
    public void thatServiceWillPlayMultipleDays() {
        CodalotGameParameters parameters = new CodalotGameParameters();
        parameters.setKnightCount(1);
        parameters.setGameDurationDays(2);
        CodalotGameResult result = underTest.play(parameters);

        assertNotNull(result);

        assertTrue(result.isFinished());
        assertThat(result.getKnightCount(), is(1));
        assertThat(result.getGameDurationHours(), is(48));
    }


}
