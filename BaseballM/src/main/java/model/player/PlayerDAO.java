package model.player;

import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PlayerDAO {
    private Connection connection;
    public PlayerDAO(Connection connection){
        this.connection = connection;
    }

    public Player playerInsert(int playerTeamIdx, String playerName, String playerPosition) throws SQLException {
        String sql = "INSERT INTO player (team_idx,name, position, created_at) VALUES (?, ?, ?, now())";
        try(PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1,playerTeamIdx);
            statement.setString(2,playerName);
            statement.setString(3,playerPosition);
            statement.executeUpdate();
            System.out.println("입력 성공");
        }
        return null;
    }
    public void playerUpdate(int playerIdx)throws SQLException{
        String sql = "update player set team_idx = null where idx = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1,playerIdx);
            statement.executeUpdate();
            System.out.println("업데이트 성공");
        }
    }
//    public Player playerFindMyId(int player) throws SQLException {
//        String query = "SELECT * FROM account_tb WHERE account_number = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, accountNumber);
//            try (ResultSet rs = statement.executeQuery()) {
//                if (rs.next()) {
//                    return buildAccountFromResultSet(rs);
//                }
//            }
//        }
//        return null; // Account not found
//    }

    public List<Player> playerFindByTeamId(int playerTeamIdx) throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "select s.name,t.name,p.* from player p inner join team t on p.team_idx = t.idx inner join stadium s on t.idx = s.idx;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerTeamIdx);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Player player = buildPlayerFromResultSet(rs);
                    players.add(player);
                }
            }
        }
        return players; // Account not found
    }

    public List<Player> playerFindByAll() throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Player player = buildPlayerFromResultSet(resultSet);
                    players.add(player);
                }
            }
        }
        return players;
    }

    private Player buildPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int playerIdx = resultSet.getInt("idx");
        int playerTeamIdx = resultSet.getInt("team_idx");
        String playerName = resultSet.getString("name");
        String playerPosition = resultSet.getString("position");
        Timestamp playerCreatedAt = resultSet.getTimestamp("created_at");

        return Player.builder()
                .playerIdx(playerIdx)
                .playerTeamIdx(playerTeamIdx)
                .playerName(playerName)
                .playerPosition(playerPosition)
                .playerCreatedAt(playerCreatedAt)
                .build();
    }

}
