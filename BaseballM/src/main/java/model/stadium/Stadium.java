package model.stadium;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stadium {
    private String stadiumName;
    private Timestamp stadiumCreatedAt;

//    @Builder
//    public Stadium(String stadiumName, Timestamp stadiumCreatedAt) {
//        this.stadiumName = stadiumName;
//        this.stadiumCreatedAt = stadiumCreatedAt;
    }

