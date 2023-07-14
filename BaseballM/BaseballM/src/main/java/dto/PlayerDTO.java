package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;


@Getter @Setter @AllArgsConstructor
public class PlayerDTO {
    private Integer playerIdx;
    private Integer playerTeamIdx;

    private String playerTeamName;

    private String playerName;
    private String playerPosition;
    private Timestamp playerJoiningDate;
}