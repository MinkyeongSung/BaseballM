import db.DBConnection;

import model.outplayer.OutPlayerDAO;

import model.player.PlayerDAO;
import model.service.OutPlayerService;
import model.service.PlayerService;

import model.service.StadiumService;
import model.service.TeamService;
import model.stadium.StadiumDAO;
import model.team.TeamDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BaseballAPP {
    public static void main(String[] args) throws SQLException {

        Connection connection = DBConnection.getInstance();

        StadiumDAO stadiumDAO = new StadiumDAO(connection);
        TeamDAO teamDAO = new TeamDAO(connection);
        PlayerDAO playerDAO = new PlayerDAO(connection);
        OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);

        StadiumService stadiumService = new StadiumService(stadiumDAO); // 2
        TeamService teamService = new TeamService(teamDAO); // 4
        OutPlayerService outPlayerService = new OutPlayerService(outPlayerDAO, playerDAO);  // 8
        PlayerService playerService = new PlayerService(playerDAO);  // 9

        Scanner scanner = new Scanner(System.in);
        System.out.println("어떤 기능을 요청하시겠습니까?");
        System.out.println("--------------------------");
        System.out.println("1. 야구장등록 (야구장등록?name=야구장이름)");
        System.out.println("2. 야구장목록");
        System.out.println("3. 팀등록 (팀등록?stadiumId=팀번호&name=팀이름)");
        System.out.println("4. 팀목록");
        System.out.println("5. 선수등록 (선수등록?teamId=팀번호&name=선수이름&position=포지션)");
        System.out.println("6. 선수목록 (선수목록?teamId=1)");
        System.out.println("7. 선수 퇴출 등록 (퇴출등록?playerId=플레이어번호&reason=사유)");
        System.out.println("8. 퇴출목록");
        System.out.println("9. 포지션별목록");
        System.out.println("0. 종료");
        System.out.println("--------------------------");
        String input = scanner.nextLine();

        // 1 야구장 등록
        if (input.startsWith("야구장등록")) {
            String name = input.substring(input.indexOf('?') + 1);
            stadiumService.registerStadium(name);
        }

        // 2. 야구장 목록
        if (input.equals("야구장목록")) {
            stadiumService.stadiumList();
        }

        // 3. 팀 등록
        if (input.startsWith("팀등록")) {
            String paramsString = input.substring(input.indexOf('?') + 1);
            teamService.registerTeam(paramsString);
        }

        // 4. 팀 목록
        if (input.equals("팀목록")) {

            teamService.teamList();
        }

        // 5. 선수등록
        if (input.startsWith("선수등록")) {
            String paramsString = input.substring(input.indexOf('?') + 1);
            playerService.registerPlayer(paramsString);
        }

        // 6. 팀 별 선수목록    요청 : 선수목록?teamId=1
        if (input.startsWith("선수목록")) {
            String name = input.substring(input.indexOf('?') + 1);
            playerService.playerListByTeamId(name);
        }

        // 7. 선수 퇴출 등록
        if (input.startsWith("퇴출등록")) {
            String paramsString = input.substring(input.indexOf('?') + 1);
            outPlayerService.registerOutPlayer(paramsString);
        }

        // 8. 퇴출목록
        if (input.equals("퇴출목록")) {
            outPlayerService.outPlayerList();
        }
        // 9. 포지션별목록
        if (input.equals("포지션별목록")) {
            playerService.callpotisionlist();
        }


    }
}








//            StadiumService stadiumService = new StadiumService(stadiumDAO); // 1
//            TeamService teamService = new TeamService(teamDAO); // 3
//            PlayerService playerService = new PlayerService(playerDAO);  // 5
//            PlayerService playerService = new PlayerService(playerDAO); // 6
//            OutPlayerService outPlayerService = new OutPlayerService(outPlayerDAO, playerDAO); //7
