package com.sport.service;

import com.sport.model.Match;
import com.sport.model.impl.Team;

import java.util.List;

public interface ScoreBoard {
    Match startMatch(Team homeTeam, Team awayTeam);
    Match updateMatchScore(Match match, int homeTeamScore, int awayTeamScore);
    void removeMatch(Match match);
    List<Match> getMatchesSummary();
}
