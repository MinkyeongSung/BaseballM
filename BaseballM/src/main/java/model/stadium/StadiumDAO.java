package model.stadium;

import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Getter
public class StadiumDAO {
    private Connection connection;

    public StadiumDAO(Connection connection) {
        this.connection = connection;
    }


    // 3.1 야구장 생성
    // 요청 : 야구장등록?name=잠실야구장
    //응답 : 성공이라는 메시지를 출력한다.
    public void createStadium(String name) throws SQLException {
        String query = "INSERT INTO stadium (name, created_at) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
        }

    }


    // 3. 2 전체 야구장 목록보기
    // 요청 : 야구장목록
    // 응답 : 야구장 목록은 Model -> Stadium을 List에 담아서 출력한다.
}
