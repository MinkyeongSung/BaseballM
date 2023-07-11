package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.player.Player;
import model.team.Team;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor
public class PlayerTeamDTO {
    private Team team;
    private List<Player> players = new ArrayList<>();

}
