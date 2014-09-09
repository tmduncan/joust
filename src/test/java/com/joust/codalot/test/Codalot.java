package com.joust.codalot.test;

public interface Codalot {
    void setKnight(int id, KnightPosition position);
    void process();
    int calculateEarnedXp();
}
