package model.team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        System.out.println("결과 : "+ result);
    }

}
}
