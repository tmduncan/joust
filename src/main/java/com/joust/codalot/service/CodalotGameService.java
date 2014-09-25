package com.joust.codalot.service;

import com.joust.codalot.domain.game.CodalotGameParameters;
import com.joust.codalot.domain.game.CodalotGameResult;

public interface CodalotGameService {

    CodalotGameResult play(CodalotGameParameters parameters);
}
