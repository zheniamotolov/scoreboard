package com.sport.service;

import com.sport.model.Match;
import com.sport.model.impl.Score;
import com.sport.model.impl.Team;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FootballScoreBoard implements ScoreBoard {
    private final Set<Match> matches = new LinkedHashSet<>();

    @Override
    public Match startMatch(Team homeTeam, Team awayTeam) {
        Match match = MatchFactory.createMatch(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }

    @Override
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

    @Override
    public void removeMatch(Match match) {
        matches.remove(match);
    }

    @Override
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

