package model.player;

import dto.PlayerTeamDTO;
import dto.PositionRespDTO;
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
        try(PreparedStatement statement = connection.prepareStatement(sql)){
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

//    public void updatePlayerTeamId(int playerId, Integer teamId) throws SQLException {
//        String sql = "UPDATE player SET team_id = ? WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql);) {
//            if (teamId == null) {
//                statement.setNull(1, Types.INTEGER);
//            } else {
//                statement.setInt(1, teamId);
//            }
//            statement.setInt(2, playerId);
//            statement.executeUpdate();
//        }
//    }


    public List<PlayerTeamDTO> playerFindByTeamId(int playerTeamIdx) throws SQLException {

        List<PlayerTeamDTO> dtos = new ArrayList<>();
        String query = "select s.name , t.name, p.*  from player p left outer join team t on p.team_idx = t.idx left outer join stadium s on t.stadium_idx = s.idx where team_idx = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerTeamIdx);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    PlayerTeamDTO dto = PlayerTeamDTO.builder()
                            .pidx(rs.getInt("idx"))
                            .tidx(rs.getInt("team_idx"))
                            .playerName(rs.getString("p.name"))
                            .position(rs.getString("position"))
                            .createdAt(rs.getTimestamp("p.created_at"))
                            .teamName(rs.getString("t.name"))
                            .stadiumName(rs.getString("s.name"))
                            .build();
                    dtos.add(dto);
                }
            }
        }
        return dtos; // Account not found
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

    public List<PositionRespDTO> positionList() throws SQLException {
        List<PositionRespDTO> dtos = new ArrayList<>();
        String query = "select\n" +
                "    position,\n" +
                "    max(if(team_idx =1,name,null)) '롯데',\n" +
                "    max(if(team_idx =2,name,null)) 'LG',\n" +
                "    max(if(team_idx =3,name,null)) 'NC'\n" +
                "from player p\n" +
                "group by position;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    PositionRespDTO dto = PositionRespDTO.builder()
                            .playerName1(rs.getString("롯데"))
                            .playerName2(rs.getString("LG"))
                            .playerName3(rs.getString("NC"))
                            .position(rs.getString("포지션"))
                            .build();
                    dtos.add(dto);
                }
            }
        }
        return dtos; // Account not found
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