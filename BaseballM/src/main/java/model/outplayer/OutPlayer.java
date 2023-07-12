package model.outplayer;

import lombok.*;

import java.sql.Timestamp;

@Getter @Setter @ToString
public class OutPlayer {
    private int outplayerIdx;
    private int outplayerPlayerIdx;
    private String outplayerReason;
    private Timestamp outplayerCreatedAt;

    @Builder
    public OutPlayer(int outplayerIdx, int outplayerPlayerIdx, String outplayerReason,Timestamp outplayerCreatedAt) {
        this.outplayerIdx = outplayerIdx;
        this.outplayerPlayerIdx = outplayerPlayerIdx;
        this.outplayerReason = outplayerReason;
        this.outplayerCreatedAt = outplayerCreatedAt;

    }

}