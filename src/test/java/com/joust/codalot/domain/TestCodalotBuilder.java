package com.joust.codalot.domain;

import org.joda.time.DateTime;

import java.util.List;

public class TestCodalotBuilder {

    public static Codalot createBasicCodalotWithKnights(List<Citizen> citizens){
        Codalot codalot = new Codalot(DateTime.now());
        codalot.setCitizens(citizens);
        return codalot;
    }

    public static Codalot createBasicCodalot(){
        Codalot codalot = new Codalot(DateTime.now());
        return codalot;
    }


}
