package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor

public class OutPlayerDTO {
    private String teamName;
    private String stadium;
    private Timestamp teamCreatedAt;
}
