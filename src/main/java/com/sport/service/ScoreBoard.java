package com.sport.service;

import com.sport.model.Match;
import com.sport.model.impl.Score;
import com.sport.model.impl.Team;

import java.util.*;
import java.util.stream.Collectors;

public class ScoreBoard {
    private final Set<Match> matches = new LinkedHashSet<>();

    public Match startMatch(Team homeTeam, Team awayTeam) {
        Match match = MatchFactory.createMatch(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }

    public Match updateMatchScore(Match match, int homeTeamScore, int awayTeamScore) {
        if (matches.contains(match)) {
            Score score = match.getScore();
            score.setHomeTeamScore(homeTeamScore);
            score.setAwayTeamScore(awayTeamScore);
            return match;
        } else {
            throw new IllegalArgumentException("Match not found");
        }
    }

    public void removeMatch(Match match) {
        matches.remove(match);
    }

    public List<Match> getMatchesSummary() {
        Comparator<Match> matchComparator = Comparator
                .comparing((Match match) -> match.getScore().getTotalScore())
                .thenComparing(Match::getStartTime)
                .reversed();

        return matches.stream()
                .sorted(matchComparator)
                .collect(Collectors.toList());
    }

}

