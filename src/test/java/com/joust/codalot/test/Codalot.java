package com.joust.codalot.test;

import com.joust.codalot.domain.Position;

public interface Codalot {
    void setKnight(int id, Position position);
    void process();
    int calculateEarnedXp();
}
