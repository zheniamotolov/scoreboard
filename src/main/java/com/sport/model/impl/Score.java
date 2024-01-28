package com.sport.model.impl;

import java.util.Objects;

public class Score {
    private int homeTeamScore;
    private int awayTeamScore;

    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score score)) return false;
        return getHomeTeamScore() == score.getHomeTeamScore() && getAwayTeamScore() == score.getAwayTeamScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHomeTeamScore(), getAwayTeamScore());
    }
}
