package com.sport.model;

import com.sport.model.impl.Score;
import com.sport.model.impl.Team;

import java.time.Instant;

public interface Match {
    Team getHomeTeam();
    Team getAwayTeam();
    Instant getStartTime();
    Score getScore();
}
