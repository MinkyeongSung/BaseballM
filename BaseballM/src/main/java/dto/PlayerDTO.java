package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.player.Player;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor
public class PlayerDTO {
//    private List<Player> players = new ArrayList<>();
    private Integer playerIdx;
    private Integer playerTeamIdx;

    private String playerTeamName;

    private String playerName;
    private String playerPosition;
    private Timestamp playerJoiningDate;
}
