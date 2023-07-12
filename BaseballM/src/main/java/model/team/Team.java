package model.team;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class Team {
    private int teamIdx;
    private int teamStadiumIdx;
    private String teamName;
    private Timestamp teamCreatedAt;

    @Builder
    public Team(int teamIdx, int teamStadiumIdx, String teamName, Timestamp teamCreatedAt) {
        this.teamIdx = teamIdx;
        this.teamStadiumIdx = teamStadiumIdx;
        this.teamName = teamName;
        this.teamCreatedAt = teamCreatedAt;
    }
}

//package model.team;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.ToString;
//
//import java.sql.Timestamp;
//
//@ToString
//@Getter
//public class Team {
//    private int stadiumIdx;
//    private String teamName;
//    private Timestamp teamCreatedAt;
//
//    @Builder
//    public Team(int stadiumIdx, String teamName, Timestamp teamCreatedAt) {
//        this.stadiumIdx = stadiumIdx;
//        this.teamName = teamName;
//        this.teamCreatedAt = teamCreatedAt;
//    }
//}
