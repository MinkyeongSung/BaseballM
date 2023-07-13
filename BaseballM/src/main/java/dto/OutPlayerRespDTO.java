package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class OutPlayerRespDTO {
    private Integer playerIdx;
    private String playerName;
    private String position;
    private String reason;
    private Timestamp createdAt;
}
