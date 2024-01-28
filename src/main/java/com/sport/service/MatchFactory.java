package com.sport.service;

import com.sport.model.Match;
import com.sport.model.impl.FootballMatch;
import com.sport.model.impl.Team;

public class MatchFactory {
    public static Match createMatch(Team homeTeam, Team awayTeam) {
        return new FootballMatch(homeTeam, awayTeam);
    }
}
