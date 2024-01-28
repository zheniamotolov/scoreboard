package com.sport.model.impl;

import com.sport.model.Match;

import java.time.Instant;
import java.util.Objects;

public class FootballMatch implements Match {
    private Instant startTime;
    private Team homeTeam;
    private Team awayTeam;
    private Score score;

    public FootballMatch(Team homeTeam, Team awayTeam) {
        this(homeTeam, awayTeam, new Score(), Instant.now());
    }

    private FootballMatch(Team homeTeam, Team awayTeam, Score score, Instant startTime) {
        validateParams(homeTeam, awayTeam);

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTime = startTime;
        this.score = score;
    }

    @Override
    public Team getHomeTeam() {
        return homeTeam;
    }

    @Override
    public Team getAwayTeam() {
        return awayTeam;
    }

    @Override
    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public Score getScore() {
        return score;
    }

    private static void validateParams(Team homeTeam, Team awayTeam) {
        Objects.requireNonNull(homeTeam, "Home team cannot be null");
        Objects.requireNonNull(awayTeam, "Away team cannot be null");
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Home team and away team cannot be the same");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FootballMatch match)) return false;
        return Objects.equals(getStartTime(), match.getStartTime()) && Objects.equals(getHomeTeam(), match.getHomeTeam()) && Objects.equals(getAwayTeam(), match.getAwayTeam()) && Objects.equals(getScore(), match.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartTime(), getHomeTeam(), getAwayTeam(), getScore());
    }
}
