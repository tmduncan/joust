package com.joust.codalot.domain.game;

public class CodalotGameParameters {

    int knightCount = 12;
    int gameDurationDays = 1;

    public int getKnightCount() {
        return knightCount;
    }

    public void setKnightCount(int knightCount) {
        this.knightCount = knightCount;
    }

    public int getGameDurationDays() {
        return gameDurationDays;
    }

    public void setGameDurationDays(int gameDurationDays) {
        this.gameDurationDays = gameDurationDays;
    }

    public static class CodalotGameParametersBuilder {

        private CodalotGameParameters parameters = new CodalotGameParameters();

        public CodalotGameParametersBuilder withKnightCount(Integer knightCount) {
            if (null != knightCount){
                parameters.setKnightCount(knightCount);
            }
            return this;
        }

        public CodalotGameParametersBuilder withGameDurationDays(Integer gameDurationDays) {
            if(null != gameDurationDays){
                parameters.setGameDurationDays(gameDurationDays);
            }
            return this;
        }

        public CodalotGameParameters build() {
            return this.parameters;
        }

    }
}
