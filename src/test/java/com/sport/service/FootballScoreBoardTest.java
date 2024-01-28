package com.sport.service;


import com.sport.model.Match;
import com.sport.model.impl.FootballMatch;
import com.sport.model.impl.Score;
import com.sport.model.impl.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FootballScoreBoardTest {

    private ScoreBoard scoreBoard;
    private Team homeTeam;
    private Team awayTeam;

    @BeforeEach
    void setUp() {
        scoreBoard = new FootballScoreBoard();
        homeTeam = new Team("Home Team");
        awayTeam = new Team("Away Team");
    }

    @Test
    void testStartingMatch() {
        Match match = scoreBoard.startMatch(homeTeam, awayTeam);
        assertNotNull(match);
        assertEquals(homeTeam, match.getHomeTeam());
        assertEquals(awayTeam, match.getAwayTeam());
        assertEquals(0, match.getScore().getHomeTeamScore());
        assertEquals(0, match.getScore().getAwayTeamScore());
    }

    @Test
    void testThrowExceptionWhenStartingMatchWithSameTeam() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.startMatch(homeTeam, homeTeam));
    }

    @Test
    void testThrowExceptionWhenStartingMatchWithNullTeam() {
        assertThrows(NullPointerException.class, () -> scoreBoard.startMatch(homeTeam, null));
        assertThrows(NullPointerException.class, () -> scoreBoard.startMatch(null, awayTeam));
        assertThrows(NullPointerException.class, () -> scoreBoard.startMatch(null, null));
    }

    @Test
    void testUpdatingMatchScore() {
        Match match = scoreBoard.startMatch(homeTeam, awayTeam);
        Match updatedMatch = scoreBoard.updateMatchScore(match, 3, 2);
        Score score = updatedMatch.getScore();
        assertEquals(3, score.getHomeTeamScore());
        assertEquals(2, score.getAwayTeamScore());
    }

    @Test
    void testStartMatchWithoutTeams() {
        assertThrows(NullPointerException.class, () -> scoreBoard.startMatch(null, null));
    }

    @Test
    void testThrowExceptionWhenUpdatingScoreOfNonExistingMatch() {
        Match match = new FootballMatch(homeTeam, awayTeam);
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.updateMatchScore(match, 3, 2));
    }

    @Test
    void testMatchRemoving() {
        Match match = scoreBoard.startMatch(homeTeam, awayTeam);
        scoreBoard.removeMatch(match);
        List<Match> matches = scoreBoard.getMatchesSummary();
        assertTrue(matches.isEmpty());
    }

    @Test
    void testTeamWithoutName() {
        assertThrows(NullPointerException.class, () -> new Team(null));
    }

    @Test
    void testReturnOfMatchesSummary() {
        Match match = scoreBoard.startMatch(homeTeam, awayTeam);
        List<Match> matches = scoreBoard.getMatchesSummary();
        assertFalse(matches.isEmpty());
        assertEquals(match, matches.get(0));
    }


    @Test
    void testSummaryOrder() {
        Team team1 = new Team("Mexico");
        Team team2 = new Team("Canada");
        Team team3 = new Team("Spain");
        Team team4 = new Team("Brazil");
        Team team5 = new Team("Germany");
        Team team6 = new Team("France");
        Team team7 = new Team("Uruguay");
        Team team8 = new Team("Italy");
        Team team9 = new Team("Argentina");
        Team team10 = new Team("Australia");

        Match match1 = scoreBoard.startMatch(team1, team2);
        wait10ms();
        Match match2 = scoreBoard.startMatch(team3, team4);
        wait10ms();
        Match match3 = scoreBoard.startMatch(team5, team6);
        wait10ms();
        Match match4 = scoreBoard.startMatch(team7, team8);
        wait10ms();
        Match match5 = scoreBoard.startMatch(team9, team10);

        scoreBoard.updateMatchScore(match1, 0, 5);
        scoreBoard.updateMatchScore(match2, 10, 2);
        scoreBoard.updateMatchScore(match3, 2, 2);
        scoreBoard.updateMatchScore(match4, 6, 6);
        scoreBoard.updateMatchScore(match5, 3, 1);
        List<Match> matches = scoreBoard.getMatchesSummary();

        assertEquals(match4, matches.get(0));
        assertEquals(match2, matches.get(1));
        assertEquals(match1, matches.get(2));
        assertEquals(match5, matches.get(3));
        assertEquals(match3, matches.get(4));
    }

    private static void wait10ms() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ignored) {
        }
    }
}