package model.outplayer;

import dto.OutPlayerRespDTO;
import lombok.Getter;
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
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, outplayerPlayerIdx);
            statement.setString(2, outplayerReason);
            statement.executeUpdate();
            System.out.println("입력 성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("입력 실패");
        }
        return null;
    }




    public List<OutPlayerRespDTO> outplayerFindByAll() throws SQLException {
        List<OutPlayerRespDTO> outPlayerList = new ArrayList<>();
        String query = "SELECT p.idx, p.name, p.position, op.reason, op.created_at FROM player p LEFT OUTER JOIN out_player op ON p.idx = op.player_id";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int playerIdx = resultSet.getInt("idx");
                String playerName = resultSet.getString("name");
                String position = resultSet.getString("position");
                String reason = resultSet.getString("reason");
                Timestamp createdAt = resultSet.getTimestamp("created_at");

                OutPlayerRespDTO outPlayer = new OutPlayerRespDTO(playerIdx, playerName, position, reason, createdAt);
                outPlayerList.add(outPlayer);
            }
        }

        return outPlayerList;
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