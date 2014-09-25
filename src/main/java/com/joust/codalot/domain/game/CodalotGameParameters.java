package com.joust.codalot.domain.game;

public class CodalotGameParameters {

    int knightCount = 6;

    public int getKnightCount() {
        return knightCount;
    }

    public void setKnightCount(int knightCount) {
        this.knightCount = knightCount;
    }

    public static class CodalotGameParametersBuilder {

        private CodalotGameParameters parameters = new CodalotGameParameters();

        public CodalotGameParametersBuilder withKnightCount(int knightCount) {
            parameters.setKnightCount(knightCount);
            return this;
        }

        public CodalotGameParameters build() {
            return this.parameters;
        }

    }
}
