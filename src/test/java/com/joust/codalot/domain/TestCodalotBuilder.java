package com.joust.codalot.domain;

import java.util.List;

public class TestCodalotBuilder {

    public static Codalot createBasicCodalotWithKnights(List<Knight> knights){
        Codalot codalot = new Codalot();
        codalot.setKnights(knights);
        return codalot;
    }

    public static Codalot createBasicCodalot(){
        Codalot codalot = new Codalot();
        return codalot;
    }


}
