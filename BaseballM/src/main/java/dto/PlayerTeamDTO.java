package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.player.Player;
import model.stadium.Stadium;
import model.team.Team;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @Builder
public class PlayerTeamDTO {
    private int pidx;
    private int tidx;
    private String playerName;
    private String position;
    private Timestamp createdAt;


    private String teamName;
    private String stadiumName;
}
