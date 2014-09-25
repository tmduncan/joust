package com.joust.codalot.domain;

public abstract class Citizen {

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isInPostion(Position position){
        return getPosition().equals(position);
    }
}
