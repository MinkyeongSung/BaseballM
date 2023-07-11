package model.team;

import dto.TeamRespDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
stadium_id (int)
name (varchar)
created_at (timestamp)
stadium_id는 fk 입니다.

 */
public class TeamDAO {
    private Connection connection;

    public TeamDAO(Connection connection) {
        this.connection = connection;
    }

    /*
    3.3 팀 등록
    요청 : 팀등록?stadiumId=1&name=NC
    응답 : 성공이라는 메시지를 출력한다.
     */
    public void createTeam(int stadiumIdx, String teamName) throws SQLException {
        String query = "INSERT INTO team (stadium_idx ,name, created_at) VALUES (?, ?, now())";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumIdx);
            statement.setString(2, teamName);

            int result = statement.executeUpdate();
            System.out.println("결과 : " + result);
        }

    }

// 3.4 팀 목록 불러오기
//요청 : 팀목록
//응답 : 팀 목록은 Stadium 정보를 조인해서 출력해야 된다. TeamRespDTO가 필요하다.

    public List<TeamRespDTO> getTeamList() {
        List<TeamRespDTO> teamList = new ArrayList<>();

        try {
            String sql = "SELECT t.name AS team_name, s.name AS stadium_name " +
                    "FROM team t " +
                    "JOIN stadium s ON t.stadium_idx = s.idx";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String teamName = resultSet.getString("team_name");
                String stadium = resultSet.getString("stadium_name");

                TeamRespDTO teamRespDTO = new TeamRespDTO(teamName, stadium);
                teamRespDTO.setTeamName(teamName);
                teamRespDTO.setStadium(stadium);

                teamList.add(teamRespDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teamList;
    }


}
