package model.player;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString

public class Player {
    private int playerIdx;
    private int playerTeamIdx;
    private String playerName;
    private String playerPosition;
    private Timestamp playerCreatedAt;

    @Builder
    public Player(int playerIdx, int playerTeamIdx, String playerName, String playerPosition,Timestamp playerCreatedAt) {
        this.playerIdx = playerIdx;
        this.playerTeamIdx = playerTeamIdx;
        this.playerName = playerName;
        this.playerPosition = playerPosition;

    }
}

