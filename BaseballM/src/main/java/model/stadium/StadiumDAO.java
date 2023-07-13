package model.stadium;

import anno.RequestMapping;
import lombok.Getter;
import model.player.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class StadiumDAO {
    private Connection connection;

    public StadiumDAO(Connection connection) {
        this.connection = connection;
    }

    // 3.1 야구장 생성
    // 요청 : 야구장등록?name=잠실야구장
    //응답 : 성공이라는 메시지를 출력한다.
    public void createStadium(String stdiumName) throws SQLException {
        String query = "INSERT INTO stadium (name, created_at) VALUES (?, now())";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, stdiumName);
            int result = statement.executeUpdate();
        }

    }


    // 3. 2 전체 야구장 목록보기
    // 요청 : 야구장목록
    // 응답 : 야구장 목록은 Model -> Stadium을 List에 담아서 출력한다.

    @RequestMapping(uri = "getAllStadium")
    public List<Stadium> getAllStadiums() throws SQLException {
        List<Stadium> stadiums = new ArrayList<>();
        String sql = "SELECT * FROM stadium";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Stadium stadium = buildStadiumFromResultSet(rs);
                    stadiums.add(stadium);
                }
            }
            return stadiums;
        }
    }



    private Stadium buildStadiumFromResultSet(ResultSet resultSet) throws SQLException {
        String stadiumName = resultSet.getString("name");
        Timestamp stadiumCreatedAt = resultSet.getTimestamp("created_at");

        return Stadium.builder()
                .stadiumName(stadiumName)
                .stadiumCreatedAt(stadiumCreatedAt)
                .build();
    }
}
