package model.stadium;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
public class Stadium {
    private String stadiumName;
    private Timestamp stadiumCreatedAt;

    @Builder
    public Stadium(String stadiumName, Timestamp stadiumCreatedAt) {
        this.stadiumName = stadiumName;
        this.stadiumCreatedAt = stadiumCreatedAt;
    }
}
