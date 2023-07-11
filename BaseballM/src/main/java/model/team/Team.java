package model.team;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
public class Team {
    private int stadiumIdx;
    private String teamName;
    private Timestamp teamCreatedAt;

    @Builder
    public Team(int stadiumIdx, String teamName, Timestamp teamCreatedAt) {
        this.stadiumIdx = stadiumIdx;
        this.teamName = teamName;
        this.teamCreatedAt = teamCreatedAt;
    }
}
