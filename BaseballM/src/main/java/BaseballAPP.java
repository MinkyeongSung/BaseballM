import db.DBConnection;

import model.service.*;
import java.sql.Connection;
import java.util.Scanner;

public class BaseballAPP {
    public static void main(String[] args) {

        Connection connection = DBConnection.getInstance();
<<<<<<< HEAD
        Scanner scanner = new Scanner(System.in);
        PrintOut printOut = new PrintOut();
        Service service = new Service(connection);
        printOut.mainPrint();
=======

        StadiumDAO stadiumDAO = new StadiumDAO(connection);
        TeamDAO teamDAO = new TeamDAO(connection);
        PlayerDAO playerDAO = new PlayerDAO(connection);
        OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);

        StadiumService stadiumService = new StadiumService(stadiumDAO);
        TeamService teamService = new TeamService(teamDAO);
        OutPlayerService outPlayerService = new OutPlayerService(outPlayerDAO, playerDAO);
        PlayerService playerService = new PlayerService(playerDAO);

        Scanner scanner = new Scanner(System.in);
        System.out.println("어떤 기능을 요청하시겠습니까?");
        System.out.println("--------------------------");
        System.out.println("1. 야구장등록 (야구장등록?name=야구장이름)");
        System.out.println("2. 야구장목록");
        System.out.println("3. 팀등록 (팀등록?stadiumId=팀번호&name=팀이름)");
        System.out.println("4. 팀목록");
        System.out.println("5. 선수등록 (선수등록?teamId=팀번호&name=선수이름&position=포지션)");
        System.out.println("6. 선수목록 (선수목록?teamId=팀번호)");
        System.out.println("7. 선수 퇴출 등록 (퇴출등록?playerId=플레이어번호&reason=사유)");
        System.out.println("8. 퇴출목록");
        System.out.println("9. 포지션별목록");
        System.out.println("0. 종료");
        System.out.println("--------------------------");
>>>>>>> 0e01057d44122cd57d9d82809b2060011833a87b
        String input = scanner.nextLine();
        service.run(input);


    }
<<<<<<< HEAD
}
=======
}


>>>>>>> 0e01057d44122cd57d9d82809b2060011833a87b
