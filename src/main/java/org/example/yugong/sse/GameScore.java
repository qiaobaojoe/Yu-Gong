package org.example.yugong.sse;

import lombok.Data;

@Data
public class GameScore {
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private String quarter;
    private String timeRemaining;

}