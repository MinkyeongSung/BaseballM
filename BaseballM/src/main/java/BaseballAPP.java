import db.DBConnection;
import model.stadium.StadiumDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BaseballAPP {
    public static void main(String[] args) {

        Connection connection = DBConnection.getInstance();

        StadiumDAO stadiumDAO = new StadiumDAO(connection);

        // 전체 야구장 목록보기



        // 야구장 생성
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("야구장 이름 : ");
            String stadiumName = scanner.nextLine();

            stadiumDAO.createStadium(stadiumName);
            System.out.println("성공");
        } catch (SQLException e) {
            System.out.println("실패");
            e.printStackTrace();
        }
    }

}










/*
BaseBallApp 생성
콘솔 예시!!
1. 콘솔에 출력되는 질문
어떤 기능을 요청하시겠습니까?
2. 클라이언트가 입력하는 내용
선수등록?teamId=1&name=이대호&position=1루수
3. main 메서드에서 할 일
id (PK, 자동 번호 증가)
team_id (int)
name (varchar)
position (varchar)
created_at (timestamp)
team_id와 position은 다중 칼럼 유니크 제약조건이 필요합니다.
team_id는 fk 입니다.
id (PK, 자동 번호 증가)
player_id (int)
reason (varchar)
created_at (timestamp)
player_id는 fk 입니다.
main 함수를 가지는 클래스
해당 클래스에서 Scanner로 입력을 받고, 입력받은 값을 토대로 Service의 메서드를 호출합니다.
Service의 메서드를 호출한 뒤 return 받은 결과를 Console에 출력합니다.
입력되는 값을 파싱한다.
? 앞의 내용이 선수등록이면 PlayerService의 선수등록() 메서드를 호출한다.
호출시에 인수로 1, 이대호, 1루수를 전달한다.
PlayerService의 선수등록() 메서드에서 해당 값을 받아서 PlayerDao의 insert() 메서드를 호출한다.
값이 DB에 잘 들어갔다면, 결과가 1로 리턴될 것이다. 1이 리턴되면 Console에 성공이라는 메시지를 출
력한다.
 */