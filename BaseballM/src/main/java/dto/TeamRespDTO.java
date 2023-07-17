package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class TeamRespDTO {
    private String teamName;
    private String stadium;
    private Timestamp teamCreatedAt;

}
