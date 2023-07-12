package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter @Builder
public class PositionResDTO {
    private int playerIdx;
    private int teamIdx;
    private String playerTeamName;
    private String playerName1;
    private String playerName2;
    private String playerName3;
    private String position;
}