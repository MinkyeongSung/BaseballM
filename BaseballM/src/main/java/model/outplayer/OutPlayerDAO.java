package model.outplayer;

import lombok.Getter;


import model.player.Player;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OutPlayerDAO {
    private Connection connection;



    private PreparedStatement statement;

    public OutPlayerDAO(Connection connection){
        this.connection = connection;
    }

    public OutPlayer outplayerInsert(int outplayerPlayerIdx, String outplayerReason) throws SQLException {
        String sql = "INSERT INTO out_player (player_id, reason, created_at) VALUES (?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1,outplayerPlayerIdx);
            statement.setString(2,outplayerReason);
            statement.executeUpdate();
            System.out.println("입력 성공");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("입력 실패");
        }
        return null;

      
    public void outplayerInsert(int outplayerPlayerIdx,String outplayerReason) throws SQLException {
        String sql = "INSERT INTO player (player_idx, reason, created_at) VALUES (?, ?, ?, now())";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,outplayerPlayerIdx);
        statement.setString(2,outplayerReason);
        statement.executeUpdate();
        System.out.println("입력 성공");

    }

    public List<OutPlayer> outplayerFindByAll() throws SQLException {
        List<OutPlayer> outplayers = new ArrayList<>();
        String query = "select p.idx,p.name,p.position,op.reason,op.created_at from player p left outer join out_player op on p.idx = op.player_id";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    OutPlayer outPlayer = buildOutPlayerFromResultSet(resultSet);
                    outplayers.add(outPlayer);
                }
            }
        }
        return outplayers;
    }
    private OutPlayer buildOutPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int outplayerIdx = resultSet.getInt("idx");
        int outplayerPlayerIdx = resultSet.getInt("player_id");
        String outplayerReason = resultSet.getString("reason");
        Timestamp outplayerCreatedAt = resultSet.getTimestamp("created_at");

        return OutPlayer.builder()
                .outplayerIdx(outplayerIdx)
                .outplayerPlayerIdx(outplayerPlayerIdx)
                .outplayerReason(outplayerReason)
                .outplayerCreatedAt(outplayerCreatedAt)
                .build();
    }





}