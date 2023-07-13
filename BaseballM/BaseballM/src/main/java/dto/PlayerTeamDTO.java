package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;


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

